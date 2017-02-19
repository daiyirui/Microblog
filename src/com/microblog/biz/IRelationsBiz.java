package com.microblog.biz;

import java.util.List;

import com.microblog.po.Users;

public interface IRelationsBiz {
	//��ʾ��¼�߹�ע�˵�����
	public int CountByAttention(int uid);
	//��ʾ��¼�߷�˿����
	public int CountByVermicelli(int uid);
	//��ӹ�ע                                       �ӹ�ע��     ���ӹ�ע��
	public boolean InsertRelation(int uid,int gid);
	//delete��ע                                       �ӹ�ע��     ���ӹ�ע��
	public boolean DeleteRelationByuid(int uid,int gid);
	//���ҵ�ǰ��ע״̬
	public int FindRelationByuid(int uid, int gid);
	//��ѯ�ҹ�ע����Щ��
	public List<Users> FindAllMyInterestByuid(int uid);
}
