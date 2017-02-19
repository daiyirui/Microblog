package com.microblog.biz;

import java.util.List;

import com.microblog.filter.PageBean;
import com.microblog.po.Weibo;

public interface IWeiboBiz {
	//��ȡ��¼�ߺ�������ע�˵�΢����Ϣ
	public List<Weibo> SelectByLogin(int uid);
	//��ʾ΢������
	public int CountByMicroblog(int uid);
	//��ҳ��ʾ��¼�ߺ�������ע�˵�΢����Ϣ  sql���   ��ǰ�ڼ�ҳ       ÿҳ��ʾ������
	public PageBean SelectByPage(int uid,int currentPage,int pageSize);
	//��ҳ��ʾ��¼��΢����Ϣ  sql���   ��ǰ�ڼ�ҳ       ÿҳ��ʾ������
	public PageBean SelectByPageOnlyOwn(int uid, int currentPage, int pageSize);
	//ת��΢��
	public boolean ForWardMicroblog(int uid,String wcontent,int wid,String wimage);
	//��ʾ����΢����Ϣ����
	public Weibo SelectBywid(int wid);
	//���΢����Ϣ
	public boolean InsertWeibo(Weibo weibo,int uid);
	//ģ����Ѱ΢����Ϣ
	public PageBean FuzzyFindWeiboByPage(int uid,String content,int currentPage,int pageSize);
	//��ҳ��ʾ����΢����Ϣ
	public PageBean SelectWeiboByuid(int uid,int currentPage,int pageSize);
}
