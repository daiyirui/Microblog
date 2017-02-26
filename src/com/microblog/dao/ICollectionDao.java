package com.microblog.dao;

import java.util.List;

import com.microblog.po.Collection;

public interface ICollectionDao {
    //添加收藏
	public int InsertCollection(Collection coll);
	//获取我所有收藏的微博
	public List<Collection> FindCollectionByuid(int uid);
	//delete 取消收藏
	public int DeleteCollection(int lid);
    //统计登陆者收藏微博数量
	public int CountCollectionByUid(int uid);
	//判断该微博是否被我收藏了
	public int judgeColletionBywid(int uid,int wid);
}
