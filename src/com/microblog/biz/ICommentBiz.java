package com.microblog.biz;

import com.microblog.filter.PageBean;
import com.microblog.po.Comment;

public interface ICommentBiz {
	//��ҳ��ʾ������Ϣ
	public PageBean SelectByPageComment(int cid,int currentPage,int pageSize);
	//�������
	public boolean InsertComment(Comment comm);
	//ɾ������
	public boolean DeleteComment(int cid);
	//ģ������
	public PageBean FuzzyFindCommentByPage(int wid,String content,int currentPage,int pageSize);
}
