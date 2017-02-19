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
import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.RelationsBizImpl;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	public RegisterServlet() {
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
		String sheng=request.getParameter("selectProvinceArray").trim();
		String city=request.getParameter("selectCityArray").trim();
		String uname=request.getParameter("uname").trim();
		String upwd=request.getParameter("upwd").trim();
		String sex=request.getParameter("usex").trim();
		String unickname=request.getParameter("unickname").trim();
		Users use=new Users();
		HttpSession session=request.getSession();
		use.setUname(uname);
		use.setUpwd(upwd);
		use.setUaddress(sheng+"-"+city);
		use.setUsex(sex);
		use.setUnickname(unickname);
		IUsersBiz useBiz=new UsersBizImpl();
		if(useBiz.RegisterUser(use)){
			//֤���û���¼��ֱ����ת������ҳ�� home
			if(request.getParameter("sel")!=null){
				//��ȡ��ע���û�������ϸ
				use=useBiz.SelectByObject(uname, upwd, sex);				
				session.setAttribute("userinfo", use);
				//��ȡ��¼�߼����ע΢����ϸ===============
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
					listAllUser=useBiz.SelectByInterest(use.getUid());
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
				//��ת������ҳ
				response.getWriter().printf("<script>alert('�û�"+uname+"ע��ɹ�!');location.href='home.jsp'</script>");
			}else{
				response.getWriter().printf("<script>alert('�û�"+uname+"ע��ɹ�!');location.href='login.jsp'</script>");
			}
		}else{
			response.getWriter().printf("<script>alert('�û�ע��ʧ��!');location.href='register.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
