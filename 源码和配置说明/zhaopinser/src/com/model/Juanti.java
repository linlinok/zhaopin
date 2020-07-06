package com.model;

/**
 * Huiyishi entity. @author MyEclipse Persistence Tools
 */

public class Juanti {

	// Fields

	private Integer juantiId;
	private Integer shijuanId;
	private Integer shitiId;

	// Constructors

	/** default constructor */
	public Juanti() {
		super();
	}

	/** full constructor */
	public Juanti( Integer shijuanId,Integer shitiId) {
		super();
		this.shijuanId = shijuanId;
		this.shitiId = shitiId;
	}

	public Integer getJuantiId() {
		return juantiId;
	}

	public void setJuantiId(Integer juantiId) {
		this.juantiId = juantiId;
	}
	
	

	public Integer getShijuanId() {
		return shijuanId;
	}

	public void setShijuanId(Integer shijuanId) {
		this.shijuanId = shijuanId;
	}

	public Integer getShitiId() {
		return shitiId;
	}

	public void setShitiId(Integer shitiId) {
		this.shitiId = shitiId;
	}
}