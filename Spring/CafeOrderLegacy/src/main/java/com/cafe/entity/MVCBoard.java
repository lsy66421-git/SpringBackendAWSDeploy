package com.cafe.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class MVCBoard {
    private Long idx;
    private String name;
    private String title;
    private String content;
    private Date postdate; // Handled by Database Default or Mapper
    private String ofile;
    private String sfile;
    private Integer downcount;
    private String pass;
    private Integer visitcount;
}
