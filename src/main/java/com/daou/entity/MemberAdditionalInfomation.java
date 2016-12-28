package com.daou.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="member_additional_infomation")
public class MemberAdditionalInfomation {
	@Id
	@GeneratedValue
	private long id;

	@Column(name="home_address")
	private String homeAddress;
	
	@OneToOne
	@JoinColumn(name="member_id")
	private Member member;
}
