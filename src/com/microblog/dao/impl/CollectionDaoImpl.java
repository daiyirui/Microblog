package com.microblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.common.JDBCUtil;
import com.microblog.dao.ICollectionDao;
import com.microblog.po.Collection;
import com.microblog.po.Users;

public class CollectionDaoImpl implements ICollectionDao {

	@Override
	public int InsertCollection(Collection coll) {
		 Connection connection = null;
	       PreparedStatement statement = null;
	       int  a = 0;
	        try {
	        	String sql="insert into collection(l_uid,lcontent,ldate,limages,wremarks,l_wid) values(?,?,now(),?,null,?)";
	            connection = JDBCUtil.getConn();
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, coll.getL_uid());
	            statement.setString(2, coll.getLcontent());
	            statement.setString(3, coll.getLimages());
	            statement.setInt(4, coll.getL_wid());
	            a=statement.executeUpdate();
	        } catch (SQLException e) {
	        	a=0;
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeDB(connection, statement, null);
	        }
		return a;
	}


	@Override
	public int DeleteCollection(int lid) {
		 Connection connection = null;
	       PreparedStatement statement = null;
         int a = 0;
         try {
      	   connection = JDBCUtil.getConn();
             String sql = "DELETE FROM collection where lid=?";
             System.out.println(sql);
             statement = connection.prepareStatement(sql);
             statement.setInt(1, lid);
             a=statement.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             JDBCUtil.closeDB(connection, statement, null);
         }
		return a;
	}

	

	@Override
	public int CountCollectionByUid(int uid) {
		Connection connection = null;
	    PreparedStatement statement = null;
		int count=0;
		try {
			//step1： sql语句
			String sql="SELECT count(*) FROM collection where  l_uid=?";	
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, uid);
			//step3:获取返回值结果集
			ResultSet rs=statement.executeQuery();
			//step4:int 变量
			if(rs.next()){
				//step6:遍历结果集
				count=rs.getInt(1);
			}			 
		} catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           JDBCUtil.closeDB(connection, statement, null);
	       }
		return count;
	}

    //获取登录用户的所有收藏
	@Override
	public List<Collection> FindCollectionByuid(int uid) {
		List<Collection> collections=new ArrayList<Collection>();
		Connection connection = null;
	    PreparedStatement statement = null;
	    try {
			String sql="SELECT * FROM collection where l_uid=? by ldate desc";
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, uid);
	        System.out.println("uid:"+uid);
	        System.out.println("sql:"+sql);
	        ResultSet rs = statement.executeQuery();
			//step6:遍历结果集
				while (rs.next()) {
					Collection collection=new Collection();		
					collection.setLid(rs.getInt("lid"));
					collection.setL_uid(rs.getInt("l_uid"));
					collection.setLcontent(rs.getString("lcontent"));
					collection.setLdate(rs.getDate("ldate"));
					collection.setLimages(rs.getString("limages"));
					collection.setL_wid(rs.getInt("l_wid"));
				    sql = "SELECT * FROM users where uid=?";
				    statement = connection.prepareStatement(sql);
				    statement.setInt(1, rs.getInt("l_uid"));
				   ResultSet re=statement.executeQuery();
				   if(re.next()){
					    Users use=new Users();
					    use.setUid(re.getInt("uid"));
					    use.setUname(re.getString("uname"));
					    use.setUpwd(re.getString("upwd"));
					    use.setUnickname(re.getString("unickname"));
					    use.setUsex(re.getString("usex"));
					    use.setUaddress(re.getString("uaddress"));
					    use.setUdate(re.getDate("udate"));
					    use.setUpic(re.getString("upic"));
					    use.setUqq(re.getString("uqq"));
					    use.setUedu(re.getString("uedu"));
					    use.setUques(re.getString("uques"));
					    use.setUrealname(re.getString("urealname"));
					    use.setUremarks(re.getString("uremarks"));
					    collection.setUse(use);
				   }
				   //step6:添加到list集合
				   collections.add(collection);
				}
	   } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           JDBCUtil.closeDB(connection, statement, null);
       }
	   return collections;
	}

    //判断该微博是否被我收藏了
	@Override
	public int judgeColletionBywid(int uid, int wid) {
		Connection connection = null;
	    PreparedStatement statement = null;
		int count=0;
		try {
			//step1： sql语句
			String sql="SELECT count(*) FROM collection where  l_uid=? and l_wid";	
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, uid);
			//step3:获取返回值结果集
			ResultSet rs=statement.executeQuery();
			//step4:int 变量
			if(rs.next()){
				//step6:遍历结果集
				count=rs.getInt(1);
			}			 
		} catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           JDBCUtil.closeDB(connection, statement, null);
	       }
		return count;
	}

   
}
