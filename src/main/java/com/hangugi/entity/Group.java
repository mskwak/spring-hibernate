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
	// 이 테이블에서 Id는 1개(One)만 uniq하게 존재할 수 있지만, Member 테이블에서는 FK로서 여러개(Many) 존재할 수 있다.
	// 그룹 테이블에는 Member 와 관련한 필드가 없다. 가설: 아래 @OneToMany 존재의 이유는 그룹에 속한 멤버를 찾는데 활용이 되기 위함인가?
	@OneToMany(mappedBy="memberGroup")
	private List<Member> memberList = new ArrayList<Member>();
}

