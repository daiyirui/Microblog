package com.microblog.dao;


import java.util.List;

import com.microblog.filter.PageBean;
import com.microblog.po.Users;

public interface IUserDao {
    //�û���¼��֤
	public Users UserLoginCheck(String usn,String pwd);
    //��¼����Ҫ��ע�˵���Ϣ
	public List<Users> FindByInterest(int uid );
	//��������ʾ��ҳ�û�ͷ����Ϣ
	public List<Users> FindByListener();
	//��ҳ��ʾ ��½����Ҫ�Ѿ���ע�˵���Ϣ
	public PageBean FindByOverInterest(int uid,String strSQL,int currentPage,int pageSize);
	//��ҳ��ʾ��½�߷�˿��Ϣ
	public PageBean FindFansByPage(int uid,String strSQL,int currentPage,int pageSize);
	//��ʾ�û�������ϸ��Ϣ
	public Users FindByuid(int uid);
	//ע���û�
	public int RegisterUser(Users use);
	// ��ʾ��ע���û�������ϸ��ϸ
	public Users FindByObject(String uname,String upwd,String sex);
	//�����û���Ϣ
	public int UpdateUser(Users use);
	//find password
	public Users FindByMail(String uname,String uques);
}
