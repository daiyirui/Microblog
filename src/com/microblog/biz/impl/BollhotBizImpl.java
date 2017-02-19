package com.microblog.biz.impl;

import java.util.List;

import com.microblog.biz.IBollhotBiz;
import com.microblog.dao.IBollhotDao;
import com.microblog.dao.impl.BollhotDaoImpl;
import com.microblog.po.Bloghot;

public class BollhotBizImpl implements IBollhotBiz {
    IBollhotDao bollDao=null;
    public BollhotBizImpl(){
    	bollDao=new BollhotDaoImpl();
    }
	@Override
	public List<Bloghot> SelectByHot() {
		// TODO Auto-generated method stub
		return bollDao.FindByHot();
	}

	@Override
	public boolean VoitHot(String hot) {
		// TODO Auto-generated method stub
		int a =bollDao.VoitHot(hot);
		if(a==1){
			return true;
		}else{
		return false;
		}
	}  
}
