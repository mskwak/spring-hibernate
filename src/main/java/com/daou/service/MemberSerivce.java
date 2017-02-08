package com.daou.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.entity.Member;
import com.daou.repository.MemberRepository;
import com.daou.type.UserStatus;

@Service
public class MemberSerivce {
	@Autowired
	private MemberRepository memberRepository;

	@PostConstruct
	public void insert() {
		Member member = new Member();
		member.setCreatedTime(new Date());
		member.setEmailId("mskw@daou.co.kr");
		member.setUserName("곽면순");
		member.setUserStatus(UserStatus.ENABLE);

		this.memberRepository.save(member);
	}
}
