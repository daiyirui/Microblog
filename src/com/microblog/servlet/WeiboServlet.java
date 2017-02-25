package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微博相关操作
 * @author admin
 *
 */
public class WeiboServlet  extends HttpServlet{
	
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
		//插入微博功能
		//删除自己的微博的功能
		//显示自己微博数量的功能
		//显示自己的所有微博功能
		//微博转发功能
		String action = request.getParameter("action");
		//实现插入微博功能
		if("insertweibo".equals(action)) {
			insertweibo(request,response);
			//实现登陆功能
		}else if("deleteweibo".equals(action)) {
			deleteweibo(request,response);
			//显示自己所有的微博
		}else if("allweibo".equals(action)) {
			allweibo(request,response);
			//显示自己微博数量的功能
		}else if("countweibo".equals(action)) {
			countweibo(request,response);
		}else if("forwardweibo".equals(action)) {
			forwardweibo(request,response);
		}
	}
	
	/**
	 * 转发微博的功能
	 * @param request
	 * @param response
	 */
	private void forwardweibo(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	/**自己的微博数量
	 * @param request
	 * @param response
	 */
	private void countweibo(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	/**
	 * 自己的所有微博
	 * @param request
	 * @param response
	 */
	private void allweibo(HttpServletRequest request,HttpServletResponse response) {
		
	}

	/**
	 * 删除微博
	 * @param request
	 * @param response
	 */
	private void deleteweibo(HttpServletRequest request,HttpServletResponse response) {
		
		
	}

	/**
	 * 插入微博的功能
	 * @param request
	 * @param response
	 */
	private void insertweibo(HttpServletRequest request,HttpServletResponse response) {
		
	}
}
