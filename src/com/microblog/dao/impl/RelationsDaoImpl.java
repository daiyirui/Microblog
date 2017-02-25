package com.microblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.common.JDBCUtil;
import com.microblog.dao.IRelationsDao;
import com.microblog.po.Users;

public class RelationsDaoImpl implements IRelationsDao {
	
	@SuppressWarnings("resource")
	@Override
	public int DeleteRelationByuid(int uid, int gid) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		//首先判断是单向还是双休关注
		try { 
			    String sql="SELECT * FROM relations where r_id=? and g_id=?";
				 connection = JDBCUtil.getConn();
				 statement = connection.prepareStatement(sql);
				 statement.setInt(1,gid);
				 statement.setInt(2,uid);
				 ResultSet rs=statement.executeQuery();
			if(rs.next()){
				//证明是双向关注
				int id=rs.getInt(1);
				sql="update relations set rstate=0 where rid=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1,id);
				int a=statement.executeUpdate();
				if(a==1){
					return 1;
				}else{
					return 0;
				}
			}
			//无论是单还是双休，都需要删除登陆者关注的信息
			sql="delete from relations where r_id=? and g_id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1,uid);
			statement.setInt(1,gid);
			int b=statement.executeUpdate();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			JDBCUtil.closeDB(connection, statement, null);
		}		
	}
	/*
	 * 1.检查此用户是否已经在我的好友中：SELECT * FROM relations where r_id=1 and g_id=2
	 *    如果有返回值证明已经添加过，返回0，如果没有则执行下一步
	 * 2.判断此用户是否已经关注我了(在我关注他之前)：SELECT * FROM relations where r_id=2 and g_id=1
	 * 3.如果有返回值，证明已经关注我了，说明是双向关注，更改rstate=1
	 *   update  relations set rstate=1 where 
	 *   insert into relations values(null,1,2,1);
	 * 4如果没有返回值，证明单向关注，只做添加操作
	 * insert into relations values(null,1,2,0);    
	 */
	@SuppressWarnings("resource")
	@Override
	public int InsertRelation(int uid, int gid,int flag) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		try {
			    connection=JDBCUtil.getConn();
				if(flag>0){
					//3,证明是双向关注
					String sql1="update relations set rstate=1 where r_id=? and g_id=?";
					statement=connection.prepareStatement(sql1);
					statement.setInt(1, gid);
					statement.setInt(2, uid);
					System.out.println("sql1:"+sql1);
					int a=statement.executeUpdate();
					System.out.println("a:"+a);
					if(a==1){
						sql1="insert into relations(r_id,g_id,rstate) values(?,?,?)";
						statement=connection.prepareStatement(sql1);
						statement.setInt(1, uid);
						statement.setInt(2, gid);
						statement.setInt(3,flag);
						a=statement.executeUpdate();
						if(a==1){
							return 1;
						}else{
							return 0;
						}
					}else{
						return 0;
					}
				}else{
					//4证明是单向关注
					String	sql2="insert into relations(r_id,g_id,rstate) values(?,?,?)";
					statement=connection.prepareStatement(sql2);
					statement.setInt(1, uid);
					statement.setInt(2, gid);
					statement.setInt(3,flag);
					int b=statement.executeUpdate();
					if(b==1){
						return 1;
					}else{
						return 0;
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			JDBCUtil.closeDB(connection, statement, null);
		}
	}
	 //显示登录者关注人的数量,包括了双向关注的情况
	@Override
	public int CountByAttention(int uid) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		//step1:sql语句
		int flag = 0;
		try{
		    String sql="SELECT count(*) FROM relations where g_id!=? and r_id=?";
			 connection = JDBCUtil.getConn();
			 statement = connection.prepareStatement(sql);
			 statement.setInt(1,uid);
			 statement.setInt(2,uid);
			 ResultSet rs=statement.executeQuery();
			while(rs.next()){
				//1.证明对方已经关注我了
				flag =rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			JDBCUtil.closeDB(connection, statement, null);
		}		
		return flag;
	}
	//查询我的粉丝，包括了双向关注的情况
	@Override
	public int CountByVermicelli(int uid) {
		
		 Connection connection = null;
		 PreparedStatement statement = null;
		//step1:sql语句
		int flag = 0;
		try{

			String sql="SELECT count(*) FROM relations where g_id=? and r_id!=?";
			 connection = JDBCUtil.getConn();
			 statement = connection.prepareStatement(sql);
			 statement.setInt(1,uid);
			 statement.setInt(2,uid);
			 ResultSet rs=statement.executeQuery();
			while(rs.next()){
				//1.证明对方已经关注我了
				flag =rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			JDBCUtil.closeDB(connection, statement, null);
		}		
		return flag;
	}
	//判断对方是否已经关注我啦     (r_id关注了g_id)(所以这里判断gid是否关注了uid)
	@Override
	public int FindRelationByuid(int uid, int gid) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		//step1:sql语句
		int flag = 0;
		try{
			 String sql="SELECT count(*) FROM relations where g_id=? and r_id=?";
			 connection = JDBCUtil.getConn();
			 statement = connection.prepareStatement(sql);
			 statement.setInt(1,uid);
			 statement.setInt(2,gid);
			 ResultSet rs=statement.executeQuery();
			while(rs.next()){
				//1.证明对方已经关注我了
				flag =rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			JDBCUtil.closeDB(connection, statement, null);
		}		
		return flag;
	}
	//获取我关注的人的信息
	@Override
	public List<Users> FindAllMyInterestByuid(int uid) {
		 List<Users> users = null;
		 Connection connection = null;
		 PreparedStatement statement = null;
		 String sql="SELECT * FROM relations where r_id=? ";
		 try{
			 connection = JDBCUtil.getConn();
			 statement = connection.prepareStatement(sql);
			 statement.setInt(1,uid);
			 ResultSet rsr=statement.executeQuery();
			 users = new ArrayList<Users>();
			 while(rsr.next()){
				 String sql1="SELECT * FROM users where uid=? ";
				 statement = connection.prepareStatement(sql1);
				 statement.setInt(1, rsr.getInt("g_id"));
				 ResultSet rs=statement.executeQuery();
				 while(rs.next()) {
						Users user = new Users();
						user.setUid(rs.getInt("uid"));
						user.setUname(rs.getString("uname"));
						user.setUpwd(rs.getString("upwd"));
						user.setUnickname(rs.getString("unickname"));
						user.setUsex(rs.getString("usex"));
						user.setUaddress(rs.getString("uaddress"));
						user.setUdate(rs.getString("udate"));
						user.setUqq(rs.getString("uqq"));
						user.setUedu(rs.getString("uedu"));
						user.setUpic(rs.getString("upic"));
						user.setUques(rs.getString("uques"));
						user.setUrealname(rs.getString("urealname"));
						user.setUremarks(rs.getString("uremarks"));
						users.add(user);
					}
			 }
			return users;
		  } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			JDBCUtil.closeDB(connection, statement, null);
		}	
	}
	
	@Override
	public  List<Users> FindAllMyFansByuid(int uid) {
		 List<Users> users = null;
		 Connection connection = null;
		 PreparedStatement statement = null;
		 String sql="SELECT * FROM relations where g_id=? ";
		 try{
			 connection = JDBCUtil.getConn();
			 statement = connection.prepareStatement(sql);
			 statement.setInt(1,uid);
			 ResultSet rsr=statement.executeQuery();
			 users = new ArrayList<Users>();
			 while(rsr.next()){
				 String sql1="SELECT * FROM users where uid=? ";
				 statement = connection.prepareStatement(sql1);
				 statement.setInt(1, rsr.getInt("r_id"));
				 ResultSet rs=statement.executeQuery();
				 while(rs.next()) {
						Users user = new Users();
						user.setUid(rs.getInt("uid"));
						user.setUname(rs.getString("uname"));
						user.setUpwd(rs.getString("upwd"));
						user.setUnickname(rs.getString("unickname"));
						user.setUsex(rs.getString("usex"));
						user.setUaddress(rs.getString("uaddress"));
						user.setUdate(rs.getString("udate"));
						user.setUqq(rs.getString("uqq"));
						user.setUedu(rs.getString("uedu"));
						user.setUpic(rs.getString("upic"));
						user.setUques(rs.getString("uques"));
						user.setUrealname(rs.getString("urealname"));
						user.setUremarks(rs.getString("uremarks"));
						users.add(user);
					}
			 }
			return users;
		  } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			JDBCUtil.closeDB(connection, statement, null);
		}	
	}
	public static void main(String[] args) {
		RelationsDaoImpl rl = new RelationsDaoImpl();
		System.out.println(rl.CountByAttention(1));
	}
}