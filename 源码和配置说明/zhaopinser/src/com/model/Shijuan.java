package com.model;


public class Shijuan {
	
	private Integer shijuanId;
	private String shijuanName;
	private String shijuanMark;
	private Integer zhiweiId;
	
	
	
	public Shijuan() {
		super();
	}
	

	public Shijuan(String shijuanName, String shijuanMark,Integer zhiweiId) {
		super();
		this.shijuanName = shijuanName;
		this.shijuanMark = shijuanMark;
		this.zhiweiId = zhiweiId;
	}
	
	public String getShijuanName() {
		return shijuanName;
	}


	public void setShijuanName(String shijuanName) {
		this.shijuanName = shijuanName;
	}




	public Integer getShijuanId() {
		return shijuanId;
	}


	public void setShijuanId(Integer shijuanId) {
		this.shijuanId = shijuanId;
	}


	public String getShijuanMark() {
		return shijuanMark;
	}


	public void setShijuanMark(String shijuanMark) {
		this.shijuanMark = shijuanMark;
	}


	public Integer getZhiweiId() {
		return zhiweiId;
	}


	public void setZhiweiId(Integer zhiweiId) {
		this.zhiweiId = zhiweiId;
	}
}
