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
		//判断图片是否存在，如果存在删除
        File f=new File("F:\\Java方向\\apache-tomcat-7.0.5\\webapps\\Microblog\\upload\\pic\\6c.jpg");
		if(f.isFile()){
			f.delete();	
		}		
		HttpSession session=request.getSession();
		    IBollhotBiz bolBiz=new BollhotBizImpl();
		    List<Bloghot> bol=bolBiz.SelectByHot();
	        List<Bloghot> bol2=new ArrayList<Bloghot>();	      
	        String t1="1",t2="1";
	        //循环遍历出两个Title
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
	        //循环遍历两个Items加载集合
	        for (int i = 0; i < bol.size(); i++) {
	        	if(t2.equals(bol.get(i).getBtitle())){        	
	        		bol2.add(bol.get(i));
	        	}
	        }	 
	        session.setAttribute("bol2", bol2);
	      //Jfreechart 3D饼图
	        DefaultPieDataset dataset=new DefaultPieDataset();
	        for (int i = 0; i < bol2.size(); i++) {
				dataset.setValue(bol2.get(i).getBitems()+":"+bol2.get(i).getBvote(), bol2.get(i).getBvote());
			}
	        JFreeChart chart;
	        chart=ChartFactory.createPieChart3D("您最喜欢的暑期档影视作品", dataset,true,false,true);
	        //重新设置图标表标题
	        chart.setTitle(new TextTitle("您最喜欢的暑期档影视作品",new Font("黑体",Font.ITALIC, 22)));
	        //取得统计图表第一个图例
	        LegendTitle legend=chart.getLegend(0);	        
	        //修改图例的字体
	        legend.setItemFont(new Font("宋体",Font.BOLD,14));
	        //获得3D饼图的plot对象
	        PiePlot plot=(PiePlot) chart.getPlot();
	        //设置饼图的各部分标签字体
	        plot.setLabelFont(new Font("隶书",Font.BOLD,18));
	        //设置背景图透明度（0-1.0）之间
	        plot.setBackgroundAlpha(0.9f);
		    //设置前景图透明度（0-1.0）之间
	        plot.setForegroundAlpha(0.50f);
	        //输出IO流
	        FileOutputStream fos;
	        fos=new FileOutputStream("F:\\Java方向\\apache-tomcat-7.0.5\\webapps\\Microblog\\upload\\pic\\6c.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 800,600,null);
	        fos.close();
	        request.getRequestDispatcher("/sample1.jsp").forward(request, response);
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
