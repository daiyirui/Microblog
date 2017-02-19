package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.biz.ICollectionBiz;
import com.microblog.biz.IRelationsBiz;
import com.microblog.biz.IUsersBiz;
import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.CollectionBizImpl;
import com.microblog.biz.impl.RelationsBizImpl;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class MyCollectionServlet extends HttpServlet {

	public MyCollectionServlet() {
		super();
	}
	
	@Override
	public void destroy() {
		super.destroy(); 
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          this.doPost(request, response);
	}
	@Override
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");
		}
		//step3 ����û���������ȷ
		if(use!=null){
			session.setAttribute("userinfo", use);
			//��ʾ��¼���ղص�΢����Ϣ-��ҳ��ʾ
			ICollectionBiz collectionBiz=new CollectionBizImpl();
			PageBean pb=new PageBean();			
			//�����ҳ����
			int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
			int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
			pb=collectionBiz.SelectCollectionByPage(use.getUid(),nowpage, pagesize);
			session.setAttribute("weiboList",pb);
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
			IWeiboBiz weiboBiz=new WeiboBizImpl();
			//΢������
			int countMicroblog=weiboBiz.CountByMicroblog(use.getUid());
			session.setAttribute("countBlog",countMicroblog);
			//��ʾ����ע������
			int countRlat=relationBiz.CountByAttention(use.getUid());
			session.setAttribute("countRlation",countRlat);
			//��ʾ��˿����
			int countVeri=relationBiz.CountByVermicelli(use.getUid());
			session.setAttribute("countVeri",countVeri);
			//step5 ��ת��������ҳ��
			response.sendRedirect("collection.jsp");
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
