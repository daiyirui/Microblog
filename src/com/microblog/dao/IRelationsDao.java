package com.microblog.dao;

import java.util.List;

import com.microblog.po.Users;

public interface IRelationsDao {
   //显示登录者关注人的数量
	public int CountByAttention(int uid);
	//显示登录者粉丝数量 ，不是双向关注的情况
	public int CountByVermicelli(int uid);
	//添加关注                                       加关注着     被加关注者   flag判断对方是否已经关注我了，1为关注0为没有关注
	public int InsertRelation(int uid,int gid);
	//delete关注                                       加关注着     被加关注者
	public int DeleteRelationByuid(int uid,int gid);
	//查询对方是否关注我
	public int FindRelationByuid(int uid,int gid);
	//查询我关注了那些人
	public List<Users> FindAllMyInterestByuid(int uid);
	//查询哪些人关注了我
	public List<Users> FindAllMyFansByuid(int uid);
	//判断我是不是关注了对方
	public int judgeGuanzhu(int uid,int gid);
}
