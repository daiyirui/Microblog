package com.microblog.dao;

import java.util.List;

import com.microblog.po.Bloghot;

public interface IBollhotDao {
   //��ʾ΢����������
	public List<Bloghot> FindByHot();
	//����ͶƱ
	public int VoitHot(String hot);
}
