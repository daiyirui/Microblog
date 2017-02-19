package com.microblog.biz;

import java.util.List;

import com.microblog.po.Users;

public interface IRelationsBiz {
	//显示登录者关注人的数量
	public int CountByAttention(int uid);
	//显示登录者粉丝数量
	public int CountByVermicelli(int uid);
	//添加关注                                       加关注着     被加关注者
	public boolean InsertRelation(int uid,int gid);
	//delete关注                                       加关注着     被加关注者
	public boolean DeleteRelationByuid(int uid,int gid);
	//查找当前关注状态
	public int FindRelationByuid(int uid, int gid);
	//查询我关注了那些人
	public List<Users> FindAllMyInterestByuid(int uid);
}
