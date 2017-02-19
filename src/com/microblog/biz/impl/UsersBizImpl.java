package com.microblog.biz.impl;

import java.util.List;

import com.microblog.biz.IUsersBiz;
import com.microblog.dao.IUserDao;
import com.microblog.dao.impl.UserDaoImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

public class UsersBizImpl implements IUsersBiz {
	IUserDao userDao=null;
	public UsersBizImpl(){
		userDao=new UserDaoImpl();
	}
	@Override
	public Users UserLogin(String usn, String pwd) {
		return userDao.UserLoginCheck(usn, pwd);
	}

	@Override
	public List<Users> SelectByInterest(int uid) {
		return userDao.FindByInterest(uid);
	}

	@Override
	public List<Users> SelectByListener() {
		// TODO Auto-generated method stub
		return userDao.FindByListener();
	}   
	@Override
	public PageBean SelectByOverInterest(int uid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.FindByOverInterest(uid, "SELECT * FROM users where uremarks!='no' and uid!=? and uid in (select g_id from relations where r_id=?) order by udate desc", currentPage, pageSize);
		
	}
	@Override
	public PageBean SelectFansByPage(int uid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.FindFansByPage(uid,"SELECT * FROM users where uremarks!='no' and uid in (select r_id from relations where g_id=? and r_id!=?) order by udate desc", currentPage, pageSize);
	}
	@Override
	public Users SelectByuid(int uid) {
		// TODO Auto-generated method stub
		return userDao.FindByuid(uid);
	}
	@Override
	public boolean RegisterUser(Users use) {
		// TODO Auto-generated method stub
		int a=userDao.RegisterUser(use);
		if(a==1){
			return true;
		}else{
		   return false;
		}
	}
	@Override
	public Users SelectByObject(String uname, String upwd, String sex) {
		// TODO Auto-generated method stub
		return userDao.FindByObject(uname, upwd, sex);
	}
	@Override
	public boolean UpdateUser(Users use) {
		// TODO Auto-generated method stub
		int a=userDao.UpdateUser(use);
		if(a==1){
			return true;
		}else{
		return false;
		}
	}
	@Override
	public Users SelectByMail(String uname,String uques) {
		// TODO Auto-generated method stub
		return userDao.FindByMail(uname,uques);
	}	 
}
