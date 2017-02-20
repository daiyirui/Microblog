package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.ICommentBiz;
import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.CommentBizImpl;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Weibo;

@SuppressWarnings("serial")
public class CommentServlet extends HttpServlet {

	public CommentServlet() {
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
		IWeiboBiz weiboBiz=new WeiboBizImpl();
		Weibo weibo=new Weibo();
		String wid=null;
		//在从个人主页面上跳转至评论页面时候wid不为空      /Microblog/upload/pic/dc.jpg
		if(request.getParameter("wid")!=null){
			wid=request.getParameter("wid").trim();
			weibo=weiboBiz.SelectBywid(Integer.parseInt(wid));
			session.setAttribute("WeiboDetail", weibo);
		}
		//在分页跳转的时候，wid就为空
		else{
			wid=((Weibo)session.getAttribute("WeiboDetail")).getWid().toString();
		}
		//显示对此微博信息评论的分页显示信息
		ICommentBiz commentBiz=new CommentBizImpl();
		PageBean pb=new PageBean();
		//定义分页参数
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		pb=commentBiz.SelectByPageComment(Integer.parseInt(wid), nowpage,pagesize);
		session.setAttribute("CommentList",pb);
		response.sendRedirect("comment.jsp");
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
