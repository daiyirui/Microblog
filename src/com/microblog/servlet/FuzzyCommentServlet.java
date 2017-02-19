package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.microblog.biz.ICommentBiz;
import com.microblog.biz.impl.CommentBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Weibo;

@SuppressWarnings("serial")
public class FuzzyCommentServlet extends HttpServlet {

	public FuzzyCommentServlet() {
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
		String content=request.getParameter("textcomment").trim();
		HttpSession session=request.getSession();
		PageBean pb=new PageBean();		
		Weibo weibo=new Weibo();
		if(session.getAttribute("WeiboDetail")!=null){
			weibo=(Weibo) session.getAttribute("WeiboDetail");
		}else{
			weibo.setWid(Integer.parseInt(request.getParameter("wid").trim()));
		}
		ICommentBiz comBiz=new CommentBizImpl();
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		pb=comBiz.FuzzyFindCommentByPage(weibo.getWid(), content,nowpage, pagesize);
		session.setAttribute("CommentList",pb);
		response.sendRedirect("comment.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
