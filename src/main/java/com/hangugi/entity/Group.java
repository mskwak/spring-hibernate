package com.hangugi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="member_group") // 테이블 이름을 group로 쓰면 안된다.
public class Group {
	@Id
	@GeneratedValue
	private long id;

	@Column(name="description")
	private String description;

	// 이 테이블(MemberGroup) 에서는 PK가 다른 테이블(Member)에서 FK로 사용된다. PK -> FK (One -> Many)
	@OneToMany(mappedBy="memberGroup")
	private List<Member> memberList = new ArrayList<Member>();
}

