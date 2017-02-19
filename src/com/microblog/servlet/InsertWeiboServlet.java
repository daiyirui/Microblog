package com.microblog.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.microblog.biz.IWeiboBiz;
import com.microblog.biz.impl.WeiboBizImpl;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;
import com.microblog.po.Weibo;

@SuppressWarnings("serial")
public class InsertWeiboServlet extends HttpServlet {
	
	public InsertWeiboServlet() {
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
		request.setCharacterEncoding("utf-8");//
		HttpSession session=request.getSession();
		Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
			use=(Users) session.getAttribute("userinfo");
		}
		String w_image=request.getParameter("upfile");
		IWeiboBiz weiboBiz=new WeiboBizImpl();		
		int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
		int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
		//上传操作		
		try {
			FileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload fileload=new ServletFileUpload(factory);
			//设置文件大小，4m
			fileload.setSizeMax(4194304);
			List<FileItem> iteraor=fileload.parseRequest(request);
			Iterator<FileItem> iter=iteraor.iterator();
			Weibo weibo=new Weibo();
			PageBean pb=new PageBean();
			while (iter.hasNext()) {
				FileItem item=iter.next();
				if(item.isFormField()){
					if("weibotext".equals(item.getFieldName())){
						weibo.setWcontent(item.getString("gbk"));						
					}
				}else{
					//获取文件名，包含上传文件路径
					String filename=item.getName();
					if(filename!=""){
						File file=new File(filename);
						File filetoserver=new File(this.getServletContext().getRealPath("/upload/pic"),file.getName());
						item.write(filetoserver);
						w_image=request.getContextPath()+"/upload/pic/"+filename.substring(filename.lastIndexOf("\\")+1);
						weibo.setWimage(w_image);
						if(weiboBiz.InsertWeibo(weibo, use.getUid())){
							pb=weiboBiz.SelectByPage(use.getUid(),nowpage, pagesize);
							session.setAttribute("weiboList",pb);
							//微博数量
							int countMicroblog=weiboBiz.CountByMicroblog(use.getUid());
							session.setAttribute("countBlog",countMicroblog);
							response.getWriter().printf("<script>alert('添加微博成功!');location.href='home.jsp'</script>");
						}else{
							response.getWriter().printf("<script>alert('添加微博失败!');location.href='home.jsp'</script>");
						}
					}else{
						if(weiboBiz.InsertWeibo(weibo, use.getUid())){
							pb=weiboBiz.SelectByPage(use.getUid(),nowpage, pagesize);
							session.setAttribute("weiboList",pb);
							//微博数量
							int countMicroblog=weiboBiz.CountByMicroblog(use.getUid());
							session.setAttribute("countBlog",countMicroblog);
							response.getWriter().printf("<script>alert('添加微博成功!');location.href='home.jsp'</script>");
						}else{
							response.getWriter().printf("<script>alert('添加微博失败!');location.href='home.jsp'</script>");
						}
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
