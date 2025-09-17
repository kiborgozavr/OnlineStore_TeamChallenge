package io.teamchallenge.service.impl;

import io.teamchallenge.dto.cart.CartItemRequestDto;
import io.teamchallenge.dto.order.*;
import io.teamchallenge.dto.pageable.PageableDto;
import io.teamchallenge.dto.user.UserVO;
import io.teamchallenge.entity.*;
import io.teamchallenge.entity.orderitem.OrderItem;
import io.teamchallenge.entity.orderitem.OrderItemId;
import io.teamchallenge.enumerated.DeliveryStatus;
import io.teamchallenge.exception.ConflictException;
import io.teamchallenge.exception.ForbiddenException;
import io.teamchallenge.exception.NotFoundException;
import io.teamchallenge.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.teamchallenge.constant.ExceptionMessage.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new {@code Order} based on the given {@code OrderRequestDto} and the authenticated user.
     *
     * @param orderRequestDto the DTO containing the order request details.
     * @param userPrincipal   the authenticated user principal, or {@code null} if the order is placed by a guest.
     * @return the ID of the newly created order.
     * @throws ForbiddenException if the email or phone number already exists and is not associated with current user.
     * @throws NotFoundException  if the authenticated user cannot be found in the repository.
     */
    @Transactional
    public Long create(OrderRequestDto orderRequestDto, Principal userPrincipal) {
        Order order = createOrderSample(orderRequestDto);

        if (userPrincipal == null) {
            if (userRepository.existsByEmail(orderRequestDto.getEmail())) {
                throw new ForbiddenException(USER_WITH_EMAIL_ALREADY_EXISTS.formatted(orderRequestDto.getEmail()));
            }
            if (userRepository.existsByPhoneNumber(orderRequestDto.getPhoneNumber())) {
                throw new ForbiddenException(USER_WITH_PHONE_NUMBER_ALREADY_EXISTS.formatted(
                        orderRequestDto.getPhoneNumber()));
            }
        } else {
            String email = userPrincipal.getName();
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_BY_EMAIL.formatted(email)));
            if (userRepository.existsByEmail(orderRequestDto.getEmail())
                    && !orderRequestDto.getEmail().equals(user.getEmail())) {
                throw new ForbiddenException(USER_WITH_EMAIL_ALREADY_EXISTS.formatted(orderRequestDto.getEmail()));
            }
            if (userRepository.existsByPhoneNumber(orderRequestDto.getPhoneNumber())
                    && !orderRequestDto.getPhoneNumber().equals(user.getPhoneNumber())) {
                throw new ForbiddenException(USER_WITH_PHONE_NUMBER_ALREADY_EXISTS.formatted(
                        orderRequestDto.getPhoneNumber()));
            }
            cartItemRepository.deleteByUserId(user.getId());
            user.addOrder(order);
        }
        order.setAddress(modelMapper.map(orderRequestDto.getAddress(), Address.class));

        List<Product> products = productRepository.findAllById(orderRequestDto.getCartItems().stream()
                .map(CartItemRequestDto::getProductId)
                .collect(Collectors.toList()));

        Order savedOrder = orderRepository.save(order);
        orderRequestDto.getCartItems().stream().map(cartItem -> {
            Product product = getProductByCartItem(cartItem, products);
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            return buildOrderItem(cartItem, savedOrder, product);
        }).forEach(order::addOrderItem);
        return savedOrder.getId();
    }

    /**
     * Retrieves an {@link OrderResponseDto} by its unique identifier.
     *
     * @param orderId The unique identifier of the order.
     * @return An {@link OrderResponseDto} containing the details of the order.
     * @throws NotFoundException If no order is found with the provided ID.
     */
    public OrderResponseDto getById(Long orderId) {
        Order order = orderRepository.findByIdFetchData(orderId)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND_BY_ID.formatted(orderId)));
        UserVO user = userRepository.findVOByOrdersId(orderId).orElse(null);
        productRepository.findAllByIdWithImages(order.getOrderItems().stream()
                .map(orderItem -> orderItem.getProduct().getId()).toList());
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        orderResponseDto.setUser(user);
        return orderResponseDto;
    }

    /**
     * Changes an {@link Order} by its unique identifier.
     *
     * @param orderId The unique identifier of the order.
     * @throws NotFoundException If no order is found with the provided ID.
     */
    @Transactional
    public void changeOrderDetails(Long orderId, OrderUpdateRequestDto orderRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND_BY_ID.formatted(orderId)));
        order.setContactInfo(ContactInfo.builder()
                .email(orderRequestDto.getEmail())
                .phoneNumber(orderRequestDto.getPhoneNumber())
                .fullName(orderRequestDto.getFullName())
                .build());
        order.setAddress(modelMapper.map(orderRequestDto.getAddress(), Address.class));
        order.setDeliveryMethod(orderRequestDto.getDeliveryMethod());
        order.setDeliveryStatus(orderRequestDto.getDeliveryStatus());
        order.setComment(orderRequestDto.getComment());

        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(orderItem -> {
            Product product = productRepository.findById(orderItem.getProduct().getId()).orElseThrow();
            product.setQuantity(product.getQuantity() + orderItem.getQuantity());
        });

        order.removeAllItems();
        orderItemRepository.deleteAll(orderItems);

        orderRequestDto.getCartItems().stream()
                .map(n -> {
                    Product product = productRepository.findById(n.getProductId()).orElseThrow();
                    product.setQuantity(product.getQuantity() - n.getQuantity());
                    return buildOrderItem(n, order, product);
                }).forEach(order::addOrderItem);

        orderRepository.save(order);
    }

    /**
     * Updates the delivery status of an order.
     *
     * @param orderId The unique identifier of the order.
     * @param status  The new delivery status to be set for the order.
     * @throws NotFoundException If no order is found with the provided ID.
     */
    @Transactional
    public void setDeliveryStatus(Long orderId, DeliveryStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND_BY_ID.formatted(orderId)));
        order.setDeliveryStatus(status);
    }

    /**
     * Cancels an order if the user has the specified order.
     *
     * @param orderId The unique identifier of the order to be canceled.
     * @param userId  The unique identifier of the user requesting the cancellation.
     * @throws ForbiddenException If the user does not have an order with the specified ID.
     */
    @Transactional
    public void cancelOrder(Long orderId, Long userId) {
        if (userRepository.userHasOrderWithId(userId, orderId)) {
            setDeliveryStatus(orderId, DeliveryStatus.CANCEL);
        } else {
            throw new ForbiddenException(USER_HAS_NO_ORDERS_WITH_ID.formatted(orderId));
        }
    }

    /**
     * Retrieves a pageable list of orders filtered by the given parameters.
     *
     * @param filterParametersDto The filter parameters to apply to the query.
     * @param pageable            The pagination information.
     * @return A {@link PageableDto} containing a list of {@link ShortOrderResponseDto} and pagination details.
     */
    public PageableDto<ShortOrderResponseDto> getAllByFilter(OrderFilterDto filterParametersDto, Pageable pageable) {
        var orders = orderRepository
                .findAllByFilterParameters(filterParametersDto, pageable);
        var content = orders.getContent().stream()
                .map(order -> modelMapper.map(order, ShortOrderResponseDto.class))
                .collect(Collectors.toList());
        return PageableDto.<ShortOrderResponseDto>builder()
                .page(content)
                .totalElements(orders.getTotalElements())
                .currentPage(orders.getPageable().getPageNumber())
                .totalPages(orders.getTotalPages())
                .build();
    }

    private static OrderItem buildOrderItem(CartItemRequestDto cartItem, Order savedOrder, Product product) {
        return OrderItem.builder()
                .id(OrderItemId.builder()
                        .orderId(savedOrder.getId())
                        .productId(product.getId())
                        .build())
                .product(product)
                .price(product.getPrice())
                .quantity(cartItem.getQuantity())
                .order(savedOrder)
                .build();
    }

    private static Product getProductByCartItem(CartItemRequestDto cartItem, List<Product> products) {
        return products.stream()
                .filter(p -> p.getId().equals(cartItem.getProductId()) && cartItem.getQuantity() <= p.getQuantity())
                .findAny()
                .orElseThrow(() -> new ConflictException(PRODUCT_QUANTITY_CONFLICT.formatted(cartItem.getProductId())));
    }

    private Order createOrderSample(OrderRequestDto orderRequestDto) {
        return Order.builder()
                .contactInfo(ContactInfo.builder()
                        .email(orderRequestDto.getEmail())
                        .fullName(orderRequestDto.getFullName())
                        .phoneNumber(orderRequestDto.getPhoneNumber())
                        .build())
                .deliveryMethod(orderRequestDto.getDeliveryMethod())
                .deliveryStatus(DeliveryStatus.ORDER)
                .orderItems(new ArrayList<>())
                .isPaid(false)
                .comment(orderRequestDto.getComment())
                .build();
    }
}
