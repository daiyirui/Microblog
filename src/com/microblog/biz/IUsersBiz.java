package com.microblog.biz;

import java.util.List;

import com.microblog.filter.PageBean;
import com.microblog.po.Users;

public interface IUsersBiz {
	//�û���¼
    public Users UserLogin(String usn,String pwd);
    //��¼����Ҫ��ע�˵���Ϣ
	public List<Users> SelectByInterest(int uid );
	//��������ʾ��ҳ�û�ͷ����Ϣ
	public List<Users> SelectByListener();
	//��ҳ��ʾ ��½����Ҫ�Ѿ���ע�˵���Ϣ
	public PageBean SelectByOverInterest(int uid,int currentPage,int pageSize);
	//��ҳ��ʾ��½�߷�˿��Ϣ
	public PageBean SelectFansByPage(int uid,int currentPage,int pageSize);
	//��ʾ�û�������ϸ��Ϣ
	public Users SelectByuid(int uid);
	//ע���û�
	public boolean RegisterUser(Users use);
	// ��ʾ��ע���û�������ϸ��ϸ
	public Users SelectByObject(String uname,String upwd,String sex);
	//�����û���Ϣ
	public boolean UpdateUser(Users use);
	//find password
	public Users SelectByMail(String uname,String uques);
}
