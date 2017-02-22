package com.microblog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				hot.setBvote(rs.getInt("bvote"));
				hot.setBremarks(rs.getString("bremarks"));
				litHot.add(hot);
			}
			return litHot;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		String sql="SELECT * FROM bloghot where bstate=1 order by bvote desc";
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
}