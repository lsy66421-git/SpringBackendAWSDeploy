package com.example.kiosk.repository;

import com.example.kiosk.entity.Menu;
import com.example.kiosk.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByMenu(Menu menu);
}
