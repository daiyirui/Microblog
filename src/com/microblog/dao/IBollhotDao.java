package com.microblog.dao;

import java.util.List;

import com.microblog.po.Bloghot;
import com.microblog.po.Bloghotitem;

public interface IBollhotDao {
	
	//显示所有微博内容，按投票进行排序
	public List<Bloghot> FindAllHot();
	//单个热议内容下面的子选项
	public List<Bloghotitem> FindAllHotItem(Integer bid);
	//对子微博进行投票
	public int viotBollhotItem(int itemId);
	//对投票总数进行更新
	public int viotBollhot(int bid,int count);
	
}
