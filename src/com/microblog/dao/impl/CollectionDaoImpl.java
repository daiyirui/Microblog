package com.microblog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dao.ICollectionDao;
import com.microblog.dbutil.DBConn;
import com.microblog.filter.PageBean;
import com.microblog.po.Collection;
import com.microblog.po.Users;

public class CollectionDaoImpl implements ICollectionDao {

	@Override
	public int InsertCollection(int uid, Collection coll) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageBean FindCollectionByPage(int uid, String strSQL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int DeleteCollection(int lid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int CountCollectionByLid(int lid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageBean FuzzyFindCollectionByuid(int uid, String content,
			String strSQL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int CountCollectionByUid(int uid) {
		// TODO Auto-generated method stub
		return 0;
	}
   
}
