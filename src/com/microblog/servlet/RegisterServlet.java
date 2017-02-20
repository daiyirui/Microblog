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
public class RegisterServlet extends HttpServlet {

	public RegisterServlet() {
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
		String sheng=request.getParameter("selectProvinceArray").trim();
		String city=request.getParameter("selectCityArray").trim();
		String uname=request.getParameter("uname").trim();
		String upwd=request.getParameter("upwd").trim();
		String sex=request.getParameter("usex").trim();
		String unickname=request.getParameter("unickname").trim();
		Users use=new Users();
		HttpSession session=request.getSession();
		use.setUname(uname);
		use.setUpwd(upwd);
		use.setUaddress(sheng+"-"+city);
		use.setUsex(sex);
		use.setUnickname(unickname);
		IUsersBiz useBiz=new UsersBizImpl();
		if(useBiz.RegisterUser(use)){
			//证明用户登录后直接跳转个人主页面 home
			if(request.getParameter("sel")!=null){
				//获取刚注册用户对象详细
				use=useBiz.SelectByObject(uname, upwd, sex);				
				session.setAttribute("userinfo", use);
				//获取登录者及其关注微博详细===============
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
				//跳转个人主页
				response.getWriter().printf("<script>alert('用户"+uname+"注册成功!');location.href='home.jsp'</script>");
			}else{
				response.getWriter().printf("<script>alert('用户"+uname+"注册成功!');location.href='login.jsp'</script>");
			}
		}else{
			response.getWriter().printf("<script>alert('用户注册失败!');location.href='register.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
