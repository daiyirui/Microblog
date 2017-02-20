package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
public class LoginServlet extends HttpServlet {

	public LoginServlet() {
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
		//step1:获取页面提交表单值
		String usn=request.getParameter("usn").trim();
		String pwd=request.getParameter("pwd").trim();
		//Cookie
		String keep=request.getParameter("keep");
		if(keep!=null&&keep.equals("on")){
			System.out.println("Keep "+keep);
			//保存用户名
			Cookie cookie=new Cookie("usn",usn);
			cookie.setPath("/");
			cookie.setMaxAge(24*60*60);
			response.addCookie(cookie);
			//保存密码
			cookie=new Cookie("pwd",pwd);
			cookie.setPath("/");
			cookie.setMaxAge(24*60*60);
			response.addCookie(cookie);
		}
		//step2:通过biz层验证
		IUsersBiz userBiz=new UsersBizImpl();
		Users use=new Users();
		use=userBiz.UserLogin(usn, pwd);
		//step3 如何用户名密码正确
		if(use!=null){
			//step4 登录后的操作：显示登录者username
			HttpSession session=request.getSession();
			session.setAttribute("userinfo", use);
			//显示登录者和其所关注人的微博信息-分页显示
			IWeiboBiz weiboBiz=new WeiboBizImpl();
			PageBean pb=new PageBean();			
			//定义分页参数
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=weiboBiz.SelectByPage(use.getUid(),nowpage, pagesize);
			session.setAttribute("weiboList",pb);
			//显示登录者要关注人的信息-第一次登陆只显示前八个陌生朋友
			List<Users> listAllUser=new ArrayList<Users>();//全部陌生朋友信息
			List<Users> listUser=new ArrayList<Users>();//显示前8个陌生朋友信息
			if(session.getAttribute("userAllList")==null){
				listAllUser=userBiz.SelectByInterest(use.getUid());
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
			//微博数量
			int countMicroblog=weiboBiz.CountByMicroblog(use.getUid());
			session.setAttribute("countBlog",countMicroblog);
			//显示所关注人数量
			IRelationsBiz relationBiz=new RelationsBizImpl();
			int countRlat=relationBiz.CountByAttention(use.getUid());
			session.setAttribute("countRlation",countRlat);
			//显示粉丝数量
			int countVeri=relationBiz.CountByVermicelli(use.getUid());
			session.setAttribute("countVeri",countVeri);
			//step5 跳转到个人主页面
			response.sendRedirect("home.jsp");
		}
		//step6 错误返回登录页面
		else{
			 
			response.getWriter().printf("<script>alert('用户名或密码错误\n获取此用户被禁用!');location.href='login.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
