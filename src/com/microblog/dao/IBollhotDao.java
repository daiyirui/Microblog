package com.microblog.dao;

import java.util.List;

import com.microblog.po.Bloghot;

public interface IBollhotDao {
   //显示微博热议内容
	public List<Bloghot> FindByHot();
	//热议投票
	public int VoitHot(String hot);
}
