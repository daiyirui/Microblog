package com.microblog.biz;

import java.util.List;

import com.microblog.filter.PageBean;
import com.microblog.po.Users;

public interface IUsersBiz {
	//用户登录
    public Users UserLogin(String usn,String pwd);
    //登录者所要关注人的信息
	public List<Users> SelectByInterest(int uid );
	//监听器显示首页用户头像信息
	public List<Users> SelectByListener();
	//分页显示 登陆者所要已经关注人的信息
	public PageBean SelectByOverInterest(int uid,int currentPage,int pageSize);
	//分页显示登陆者粉丝信息
	public PageBean SelectFansByPage(int uid,int currentPage,int pageSize);
	//显示用户个人详细信息
	public Users SelectByuid(int uid);
	//注册用户
	public boolean RegisterUser(Users use);
	// 显示刚注册用户个人详细详细
	public Users SelectByObject(String uname,String upwd,String sex);
	//更新用户信息
	public boolean UpdateUser(Users use);
	//find password
	public Users SelectByMail(String uname,String uques);
}
