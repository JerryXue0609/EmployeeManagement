package com.st.domain;

public class LoginLog {
	private int id;
	private String account;
	private String time;
	public LoginLog( String account, String time) {
		super();		
		this.account = account;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
