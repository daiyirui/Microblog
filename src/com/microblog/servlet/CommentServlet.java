package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.microblog.dao.ICommentDao;
import com.microblog.dao.IRelationsDao;
import com.microblog.dao.IUserDao;
import com.microblog.dao.IWeiboDao;
import com.microblog.dao.impl.CommentDaoImpl;
import com.microblog.dao.impl.RelationsDaoImpl;
import com.microblog.dao.impl.UserDaoImpl;
import com.microblog.dao.impl.WeiboDaoImpl;
import com.microblog.po.Comment;
import com.microblog.po.Users;
import com.microblog.po.Weibo;

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
	    	   try {
				insertComment(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	       //删除评论功能
	       }else if("deleteComment".equals(action)) {
	    	   deleteComment(request,response);
	       }
	}
	private void deleteComment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    String uid = request.getParameter("uid");
		    String cid = request.getParameter("cid");
		    //实现删除功能
		    ICommentDao commentdao =new CommentDaoImpl();
		    commentdao.DeleteComment(Integer.parseInt(cid));
		    
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
	
	
	private void insertComment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		   String uid = request.getParameter("uid");
		    String wid = request.getParameter("wid");
		    String cid = request.getParameter("cid");
		    String commenttext=request.getParameter("commenttext");
		    //实现插入评论功能
		   
		    ICommentDao commentdao =new CommentDaoImpl();
		    
		    Comment comm = new Comment();
		    if(cid!=null) {
		       comm.setC_cid(Integer.parseInt(cid));
		     }else{
		       comm.setC_cid(0);
		     }
		    comm.setC_wid(Integer.parseInt(wid));
		    comm.setC_uid(Integer.parseInt(uid));
		    comm.setCcontent(commenttext);
			commentdao.InsertComment(comm);
		    
		    
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
