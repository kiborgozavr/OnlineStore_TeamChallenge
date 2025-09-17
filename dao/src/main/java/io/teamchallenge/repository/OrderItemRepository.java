package io.teamchallenge.repository;

import io.teamchallenge.entity.orderitem.OrderItem;
import io.teamchallenge.entity.orderitem.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    // You can add custom queries here if needed
}
