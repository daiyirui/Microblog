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
import com.microblog.biz.IUsersBiz;
import com.microblog.biz.impl.UsersBizImpl;
import com.microblog.po.Users;
@SuppressWarnings("serial")
public class UpdateUserServlet extends HttpServlet {

	public UpdateUserServlet() {
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
		//修改上传图片
		IUsersBiz useBiz=new UsersBizImpl();		
		String w_image=request.getParameter("upfile");		
		HttpSession session=request.getSession();
		Users use=new Users();
		if(session.getAttribute("userinfo")!=null){
		   	use=(Users) session.getAttribute("userinfo");
		}	   
		//上传操作		
		try {
			FileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload fileload=new ServletFileUpload(factory);
			//设置文件大小，4m
			fileload.setSizeMax(4194304);
			List<FileItem> iteraor=fileload.parseRequest(request);
			Iterator<FileItem> iter=iteraor.iterator();			
			while (iter.hasNext()) {
				FileItem item=iter.next();
				if(item.isFormField()){					
				}else{
					//获取文件名，包含上传文件路径
					String filename=item.getName();
					if(filename!=""){
						File file=new File(filename);
						File filetoserver=new File(this.getServletContext().getRealPath("/upload/pic"),file.getName());
						item.write(filetoserver);
						w_image=request.getContextPath()+"/upload/pic/"+filename.substring(filename.lastIndexOf("\\")+1);						
						use.setUpic(w_image);						
						if(useBiz.UpdateUser(use)){
							session.setAttribute("userinfo", use);
							response.getWriter().printf("<script>alert('修改个人信息成功!');location.href='home.jsp'</script>");
						}else{
							response.getWriter().printf("<script>alert('修改个人信息失败!');location.href='myface.jsp'</script>");
						}
					}else{
						use.setUpic(null);			
						if(useBiz.UpdateUser(use)){
							session.setAttribute("userinfo", use);							
							response.getWriter().printf("<script>alert('修改个人信息成功!');location.href='home.jsp'</script>");
						}else{
							response.getWriter().printf("<script>alert('修改个人信息失败!');location.href='myface.jsp'</script>");
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
