package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.ICollectionBiz;
import com.microblog.biz.impl.CollectionBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class CollectionChangeServlet extends HttpServlet {

	public CollectionChangeServlet() {
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
	    ICollectionBiz collBiz=new CollectionBizImpl();
	    HttpSession session=request.getSession();
	    Users use=new Users();	   
	    if(session.getAttribute("userinfo")!=null){
	    	use=(Users) session.getAttribute("userinfo");
	    }
	    PageBean pb=new PageBean();
    	//定义分页参数
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		pb=collBiz.SelectCollectionByPage(use.getUid(), nowpage, pagesize);
		session.setAttribute("CollectionList",pb);
		response.sendRedirect("profile.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
