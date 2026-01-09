package com.example.kiosk.repository;

import com.example.kiosk.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByDisplayOrderAsc();

    // List<Category>
    // findAllByMemberOrderByDisplayOrderAsc(com.example.kiosk.entity.Member
    // member); // Removed for CSV logic
}
