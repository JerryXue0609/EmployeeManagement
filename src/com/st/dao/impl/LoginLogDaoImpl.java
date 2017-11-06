package com.st.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.st.dao.LoginLogDao;
import com.st.domain.LoginLog;
import com.st.utils.JDBCUTilSingle;

public class LoginLogDaoImpl implements LoginLogDao{
	@Override
	public int insertLoginLog(LoginLog loginLog)throws SQLException{
		int n = 0;
		Connection conn= null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO t_loginlog VALUES(null,?,?) ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,loginLog.getAccount());
			pstmt.setString(2,loginLog.getTime());
			n = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			JDBCUTilSingle.getJdbcuTilSingle().closeConnection(null, pstmt, conn);
		}
		return n;
		
	}

	@Override
	public int insertLoginLog(LoginLogDao loginLog) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
