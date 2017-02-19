package com.microblog.dao;

import java.util.List;

import com.microblog.po.Users;

public interface IRelationsDao {
   //��ʾ��¼�߹�ע�˵�����
	public int CountByAttention(int uid);
	//��ʾ��¼�߷�˿����
	public int CountByVermicelli(int uid);
	//��ӹ�ע                                       �ӹ�ע��     ���ӹ�ע��   flag�ж϶Է��Ƿ��Ѿ���ע���ˣ�1Ϊ��ע0Ϊû�й�ע
	public int InsertRelation(int uid,int gid,int flag);
	//delete��ע                                       �ӹ�ע��     ���ӹ�ע��
	public int DeleteRelationByuid(int uid,int gid);
	//��ѯ�Է��Ƿ��ע��
	public int FindRelationByuid(int uid,int gid);
	//��ѯ�ҹ�ע����Щ��
	public List<Users> FindAllMyInterestByuid(int uid);
}
