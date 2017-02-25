package com.microblog.dao;

import java.util.List;

import com.microblog.filter.PageBean;
import com.microblog.po.Weibo;

public interface IWeiboDao { 
	//获取登录者和其所关注人的微博信息
	public List<Weibo> FindByLogin(int uid);
	//显示微博数量
	public int CountByMicroblog(int uid);
	//添加微博信息
	public int InsertWeibo(Weibo weibo,int uid);
	//显示个人微博信息
	public List<Weibo> FindWeiboByuid(int uid);
	//删除自己发的微博
	public int DeleteWeibo(int wid,int uid);
}
