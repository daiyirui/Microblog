package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class ReturnHomeServlet extends HttpServlet {

	
	public ReturnHomeServlet() {
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
		//显示登录者和其所关注人的微博信息-分页显示
		HttpSession session=request.getSession();
		IWeiboBiz weiboBiz=new WeiboBizImpl();
		PageBean pb=new PageBean();	
		Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");
		}
		//定义分页参数
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		pb=weiboBiz.SelectByPage(use.getUid(),nowpage, pagesize);
		session.setAttribute("weiboList",pb);
		response.sendRedirect("home.jsp");
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
