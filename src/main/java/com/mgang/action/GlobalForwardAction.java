package com.mgang.action;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.stereotype.Controller;

import com.mgang.domain.MGTest;
import com.mgang.service.inter.MGTestServiceInter;


@Controller
public class GlobalForwardAction {
	@Resource
	private MGTestServiceInter tService;
	
	public String toDraggle(){
		
		return "draggle";
	}
	
	public String testHibernate(){
		MGTest t = new MGTest();
		t.setTkey("time"+new Random().toString());
		t.setTvalue(new Date().toLocaleString());
		tService.addMGTest(t);
		System.out.println("经过testHibernate action");
		return "testHibernate";
	}
}
