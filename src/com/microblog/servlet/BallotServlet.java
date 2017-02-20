package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.biz.IBollhotBiz;
import com.microblog.biz.impl.BollhotBizImpl;

@SuppressWarnings("serial")
public class BallotServlet extends HttpServlet {

	public BallotServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");	
		String tennis=null;
		if(request.getParameter("tennis")!=null){
			tennis=request.getParameter("tennis").trim();
			IBollhotBiz bolBiz=new BollhotBizImpl();
			if(bolBiz.VoitHot(tennis)){
				response.getWriter().printf("<script>alert('"+tennis+"投票成功!');location.href='ballot.jsp'</script>");
			}else{
				response.getWriter().printf("<script>alert('投票失败!');location.href='ballot.jsp'</script>");
			}
		}else{
			response.getWriter().printf("<script>alert('请先选择投票!');location.href='ballot.jsp'</script>");
		}		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
