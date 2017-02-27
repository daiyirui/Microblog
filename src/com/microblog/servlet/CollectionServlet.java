package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.ICollectionDao;
import com.microblog.dao.IRelationsDao;
import com.microblog.dao.IUserDao;
import com.microblog.dao.IWeiboDao;
import com.microblog.dao.impl.CollectionDaoImpl;
import com.microblog.dao.impl.RelationsDaoImpl;
import com.microblog.dao.impl.UserDaoImpl;
import com.microblog.dao.impl.WeiboDaoImpl;
import com.microblog.po.Collection;
import com.microblog.po.Users;
import com.microblog.po.Weibo;

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
		 System.out.println("action:"+action);
		 
		//收藏功能
		if("collection".equals(action)) {
			 System.out.println("action:"+action);
			collection(request,response);
		//取消收藏功能
		}else if("cancelcollection".equals(action)) {
			cancelcollection(request,response);
			//显示我所有的收藏页面
		}else if("allcollection".equals(action)) {
			allcollection(request,response);
		}
   }

	private void allcollection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取收藏的所有微博
	    String uid = request.getParameter("uid");
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

	private void cancelcollection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    //实现取消收藏功能
				String wid=request.getParameter("wid");
			    String uid = request.getParameter("uid");
			    IWeiboDao weibodao = new WeiboDaoImpl();
				ICollectionDao collectionDao = new CollectionDaoImpl();
				collectionDao.DeleteCollection(Integer.parseInt(uid), Integer.parseInt(wid));

				
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

	private void collection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//实现收藏功能
		String wid=request.getParameter("wid");
	    String uid = request.getParameter("uid");
	    IWeiboDao weibodao = new WeiboDaoImpl();
		ICollectionDao collectionDao = new CollectionDaoImpl();
		Weibo weibo = weibodao.FindBywid(Integer.parseInt(uid), Integer.parseInt(wid));
		Collection collection = new Collection();
		collection.setL_uid(Integer.parseInt(uid));
		collection.setL_wid(Integer.parseInt(wid));
		collection.setLcontent(weibo.getWcontent());
		collection.setLimages(weibo.getWimage());
		collectionDao.InsertCollection(collection);

		
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
