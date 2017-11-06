package com.st.domain;

public class Student {
	private int id;
	private String account;
	private String password;
	private String name;
	private String department;
	public Student(String account, String password, String name,
			String department) {
		super();
		
		this.account = account;
		this.password = password;
		this.name = name;
		this.department = department;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

}
