package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CollectionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		//收藏功能
		if("collection".equals(action)) {
			collection(request,response);
		//取消收藏功能
		}else if("cancelcollection".equals(action)) {
			cancelcollection(request,response);
		}
   }

	private void cancelcollection(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	private void collection(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

}
