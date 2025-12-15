package com.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "CAFE_MEMBERS")
public class Member {

    @Id
    @Column(length = 20)
    private String userid;

    @Column(nullable = false, length = 20)
    private String pwd;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer admin = 0;

    @CreationTimestamp
    @Column(name = "join_date", updatable = false)
    private Timestamp joinDate;
}
