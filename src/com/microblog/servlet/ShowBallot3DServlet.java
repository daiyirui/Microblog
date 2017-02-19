package com.microblog.servlet;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.microblog.biz.IBollhotBiz;
import com.microblog.biz.impl.BollhotBizImpl;
import com.microblog.po.Bloghot;

@SuppressWarnings("serial")
public class ShowBallot3DServlet extends HttpServlet {

	public ShowBallot3DServlet() {
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
		//�ж�ͼƬ�Ƿ���ڣ��������ɾ��
        File f=new File("F:\\Java����\\apache-tomcat-7.0.5\\webapps\\Microblog\\upload\\pic\\6c.jpg");
		if(f.isFile()){
			f.delete();	
		}		
		HttpSession session=request.getSession();
		    IBollhotBiz bolBiz=new BollhotBizImpl();
		    List<Bloghot> bol=bolBiz.SelectByHot();
	        List<Bloghot> bol2=new ArrayList<Bloghot>();	      
	        String t1="1",t2="1";
	        //ѭ������������Title
	        for (int j = 0; j < bol.size(); j++) {
				if(t1!=bol.get(j).getBtitle()){
					if(t1=="1"){
					   t1=bol.get(j).getBtitle();	
					}
					else{
					   t2=bol.get(j).getBtitle();
					}
				}
			}
	        //ѭ����������Items���ؼ���
	        for (int i = 0; i < bol.size(); i++) {
	        	if(t2.equals(bol.get(i).getBtitle())){        	
	        		bol2.add(bol.get(i));
	        	}
	        }	 
	        session.setAttribute("bol2", bol2);
	      //Jfreechart 3D��ͼ
	        DefaultPieDataset dataset=new DefaultPieDataset();
	        for (int i = 0; i < bol2.size(); i++) {
				dataset.setValue(bol2.get(i).getBitems()+":"+bol2.get(i).getBvote(), bol2.get(i).getBvote());
			}
	        JFreeChart chart;
	        chart=ChartFactory.createPieChart3D("����ϲ�������ڵ�Ӱ����Ʒ", dataset,true,false,true);
	        //��������ͼ������
	        chart.setTitle(new TextTitle("����ϲ�������ڵ�Ӱ����Ʒ",new Font("����",Font.ITALIC, 22)));
	        //ȡ��ͳ��ͼ���һ��ͼ��
	        LegendTitle legend=chart.getLegend(0);	        
	        //�޸�ͼ��������
	        legend.setItemFont(new Font("����",Font.BOLD,14));
	        //���3D��ͼ��plot����
	        PiePlot plot=(PiePlot) chart.getPlot();
	        //���ñ�ͼ�ĸ����ֱ�ǩ����
	        plot.setLabelFont(new Font("����",Font.BOLD,18));
	        //���ñ���ͼ͸���ȣ�0-1.0��֮��
	        plot.setBackgroundAlpha(0.9f);
		    //����ǰ��ͼ͸���ȣ�0-1.0��֮��
	        plot.setForegroundAlpha(0.50f);
	        //���IO��
	        FileOutputStream fos;
	        fos=new FileOutputStream("F:\\Java����\\apache-tomcat-7.0.5\\webapps\\Microblog\\upload\\pic\\6c.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 800,600,null);
	        fos.close();
	        request.getRequestDispatcher("/sample1.jsp").forward(request, response);
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
