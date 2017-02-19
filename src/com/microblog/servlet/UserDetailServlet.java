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
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		int uid=Integer.parseInt(request.getParameter("uid"));
		HttpSession session=request.getSession();
		Users use=new Users();
		IUsersBiz useBiz=new UsersBizImpl();
		use=useBiz.SelectByuid(uid);
		session.setAttribute("UsersDetail", use);
		IWeiboBiz weiBiz=new WeiboBizImpl();
		PageBean pb=new PageBean();			
		//定义分页参数
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));	
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		pb=weiBiz.SelectWeiboByuid(uid,nowpage, pagesize);
		session.setAttribute("UsersDetailWeibo",pb);
		//显示登录者要关注人的信息-第一次登陆只显示前八个陌生朋友
		List<Users> listAllUser=new ArrayList<Users>();//全部陌生朋友信息
		List<Users> listUser=new ArrayList<Users>();//显示前8个陌生朋友信息
		if(session.getAttribute("userAllList")==null){
			listAllUser=useBiz.SelectByInterest(use.getUid());
			for (int i = 0; i < 8; i++) {
				listUser.add(listAllUser.get(i));		
			}
		}else{
			listAllUser=(List<Users>) session.getAttribute("userAllList");
			for (int i = 0; i < 8; i++) {
				listUser.add(listAllUser.get(i));		
			}
		}
		session.setAttribute("userAllList", listAllUser);
		if(listUser!=null){
			session.setAttribute("userList",listUser);	
		}			
		//好友微博数量
		int countMicroblog=weiBiz.CountByMicroblog(use.getUid());
		session.setAttribute("countDBlog",countMicroblog);
		//显示所关注人数量
		IRelationsBiz relationBiz=new RelationsBizImpl();
		int countRlat=relationBiz.CountByAttention(use.getUid());
		session.setAttribute("countDRlation",countRlat);
		//显示粉丝数量
		int countVeri=relationBiz.CountByVermicelli(use.getUid());
		session.setAttribute("countDVeri",countVeri);
		response.sendRedirect("user.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
