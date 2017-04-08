package com.microblog.dao;


import java.util.List;

import com.microblog.po.Users;

public interface IUserDao {
    //用户登录验证
	public Users UserLoginCheck(String usn,String pwd);
    //登录者所要关注人的信息
	public List<Users> FindByInterest(int uid );
	//监听器显示首页用户头像信息
	public List<Users> FindByListener();
	//显示用户个人详细信息
	public Users FindByuid(int uid);
	//注册用户
	public int RegisterUser(Users use);
	//find password
	public Users FindByMail(String uemail);
	//获取所有的用户邮箱
	public List<String> AllEmails();
	//改变用户的头像
	public int changeFace(Users user);
	//修改密码功能实现
	public int changePassword(Users user);
	//修改个人信息功能实现
	public int changeUserInfo(Users user);
	
}
