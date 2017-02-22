package com.microblog.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microblog.common.JDBCUtil;
import com.microblog.dao.IBollhotDao;
import com.microblog.dbutil.DBConn;
import com.microblog.po.Bloghot;


public class BollhotDaoImpl implements IBollhotDao {
    DBConn db=null;
    public BollhotDaoImpl(){
    	db=new DBConn();
    }
	@Override
	public List<Bloghot> FindByHot() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM bloghot where bstate=1";
		ResultSet rs=db.execQuery(sql, new Object[]{});
		List<Bloghot> litHot=new ArrayList<Bloghot>();
		Bloghot hot=null;		
		try {
			while (rs.next()) {
				hot=new Bloghot();
		        hot.setBid(rs.getInt("bid"));
		        hot.setBstate(rs.getInt("bstate"));
				hot.setBtitle(rs.getString("btitle"));
				hot.setBitems(rs.getString("bitems"));
				hot.setBimages(rs.getString("bimages"));
				hot.setBvote(rs.getInt("bvote"));
				hot.setBremarks(rs.getString("bremarks"));
				litHot.add(hot);
			}
			return litHot;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
	}

	@Override
	public int VoitHot(String hot) {
		// TODO Auto-generated method stub
		String sql="update bloghot set bvote=bvote+1 where bstate=1 and bitems=?";
		int a=db.execOther(sql, new Object[]{hot});
		return a;
	}
	
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
					hot.setBitems(rs.getString("bitems"));
					hot.setBimages(rs.getString("bimages"));
					hot.setBvote(rs.getInt("bvote"));
					hot.setBremarks(rs.getString("bremarks"));
					litHot.add(hot);
				}
				return litHot;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeDB(conn, stat, rs);
	            return litHot;
	        }
		
	}
}