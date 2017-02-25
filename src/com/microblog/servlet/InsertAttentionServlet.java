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
import com.microblog.biz.impl.RelationsBizImpl;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

import sun.rmi.server.Dispatcher;

@SuppressWarnings("serial")
public class InsertAttentionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//从我的粉丝页面添加关注的
		if("1".equals(action)) {
			addInterestByFanspage(request,response);
		} else {
			IRelationsBiz relBiz=new RelationsBizImpl();
			HttpSession session=request.getSession();
			Users use=new Users();
			if(session.getAttribute("userinfo")!=null){
				use=(Users) session.getAttribute("userinfo");			
			}
			int gid=Integer.parseInt(request.getParameter("gid"));
			System.out.println("gid:"+gid);
			System.out.println("use:"+use.getUid());
			if(relBiz.InsertRelation(use.getUid(), gid)){
				//显示登录者要关注人的信息-第一次登陆只显示前八个陌生朋友
				IRelationsBiz relationBiz=new RelationsBizImpl();
				IUsersBiz userBiz=new UsersBizImpl();
				List<Users> MyInterestUsers= relationBiz.FindAllMyInterestByuid(use.getUid());
				List<Users> listAllUser=new ArrayList<Users>();//全部陌生朋友信息
				List<Users> listUser=new ArrayList<Users>();//显示前8个陌生朋友信息
				if(session.getAttribute("userAllList")==null){
					listAllUser=userBiz.SelectByInterest(use.getUid());
					listAllUser.removeAll(MyInterestUsers);
					for (int i = 0; i < 8; i++) {
						listUser.add(listAllUser.get(i));		
					}
				}else{
					listAllUser=(List<Users>) session.getAttribute("userAllList");
					listAllUser.removeAll(MyInterestUsers);
					for (int i = 0; i < 8; i++) {
						listUser.add(listAllUser.get(i));		
					}
				}
				session.setAttribute("userAllList", listAllUser);
				if(listUser!=null){
					session.setAttribute("userList",listUser);	
				}			
				////显示所关注人数量
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
				request.getRequestDispatcher("HomeServlet").forward(request,response);
	
			}else{
				response.getWriter().printf("<script>alert('添加好友关注失败!');location.href='home.jsp'</script>");
			}
		}
	}
	
	private void addInterestByFanspage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IRelationsBiz relBiz=new RelationsBizImpl();
		HttpSession session=request.getSession();
		Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");			
		}
		int gid=Integer.parseInt(request.getParameter("gid"));
		System.out.println("use.getUid() :"+use.getUid());
		System.out.println("gid:"+gid);
		if(relBiz.InsertRelation(use.getUid(), gid)){
			//显示登录者要关注人的信息-第一次登陆只显示前八个陌生朋友
			IRelationsBiz relationBiz=new RelationsBizImpl();
			IUsersBiz userBiz=new UsersBizImpl();
			List<Users> MyInterestUsers= relationBiz.FindAllMyInterestByuid(use.getUid());
			List<Users> listAllUser=new ArrayList<Users>();//全部陌生朋友信息
			List<Users> listUser=new ArrayList<Users>();//显示前8个陌生朋友信息
			if(session.getAttribute("userAllList")==null){
				listAllUser=userBiz.SelectByInterest(use.getUid());
				listAllUser.removeAll(MyInterestUsers);
				for (int i = 0; i < 8; i++) {
					listUser.add(listAllUser.get(i));		
				}
			}else{
				listAllUser=(List<Users>) session.getAttribute("userAllList");
				listAllUser.removeAll(MyInterestUsers);
				for (int i = 0; i < 8; i++) {
					listUser.add(listAllUser.get(i));		
				}
			}
			session.setAttribute("userAllList", listAllUser);
			if(listUser!=null){
				session.setAttribute("userList",listUser);	
			}			
			////显示所关注人数量
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
			request.getRequestDispatcher("MyFans.jsp").forward(request,response);
	}

	
  }
}