package io.teamchallenge.service.impl;

import io.teamchallenge.dto.product.ShortProductResponseDto;
import io.teamchallenge.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteProductsService {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Transactional
    public Set<ShortProductResponseDto> getAll(String name) {
        return userService.getUser(name)
                .orElseThrow(() -> new ConflictException("User with email " + name + " not found"))
                .getWishlists().stream()
                .map(product -> modelMapper.map(product, ShortProductResponseDto.class))
                .collect(Collectors.toSet());
    }

    public void add(String name, Long productId) {
        userService.addProductToWishlist(name, productId);
    }

    public void remove(String name, Long productId) {
        userService.removeProductFromWishlist(name, productId);
    }
}
