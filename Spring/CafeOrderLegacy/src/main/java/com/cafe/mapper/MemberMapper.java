package com.cafe.mapper;

import com.cafe.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member findByUserid(String userid);

    void insert(Member member);

    int checkUserid(String userid);
}
