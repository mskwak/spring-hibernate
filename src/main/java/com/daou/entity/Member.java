package com.daou.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.daou.type.UserStatus;

@Entity
@Table(name="member")
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

	@OneToOne
	@JoinColumn(name="add_info_id")
	private MemberAdditionalInfomation memberAdditionalInfomation;

	// 이 테이블(Member)의 PK들(Many)이 하나의 FK(One)을 갖는다.
	@ManyToOne
	@JoinColumn(name="member_group_id")
	private MemberGroup memberGroup;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserStatus getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public MemberAdditionalInfomation getMemberAdditionalInfomation() {
		return this.memberAdditionalInfomation;
	}

	public void setMemberAdditionalInfomation(MemberAdditionalInfomation memberAdditionalInfomation) {
		this.memberAdditionalInfomation = memberAdditionalInfomation;
	}

	public MemberGroup getMemberGroup() {
		return this.memberGroup;
	}

	public void setMemberGroup(MemberGroup memberGroup) {
		this.memberGroup = memberGroup;
	}
}
