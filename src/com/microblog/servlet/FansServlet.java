package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.IUsersBiz;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class FansServlet extends HttpServlet {

	public FansServlet() {
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
		HttpSession session=request.getSession();
	    Users use=new Users();	   
	    if(session.getAttribute("userinfo")!=null){
	    	use=(Users) session.getAttribute("userinfo");
	    }
	    PageBean pb=new PageBean();
	    IUsersBiz useBiz=new UsersBizImpl();
    	//定义分页参数
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		pb=useBiz.SelectFansByPage(use.getUid(), nowpage, pagesize);
		session.setAttribute("fansList",pb);
		response.sendRedirect("fans.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
