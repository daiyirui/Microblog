package com.microblog.biz;

import com.microblog.filter.PageBean;
import com.microblog.po.Collection;

public interface ICollectionBiz {
	//����ղ�
	public boolean InsertCollection(int uid,Collection coll);
	//��ҳ��ʾ�ղ���Ϣ
	public PageBean SelectCollectionByPage(int uid,int currentPage,int pageSize);
	//delete ɾ��
	public boolean DeleteCollection(int lid);
	//count ͳ���ղ�����
	public int CountCollectionByLid(int lid);
	//count ͳ�Ƶ�½���ղ�����
	public int CountCollectionByUid(int uid);
	//ģ������
	public PageBean FuzzyFindCollectionByuid(int uid,String content,int currentPage,int pageSize);
}
