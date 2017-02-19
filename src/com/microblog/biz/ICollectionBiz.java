package com.microblog.biz;

import com.microblog.filter.PageBean;
import com.microblog.po.Collection;

public interface ICollectionBiz {
	//添加收藏
	public boolean InsertCollection(int uid,Collection coll);
	//分页显示收藏信息
	public PageBean SelectCollectionByPage(int uid,int currentPage,int pageSize);
	//delete 删除
	public boolean DeleteCollection(int lid);
	//count 统计收藏数量
	public int CountCollectionByLid(int lid);
	//count 统计登陆者收藏数量
	public int CountCollectionByUid(int uid);
	//模糊搜索
	public PageBean FuzzyFindCollectionByuid(int uid,String content,int currentPage,int pageSize);
}
