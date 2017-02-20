package com.microblog.dao;


import java.util.List;

import com.microblog.filter.PageBean;
import com.microblog.po.Users;

public interface IUserDao {
    //用户登录验证
	public Users UserLoginCheck(String usn,String pwd);
    //登录者所要关注人的信息
	public List<Users> FindByInterest(int uid );
	//监听器显示首页用户头像信息
	public List<Users> FindByListener();
	//分页显示 登陆者所要已经关注人的信息
	public PageBean FindByOverInterest(int uid,String strSQL,int currentPage,int pageSize);
	//分页显示登陆者粉丝信息
	public PageBean FindFansByPage(int uid,String strSQL,int currentPage,int pageSize);
	//显示用户个人详细信息
	public Users FindByuid(int uid);
	//注册用户
	public int RegisterUser(Users use);
	// 显示刚注册用户个人详细详细
	public Users FindByObject(String uname,String upwd,String sex);
	//更新用户信息
	public int UpdateUser(Users use);
	//find password
	public Users FindByMail(String uname,String uques);
}
