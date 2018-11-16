package com.niit.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Notification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String blogPostTitle;
	private String email;
	private String approvalStatus; //[Approved or Rejected]
	private String rejectReason;
	private boolean viewed;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlogPostTitle() {
		return blogPostTitle;
	}
	public void setBlogPostTitle(String blogPostTitle) {
		this.blogPostTitle = blogPostTitle;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public boolean isViewed() {
		return viewed;
	}
	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

}
