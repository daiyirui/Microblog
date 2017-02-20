package com.microblog.biz;

import java.util.List;

import com.microblog.filter.PageBean;
import com.microblog.po.Weibo;

public interface IWeiboBiz {
	//获取登录者和其所关注人的微博信息
	public List<Weibo> SelectByLogin(int uid);
	//显示微博数量
	public int CountByMicroblog(int uid);
	//分页显示登录者和其所关注人的微博信息  sql语句   当前第几页       每页显示多少行
	public PageBean SelectByPage(int uid,int currentPage,int pageSize);
	//分页显示登录者微博信息  sql语句   当前第几页       每页显示多少行
	public PageBean SelectByPageOnlyOwn(int uid, int currentPage, int pageSize);
	//转发微博
	public boolean ForWardMicroblog(int uid,String wcontent,int wid,String wimage);
	//显示单个微博信息对象
	public Weibo SelectBywid(int wid);
	//添加微博信息
	public boolean InsertWeibo(Weibo weibo,int uid);
	//模糊搜寻微博信息
	public PageBean FuzzyFindWeiboByPage(int uid,String content,int currentPage,int pageSize);
	//分页显示个人微博信息
	public PageBean SelectWeiboByuid(int uid,int currentPage,int pageSize);
}
