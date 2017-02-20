package com.microblog.biz;

import com.microblog.filter.PageBean;
import com.microblog.po.Comment;

public interface ICommentBiz {
	//分页显示评论信息
	public PageBean SelectByPageComment(int cid,int currentPage,int pageSize);
	//添加评论
	public boolean InsertComment(Comment comm);
	//删除评论
	public boolean DeleteComment(int cid);
	//模糊搜索
	public PageBean FuzzyFindCommentByPage(int wid,String content,int currentPage,int pageSize);
}
