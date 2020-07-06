package com.model;

/**
 * Huiyishi entity. @author MyEclipse Persistence Tools
 */

public class Dati {

	// Fields

	private Integer datiId;
	private Integer shitiId;
	private Integer userId;
	private String userDaan;

	// Constructors

	/** default constructor */
	public Dati() {
		super();
	}

	/** full constructor */
	public Dati( Integer shitiId,Integer userId,String userDaan) {
		super();
		this.shitiId = shitiId;
		this.userId = userId;
		this.userDaan = userDaan;
	}

	public Integer getDatiId() {
		return datiId;
	}

	public void setDatiId(Integer datiId) {
		this.datiId = datiId;
	}
	
	

	public String getUserDaan() {
		return userDaan;
	}

	public void setUserDaan(String userDaan) {
		this.userDaan = userDaan;
	}

	public Integer getShitiId() {
		return shitiId;
	}

	public void setShitiId(Integer shitiId) {
		this.shitiId = shitiId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}