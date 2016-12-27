package com.daou.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_additional_infomation")
public class MemberAdditionalInfomation {
	@Id
	@GeneratedValue
	private long id;

	@Column(name="member_id")
	private String memberId;
}