package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.IRelationsBiz;
import com.microblog.biz.IUsersBiz;
import com.microblog.biz.impl.RelationsBizImpl;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

import sun.rmi.server.Dispatcher;

@SuppressWarnings("serial")
public class InsertAttentionServlet extends HttpServlet {

	public InsertAttentionServlet() {
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
		IRelationsBiz relBiz=new RelationsBizImpl();
		HttpSession session=request.getSession();
		Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");			
		}
		int gid=Integer.parseInt(request.getParameter("gid"));
		System.out.println("gid:"+gid);
		System.out.println("use:"+use.getUid());
		if(relBiz.InsertRelation(use.getUid(), gid)){
			//��ʾ��¼��Ҫ��ע�˵���Ϣ-��һ�ε�½ֻ��ʾǰ�˸�İ������
			IRelationsBiz relationBiz=new RelationsBizImpl();
			IUsersBiz userBiz=new UsersBizImpl();
			List<Users> MyInterestUsers= relationBiz.FindAllMyInterestByuid(use.getUid());
			List<Users> listAllUser=new ArrayList<Users>();//ȫ��İ��������Ϣ
			List<Users> listUser=new ArrayList<Users>();//��ʾǰ8��İ��������Ϣ
			if(session.getAttribute("userAllList")==null){
				listAllUser=userBiz.SelectByInterest(use.getUid());
				listAllUser.removeAll(MyInterestUsers);
				for (int i = 0; i < 8; i++) {
					listUser.add(listAllUser.get(i));		
				}
			}else{
				listAllUser=(List<Users>) session.getAttribute("userAllList");
				listAllUser.removeAll(MyInterestUsers);
				for (int i = 0; i < 8; i++) {
					listUser.add(listAllUser.get(i));		
				}
			}
			session.setAttribute("userAllList", listAllUser);
			if(listUser!=null){
				session.setAttribute("userList",listUser);	
			}			
			////��ʾ����ע������
			int countRlat=relBiz.CountByAttention(use.getUid());
			session.setAttribute("countRlation",countRlat);
			//��ʾ�Ѿ���ע�˵���Ϣ
			PageBean pb=new PageBean();
			IUsersBiz useBiz=new UsersBizImpl();
			//�����ҳ����
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=useBiz.SelectByOverInterest(use.getUid(), nowpage, pagesize);
			session.setAttribute("AttentionUsers",pb);
			request.getRequestDispatcher("HomeServlet").forward(request,response);

		}else{
			response.getWriter().printf("<script>alert('��Ӻ��ѹ�עʧ��!');location.href='home.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
