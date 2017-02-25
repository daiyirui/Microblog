package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RelationServlet extends HttpServlet {

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
		//关注对方功能
		//取消关注功能
		//显示自己关注的人
		//显示关注自己的人
		
		String action = request.getParameter("action");
		if("guanzhu".equals(action)) {
			guanzhu(request,response);
		}else if("cancelGuanzhu".equals(action)){
			cancelGuanzhu(request,response);
		}else if("showGuanzhu".equals(action)) {
			showGuanzhu(request,response);
		}else if("showFans".equals(action)) {
			showFans(request,response);
		}
	}

	/**
	 * 显示自己的粉丝，即关注我的人
	 * @param request
	 * @param response
	 */
	private void showFans(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 显示自己关注的人
	 * @param request
	 * @param response
	 */
	private void showGuanzhu(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	/**
	 * 取消关注对方
	 * @param request
	 * @param response
	 */
	private void cancelGuanzhu(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * 关注对方
	 * @param request
	 * @param response
	 */
	private void guanzhu(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
}
