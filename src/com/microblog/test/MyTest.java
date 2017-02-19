package com.microblog.test;

import org.junit.Test;

import com.microblog.dao.IRelationsDao;
import com.microblog.dao.impl.RelationsDaoImpl;

public class MyTest {
	IRelationsDao relatinDao = new RelationsDaoImpl();
	@Test
    public void test(){
		
		//System.out.println(relatinDao.FindRelationByuid(5,2));
		System.out.println(relatinDao.FindAllMyInterestByuid(2));
	}
}
