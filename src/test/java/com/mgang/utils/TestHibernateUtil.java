package com.mgang.utils;

import org.junit.Test;

import com.mgang.domain.MGTest;

public class TestHibernateUtil {
	@Test
	public void testHibernate(){
		MGTest t = new MGTest();
		t.setTid(1);
		t.setTkey("time");
		t.setTvalue("2014-10-7 10:30");
		HibernateUtil.save(t);
		
	}
}
