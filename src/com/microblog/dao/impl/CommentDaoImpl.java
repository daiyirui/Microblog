package com.microblog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dao.ICommentDao;
import com.microblog.dbutil.DBConn;
import com.microblog.filter.PageBean;
import com.microblog.po.Comment;
import com.microblog.po.Users;
import com.microblog.po.Weibo;
public class CommentDaoImpl implements ICommentDao {

	@Override
	public PageBean FindByPageComment(int cid, String strSQL) {
		// TODO Auto-generated method stub
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
