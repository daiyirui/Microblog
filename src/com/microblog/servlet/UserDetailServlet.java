package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.IRelationsBiz;
import com.microblog.biz.IUsersBiz;
import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.RelationsBizImpl;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class UserDetailServlet extends HttpServlet {

	public UserDetailServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		int uid=Integer.parseInt(request.getParameter("uid"));
		HttpSession session=request.getSession();
		IUsersBiz useBiz=new UsersBizImpl();
		Users use=useBiz.SelectByuid(uid);
		session.setAttribute("UsersDetail", use);
		IRelationsBiz relationsBiz = new RelationsBizImpl();
		int interests = relationsBiz.CountByAttention(uid);
		int follows = relationsBiz.CountByVermicelli(uid);
		session.setAttribute("interests", interests);
		session.setAttribute("follows", follows);
		response.sendRedirect("user.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
