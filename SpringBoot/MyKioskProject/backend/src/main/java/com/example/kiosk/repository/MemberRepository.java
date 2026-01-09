package com.example.kiosk.repository;

import com.example.kiosk.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    @org.springframework.data.jpa.repository.Query("SELECT m FROM Member m WHERE m.isApproved = :isApproved OR (:isApproved = 0 AND m.isApproved IS NULL)")
    java.util.List<Member> findByIsApproved(
            @org.springframework.web.bind.annotation.RequestParam("isApproved") Integer isApproved);
}
