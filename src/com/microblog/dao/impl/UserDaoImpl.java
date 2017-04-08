package com.microblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.common.JDBCUtil;
import com.microblog.dao.IUserDao;
import com.microblog.po.Users;

public class UserDaoImpl implements IUserDao {
  
    @Override
	public Users FindByMail(String uemail) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
    	String sql="SELECT * FROM users where  uemail=?";
    	Users use=new Users();		
    	try {
    		connection = JDBCUtil.getConn();
		    statement = connection.prepareStatement(sql);
		    statement.setString(1, uemail);
			//step3:获取查询结果
			rs=statement.executeQuery();
			if(rs.next()){
			    use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getDate("udate"));
			    use.setUpic(rs.getString("upic"));   
			    use.setUqq(rs.getString("uqq"));
			    use.setUemail(rs.getString("uemail"));
			    use.setUedu(rs.getString("uedu"));
			    use.setUques(rs.getString("uques"));
			    use.setUrealname(rs.getString("urealname"));
			    use.setUremarks(rs.getString("uremarks"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.closeDB(connection, statement, rs);
		}
    	   return use;
	}
   	
    
    @Override
	public int RegisterUser(Users use) {
	    Connection connection = null;
        PreparedStatement statement = null;
        int  a = 0;
        try {
        	String sql="insert into users(uname,upwd,unickname,usex,uaddress,udate,uqq,uedu,urealname,uemail,uremarks) values(?,?,?,?,?,now(),?,?,?,?,null)";
            connection = JDBCUtil.getConn();
            statement = connection.prepareStatement(sql);
            statement.setString(1, use.getUname());
            statement.setString(2, use.getUpwd());
            statement.setString(3, use.getUnickname());
            statement.setString(4, use.getUsex());
            statement.setString(5, use.getUaddress());
            statement.setString(6, use.getUqq());
            statement.setString(7, use.getUedu());
            statement.setString(8, use.getUrealname());
            statement.setString(9, use.getUemail());
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
	public Users FindByuid(int uid) {
    	  Connection connection = null;
	      PreparedStatement statement = null;
    	try {
    		String sql="SELECT * FROM users where  uid=?";
    		  connection = JDBCUtil.getConn();
    	      statement = connection.prepareStatement(sql);
    	      statement.setInt(1, uid);
    	      ResultSet rs=statement.executeQuery();
			if(rs.next()){
				Users use=new Users();			  
			    use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getDate("udate"));
			    use.setUpic(rs.getString("upic"));   
			    use.setUqq(rs.getString("uqq"));
			    use.setUemail(rs.getString("uemail"));
			    use.setUedu(rs.getString("uedu"));
			    use.setUques(rs.getString("uques"));
			    use.setUrealname(rs.getString("urealname"));
			    use.setUremarks(rs.getString("uremarks"));
			    return use;
			}else{
				return null;
			}
    	  }catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            JDBCUtil.closeDB(connection, statement, null);
	        }
	}
	@Override
	public Users UserLoginCheck(String usn, String pwd) {
		  Connection connection = null;
	      PreparedStatement statement = null;
		  Users use=null;
	  try {
	     //step1:创建查询数据库sql语句
		String sql="SELECT * FROM users where  uname=? and upwd=?";
        connection = JDBCUtil.getConn();
        statement = connection.prepareStatement(sql);
        statement.setString(1, usn);
        statement.setString(2, pwd);
		//step3:获取查询结果
        use=new Users();
		ResultSet rs=statement.executeQuery();
			//step5:获取结果对象
			if(rs.next()){
			    use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getDate("udate"));
			    use.setUpic(rs.getString("upic"));
			    use.setUqq(rs.getString("uqq"));
			    use.setUedu(rs.getString("uedu"));
			    use.setUemail(rs.getString("uemail"));
			    use.setUques(rs.getString("uques"));
			    use.setUrealname(rs.getString("urealname"));
			    use.setUremarks(rs.getString("uremarks"));
			  } 
	    }catch (SQLException e) {
		            e.printStackTrace();
		            return null;
		        } finally {
		            JDBCUtil.closeDB(connection, statement, null);
		        }
		       return use;
	}	
	//获取登录者关注人的信息
	@Override
	public List<Users> FindByInterest(int uid) {
		 List<Users> users = null;
		 Connection connection = null;
		 PreparedStatement statement = null;
     	try {
			String sql="SELECT * FROM users where  uid!=? and uid not in (select g_id from relations where r_id=?)";
	        connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, uid);
	        statement.setInt(2, uid);
	         users = new ArrayList<Users>();
			//step3:获取查询结果
			ResultSet rs=statement.executeQuery();
				while (rs.next()) {
					//step6:获取结果对象
					Users	use=new Users();
					use.setUid(rs.getInt("uid"));
				    use.setUname(rs.getString("uname"));
				    use.setUpwd(rs.getString("upwd"));
				    use.setUnickname(rs.getString("unickname"));
				    use.setUsex(rs.getString("usex"));
				    use.setUaddress(rs.getString("uaddress"));
				    use.setUdate(rs.getDate("udate"));
				    use.setUpic(rs.getString("upic"));
				    use.setUqq(rs.getString("uqq"));
				    use.setUemail(rs.getString("uemail"));
				    use.setUedu(rs.getString("uedu"));
				    use.setUques(rs.getString("uques"));
				    use.setUrealname(rs.getString("urealname"));
				    use.setUremarks(rs.getString("uremarks"));
				    use.setiGtflag(1);
				    //结合添加对象
				    users.add(use);
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
	public List<Users> FindByListener() {
		 String sql="SELECT * FROM users where uremarks!='no' order by uid limit 55";
		 List<Users> users = null;
		 Connection connection = null;
		 PreparedStatement statement = null;
     	try {
	        connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	         users = new ArrayList<Users>();
			//step3:获取查询结果
			ResultSet rs=statement.executeQuery();
				while (rs.next()) {
					//step6:获取结果对象
					Users	use=new Users();
					use.setUid(rs.getInt("uid"));
				    use.setUname(rs.getString("uname"));
				    use.setUpwd(rs.getString("upwd"));
				    use.setUnickname(rs.getString("unickname"));
				    use.setUsex(rs.getString("usex"));
				    use.setUaddress(rs.getString("uaddress"));
				    use.setUdate(rs.getDate("udate"));
				    use.setUpic(rs.getString("upic"));
				    use.setUqq(rs.getString("uqq"));
				    use.setUemail(rs.getString("uemail"));
				    use.setUedu(rs.getString("uedu"));
				    use.setUques(rs.getString("uques"));
				    use.setUrealname(rs.getString("urealname"));
				    use.setUremarks(rs.getString("uremarks"));
				    use.settGiflag(1);
				    //结合添加对象
				    users.add(use);
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
	public List<String> AllEmails() {
		 String sql="SELECT uemail FROM users";
		 List<String> emails = null;
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet rs = null;
		 try {
			    connection = JDBCUtil.getConn();
			    statement = connection.prepareStatement(sql);
			    emails = new ArrayList<String>();
				//step3:获取查询结果
				rs=statement.executeQuery();
				while (rs.next()) {
				    emails.add(rs.getString("uemail"));
				}
			} catch (Exception e) {
				return emails;
			}finally{
				JDBCUtil.closeDB(connection, statement, rs);
			}	
		return emails;
	}

	@Override
	public int changeFace(Users user) {
		 String sql = "UPDATE users SET upic = ? WHERE uid = ?";
		 Connection connection = null;
		 PreparedStatement statement = null;
		 int flag = 0;
		 try {
			    connection = JDBCUtil.getConn();
			    statement = connection.prepareStatement(sql);
			    statement.setString(1, user.getUpic());
			    statement.setInt(2, user.getUid());
				//step3:获取查询结果
				flag=statement.executeUpdate();
			
			} catch (Exception e) {
				return flag;
			}finally{
				JDBCUtil.closeDB(connection, statement, null);
			}	
		return flag;
	}
	
	@Override
	public int changePassword(Users user) {
		 String sql = "UPDATE users SET upwd = ? WHERE uid = ?";
		 Connection connection = null;
		 PreparedStatement statement = null;
		 int flag = 0;
		 try {
			    connection = JDBCUtil.getConn();
			    statement = connection.prepareStatement(sql);
			    statement.setString(1, user.getUpwd());
			    statement.setInt(2, user.getUid());
				//step3:获取查询结果
				flag=statement.executeUpdate();
			} catch (Exception e) {
				return flag;
			}finally{
				JDBCUtil.closeDB(connection, statement, null);
			}	
		return flag;
	}
	
	@Override
	public int changeUserInfo(Users user) {
		String sql = "UPDATE users SET unickname = ? , uaddress = ?, uqq = ?, uedu = ? , uremarks = ? , uemail = ?"
				+ "WHERE uid = ?";
		 Connection connection = null;
		 PreparedStatement statement = null;
		 int flag = 0;
		 try {
			    connection = JDBCUtil.getConn();
			    statement = connection.prepareStatement(sql);
			    statement.setString(1, user.getUnickname());
			    statement.setString(2, user.getUaddress());
			    statement.setString(3, user.getUqq());
			    statement.setString(4, user.getUedu());
			    statement.setString(5, user.getUremarks());
			    statement.setInt(6, user.getUid());
				//step3:获取查询结果
				flag=statement.executeUpdate();
			} catch (Exception e) {
				return flag;
			}finally{
				JDBCUtil.closeDB(connection, statement, null);
			}	
		return flag;
	}
	
	public static void main(String[] args) {
		IUserDao u=new UserDaoImpl();
		List<Users> listUser=u.FindByListener();
		System.out.println("s "+listUser.size());
	}



}
