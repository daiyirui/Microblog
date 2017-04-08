package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.IBollhotDao;
import com.microblog.dao.IRelationsDao;
import com.microblog.dao.IUserDao;
import com.microblog.dao.IWeiboDao;
import com.microblog.dao.impl.BollhotDaoImpl;
import com.microblog.dao.impl.RelationsDaoImpl;
import com.microblog.dao.impl.UserDaoImpl;
import com.microblog.dao.impl.WeiboDaoImpl;
import com.microblog.po.Bloghot;
import com.microblog.po.Users;
import com.microblog.po.Weibo;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//游客查看单个用户信息
		//注册功能
		//登陆功能
		//其他页面跳转到主页面功能
		//退出功能
		//游客页面之间相互跳转功能
		String action = request.getParameter("action");
		System.out.println(action);
		//实现注册功能
		if("register".equals(action)) {
			register(request,response);
			//实现登陆功能
		}else if("login".equals(action)) {
			login(request,response);
			//实现注册功能
		}else if("home".equals(action)) {
			System.out.println("home");
			home(request,response);
			//实现退出功能
		}else if("exit".equals(action)) {
			exit(request,response);
		}else if("userdetail".equals(action)) {
			userdetail(request,response);
		//游客跳转到热议话题
		}else if("ballot".equals(action)) {
			ballot(request,response);
		}
	}
	/**
	 * 游客跳转到热议话题
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ballot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  IBollhotDao boldao=new BollhotDaoImpl();
		  List<Bloghot> bloghots = boldao.FindAllHot();
		  request.setAttribute("bloghots", bloghots);
	      request.getRequestDispatcher("./index_ballot.jsp").forward(request, response);
	}

	/**
	 * 游客跳转到用户信息展示页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userdetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 int uid=Integer.parseInt(request.getParameter("uid"));
		 IUserDao useBiz=new UserDaoImpl();
		 Users user=useBiz.FindByuid(uid);
		 request.setAttribute("user", user);
		 request.getRequestDispatcher("user.jsp").forward(request, response);	
	}

	/**
	 * 用户退出功能
	 * @param request
	 * @param response
	 */
	private void exit(HttpServletRequest request, HttpServletResponse response) {
		
	}
	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			request.getRequestDispatcher("./home.jsp").forward(request, response);
		
	}
	/**
	 * 用户登录功能
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   {

		String usn=request.getParameter("usn").trim();
		String pwd=request.getParameter("pwd").trim();
		IUserDao userdao=new UserDaoImpl();
		Users use=new Users();
		use=userdao.UserLoginCheck(usn, pwd);
		if(use!=null){
			request.setAttribute("user", use);
			IWeiboDao weibodao=new WeiboDaoImpl();
			List<Weibo> weibos=weibodao.FindByLogin(use.getUid());
			request.setAttribute("weibos",weibos);
			System.out.println("weibos:"+weibos);
			//显示所关注人数量
			IRelationsDao relationBiz=new RelationsDaoImpl();
			int countRlat=relationBiz.CountByAttention(use.getUid());
			request.setAttribute("countRlation",countRlat);
			//显示粉丝数量
			int countVeri=relationBiz.CountByVermicelli(use.getUid());
			request.setAttribute("countVeri",countVeri);
			//自己已经关注成功的人
			List<Users> interests = relationBiz.FindAllMyInterestByuid(use.getUid());
			System.out.println("我关注的人有:"+interests);
			//显示登录者要关注人的信息-第一次登陆只显示前八个陌生朋友
			List<Users> listAllUser=new ArrayList<Users>();//全部陌生朋友信息
			List<Users> listUser=new ArrayList<Users>();//显示前8个陌生朋友信息
			listAllUser=userdao.FindByInterest(use.getUid());
			listAllUser.remove(interests);
			for (int i = 0; i < 8; i++) {
				listUser.add(listAllUser.get(i));		
			}
			
			request.setAttribute("userAllList", listAllUser);
			if(listUser!=null){
				request.setAttribute("userList",listUser);	
			}			
			//微博数量
			int countMicroblog=weibodao.CountByMicroblog(use.getUid());
			request.setAttribute("countBlog",countMicroblog);
			//step5 跳转到个人主页面
			request.getRequestDispatcher("./home.jsp").forward(request, response);
		}
		//step6 错误返回登录页面
		else{
			request.setAttribute("flag", 1);
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		}
	
	}
	/**
	 * 用户注册功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		String urealname=request.getParameter("urealname");
		String unickname=request.getParameter("unickname");
		String uqq = request.getParameter("uqq");
		String uemail = request.getParameter("uemail");
		IUserDao usersDao = new UserDaoImpl();
		List<String> emails = usersDao.AllEmails();
		if(emails!=null&&emails.contains(uemail)) {
			request.setAttribute("emailError", 1);
			request.setAttribute("register", 1);
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return ;
		}
		String usex = request.getParameter("usex");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String uedu = request.getParameter("uedu");
		Users user = new Users();
		user.setUdate(new Date());
		user.setUname(uname);
		user.setUpwd(upwd);
		user.setUrealname(urealname);
		user.setUemail(uemail);
		user.setUaddress(province+city);
		user.setUedu(uedu);
		user.setUqq(uqq);
		user.setUsex(usex);
		user.setUnickname(unickname);
		
		int flag = usersDao.RegisterUser(user);
		
		if(flag==1) {
			request.setAttribute("register", 1);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			request.setAttribute("register", 1);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

}
