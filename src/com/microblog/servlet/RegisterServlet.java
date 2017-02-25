package com.microblog.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.biz.IUsersBiz;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.po.Users;


@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if("0".equals(action)) {
			register(request,response);
		} else{
			
		}
		
	}
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        		String uname=request.getParameter("uname");
        		String upwd = request.getParameter("upwd");
        		String urealname=request.getParameter("urealname");
        		String unickname=request.getParameter("unickname");
        		String uqq = request.getParameter("uqq");
        		String uemail = request.getParameter("uemail");
        		String usex = request.getParameter("usex");
        		String province = request.getParameter("province");
        		String city = request.getParameter("city");
        		String uedu = request.getParameter("uedu");
        		Users user = new Users();
        		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        		user.setUdate(time.format(new Date()));
        		user.setUname(uname);
        		user.setUpwd(upwd);
        		user.setUrealname(urealname);
        		user.setUemail(uemail);
        		user.setUaddress(province+city);
        		user.setUedu(uedu);
        		user.setUqq(uqq);
        		user.setUsex(usex);
        		user.setUnickname(unickname);
        		IUsersBiz usersBiz = new UsersBizImpl();
        		boolean flag = usersBiz.RegisterUser(user);
        		if(flag) {
        			request.setAttribute("register", 1);
        			request.getRequestDispatcher("login.jsp").forward(request, response);
        		}else{
        			request.setAttribute("register", 1);
        			request.getRequestDispatcher("register.jsp").forward(request, response);
        		}
        		
	}
	
}
