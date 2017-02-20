package com.microblog.dao;

import com.microblog.filter.PageBean;
import com.microblog.po.Comment;

public interface ICommentDao {
   //分页显示评论信息
	public PageBean FindByPageComment(int cid,String strSQL,int currentPage,int pageSize);
	//添加评论
	public int InsertComment(Comment comm);
	//删除评论
	public int DeleteComment(int cid);
	//模糊搜索
	public PageBean FuzzyFindCommentByPage(int wid,String content,String strSQL,int currentPage,int pageSize);
}
