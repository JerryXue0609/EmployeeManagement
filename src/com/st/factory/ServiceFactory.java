package com.st.factory;

import com.st.service.AdminService;
import com.st.service.impl.AdminDaoImpl;
public class ServiceFactory {
	public static AdminDaoImpl getAdminServiceInstance(){
		return new AdminDaoImpl();
	}
}