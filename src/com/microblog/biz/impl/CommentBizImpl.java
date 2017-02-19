package com.microblog.biz.impl;

import com.microblog.biz.ICommentBiz;
import com.microblog.dao.ICommentDao;
import com.microblog.dao.impl.CommentDaoImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Comment;

public class CommentBizImpl implements ICommentBiz {
    ICommentDao commentDao=null;
    public CommentBizImpl(){
    	commentDao=new CommentDaoImpl();
    }
	@Override
	public PageBean SelectByPageComment(int cid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return commentDao.FindByPageComment(cid,"SELECT * FROM comment where c_wid=? order by cdate desc", currentPage, pageSize);
	}
	@Override
	public boolean InsertComment(Comment comm) {
		// TODO Auto-generated method stub
		int a=commentDao.InsertComment(comm);
		if(a==1){
			return true;
		}else{
			return false;	
		}		
	}
	@Override
	public boolean DeleteComment(int cid) {
		// TODO Auto-generated method stub
		if(commentDao.DeleteComment(cid)==1){
			return true;
		}else{
		    return false;
		}
	}
	@Override
	public PageBean FuzzyFindCommentByPage(int wid, String content,
			int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return commentDao.FuzzyFindCommentByPage(wid, content,"SELECT * FROM comment where c_wid=? and ccontent like ? order by cdate desc", currentPage, pageSize);
	}

}
