package com.itlaoqi.springcloud.member.repository;

import com.itlaoqi.springcloud.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    //select * from t_member where mobile = ...
    public List<Member> findByMobile(String mobile);
}
