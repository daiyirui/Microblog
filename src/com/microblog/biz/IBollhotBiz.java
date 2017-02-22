package com.microblog.biz;

import java.util.List;

import com.microblog.po.Bloghot;

public interface IBollhotBiz {
	//显示微博热议内容
	public List<Bloghot> SelectByHot();
	//热议投票
	public boolean VoitHot(String hot);
	//显示所有微博热议内容，对投票内容进行排序
	public List<Bloghot> SelectAllHot();
}
