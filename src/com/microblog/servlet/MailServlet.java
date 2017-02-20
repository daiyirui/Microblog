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
			//提交Email邮箱,单个发送邮件，不是群发，把发送方邮箱打开状态测试
			Email email=new SimpleEmail();
			email.setCharset("gbk");
			email.setHostName("smtp.qq.com");
			//如果是163邮箱为例，则为 smtp.163.com  指的是发送方邮箱属性
			//                      发送方邮箱地址      发送方邮箱密码   
			email.setAuthentication("12345@qq.com", "12345");
		    try { 
		    	//这里设置发送方邮箱地址，与上边setAuthentication设置相同
				email.setFrom("12345@qq.com");
				//邮件接收方地址
				email.addTo(mail);
				//设置发送邮件的标题
				email.setSubject("微博系统-------找回密码");
				//设置发送邮件的内容
				email.setMsg("您的密码是："+us.getUpwd()+",下次不要忘记啊！");
				//发送邮件
				email.send();
				response.getWriter().printf("<script>alert('密码已经发送到您的邮箱内!');location.href='findpassword.jsp'</script>");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().printf("<script>alert('密码发送错误!');location.href='login.jsp'</script>");
			}
		}else{
			response.getWriter().printf("<script>alert('用户名或回答的问题有误!');location.href='login.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
