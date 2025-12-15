package com.cafe.mapper;

import com.cafe.entity.MVCBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MVCBoardMapper {
    // List with paging and search
    List<MVCBoard> selectList(@Param("start") int start, @Param("size") int size,
            @Param("searchField") String searchField, @Param("searchWord") String searchWord);

    // Total count for paging
    int selectCount(@Param("searchField") String searchField, @Param("searchWord") String searchWord);

    MVCBoard selectById(Long idx);

    void insert(MVCBoard board);

    void update(MVCBoard board);

    void delete(Long idx);

    void updateVisitCount(Long idx);
}
