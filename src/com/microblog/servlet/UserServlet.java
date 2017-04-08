package com.microblog.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
			 try {
				changeFace(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
	}

	/**
	 * 更换头像功能实现
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void changeFace(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String uid = request.getParameter("uid");
		IUserDao userdao = new UserDaoImpl();
		Users user = userdao.FindByuid(Integer.parseInt(uid));
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload fileload=new ServletFileUpload(factory);
		//设置文件大小，4m
		fileload.setSizeMax(4194304);
		List<FileItem> iteraor=fileload.parseRequest(request);
		Iterator<FileItem> iter=iteraor.iterator();
		while (iter.hasNext()) {
			FileItem item=iter.next();
				//获取文件名，包含上传文件路径
				String filename=item.getName();
				if(filename!=""){
					File file=new File(filename);
					File filetoserver=new File(this.getServletContext().getRealPath("/face"),file.getName());
					item.write(filetoserver);
				    String image=request.getContextPath()+"/face/"+filename.substring(filename.lastIndexOf("\\")+1);
				    user.setUpic(image);
				}
			}
		userdao.changeFace(user);
		
		shiftChangeFace(request,response);
	}
		
		
		
	/**
	 * 修改密码功能实现
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void modifyPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 String uid = request.getParameter("uid");
		 String upwd = request.getParameter("upwd");
		 IUserDao userdao = new UserDaoImpl();
	     Users user = userdao.FindByuid(Integer.parseInt(uid));
	     user.setUpwd(upwd);
	     if(userdao.changePassword(user)>0) {
	    	 request.setAttribute("success", 1);
	    	 request.getRequestDispatcher("login.jsp").forward(request, response);
	     }else {
	    	 request.setAttribute("fail", "暂时不能修改密码");
	    	 request.setAttribute("user", user);
	  		 request.getRequestDispatcher("mypassword.jsp").forward(request, response);
	     }
	  
	}
	/**
	 * 跳转到头像更换页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void shiftChangeFace(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  String uid = request.getParameter("uid");
		  IUserDao userdao = new UserDaoImpl();
		  Users user = userdao.FindByuid(Integer.parseInt(uid));
	      request.setAttribute("user", user);
		  request.getRequestDispatcher("myface.jsp").forward(request, response);
	}
	/**
	 * 跳转到用户密码修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void shiftPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  String uid = request.getParameter("uid");
		  IUserDao userdao = new UserDaoImpl();
		  Users user = userdao.FindByuid(Integer.parseInt(uid));
	      request.setAttribute("user", user);
		  request.getRequestDispatcher("mypassword.jsp").forward(request, response);
		
	}
	/**
	 * 跳转到用户信息修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void shiftUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  String uid = request.getParameter("uid");
		  IUserDao userdao = new UserDaoImpl();
		  Users user = userdao.FindByuid(Integer.parseInt(uid));
	      request.setAttribute("user", user);
		  request.getRequestDispatcher("userinfo.jsp").forward(request, response);
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
			threePagechange(request,response);
		}else if("4".equals(page)) {
			//点击了微博热议页面的换一换功能
			fourPagechange(request,response);
		}else if("5".equals(page)) {
			//点击了我的关注页面的换一换功能
			fivePagechange(request,response);
		}else if("6".equals(page)) {
			//点击了我的粉丝页面的换一换功能
			sixPagechange(request,response);
		}
	}

	private void sixPagechange(HttpServletRequest request,
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
		
		request.getRequestDispatcher("./myFans.jsp").forward(request, response);
	}
	
	private void fivePagechange(HttpServletRequest request,
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
		
		request.getRequestDispatcher("./myFollow.jsp").forward(request, response);
	}
	
	private void fourPagechange(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取收藏的所有微博
	    String uid = request.getParameter("uid");
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
		
		request.getRequestDispatcher("./ballot.jsp").forward(request, response);
		
	}
	private void threePagechange(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取收藏的所有微博
	    String uid = request.getParameter("uid");
	    IWeiboDao weibodao = new WeiboDaoImpl();
		ICollectionDao collectionDao = new CollectionDaoImpl();
		List<Collection> collections = collectionDao.FindCollectionByuid(Integer.parseInt(uid));

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
		
		request.getRequestDispatcher("./collection.jsp").forward(request, response);
		
	}
	
	private void secondPagechange(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
	    
	    request.getRequestDispatcher("profile.jsp").forward(request, response);
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
