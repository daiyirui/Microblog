package com.microblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.common.MailSender;
import com.microblog.dao.IUserDao;
import com.microblog.dao.impl.UserDaoImpl;
import com.microblog.po.Users;

public class MailServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           this.doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail=request.getParameter("mail").trim();	
		IUserDao userDao = new UserDaoImpl();
		List<String> emails = userDao.AllEmails();
		if(emails!=null&&!emails.contains(mail)) {
			request.setAttribute("emailError", 1);
			request.getRequestDispatcher("findpassword.jsp").forward(request, response);
			return ;
		}
		Users us=userDao.FindByMail(mail);
		if(us!=null){
		    	String subject = "微博系统-------找回密码";
		        String content = "您的密码是："+us.getUpwd()+",下次不要忘记哈！";
		    	MailSender.sendEmail(mail, subject, content);
				request.setAttribute("emailSuccess", 1);
				request.getRequestDispatcher("findpassword.jsp").forward(request, response);
			
		}
   }
}