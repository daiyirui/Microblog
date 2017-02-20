package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.po.Users;

@SuppressWarnings("serial")
public class CheckPasswordServlet extends HttpServlet {

	public CheckPasswordServlet() {
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
		Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");		
		}
		String nowpassword=request.getParameter("nowpassword").trim();
		if(!use.getUpwd().equals(nowpassword)){
			response.getWriter().printf("<script>alert('您输入的原始密码不对!');location.href='mypassword.jsp'</script>");
			return ;
		}else{
			String upwd=request.getParameter("upwd").trim();
			String uques=request.getParameter("uques").trim();
			String urequest=request.getParameter("urequest").trim();
			String unickname=request.getParameter("unickname").trim();
			String urealname=request.getParameter("urealname").trim();
			String usex=request.getParameter("usex").trim();
			String uedu=request.getParameter("uedu").trim();
			String uqq=request.getParameter("uqq").trim();
			String uremarks=request.getParameter("uremarks").trim();
			
			use.setUedu(uedu);
			use.setUnickname(unickname);
			use.setUpwd(upwd);
			use.setUqq(uqq);
			use.setUques(uques+urequest);
			use.setUremarks(uremarks);
			use.setUsex(usex);
			use.setUrealname(urealname);
			session.setAttribute("userinfo", use);
			response.sendRedirect("myface.jsp");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
