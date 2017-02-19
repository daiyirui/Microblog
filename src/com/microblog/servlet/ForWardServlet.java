package com.microblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class ForWardServlet extends HttpServlet {

	public ForWardServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Users use=new Users();
		HttpSession session=request.getSession();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");
		}
		int wid=Integer.parseInt(request.getParameter("wid"));
		String wcontent=request.getParameter("wcontent");
		String content=new String(wcontent.getBytes("ISO-8859-1"),"UTF-8");
		String wimage=request.getParameter("wimage");
		int uid=use.getUid();
		IWeiboBiz weiboBiz=new WeiboBizImpl();
		if(weiboBiz.ForWardMicroblog(uid, content, wid, wimage)){
			//��ʾ��¼�˺�������ע�˵�΢����Ϣ
			PageBean pb=new PageBean();			
			//�����ҳ����
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=weiboBiz.SelectByPage(uid,nowpage, pagesize);
			session.setAttribute("weiboList",pb);
			//΢������
			int countMicroblog=weiboBiz.CountByMicroblog(use.getUid());
			session.setAttribute("countBlog",countMicroblog);
			response.getWriter().printf("<script>alert('ת��΢���ɹ�!');location.href='home.jsp'</script>");
		}else{
			response.getWriter().printf("<script>alert('ת��΢��ʧ��!');location.href='home.jsp'</script>");
		}
		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
