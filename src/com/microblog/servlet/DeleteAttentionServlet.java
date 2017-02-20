package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.IRelationsBiz;
import com.microblog.biz.IUsersBiz;
import com.microblog.biz.impl.RelationsBizImpl;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class DeleteAttentionServlet extends HttpServlet {

	public DeleteAttentionServlet() {
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
	    int gid=Integer.parseInt(request.getParameter("gid"));
	    HttpSession session=request.getSession();
	    Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");			
		}
		IRelationsBiz relBiz=new RelationsBizImpl();
		if(relBiz.DeleteRelationByuid(use.getUid(), gid)){
		    //显示所关注人数量
			int countRlat=relBiz.CountByAttention(use.getUid());
			session.setAttribute("countRlation",countRlat);
			//显示已经关注人的信息
			PageBean pb=new PageBean();
			IUsersBiz useBiz=new UsersBizImpl();
			//定义分页参数
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=useBiz.SelectByOverInterest(use.getUid(), nowpage, pagesize);
			session.setAttribute("AttentionUsers",pb);
			response.getWriter().printf("<script>alert('删除好友关注成功!');location.href='friend.jsp'</script>");
		}else{
			response.getWriter().printf("<script>alert('删除好友关注失败!');location.href='home.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
