package com.model;


public class Zhiwei {
	
	private Integer zhiweiId;
	private String zhiweiName;
	private String zhiweiMark;
	private Integer zhiweiNum;
	private Integer userId;
	
	
	
	public Zhiwei() {
		super();
	}
	

	public Zhiwei(String zhiweiName, String zhiweiMark,Integer zhiweiNum,Integer userId) {
		super();
		this.zhiweiName = zhiweiName;
		this.zhiweiMark = zhiweiMark;
		this.zhiweiNum = zhiweiNum;
		this.userId = userId;
	}
	
	public String getZhiweiName() {
		return zhiweiName;
	}


	public void setZhiweiName(String zhiweiName) {
		this.zhiweiName = zhiweiName;
	}


	public Integer getZhiweiNum() {
		return zhiweiNum;
	}


	public void setZhiweiNum(Integer zhiweiNum) {
		this.zhiweiNum = zhiweiNum;
	}


	public Integer getZhiweiId() {
		return zhiweiId;
	}


	public void setZhiweiId(Integer zhiweiId) {
		this.zhiweiId = zhiweiId;
	}


	public String getZhiweiMark() {
		return zhiweiMark;
	}


	public void setZhiweiMark(String zhiweiMark) {
		this.zhiweiMark = zhiweiMark;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
