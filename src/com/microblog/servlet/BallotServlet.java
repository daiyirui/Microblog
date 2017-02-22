package com.microblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.biz.IBollhotBiz;
import com.microblog.biz.impl.BollhotBizImpl;
import com.microblog.po.Bloghot;

@SuppressWarnings("serial")
public class BallotServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             this.doPost(request, response);	 
          }
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		
			String action = request.getParameter("action");
			if("0".equals(action)) {
				indexBallot(request,response);
			}else {}
			
		}catch (Exception e) {
		}
		
	}

	  private void indexBallot(HttpServletRequest request, HttpServletResponse response) {
		  try{
			  IBollhotBiz bolBiz=new BollhotBizImpl();
			  List<Bloghot> bloghots = bolBiz.SelectByHot();
			  request.setAttribute("bloghots", bloghots);
		      request.getRequestDispatcher("./index_ballot.jsp").forward(request, response);
			}catch (Exception e) {
			
			}
	}

}
