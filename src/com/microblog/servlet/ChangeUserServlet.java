package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.IBollhotBiz;
import com.microblog.biz.ICollectionBiz;
import com.microblog.biz.IUsersBiz;
import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.BollhotBizImpl;
import com.microblog.biz.impl.CollectionBizImpl;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class ChangeUserServlet extends HttpServlet {

	public ChangeUserServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		IUsersBiz userBiz=new UsersBizImpl();
		//获取登陆人信息
		Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");
		}
		//换一换要关注人信息
		List<Users> listAllUser=(List<Users>) session.getAttribute("userAllList");
		List<Users> listUser=new ArrayList<Users>();
		if(listAllUser==null){
			listAllUser=userBiz.SelectByInterest(use.getUid());
			for(int i=0;i<8;i++){
				listUser.add(listAllUser.get(i));
			}
		}else if(((List<Users>)session.getAttribute("userAllList")).size()>=8){
			listAllUser=(List<Users>) session.getAttribute("userAllList");
			if(session.getAttribute("userList")==null){
				for (int i = 0; i < 8; i++) {
					listUser.add(listAllUser.get(i));
				}
			}else if(((List<Users>)session.getAttribute("userList")).size()<=8){
				////核心，要随机产生8个数字，且要全在全部用户的uid中，但不应该在已经存在的listUser内的uid中
				listUser=this.RandomList(listAllUser, (List<Users>)session.getAttribute("userList"));
			}
		}else{
			//全部要关注的人的信息小于8个，也就是说一个页面会由空缺
			listAllUser=(List<Users>) session.getAttribute("userAllList");
			for (int i = 0; i < listAllUser.size(); i++) {
				listUser.add(listAllUser.get(i));
			}
		}
		session.setAttribute("userAllList", listAllUser);
		if(listUser!=null){
			session.setAttribute("userList",listUser);	
		}
		int change=Integer.parseInt(request.getParameter("change"));
		if(change==1){
			//显示登录者关注的信息-分页显示
			IUsersBiz usersBiz=new UsersBizImpl();
			PageBean pb=new PageBean();			
			//定义分页参数
			//定义分页参数
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=usersBiz.SelectByOverInterest(use.getUid(),nowpage, pagesize);
			session.setAttribute("OverInterestList",pb);
			response.sendRedirect("myFollow.jsp");
		}else if(change==2){
			//显示登录者和其所关注人的微博信息-分页显示
			IWeiboBiz weiboBiz=new WeiboBizImpl();
			PageBean pb=new PageBean();			
			//定义分页参数
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=weiboBiz.SelectByPage(use.getUid(),nowpage, pagesize);
			session.setAttribute("weiboList",pb);
			response.sendRedirect("home.jsp");	
		}else if(change==3){
			//显示登录者微博信息-分页显示
			IWeiboBiz weiboBiz=new WeiboBizImpl();
			PageBean pb=new PageBean();			
			//定义分页参数
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=weiboBiz.SelectByPageOnlyOwn(use.getUid(),nowpage, pagesize);
			session.setAttribute("weiboList",pb);
			
			response.sendRedirect("profile.jsp");	
		}else if(change==4){
			//显示登录者收藏的微博信息-分页显示
			ICollectionBiz collectionBiz=new CollectionBizImpl();
			PageBean pb=new PageBean();			
			//定义分页参数
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=collectionBiz.SelectCollectionByPage(use.getUid(),nowpage, pagesize);
			session.setAttribute("weiboList",pb);
			response.sendRedirect("collection.jsp");	
		}else if(change==6){
			//显示登录者粉丝信息-分页显示
			IUsersBiz usersBiz=new UsersBizImpl();
			PageBean pb=new PageBean();			
			//定义分页参数
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=usersBiz.SelectFansByPage(use.getUid(),nowpage, pagesize);
			session.setAttribute("fansList",pb);
			response.sendRedirect("myFans.jsp");	
		}else if(change==5){
			String tennis=null;
			if(request.getParameter("tennis")!=null){
				tennis=request.getParameter("tennis").trim();
				IBollhotBiz bolBiz=new BollhotBizImpl();
				if(bolBiz.VoitHot(tennis)){
					response.getWriter().printf("<script>alert('"+tennis+"投票成功!');location.href='ballot.jsp'</script>");
				}else{
					response.getWriter().printf("<script>alert('投票失败!');location.href='ballot.jsp'</script>");
				}
			}else{
				response.getWriter().printf("<script>alert('请先选择投票!');location.href='ballot.jsp'</script>");
			}		
			response.sendRedirect("ballot.jsp");	
		}	
	}
	
    public List<Users> RandomList(List<Users> listAllUser,List<Users> listUser){
    	List<Users> lisRan=new ArrayList<Users>();
    	int rd=0;//存放随机数
    	int count=0;//记录产生的随机数个数
        int uid=0;//记录用户的uid
        int flag=0;//判断是否已经生成过的标志
        while (count<8) {
			rd=(int)(Math.random()*(listAllUser.size()));
			//由随机数产生的用户uid
			uid=listAllUser.get(rd).getUid();
			for (int i = 0; i < listUser.size(); i++) {
				if(listUser.get(i).getUid()==uid){
					flag=1;
					break;
				}else{
					flag=0;
				}
			}
			if(flag==0){
				lisRan.add(listAllUser.get(rd));
				count++;
			}
		}
        if(lisRan.size()==8){
        	return lisRan;	
        }else{
        	return null;
        }
    	
    }
	public void init() throws ServletException {
		// Put your code here
	}

}
