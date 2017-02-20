package com.microblog.servlet;

import java.awt.Color;
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

import com.microblog.biz.IBollhotBiz;
import com.microblog.biz.impl.BollhotBizImpl;
import com.microblog.po.Bloghot;

@SuppressWarnings("serial")
public class ShowBallotServlet extends HttpServlet {
	public ShowBallotServlet() {
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
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		//判断图片是否存在，如果存在删除
        File f=new File("F:\\Java方向\\apache-tomcat-7.0.5\\webapps\\Microblog\\upload\\pic\\4d.jpg");
		if(f.isFile()){
			f.delete();	
		}
		//Jfreechart 3D柱形图
		HttpSession session=request.getSession();
		    IBollhotBiz bolBiz=new BollhotBizImpl();
		    List<Bloghot> bol=bolBiz.SelectByHot();
	        List<Bloghot> bol1=new ArrayList<Bloghot>();	      
	        String t1="1";
	        //循环遍历出两个Title
	        for (int j = 0; j < bol.size(); j++) {
				if(t1!=bol.get(j).getBtitle()){
					if(t1=="1"){
					   t1=bol.get(j).getBtitle();	
					}
				}
			}
	        //循环遍历两个Items加载集合
	        for (int i = 0; i < bol.size(); i++) {
	        	if(t1.equals(bol.get(i).getBtitle())){        	
	        		bol1.add(bol.get(i));
	        	}
	        }	 
	        session.setAttribute("bol1", bol1);
	        List<Integer> rs=new ArrayList<Integer>();
	        if(bol1.size()==5){
	        	for (int i = 0; i < bol1.size(); i++) {
					rs.add(bol1.get(i).getBvote());
				}
	        }
	        double [][] data=new double[][]{{rs.get(0)},{rs.get(1)},{rs.get(2)},{rs.get(3)},{rs.get(4)}};
	        String[] rowkeys={"费德勒","纳达尔","德约科维奇","罗迪克","穆雷"};
	        String[] columnKeys={""};
	        CategoryDataset dataset=DatasetUtilities.createCategoryDataset(rowkeys,columnKeys,data);
	        Font font=new Font("宋体",Font.BOLD,16);
	        JFreeChart chart=ChartFactory.createBarChart3D("用户投票","运动员","用户数", dataset,PlotOrientation.VERTICAL,true,false,false);
	        CategoryPlot plot=chart.getCategoryPlot();
	        TextTitle title=new TextTitle("用户投票", font);
	        TextTitle subtitle=new TextTitle("美网竞猜",new Font("黑体",Font.BOLD,12));
	        chart.addSubtitle(subtitle);
	        chart.setTitle(title);
	        NumberAxis numberaxis=(NumberAxis) plot.getRangeAxis();
	        CategoryAxis domainAxis=plot.getDomainAxis();
	        //设置X坐标轴文字
	        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN,11));
	        //设置X坐标轴标题文字
	        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN,12));
	        //设置Y坐标轴文字
	        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN,12));
	        //设置Y坐标轴标题文字
	        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN,12));
	        //底部汉字乱码
	        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN,12));
	        //设置网格背景颜色
	        plot.setBackgroundPaint(Color.white);
	        //设置网格竖线颜色
	        plot.setDomainGridlinePaint(Color.pink);
	        //设置网格横线颜色
	        plot.setRangeGridlinePaint(Color.pink);
	        //显示每个柱形的数值，并修改该数值的字体属性
	        BarRenderer3D renderer=new BarRenderer3D();
	        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	        renderer.setBaseItemLabelsVisible(true);
	        //默认数字显示在柱形图上，通过如下两句可调整数值的显示，
	        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
	        		ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_LEFT));
	        renderer.setItemLabelAnchorOffset(10D);
	        renderer.setItemLabelFont(new Font("宋体", Font.PLAIN,12));
	        renderer.setItemLabelsVisible(true);
	        //设置每个地区所包含的平行住之间距离
	        plot.setRenderer(renderer);	        
	        //输出图像
	        FileOutputStream fos;
	        fos=new FileOutputStream("F:\\Java方向\\apache-tomcat-7.0.5\\webapps\\Microblog\\upload\\pic\\4d.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 800, 600,null);
	        fos.close();	        
	        request.getRequestDispatcher("/sample.jsp").forward(request, response);	       
	        
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
