package com.hangugi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hangugi.type.UserStatus;

import lombok.Data;

@Entity
@Table(name="member")
@Data
public class Member {
	@Id
	@GeneratedValue
	private long id;

	@Column(name="email_id", nullable=false, length=1024)
	private String emailId;

	@Column(name="user_name")
	private String userName;

	@Enumerated(EnumType.STRING)
	@Column(name="user_status")
	private UserStatus userStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;

	// 이 테이블(Member)의 PK들(Many)이 하나의 FK(One)을 갖는다.
	@ManyToOne
	@JoinColumn(name="member_group_id")
	private Group memberGroup;
}
