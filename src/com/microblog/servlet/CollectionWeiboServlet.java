package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.ICollectionBiz;
import com.microblog.biz.impl.CollectionBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Collection;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class CollectionWeiboServlet extends HttpServlet {

	
	public CollectionWeiboServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("utf-8");
	    //获取登陆者用户信息，添加收藏的人
	    Users use=new Users();
	    HttpSession session=request.getSession();
	    if(session.getAttribute("userinfo")!=null){
	    	use=(Users) session.getAttribute("userinfo");
	    }
	    //获取页面参数
	    String wcontent=request.getParameter("wcontent").trim();
	    String content=new String(wcontent.getBytes("ISO-8859-1"),"gbk");	  
	    String limages=request.getParameter("wimage");
	    int uid=use.getUid();
	    Collection coll=new Collection();
	    coll.setLcontent(content);
	    coll.setLimages(limages);
	    ICollectionBiz collBiz=new CollectionBizImpl();
	    if(collBiz.InsertCollection(uid, coll)){
	    	PageBean pb=new PageBean();
	    	//定义分页参数
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=collBiz.SelectCollectionByPage(uid, nowpage, pagesize);
			session.setAttribute("CollectionList",pb);
			//显示收藏微博数量
			int count=collBiz.CountCollectionByLid(uid);			 
			session.setAttribute("CountCollection", count);
			response.getWriter().printf("<script>alert('添加收藏成功!');location.href='profile.jsp'</script>");
	    }else{
	    	response.getWriter().printf("<script>alert('添加收藏错误!');location.href='home.jsp'</script>");
	    }
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
