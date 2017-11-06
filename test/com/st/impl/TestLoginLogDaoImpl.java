package com.st.impl;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.st.domain.LoginLog;
import com.st.factory.DaoFactory;
import com.st.utils.DateTimeUtils;

public class TestLoginLogDaoImpl {

	@Test
	public void testInsertLoginLog() {
		int n = 0;
		LoginLog loginLog = new LoginLog("soft1311", DateTimeUtils.getCurrentDateTime());
		try {
			n = DaoFactory.getLogDaoInstance().
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
