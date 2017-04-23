package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

public class BallhotServlet extends HttpServlet {
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
		//所有热议话题展示
		if("allBollhot".equals(action)) {
			allBollhot(request,response);
			//对热议话题进行投票
		}else if("viotBollhot".equals(action)){
			viotBollhot(request,response);
		}
	
   }

	/**
	 * 实现对热议话题进行投票的功能
	 * @param request
	 * @param response
	 */
	private void viotBollhot(HttpServletRequest request,
			HttpServletResponse response) {
		String radio = request.getParameter("tennis");
		String bid = radio.split("\\/")[1];
		String item = radio.split("\\/")[0].replace("\\", "");
		System.out.println("bid:"+bid);
		System.out.println("item:"+item);
		IBollhotDao bollhotDao = new BollhotDaoImpl();
		
		
		
	}

	/**
	 * 展示所有的热议话题
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void allBollhot(HttpServletRequest request,
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
}
