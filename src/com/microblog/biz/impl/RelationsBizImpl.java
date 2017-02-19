package com.microblog.biz.impl;

import java.util.List;

import com.microblog.biz.IRelationsBiz;
import com.microblog.dao.IRelationsDao;
import com.microblog.dao.impl.RelationsDaoImpl;
import com.microblog.po.Users;

public class RelationsBizImpl implements IRelationsBiz {
	IRelationsDao relationDao=null;
	public RelationsBizImpl(){
		relationDao=new RelationsDaoImpl();
	}
	@Override
	public int CountByAttention(int uid) {
		return relationDao.CountByAttention(uid);
	}

	@Override
	public int CountByVermicelli(int uid) {
		return relationDao.CountByVermicelli(uid);
	}
	@Override
	public boolean InsertRelation(int uid, int gid) {
		int flag = relationDao.FindRelationByuid(uid, gid);
		System.out.println(flag);
		int a =relationDao.InsertRelation(uid, gid,flag);
		if(a==1){
			return true;
		}else{
		   return false;
		}
	}
	@Override
	public boolean DeleteRelationByuid(int uid, int gid) {
		int a=relationDao.DeleteRelationByuid(uid, gid);
		if(a==1){
			return true;
		}else{
			return false;	
		}		
	}
	@Override
	public int FindRelationByuid(int uid, int gid) {
		return relationDao.FindRelationByuid(uid, gid);
	}
	@Override
	public List<Users> FindAllMyInterestByuid(int uid) {
		return relationDao.FindAllMyInterestByuid(uid);
	}
}
