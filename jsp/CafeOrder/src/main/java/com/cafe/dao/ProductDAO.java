package com.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cafe.dto.ProductDTO;
import com.cafe.util.DBManager;

import common.DBConnPool;

public class ProductDAO extends DBConnPool{
    private ProductDAO() {}
    private static ProductDAO instance = new ProductDAO();
    public static ProductDAO getInstance() { return instance; }

    // Get all products
    public List<ProductDTO> selectAllProducts() {
        String sql = "select * from CAFE_PRODUCTS order by code desc";
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductDTO pDto = new ProductDTO();
                pDto.setCode(rs.getInt("code"));
                pDto.setName(rs.getString("name"));
                pDto.setPrice(rs.getInt("price"));
                pDto.setPicture(rs.getString("picture"));
                pDto.setDescription(rs.getString("description"));
                list.add(pDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }
}
