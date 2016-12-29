package com.daou.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daou.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
