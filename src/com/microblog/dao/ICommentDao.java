package com.microblog.dao;

import com.microblog.filter.PageBean;
import com.microblog.po.Comment;

public interface ICommentDao {
   //��ҳ��ʾ������Ϣ
	public PageBean FindByPageComment(int cid,String strSQL,int currentPage,int pageSize);
	//�������
	public int InsertComment(Comment comm);
	//ɾ������
	public int DeleteComment(int cid);
	//ģ������
	public PageBean FuzzyFindCommentByPage(int wid,String content,String strSQL,int currentPage,int pageSize);
}
