package com.cafe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "CAFE_MVCBOARD")
public class MVCBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming Auto Increment for simplicity in new DB
    private Long idx;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 2000)
    private String content;

    @CreationTimestamp
    @Column(name = "postdate", updatable = false)
    private Date postdate;

    @Column(length = 200)
    private String ofile;

    @Column(length = 30)
    private String sfile;

    @ColumnDefault("0")
    private Integer downcount = 0;

    @Column(nullable = false, length = 50)
    private String pass;

    @ColumnDefault("0")
    private Integer visitcount = 0;
}
