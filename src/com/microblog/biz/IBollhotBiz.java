package com.microblog.biz;

import java.util.List;

import com.microblog.po.Bloghot;

public interface IBollhotBiz {
	//��ʾ΢����������
	public List<Bloghot> SelectByHot();
	//����ͶƱ
	public boolean VoitHot(String hot);
}
