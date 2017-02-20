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
public class FuzzyCollectionServlet extends HttpServlet {

	public FuzzyCollectionServlet() {
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
	    String content=request.getParameter("textprofile").trim();
	    Users use=new Users();
	    HttpSession session=request.getSession();
	    if(session.getAttribute("userinfo")!=null){
	    	use=(Users) session.getAttribute("userinfo");
	    }
	    PageBean pb=new PageBean();	   
	    ICollectionBiz colBiz=new CollectionBizImpl();
	  //定义分页参数
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		pb=colBiz.FuzzyFindCollectionByuid(use.getUid(), content,  nowpage,pagesize);
		session.setAttribute("CollectionList",pb);
		response.sendRedirect("profile.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
