package com.microblog.biz;

import java.util.List;

import com.microblog.po.Bloghot;

public interface IBollhotBiz {
	//显示微博热议内容
	public List<Bloghot> SelectByHot();
	//热议投票
	public boolean VoitHot(String hot);
}
