package com.microblog.biz.impl;

import java.util.List;

import com.microblog.biz.IWeiboBiz;
import com.microblog.dao.IWeiboDao;
import com.microblog.dao.impl.WeiboDaoImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Weibo;

public class WeiboBizImpl implements IWeiboBiz {
	IWeiboDao weiboDao = null;

	public WeiboBizImpl() {
		weiboDao = new WeiboDaoImpl();
	}

	@Override
	public List<Weibo> SelectByLogin(int uid) {
		return weiboDao.FindByLogin(uid);
	}

	@Override
	public int CountByMicroblog(int uid) {
		return weiboDao.CountByMicroblog(uid);
	}

	@Override
	public PageBean SelectByPage(int uid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return weiboDao.FindByPage(uid,
				"SELECT * FROM weibo  where wremarks!='no' and w_uid = any (select g_id from relations where r_id=?) or w_uid=? order by wid desc",
				currentPage, pageSize);
	}

	@Override
	public PageBean SelectByPageOnlyOwn(int uid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return weiboDao.FindByPageOnlyOwn(uid,
				"SELECT * FROM weibo  where wremarks!='no' and w_uid=? order by wid desc",
				currentPage, pageSize);
	}
	
	@Override
	public boolean ForWardMicroblog(int uid, String wcontent, int wid, String wimage) {
		// TODO Auto-generated method stub
		int flag = weiboDao.ForWardMicroblog(uid, wcontent, wid, wimage);
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Weibo SelectBywid(int wid) {
		// TODO Auto-generated method stub
		return weiboDao.FindBywid(wid);
	}

	@Override
	public boolean InsertWeibo(Weibo weibo, int uid) {
		// TODO Auto-generated method stub
		int a = weiboDao.InsertWeibo(weibo, uid);
		if (a == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PageBean FuzzyFindWeiboByPage(int uid, String content, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return weiboDao.FuzzyFindWeiboByPage(uid, content,
				"select * from weibo where wremarks!='no' and wcontent like ? and w_uid in (SELECT w_uid FROM weibo where w_uid = any (select g_id from relations where r_id=?) or w_uid=?) order by wdate desc",
				currentPage, pageSize);
	}

	@Override
	public PageBean SelectWeiboByuid(int uid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return weiboDao.FindWeiboByuid(uid, "SELECT * FROM weibo where wremarks!='no' and w_uid =? order by wdate desc",
				currentPage, pageSize);
	}

	@Override
	public boolean DeleteWeibo(int wid, int w_uid) {
		int a = weiboDao.DeleteWeibo(wid, w_uid);
		if(a==1) {
		     return true;
		} else {
			return false;
		} 
	}
}
