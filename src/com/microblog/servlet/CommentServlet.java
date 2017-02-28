package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CommentServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	       String action = request.getParameter("action");
	       //插入评论功能
	       if("insertComment".equals(action)) {
	    	   insertComment(request,response);
	       //删除评论功能
	       }else if("deleteComment".equals(action)) {
	    	   deleteComment(request,response);
	       }
	}
	private void deleteComment(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	private void insertComment(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	

}
