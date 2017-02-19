package com.microblog.dao;

import java.util.List;

import com.microblog.filter.PageBean;
import com.microblog.po.Weibo;

public interface IWeiboDao { 
	//��ȡ��¼�ߺ�������ע�˵�΢����Ϣ
	public List<Weibo> FindByLogin(int uid);
	//��ʾ΢������
	public int CountByMicroblog(int uid);
	//��ҳ��ʾ��¼�ߺ�������ע�˵�΢����Ϣ  sql���   ��ǰ�ڼ�ҳ       ÿҳ��ʾ������
	public PageBean FindByPage(int uid,String strSQL,int currentPage,int pageSize);
	//��ҳ��ʾ��¼��΢����Ϣ
	public PageBean FindByPageOnlyOwn(int uid,String strSQL, int currentPage, int pageSize);
	//ת��΢��
	public int ForWardMicroblog(int uid,String wcontent,int wid,String wimage);
	//��ʾ����΢����Ϣ����
	public Weibo FindBywid(int wid);
	//���΢����Ϣ
	public int InsertWeibo(Weibo weibo,int uid);
	//ģ����Ѱ΢����Ϣ
	public PageBean FuzzyFindWeiboByPage(int uid,String content,String strSQL,int currentPage,int pageSize);
	//��ҳ��ʾ����΢����Ϣ
	public PageBean FindWeiboByuid(int uid,String strSQL,int currentPage,int pageSize);
}
