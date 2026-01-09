package com.example.kiosk.repository;

import com.example.kiosk.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCategoryId(Long categoryId);

    java.util.Optional<Menu> findByImageUrl(String imageUrl);

    // List<Menu> findByMember(com.example.kiosk.entity.Member member); // Removed
    // for CSV logic
}
