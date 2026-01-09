package com.example.kiosk.repository;

import com.example.kiosk.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByMemberId(Long memberId);
}
