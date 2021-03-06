package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.IBollhotDao;
import com.microblog.dao.ICollectionDao;
import com.microblog.dao.IRelationsDao;
import com.microblog.dao.IUserDao;
import com.microblog.dao.IWeiboDao;
import com.microblog.dao.impl.BollhotDaoImpl;
import com.microblog.dao.impl.CollectionDaoImpl;
import com.microblog.dao.impl.RelationsDaoImpl;
import com.microblog.dao.impl.UserDaoImpl;
import com.microblog.dao.impl.WeiboDaoImpl;
import com.microblog.po.Bloghot;
import com.microblog.po.Collection;
import com.microblog.po.Users;
import com.microblog.po.Weibo;

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
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showFans(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    String uid = request.getParameter("uid");
	        IWeiboDao weibodao = new WeiboDaoImpl();
		    IUserDao userdao = new UserDaoImpl();
		    IRelationsDao relationsDao = new RelationsDaoImpl();
		    List<Users> fansList =relationsDao.FindAllMyFansByuid(Integer.parseInt(uid));
		    request.setAttribute("fansList", fansList);
		    System.out.println("fansList:"+fansList);
		    Users user = userdao.FindByuid(Integer.parseInt(uid));
	        request.setAttribute("user", user);
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
			
			request.getRequestDispatcher("./myFans.jsp").forward(request, response);
	}

	/**
	 * 显示自己关注的人
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
        IWeiboDao weibodao = new WeiboDaoImpl();
	    IUserDao userdao = new UserDaoImpl();
	    IRelationsDao relationsDao = new RelationsDaoImpl();
	    List<Users> overInterestList =relationsDao.FindAllMyInterestByuid(Integer.parseInt(uid));
	    request.setAttribute("overInterestList", overInterestList);
	    System.out.println("overInterestList:"+overInterestList);
	    Users user = userdao.FindByuid(Integer.parseInt(uid));
        request.setAttribute("user", user);
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
		
		request.getRequestDispatcher("./myFollow.jsp").forward(request, response);
	}

	/**
	 * 取消关注对方
	 * @param request
	 * @param response
	 */
	private void cancelGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//取消关注功能只出现在了两个页面   一个是我的关注者页面   另一个是我的粉丝页面
		String page = request.getParameter("page");
		 if("1".equals(page)) {
		 	onecancelGuanzhu(request,response);
		 }else if("2".equals(page)){
		 	twocancelGuanzhu(request,response);
		 }
	}
	
	private void twocancelGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		   String uid = request.getParameter("uid");
		   String gid = request.getParameter("gid");
		   
		   //实现关注部分代码
		    IRelationsDao relationdao = new RelationsDaoImpl();
		    relationdao.DeleteRelationByuid(Integer.parseInt(uid), Integer.parseInt(gid));
		    showFans(request,response);
	}

	private void onecancelGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		   String uid = request.getParameter("uid");
		   String gid = request.getParameter("gid");
		   
		   //实现关注部分代码
		    IRelationsDao relationdao = new RelationsDaoImpl();
		    relationdao.DeleteRelationByuid(Integer.parseInt(uid), Integer.parseInt(gid));
		    showGuanzhu(request,response);
	}

	/**
	 * 关注对方
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void guanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    String page = request.getParameter("page");
		    if("1".equals(page)) {
		    	firstGuanzhu(request,response);
		    }else if("2".equals(page)) {
		    	secondGuanzhu(request,response);
		    }else if("3".equals(page)) {
		    	threeGuanzhu(request,response);
		    }else if("4".equals(page)) {
		    	fourGuanzhu(request,response);
		    }else if("5".equals(page)) {
		    	fiveGuanzhu(request,response);
		    }else if("6".equals(page)) {
		    	sixGuanzhu(request,response);
		    }else if("7".equals(page)) {
		    	sevenGuanzhu(request,response);
		    }
	}

	private void sevenGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    String uid = request.getParameter("uid");
		    String gid = request.getParameter("gid");
		    //实现关注部分代码
		    IRelationsDao relationdao = new RelationsDaoImpl();
		    relationdao.InsertRelation(Integer.parseInt(uid), Integer.parseInt(gid));
		    
		    showFans(request,response);
	}

	private void sixGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    String uid = request.getParameter("uid");
	    String gid = request.getParameter("gid");
	    //实现关注部分代码
	    IRelationsDao relationdao = new RelationsDaoImpl();
	    relationdao.InsertRelation(Integer.parseInt(uid), Integer.parseInt(gid));
	    
        IWeiboDao weibodao = new WeiboDaoImpl();
	    IUserDao userdao = new UserDaoImpl();
	    IRelationsDao relationsDao = new RelationsDaoImpl();
	    List<Users> fansList =relationsDao.FindAllMyFansByuid(Integer.parseInt(uid));
	    request.setAttribute("fansList", fansList);
	    System.out.println("fansList:"+fansList);
	    Users user = userdao.FindByuid(Integer.parseInt(uid));
        request.setAttribute("user", user);
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
		request.setAttribute("userAllList", listAllUser);
		if(listUser!=null){
			request.setAttribute("userList",listUser);	
		}			
		//微博数量
		int countMicroblog=weibodao.CountByMicroblog(user.getUid());
		request.setAttribute("countBlog",countMicroblog);
		
		request.getRequestDispatcher("./myFans.jsp").forward(request, response);
	}

	private void fiveGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		 String gid = request.getParameter("gid");
		    //实现关注部分代码
		 IRelationsDao relationdao = new RelationsDaoImpl();
		 relationdao.InsertRelation(Integer.parseInt(uid), Integer.parseInt(gid));
        IWeiboDao weibodao = new WeiboDaoImpl();
	    IUserDao userdao = new UserDaoImpl();
	    IRelationsDao relationsDao = new RelationsDaoImpl();
	    List<Users> overInterestList =relationsDao.FindAllMyInterestByuid(Integer.parseInt(uid));
	    request.setAttribute("overInterestList", overInterestList);
	    System.out.println("overInterestList:"+overInterestList);
	    Users user = userdao.FindByuid(Integer.parseInt(uid));
        request.setAttribute("user", user);
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
		
		request.getRequestDispatcher("./myFollow.jsp").forward(request, response);
	}

	private void fourGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取收藏的所有微博
	    String uid = request.getParameter("uid");
	    String gid = request.getParameter("gid");
	    //实现关注部分代码
	    IRelationsDao relationdao = new RelationsDaoImpl();
	    relationdao.InsertRelation(Integer.parseInt(uid), Integer.parseInt(gid));
	    
	    IWeiboDao weibodao = new WeiboDaoImpl();
		IBollhotDao  bloghotdao= new BollhotDaoImpl();
		List<Bloghot> bloghots = bloghotdao.FindAllHot();

		
        System.out.println("bloghots:"+bloghots);
	    IUserDao userdao = new UserDaoImpl();
	    Users user = userdao.FindByuid(Integer.parseInt(uid));
        request.setAttribute("user", user);
        request.setAttribute("bloghots",bloghots);
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
		
		request.getRequestDispatcher("./ballot.jsp").forward(request, response);
		
	}

	private void threeGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取收藏的所有微博
	    String uid = request.getParameter("uid");
	    String gid = request.getParameter("gid");
	    //实现关注部分代码
	    IRelationsDao relationdao = new RelationsDaoImpl();
	    relationdao.InsertRelation(Integer.parseInt(uid), Integer.parseInt(gid));
	    
	    IWeiboDao weibodao = new WeiboDaoImpl();
		ICollectionDao collectionDao = new CollectionDaoImpl();
		List<Collection> collections = collectionDao.FindCollectionByuid(Integer.parseInt(uid));

		
        System.out.println("collections"+collections);
	    IUserDao userdao = new UserDaoImpl();
	    Users user = userdao.FindByuid(Integer.parseInt(uid));
        request.setAttribute("user", user);
        request.setAttribute("collections",collections);
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
		
		request.getRequestDispatcher("./collection.jsp").forward(request, response);
	}

	private void secondGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    	 String uid = request.getParameter("uid");	
		  String gid = request.getParameter("gid");
	     IWeiboDao weibodao = new WeiboDaoImpl();
	     List<Weibo> weibos= weibodao.FindWeiboByuid(Integer.parseInt(uid));
	     IUserDao userdao = new UserDaoImpl();
	     Users user = userdao.FindByuid(Integer.parseInt(uid));
	     request.setAttribute("user", user);
	     request.setAttribute("weibos",weibos);
	     
	      //实现关注部分代码
		    IRelationsDao relationdao = new RelationsDaoImpl();
		    relationdao.InsertRelation(Integer.parseInt(uid), Integer.parseInt(gid));
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

	private void firstGuanzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    String uid = request.getParameter("uid");
		    String gid = request.getParameter("gid");
		    //实现关注部分代码
		    IRelationsDao relationdao = new RelationsDaoImpl();
		    relationdao.InsertRelation(Integer.parseInt(uid), Integer.parseInt(gid));
		    
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
}
