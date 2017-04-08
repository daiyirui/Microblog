package com.microblog.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microblog.common.JDBCUtil;
import com.microblog.dao.IBollhotDao;
import com.microblog.po.Bloghot;
import com.microblog.po.Bloghotitem;


public class BollhotDaoImpl implements IBollhotDao {
   
	@Override
	public List<Bloghot> FindAllHot() {
		Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql="SELECT * FROM bloghot where bstate=1 order by bvote desc";
		List<Bloghot> litHot=new ArrayList<Bloghot>();
				
		conn = JDBCUtil.getConn();
	        try {
	            stat = conn.createStatement();
	            rs = stat.executeQuery(sql);
	            while (rs.next()) {
	            	Bloghot hot=new Bloghot();
			        hot.setBid(rs.getInt("bid"));
			        hot.setBstate(rs.getInt("bstate"));
					hot.setBtitle(rs.getString("btitle"));
					hot.setBimages(rs.getString("bimages"));
					hot.setBvote(rs.getInt("bvote"));
					hot.setBremarks(rs.getString("bremarks"));
					hot.setBitems(FindAllHotItem(rs.getInt("bid")));
					litHot.add(hot);
				}
	            System.out.println(litHot);
				return litHot;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeDB(conn, stat, rs);
	            return litHot;
	        }
		
	}
	
	public List<Bloghotitem> FindAllHotItem(Integer bid) {
		Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql="SELECT * FROM bloghotitem where bid="+bid;
		List<Bloghotitem> bloghotitems=new ArrayList<Bloghotitem>();
				
		conn = JDBCUtil.getConn();
	        try {
	            stat = conn.createStatement();
	            rs = stat.executeQuery(sql);
	            while (rs.next()) {
	            	Bloghotitem item=new Bloghotitem();
	            	item.setBloghotitemid(rs.getInt("bloghotitemid"));
			        item.setBid(rs.getInt("bid"));
			        item.setBitemimage(rs.getString("bitemimage"));
			        item.setBitemName(rs.getString("bitemName"));
			        item.setBvote(rs.getInt("bvote"));
			        item.setBremarks(rs.getString("remark"));
			        bloghotitems.add(item);
				}
				return bloghotitems;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeDB(conn, stat, rs);
	            return bloghotitems;
	        }
	}
}