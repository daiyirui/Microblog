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
import com.microblog.biz.impl.BollhotBizImpl;
import com.microblog.po.Bloghot;

@SuppressWarnings("serial")
public class BallhotShowServlet extends HttpServlet {

	public BallhotShowServlet() {
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
        IBollhotBiz bolBiz=new BollhotBizImpl();
        List<Bloghot> bol=bolBiz.SelectByHot();
        List<Bloghot> bol1=new ArrayList<Bloghot>();
        List<Bloghot> bol2=new ArrayList<Bloghot>();
        String t1="1",t2="1";
        //循环遍历出两个Title
        for (int j = 0; j < bol.size(); j++) {
			if(t1!=bol.get(j).getBtitle()){
				if(t1=="1"){
				   t1=bol.get(j).getBtitle();	
				}else{
					t2=bol.get(j).getBtitle();
				}
			}
		}
        //循环遍历两个Items加载集合
        for (int i = 0; i < bol.size(); i++) {
        	if(t1.equals(bol.get(i).getBtitle())){        	
        		bol1.add(bol.get(i));
        	}else if(t2.equals(bol.get(i).getBtitle())){
        		bol2.add(bol.get(i));
        	}
        }
        HttpSession session=request.getSession();
        session.setAttribute("title1", t1);
        session.setAttribute("title2", t2);
        session.setAttribute("bol1", bol1);
        session.setAttribute("bol2", bol2);
        response.sendRedirect("ballot.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
