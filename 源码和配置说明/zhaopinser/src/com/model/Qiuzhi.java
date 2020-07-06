package com.model;

/**
 * Huiyishi entity. @author MyEclipse Persistence Tools
 */

public class Qiuzhi {

	// Fields

	private Integer qiuzhiId;
	private Integer zhiweiId;
	private Integer userId;
	private String qiuzhiMark;
	private Integer qiuzhiType;

	// Constructors

	/** default constructor */
	public Qiuzhi() {
		super();
	}

	/** full constructor */
	public Qiuzhi( Integer zhiweiId,Integer userId,String qiuzhiMark,Integer qiuzhiType) {
		super();
		this.zhiweiId = zhiweiId;
		this.userId = userId;
		this.qiuzhiMark = qiuzhiMark;
		this.qiuzhiType = qiuzhiType;
	}

	public Integer getQiuzhiId() {
		return qiuzhiId;
	}

	public void setQiuzhiId(Integer qiuzhiId) {
		this.qiuzhiId = qiuzhiId;
	}
	
	

	public String getQiuzhiMark() {
		return qiuzhiMark;
	}

	public void setQiuzhiMark(String qiuzhiMark) {
		this.qiuzhiMark = qiuzhiMark;
	}

	public Integer getQiuzhiType() {
		return qiuzhiType;
	}

	public void setQiuzhiType(Integer qiuzhiType) {
		this.qiuzhiType = qiuzhiType;
	}

	public Integer getZhiweiId() {
		return zhiweiId;
	}

	public void setZhiweiId(Integer zhiweiId) {
		this.zhiweiId = zhiweiId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}