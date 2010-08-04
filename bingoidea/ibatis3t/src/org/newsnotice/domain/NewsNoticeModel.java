package org.newsnotice.domain;

import java.util.Date;
import java.util.List;

public class NewsNoticeModel {
	private Long id;
	private String category;
	private String subject;
	private Date postedDate;
	private Date expiryDate;
	private String alert;
	private String emailAlert;
	private String audience;
	private String filter;
	private String filterValue;
	private String subFilterValue;
	private String excludeUserId;
	private String department;
	private String status;
	private String notes;
	private String defunctInd;
	private String approver;
	private NewsNoticeContentModel newsNoticeContent;
	private List<NewsNoticeMsgBoxModel> newsNoticeMsgBoxList; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getEmailAlert() {
		return emailAlert;
	}
	public void setEmailAlert(String emailAlert) {
		this.emailAlert = emailAlert;
	}
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	public String getSubFilterValue() {
		return subFilterValue;
	}
	public void setSubFilterValue(String subFilterValue) {
		this.subFilterValue = subFilterValue;
	}
	public String getExcludeUserId() {
		return excludeUserId;
	}
	public void setExcludeUserId(String excludeUserId) {
		this.excludeUserId = excludeUserId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDefunctInd() {
		return defunctInd;
	}
	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public NewsNoticeContentModel getNewsNoticeContent() {
		return newsNoticeContent;
	}
	public void setNewsNoticeContent(NewsNoticeContentModel newsNoticeContent) {
		this.newsNoticeContent = newsNoticeContent;
	}
	public List<NewsNoticeMsgBoxModel> getNewsNoticeMsgBoxList() {
		return newsNoticeMsgBoxList;
	}
	public void setNewsNoticeMsgBoxList(
			List<NewsNoticeMsgBoxModel> newsNoticeMsgBoxList) {
		this.newsNoticeMsgBoxList = newsNoticeMsgBoxList;
	}
	
}
