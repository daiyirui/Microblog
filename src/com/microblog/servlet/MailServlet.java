package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.microblog.biz.IUsersBiz;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class MailServlet extends HttpServlet {

	public MailServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		IUsersBiz useBiz=new UsersBizImpl();
		String uname=request.getParameter("usn").trim();
		String uques=request.getParameter("uques").trim();
		String urequest=request.getParameter("urequest").trim();
		String mail=request.getParameter("mail").trim();		
		String result=uques+urequest;
		Users us=new Users();
		us=useBiz.SelectByMail(uname,result);
		if(us!=null){
			//�ύEmail����,���������ʼ�������Ⱥ�����ѷ��ͷ������״̬����
			Email email=new SimpleEmail();
			email.setCharset("gbk");
			email.setHostName("smtp.qq.com");
			//�����163����Ϊ������Ϊ smtp.163.com  ָ���Ƿ��ͷ���������
			//                      ���ͷ������ַ      ���ͷ���������   
			email.setAuthentication("12345@qq.com", "12345");
		    try { 
		    	//�������÷��ͷ������ַ�����ϱ�setAuthentication������ͬ
				email.setFrom("12345@qq.com");
				//�ʼ����շ���ַ
				email.addTo(mail);
				//���÷����ʼ��ı���
				email.setSubject("΢��ϵͳ-------�һ�����");
				//���÷����ʼ�������
				email.setMsg("���������ǣ�"+us.getUpwd()+",�´β�Ҫ���ǰ���");
				//�����ʼ�
				email.send();
				response.getWriter().printf("<script>alert('�����Ѿ����͵�����������!');location.href='findpassword.jsp'</script>");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().printf("<script>alert('���뷢�ʹ���!');location.href='login.jsp'</script>");
			}
		}else{
			response.getWriter().printf("<script>alert('�û�����ش����������!');location.href='login.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
