package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.IRelationsBiz;
import com.microblog.biz.IUsersBiz;
import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.RelationsBizImpl;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public LoginServlet() {
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
		//step1:��ȡҳ���ύ��ֵ
		String usn=request.getParameter("usn").trim();
		String pwd=request.getParameter("pwd").trim();
		//Cookie
		String keep=request.getParameter("keep");
		if(keep!=null&&keep.equals("on")){
			System.out.println("Keep "+keep);
			//�����û���
			Cookie cookie=new Cookie("usn",usn);
			cookie.setPath("/");
			cookie.setMaxAge(24*60*60);
			response.addCookie(cookie);
			//��������
			cookie=new Cookie("pwd",pwd);
			cookie.setPath("/");
			cookie.setMaxAge(24*60*60);
			response.addCookie(cookie);
		}
		//step2:ͨ��biz����֤
		IUsersBiz userBiz=new UsersBizImpl();
		Users use=new Users();
		use=userBiz.UserLogin(usn, pwd);
		//step3 ����û���������ȷ
		if(use!=null){
			//step4 ��¼��Ĳ�������ʾ��¼��username
			HttpSession session=request.getSession();
			session.setAttribute("userinfo", use);
			//��ʾ��¼�ߺ�������ע�˵�΢����Ϣ-��ҳ��ʾ
			IWeiboBiz weiboBiz=new WeiboBizImpl();
			PageBean pb=new PageBean();			
			//�����ҳ����
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=weiboBiz.SelectByPage(use.getUid(),nowpage, pagesize);
			session.setAttribute("weiboList",pb);
			//��ʾ��¼��Ҫ��ע�˵���Ϣ-��һ�ε�½ֻ��ʾǰ�˸�İ������
			List<Users> listAllUser=new ArrayList<Users>();//ȫ��İ��������Ϣ
			List<Users> listUser=new ArrayList<Users>();//��ʾǰ8��İ��������Ϣ
			if(session.getAttribute("userAllList")==null){
				listAllUser=userBiz.SelectByInterest(use.getUid());
				for (int i = 0; i < 8; i++) {
					listUser.add(listAllUser.get(i));		
				}
			}else{
				listAllUser=(List<Users>) session.getAttribute("userAllList");
				for (int i = 0; i < 8; i++) {
					listUser.add(listAllUser.get(i));		
				}
			}
			session.setAttribute("userAllList", listAllUser);
			if(listUser!=null){
				session.setAttribute("userList",listUser);	
			}			
			//΢������
			int countMicroblog=weiboBiz.CountByMicroblog(use.getUid());
			session.setAttribute("countBlog",countMicroblog);
			//��ʾ����ע������
			IRelationsBiz relationBiz=new RelationsBizImpl();
			int countRlat=relationBiz.CountByAttention(use.getUid());
			session.setAttribute("countRlation",countRlat);
			//��ʾ��˿����
			int countVeri=relationBiz.CountByVermicelli(use.getUid());
			session.setAttribute("countVeri",countVeri);
			//step5 ��ת��������ҳ��
			response.sendRedirect("home.jsp");
		}
		//step6 ���󷵻ص�¼ҳ��
		else{
			 
			response.getWriter().printf("<script>alert('�û������������\n��ȡ���û�������!');location.href='login.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
