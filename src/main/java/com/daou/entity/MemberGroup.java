package com.daou.entity;

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
public class MemberGroup {
	@Id
	@GeneratedValue
	private long id;

	@Column(name="description")
	private String description;

	@OneToMany(mappedBy="memberGroup")
	private List<Member> memberList = new ArrayList<Member>();
}
