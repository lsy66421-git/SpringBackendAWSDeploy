package com.cafe.repository;

import com.cafe.entity.MVCBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MVCBoardRepository extends JpaRepository<MVCBoard, Long> {
    Page<MVCBoard> findByTitleContaining(String keyword, Pageable pageable);

    Page<MVCBoard> findByContentContaining(String keyword, Pageable pageable);
}
