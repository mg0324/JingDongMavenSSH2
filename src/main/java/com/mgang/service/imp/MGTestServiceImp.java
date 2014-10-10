package com.mgang.service.imp;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mgang.domain.MGTest;
import com.mgang.service.inter.MGTestServiceInter;

@Service @Transactional
public class MGTestServiceImp implements MGTestServiceInter {
	@Resource
	private SessionFactory factory ;
	public void addMGTest(MGTest t){
		factory.getCurrentSession().save(t);
		System.out.println("加入");
	}
}
