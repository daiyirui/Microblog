package com.microblog.biz.impl;

import com.microblog.biz.ICollectionBiz;
import com.microblog.dao.ICollectionDao;
import com.microblog.dao.impl.CollectionDaoImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Collection;

public class CollectionBizImpl implements ICollectionBiz {
    ICollectionDao collectionDao;
    public CollectionBizImpl(){
    	collectionDao=new CollectionDaoImpl();
    }
	@Override
	public boolean InsertCollection(int uid, Collection coll) {
		// TODO Auto-generated method stub
		int a=collectionDao.InsertCollection(uid, coll);
		if(a==1){
			return true;
		}else{
		   return false;
		}
	}
	@Override
	public PageBean SelectCollectionByPage(int uid, int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		return collectionDao.FindCollectionByPage(uid, "select * from collection where l_uid=? order by ldate desc", currentPage, pageSize);
	}
	@Override
	public boolean DeleteCollection(int lid) {
		// TODO Auto-generated method stub
		int a=collectionDao.DeleteCollection(lid);
		if(a==1){
			return true;
		}else{
		    return false;
		}
	}
	@Override
	public int CountCollectionByLid(int lid) {
		// TODO Auto-generated method stub
		return collectionDao.CountCollectionByLid(lid);
	}
	@Override
	public PageBean FuzzyFindCollectionByuid(int uid, String content,
			int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return collectionDao.FuzzyFindCollectionByuid(uid, content,"SELECT * FROM collection where lcontent like ? and l_uid=? order by ldate desc", currentPage, pageSize);
	}
	@Override
	public int CountCollectionByUid(int uid) {
		
		return collectionDao.CountCollectionByUid(uid);
	}
}
