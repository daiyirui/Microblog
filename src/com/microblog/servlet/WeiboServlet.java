package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.IRelationsDao;
import com.microblog.dao.IUserDao;
import com.microblog.dao.IWeiboDao;
import com.microblog.dao.impl.RelationsDaoImpl;
import com.microblog.dao.impl.UserDaoImpl;
import com.microblog.dao.impl.WeiboDaoImpl;
import com.microblog.po.Users;
import com.microblog.po.Weibo;

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
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void allweibo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	      String uid = request.getParameter("uid");	
	     IWeiboDao weibodao = new WeiboDaoImpl();
	     List<Weibo> weibos= weibodao.FindWeiboByuid(Integer.parseInt(uid));
	     IUserDao userdao = new UserDaoImpl();
	     Users user = userdao.FindByuid(Integer.parseInt(uid));
	     request.setAttribute("user", user);
	     request.setAttribute("weibos",weibos);
	        //共同的代码块
	     	//显示所关注人数量
			IRelationsDao relationBiz=new RelationsDaoImpl();
			int countRlat=relationBiz.CountByAttention(user.getUid());
			request.setAttribute("countRlation",countRlat);
			//显示粉丝数量
			int countVeri=relationBiz.CountByVermicelli(user.getUid());
			request.setAttribute("countVeri",countVeri);
			//自己已经关注成功的人
			List<Users> interests = relationBiz.FindAllMyInterestByuid(user.getUid());
			System.out.println("我关注的人有:"+interests);
			//显示登录者要关注人的信息-第一次登陆只显示前八个陌生朋友
			List<Users> listAllUser=new ArrayList<Users>();//全部陌生朋友信息
			List<Users> listUser=new ArrayList<Users>();//显示前8个陌生朋友信息
			listAllUser=userdao.FindByInterest(user.getUid());
			listAllUser.remove(interests);
			for (int i = 0; i < 8; i++) {
				listUser.add(listAllUser.get(i));		
			}
			
			request.setAttribute("userAllList", listAllUser);
			if(listUser!=null){
				request.setAttribute("userList",listUser);	
			}			
			//微博数量
			int countMicroblog=weibodao.CountByMicroblog(user.getUid());
			request.setAttribute("countBlog",countMicroblog);
	     
	     request.getRequestDispatcher("profile.jsp").forward(request, response);
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
