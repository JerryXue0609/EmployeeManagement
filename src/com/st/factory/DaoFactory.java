package com.st.factory;

import com.st.dao.AdminDao;
import com.st.dao.LoginLogDao;
import com.st.dao.impl.AdminDaoImpl;
import com.st.dao.impl.LoginLogDaoImpl;

public class DaoFactory {
	public static AdminDao getaAdminDaoInstance(){
		return new AdminDaoImpl();
	}

	public static LoginLogDao getLoginLogDaoInstance() {
		return new LoginLogDaoImpl();
	}

}
