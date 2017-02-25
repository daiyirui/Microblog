package com.microblog.dao;

import com.microblog.filter.PageBean;
import com.microblog.po.Collection;

public interface ICollectionDao {
    //添加收藏
	public int InsertCollection(int uid,Collection coll);
	//分页显示收藏信息
	public PageBean FindCollectionByPage(int uid,String strSQL);
	//delete 删除
	public int DeleteCollection(int lid);
	//count 统计收藏数量
	public int CountCollectionByLid(int lid);
	//模糊搜索
	public PageBean FuzzyFindCollectionByuid(int uid,String content,String strSQL);
    //统计登陆者收藏微博数量
	public int CountCollectionByUid(int uid);
}
