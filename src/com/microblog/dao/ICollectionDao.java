package com.microblog.dao;

import com.microblog.filter.PageBean;
import com.microblog.po.Collection;

public interface ICollectionDao {
    //����ղ�
	public int InsertCollection(int uid,Collection coll);
	//��ҳ��ʾ�ղ���Ϣ
	public PageBean FindCollectionByPage(int uid,String strSQL,int currentPage,int pageSize);
	//delete ɾ��
	public int DeleteCollection(int lid);
	//count ͳ���ղ�����
	public int CountCollectionByLid(int lid);
	//ģ������
	public PageBean FuzzyFindCollectionByuid(int uid,String content,String strSQL,int currentPage,int pageSize);
    //ͳ�Ƶ�½���ղ�΢������
	public int CountCollectionByUid(int uid);
}
