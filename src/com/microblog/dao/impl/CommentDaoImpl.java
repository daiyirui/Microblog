package com.microblog.dao.impl;


import com.microblog.dao.ICommentDao;
import com.microblog.filter.PageBean;
import com.microblog.po.Comment;
public class CommentDaoImpl implements ICommentDao {

	@Override
	public PageBean FindByPageComment(int cid, String strSQL) {
		return null;
	}

	@Override
	public int InsertComment(Comment comm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DeleteComment(int cid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageBean FuzzyFindCommentByPage(int wid, String content,
			String strSQL) {
		// TODO Auto-generated method stub
		return null;
	}
  
}
