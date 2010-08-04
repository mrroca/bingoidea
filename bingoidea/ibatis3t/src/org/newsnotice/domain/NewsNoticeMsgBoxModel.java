package org.newsnotice.domain;

import java.util.Date;

public class NewsNoticeMsgBoxModel {
	private long id;
	private String userId;
	private long nnId;
	private String folder;
	private String read;
	private Date readOn;
	private String defunctInd;
	private String piNo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getNnId() {
		return nnId;
	}
	public void setNnId(long nnId) {
		this.nnId = nnId;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public Date getReadOn() {
		return readOn;
	}
	public void setReadOn(Date readOn) {
		this.readOn = readOn;
	}
	public String getDefunctInd() {
		return defunctInd;
	}
	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}
	public String getPiNo() {
		return piNo;
	}
	public void setPiNo(String piNo) {
		this.piNo = piNo;
	}
	
}
