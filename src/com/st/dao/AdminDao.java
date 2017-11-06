package com.st.dao;

import java.sql.SQLException;

import com.st.domain.Admin;

public interface AdminDao {
	//根据帐号查找管理员
	public Admin findAdminByAccount(String account)throws SQLException;
}
