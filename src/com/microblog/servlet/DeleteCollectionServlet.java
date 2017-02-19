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
public class DeleteCollectionServlet extends HttpServlet {

	public DeleteCollectionServlet() {
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
		int lid=Integer.parseInt(request.getParameter("lid"));
		ICollectionBiz colBiz=new CollectionBizImpl();
		HttpSession session=request.getSession();
		Users use=new Users();
		if(colBiz.DeleteCollection(lid)){
			 if(session.getAttribute("userinfo")!=null){
			    	use=(Users) session.getAttribute("userinfo");
			 }
			 PageBean pb=new PageBean();
			//定义分页参数
				int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
				int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
				pb=colBiz.SelectCollectionByPage(use.getUid(), nowpage, pagesize);
				session.setAttribute("CollectionList",pb);
				//显示收藏微博数量
				int count=colBiz.CountCollectionByLid(use.getUid());			 
				session.setAttribute("CountCollection", count);
				response.getWriter().printf("<script>alert('删除收藏成功!');location.href='profile.jsp'</script>");
		}else{
			response.getWriter().printf("<script>alert('删除收藏失败!');location.href='profile.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
