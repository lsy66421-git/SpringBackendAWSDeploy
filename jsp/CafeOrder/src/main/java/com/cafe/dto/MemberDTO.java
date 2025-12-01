package com.cafe.dto;

import java.sql.Timestamp;

public class MemberDTO {
    private String userid;
    private String pwd;
    private String name;
    private String email;
    private String phone;
    private int admin;
    private Timestamp joinDate;

    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }
    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getAdmin() { return admin; }
    public void setAdmin(int admin) { this.admin = admin; }
    public Timestamp getJoinDate() { return joinDate; }
    public void setJoinDate(Timestamp joinDate) { this.joinDate = joinDate; }
}
