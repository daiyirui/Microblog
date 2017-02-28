package com.microblog.dao;

import java.util.List;

import com.microblog.po.Weibo;

public interface IWeiboDao { 
	//通过wid查找微博，uid仅仅是用来判断我收藏了这条微博没有
	public Weibo FindBywid(int uid,int wid);
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
	//转发微博,返回转发后的微博
	public int forwardWeibo(Weibo weibo,int uid);
	//通过w_wid获取原创微博
	public Weibo FindByw_wid(int uid,int w_wid);
	//通过wid获取转发这条原创微博的所有微博
	public List<Weibo> FindWeibosByw_wid(int uid,int wid);
	//更新相关微博的转发次数
	public int updateWtimes(Weibo weibo);
	
}
