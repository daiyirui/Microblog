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
		//�ж�ͼƬ�Ƿ���ڣ��������ɾ��
        File f=new File("F:\\Java����\\apache-tomcat-7.0.5\\webapps\\Microblog\\upload\\pic\\4d.jpg");
		if(f.isFile()){
			f.delete();	
		}
		//Jfreechart 3D����ͼ
		HttpSession session=request.getSession();
		    IBollhotBiz bolBiz=new BollhotBizImpl();
		    List<Bloghot> bol=bolBiz.SelectByHot();
	        List<Bloghot> bol1=new ArrayList<Bloghot>();	      
	        String t1="1";
	        //ѭ������������Title
	        for (int j = 0; j < bol.size(); j++) {
				if(t1!=bol.get(j).getBtitle()){
					if(t1=="1"){
					   t1=bol.get(j).getBtitle();	
					}
				}
			}
	        //ѭ����������Items���ؼ���
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
	        String[] rowkeys={"�ѵ���","�ɴ��","��Լ��ά��","�޵Ͽ�","����"};
	        String[] columnKeys={""};
	        CategoryDataset dataset=DatasetUtilities.createCategoryDataset(rowkeys,columnKeys,data);
	        Font font=new Font("����",Font.BOLD,16);
	        JFreeChart chart=ChartFactory.createBarChart3D("�û�ͶƱ","�˶�Ա","�û���", dataset,PlotOrientation.VERTICAL,true,false,false);
	        CategoryPlot plot=chart.getCategoryPlot();
	        TextTitle title=new TextTitle("�û�ͶƱ", font);
	        TextTitle subtitle=new TextTitle("��������",new Font("����",Font.BOLD,12));
	        chart.addSubtitle(subtitle);
	        chart.setTitle(title);
	        NumberAxis numberaxis=(NumberAxis) plot.getRangeAxis();
	        CategoryAxis domainAxis=plot.getDomainAxis();
	        //����X����������
	        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN,11));
	        //����X�������������
	        domainAxis.setLabelFont(new Font("����", Font.PLAIN,12));
	        //����Y����������
	        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN,12));
	        //����Y�������������
	        numberaxis.setLabelFont(new Font("����", Font.PLAIN,12));
	        //�ײ���������
	        chart.getLegend().setItemFont(new Font("����", Font.PLAIN,12));
	        //�������񱳾���ɫ
	        plot.setBackgroundPaint(Color.white);
	        //��������������ɫ
	        plot.setDomainGridlinePaint(Color.pink);
	        //�������������ɫ
	        plot.setRangeGridlinePaint(Color.pink);
	        //��ʾÿ�����ε���ֵ�����޸ĸ���ֵ����������
	        BarRenderer3D renderer=new BarRenderer3D();
	        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	        renderer.setBaseItemLabelsVisible(true);
	        //Ĭ��������ʾ������ͼ�ϣ�ͨ����������ɵ�����ֵ����ʾ��
	        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
	        		ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_LEFT));
	        renderer.setItemLabelAnchorOffset(10D);
	        renderer.setItemLabelFont(new Font("����", Font.PLAIN,12));
	        renderer.setItemLabelsVisible(true);
	        //����ÿ��������������ƽ��ס֮�����
	        plot.setRenderer(renderer);	        
	        //���ͼ��
	        FileOutputStream fos;
	        fos=new FileOutputStream("F:\\Java����\\apache-tomcat-7.0.5\\webapps\\Microblog\\upload\\pic\\4d.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 800, 600,null);
	        fos.close();	        
	        request.getRequestDispatcher("/sample.jsp").forward(request, response);	       
	        
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
