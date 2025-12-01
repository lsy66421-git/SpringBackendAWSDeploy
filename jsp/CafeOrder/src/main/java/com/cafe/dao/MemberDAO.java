package com.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cafe.dto.MemberDTO;
import com.cafe.util.DBManager;

public class MemberDAO {
    private MemberDAO() {}
    private static MemberDAO instance = new MemberDAO();
    public static MemberDAO getInstance() { return instance; }

    // Check user login (1: success, 0: wrong pwd, -1: no id)
    public int userCheck(String userid, String pwd) {
        int result = -1;
        String sql = "select pwd from CAFE_MEMBERS where userid=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
                    result = 1;
                } else {
                    result = 0;
                }
            } else {
                result = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return result;
    }

    // Get member by ID
    public MemberDTO getMember(String userid) {
        MemberDTO mDto = null;
        String sql = "select * from cafe_members where userid=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                mDto = new MemberDTO();
                mDto.setUserid(rs.getString("userid"));
                mDto.setPwd(rs.getString("pwd"));
                mDto.setName(rs.getString("name"));
                mDto.setEmail(rs.getString("email"));
                mDto.setPhone(rs.getString("phone"));
                mDto.setAdmin(rs.getInt("admin"));
                mDto.setJoinDate(rs.getTimestamp("join_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return mDto;
    }

    // Confirm ID availability (1: exists, -1: available)
    public int confirmID(String userid) {
        int result = -1;
        String sql = "select userid from cafe_members where userid=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = 1;
            } else {
                result = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return result;
    }

    // Insert new member
    public int insertMember(MemberDTO mDto) {
        int result = -1;
        String sql = "insert into cafe_members(userid, pwd, name, email, phone, admin) values(?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mDto.getUserid());
            pstmt.setString(2, mDto.getPwd());
            pstmt.setString(3, mDto.getName());
            pstmt.setString(4, mDto.getEmail());
            pstmt.setString(5, mDto.getPhone());
            pstmt.setInt(6, mDto.getAdmin());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return result;
    }
}
