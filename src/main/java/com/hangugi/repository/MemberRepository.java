package com.hangugi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hangugi.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
