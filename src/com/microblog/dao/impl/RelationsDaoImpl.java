package com.microblog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dao.IRelationsDao;
import com.microblog.dbutil.DBConn;
import com.microblog.po.Users;

public class RelationsDaoImpl implements IRelationsDao {
	DBConn db=null;
	public RelationsDaoImpl(){
		db=new DBConn();
	}
	@Override
	public int DeleteRelationByuid(int uid, int gid) {
		//首先判断是单向还是双休关注
		String sql="SELECT * FROM relations where r_id=? and g_id=?";
		ResultSet rs=db.execQuery(sql, new Object[]{gid,uid});
		try {
			if(rs.next()){
				//证明是双休关注
				int id=rs.getInt(1);
				sql="update relations set rstate=0 where rid=?";
				int a=db.execOther(sql, new Object[]{id});
				if(a==1){
					return 1;
				}else{
					return 0;
				}
			}
			//无论是单还是双休，都需要删除登陆者关注的信息
			sql="delete from relations where r_id=? and g_id=?";
			int b =db.execOther(sql, new Object[]{uid,gid});
			return b;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			db.closeConn();
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
	@Override
	public int InsertRelation(int uid, int gid,int flag) {
		try {
				if(flag>0){
					//3,证明是双向关注
					String sql1="update relations set rstate=1 where r_id=? and g_id=?";
					System.out.println("sql1:"+sql1);
					int a=db.execOther(sql1, new Object[]{uid,gid});
					System.out.println("a:"+a);
					if(a==1){
						sql1="insert into relations(r_id,g_id,rstate) values(?,?,?)";
						a=db.execOther(sql1, new Object[]{uid,gid,flag});
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
					int b=db.execOther(sql2, new Object[]{uid,gid,flag});
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
			db.closeConn();
		}
	}
	 //显示登录者关注人的数量
	@Override
	public int CountByAttention(int uid) {
		//step1:sql语句
		String sql="SELECT count(*) FROM relations where r_id=?";
		
		//step3:获取返回值结果集
		ResultSet rs=db.execQuery(sql, new Object[]{uid});
		//step4:int 变量
		int countrelation=0;
		try {
			if(rs.next()){
				//step6:遍历结果集
				countrelation=rs.getInt(1);
				return countrelation;
			}else{
				return 0;	
			}			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			db.closeConn();
		}		
	}
	@Override
	public int CountByVermicelli(int uid) {
		//step1:sql语句
		String sql="SELECT count(*) FROM relations where g_id=? and r_id!=?";
		
		//step3:获取返回值结果集
		ResultSet rs=db.execQuery(sql, new Object[]{uid,uid});
		//step4:int 变量
		int countver=0;
		try {
			if(rs.next()){
				//step6:遍历结果集
				countver=rs.getInt(1);
				return countver;
			}else{
				return 0;	
			}			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			db.closeConn();
		}		
	}
	//判断对方是否已经关注我啦
	@Override
	public int FindRelationByuid(int uid, int gid) {
		//step1:sql语句
		int flag = 0;
		String sql="SELECT count(*) FROM relations where r_id=? and g_id=?";
		ResultSet rs=db.execQuery(sql, new Object[]{uid,gid});
		
		try {
			while(rs.next()){
				//1.证明对方已经关注我了
				flag =rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			db.closeConn();
		}		
		return flag;
	}
	@Override
	public List<Users> FindAllMyInterestByuid(int uid) {
		List<Users> gUsers = new ArrayList<Users>();
		String sql="SELECT * FROM relations where r_id=? ";
		ResultSet rsr=db.execQuery(sql, new Object[]{uid});
		
		try {
			while(rsr.next()){
				String sql1="SELECT * FROM users where uid=? ";
				ResultSet rs=db.execQuery(sql1, new Object[]{rsr.getInt("rid")});
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
					gUsers.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
		return gUsers;
	}
	
}