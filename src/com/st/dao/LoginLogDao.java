package com.st.dao;

import java.sql.SQLException;

import com.st.domain.LoginLog;

public interface LoginLogDao {
	public int insertLoginLog(LoginLogDao loginLog)throws SQLException;

	int insertLoginLog(LoginLog loginLog) throws SQLException;

}
