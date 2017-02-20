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
public class DeleteCommentServlet extends HttpServlet {

	public DeleteCommentServlet() {
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
		int cid=Integer.parseInt(request.getParameter("cid").trim());
		ICommentBiz comBiz=new CommentBizImpl();
		HttpSession session=request.getSession();
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		if(comBiz.DeleteComment(cid)){
			PageBean pb=new PageBean();
			String wid=null;
			IWeiboBiz weiboBiz=new WeiboBizImpl();
			Weibo weibo=new Weibo();
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
			pb=comBiz.SelectByPageComment(Integer.parseInt(wid),nowpage, pagesize);
			session.setAttribute("CommentList",pb);
			response.getWriter().printf("<script>alert('删除评论成功!');location.href='comment.jsp'</script>");
		}else{
			response.getWriter().printf("<script>alert('删除评论失败!');location.href='comment.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
