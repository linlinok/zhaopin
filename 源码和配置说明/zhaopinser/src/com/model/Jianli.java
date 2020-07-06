package com.model;


public class Jianli {
	
	private Integer jianliId;
	private String xuexiao;
	private String zhuanye;
	private String jineng;
	private String xiangxi;
	private Integer userId;
	
	
	
	public Jianli() {
		super();
	}
	

	public Jianli(String xuexiao, String zhuanye,String jineng, String xiangxi,Integer userId) {
		super();
		this.xuexiao = xuexiao;
		this.zhuanye = zhuanye;
		this.jineng = jineng;
		this.xiangxi = xiangxi;
		this.userId = userId;
	}


	public Integer getJianliId() {
		return jianliId;
	}


	public void setJianliId(Integer jianliId) {
		this.jianliId = jianliId;
	}


	public String getXuexiao() {
		return xuexiao;
	}


	public void setXuexiao(String xuexiao) {
		this.xuexiao = xuexiao;
	}


	public String getZhuanye() {
		return zhuanye;
	}


	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}


	public String getJineng() {
		return jineng;
	}


	public void setJineng(String jineng) {
		this.jineng = jineng;
	}


	public String getXiangxi() {
		return xiangxi;
	}


	public void setXiangxi(String xiangxi) {
		this.xiangxi = xiangxi;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
