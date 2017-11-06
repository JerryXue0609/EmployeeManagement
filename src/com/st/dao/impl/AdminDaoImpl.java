package com.st.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.st.dao.AdminDao;
import com.st.domain.Admin;
import com.st.utils.JDBCUTilSingle;

public class AdminDaoImpl implements AdminDao {

	public Admin findAdminAccount(String account) throws SQLException {
		Admin admin = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_admin WHERE account = ? ";
		conn = JDBCUTilSingle.getJdbcuTilSingle().getConnection();// 获取连接
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				admin = new Admin(sql, sql, sql);
				admin.setId(rs.getInt("id"));
				admin.setAccount(rs.getString("account"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
			}

		} catch (SQLException e) {
			System.out.println("根据账号查找管理员操作出现异常");
		} finally {
			JDBCUTilSingle.getJdbcuTilSingle().closeConnection(rs, pstmt, conn);
		}
		return admin;
	}

	@Override
	public Admin findAdminByAccount(String account) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
