package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

public class UserServlet extends HttpServlet {
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
		//实现换一换功能
		 String action = request.getParameter("action");
		 if("userChange".equals(action)) {
			 userChange(request,response);
			//个人基本资料的修改
		 }else if("modifyUserInfo".equals(action)) {
			 modifyUserInfo(request,response);
			 //跳转到个人信息修改页面
		 }else if("shiftUserInfo".equals(action)) {
			 shiftUserInfo(request,response);
			 //跳转到密码修改页面
		 }else if("shiftPassword".equals(action)) {
			 shiftPassword(request,response);
			//跳转到更换头像页面
		 }else if("shiftChangeFace".equals(action)){
			 shiftChangeFace(request,response);
			 //修改密码功能实现
		 }else if("modifyPassword".equals(action)) {
			 modifyPassword(request,response);
			 //更换头像功能
		 }else if("changeFace".equals(action)) {
			 changeFace(request,response);
		 }
		 
		 
	}

	/**
	 * 更换头像功能实现
	 * @param request
	 * @param response
	 */
	private void changeFace(HttpServletRequest request,
			HttpServletResponse response) {
	}
	/**
	 * 修改密码功能实现
	 * @param request
	 * @param response
	 */
	private void modifyPassword(HttpServletRequest request,
			HttpServletResponse response) {
	}
	/**
	 * 跳转到头像更换页面
	 * @param request
	 * @param response
	 */
	private void shiftChangeFace(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * 跳转到用户密码修改页面
	 * @param request
	 * @param response
	 */
	private void shiftPassword(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * 跳转到用户信息修改页面
	 * @param request
	 * @param response
	 */
	private void shiftUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * 修改个人基本资料
	 * @param request
	 * @param response
	 */
	private void modifyUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * 换一换功能
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void userChange(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if("1".equals(page)) {
			//点击了首页里面的换一换
			firstPagechange(request,response);
		}else if("2".equals(page)) {
			//点击了我的微博页面的换一换
			secondPagechange(request,response);
		}else if("3".equals(page)) {
			//点击了我的收藏页面里的换一换
		}else if("4".equals(page)) {
			//点击了微博热议页面的换一换功能
		}
	}

	private void secondPagechange(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	private void firstPagechange(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    String uid = request.getParameter("uid");
	        IWeiboDao weibodao = new WeiboDaoImpl();
	        List<Weibo> weibos= weibodao.FindByLogin(Integer.parseInt(uid));
	        System.out.println("weibos:"+weibos);
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
			//显示登录者要关注人的信息-第一次登陆只显示前八个陌生朋友
			List<Users> listAllUser=new ArrayList<Users>();//全部陌生朋友信息
			List<Users> listUser=new ArrayList<Users>();//显示前8个陌生朋友信息
			listAllUser=userdao.FindByInterest(user.getUid());
			listAllUser.remove(interests);
			Collections.shuffle(listAllUser);
			for (int i = 0; i < 8; i++) {
				listUser.add(listAllUser.get(i));		
			}
			Collections.shuffle(listUser);
			request.setAttribute("userAllList", listAllUser);
			if(listUser!=null){
				request.setAttribute("userList",listUser);	
			}			
			//微博数量
			int countMicroblog=weibodao.CountByMicroblog(user.getUid());
			request.setAttribute("countBlog",countMicroblog);
			
			request.getRequestDispatcher("./home.jsp").forward(request, response);
	}
}
