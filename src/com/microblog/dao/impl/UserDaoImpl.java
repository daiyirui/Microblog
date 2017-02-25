package com.microblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.common.JDBCUtil;
import com.microblog.dao.IUserDao;
import com.microblog.dbutil.DBConn;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;

public class UserDaoImpl implements IUserDao {
    DBConn db=null;
    public UserDaoImpl(){
    	db=new DBConn();    	
    }
    @Override
	public Users FindByMail(String uname,String uques) {
    	String sql="SELECT * FROM users where  uname=? and uques=?";
    	ResultSet rs=db.execQuery(sql, new Object[]{uname,uques});
    	try {
			if(rs.next()){
				Users use=new Users();			  
			    use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getString("udate"));
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			db.closeConn();
		}		
	}
    @Override
	public int UpdateUser(Users use) {		
        String sql="update users set upwd=?,unickname=?,usex=?,uedu=?,uques=?,urealname=?,uremarks=?,uqq=?,udate=now(),upic=? where uid=? ";
        int a =db.execOther(sql, new Object[]{use.getUpwd(),use.getUnickname(),use.getUsex(),use.getUedu(),use.getUques(),use.getUrealname()
        		,use.getUremarks(),use.getUqq(),use.getUpic(),use.getUid()});    	
		return a;
	}	
    
    @Override
	public int RegisterUser(Users use) {
	    Connection connection = null;
        PreparedStatement statement = null;
        int  a = 0;
        try {
        	String sql="insert into users(uname,upwd,unickname,usex,uaddress,udate,uqq,uedu,urealname,uemail,uremarks) values(?,?,?,?,?,?,?,?,?,?,null)";
            connection = JDBCUtil.getConn();
            statement = connection.prepareStatement(sql);
            statement.setString(1, use.getUname());
            statement.setString(2, use.getUpwd());
            statement.setString(3, use.getUnickname());
            statement.setString(4, use.getUsex());
            statement.setString(5, use.getUaddress());
            statement.setString(6, use.getUdate());
            statement.setString(7, use.getUqq());
            statement.setString(8, use.getUedu());
            statement.setString(9, use.getUrealname());
            statement.setString(10, use.getUemail());
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
	public Users FindByObject(String uname, String upwd, String sex) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM users where  uname=? and upwd=? and usex=?";
		ResultSet rs=db.execQuery(sql, new Object[]{uname,upwd,sex});
		try {
			if(rs.next()){
				Users use=new Users();			  
			    use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getString("udate"));
			    use.setUpic(rs.getString("upic"));   
			    use.setUqq(rs.getString("uqq"));
			    use.setUedu(rs.getString("uedu"));
			    use.setUemail(rs.getString("uemail"));
			    use.setUques(rs.getString("uques"));
			    use.setUrealname(rs.getString("urealname"));
			    use.setUremarks(rs.getString("uremarks"));
			    return use;
			}else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			db.closeConn();
		}		
	}
    @Override
	public Users FindByuid(int uid) {
    	String sql="SELECT * FROM users where  uid=?";
    	ResultSet rs=db.execQuery(sql, new Object[]{uid});
    	try {
			if(rs.next()){
				Users use=new Users();			  
			    use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getString("udate"));
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}	
	}
	

	@Override
	public Users UserLoginCheck(String usn, String pwd) {
		//step1:创建查询数据库sql语句
		String sql="SELECT * FROM users where  uname=? and upwd=?";
		System.out.println(sql);
		//step3:获取查询结果
		ResultSet rs=db.execQuery(sql, new Object[]{usn,pwd});
		//step4:创建Users实体类对象
		Users use=new Users();
		try {
			//step5:获取结果对象
			if(rs.next()){
			    use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getString("udate"));
			    use.setUpic(rs.getString("upic"));
			    use.setUqq(rs.getString("uqq"));
			    use.setUedu(rs.getString("uedu"));
			    use.setUemail(rs.getString("uemail"));
			    use.setUques(rs.getString("uques"));
			    use.setUrealname(rs.getString("urealname"));
			    use.setUremarks(rs.getString("uremarks"));
				return use;	
			}else{
				return null;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
			return null;
		} finally{
			db.closeConn();
		}		
	}	
	//获取登录者关注人的信息
	@Override
	public List<Users> FindByInterest(int uid) {
		//step1:创建查询语句
		String sql="SELECT * FROM users where  uid!=? and uid not in (select g_id from relations where r_id=?)";
		
		//step3:获取查询结果
		ResultSet rs=db.execQuery(sql, new Object[]{uid,uid});
		//step4:创建Users实体类对象
		Users use=null;
		//step5:创建list集合
		List<Users> listUser=new ArrayList<Users>();
		try {
			while (rs.next()) {
				//step6:获取结果对象
				use=new Users();
				use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getString("udate"));
			    use.setUpic(rs.getString("upic"));
			    use.setUqq(rs.getString("uqq"));
			    use.setUemail(rs.getString("uemail"));
			    use.setUedu(rs.getString("uedu"));
			    use.setUques(rs.getString("uques"));
			    use.setUrealname(rs.getString("urealname"));
			    use.setUremarks(rs.getString("uremarks"));
			    //结合添加对象
				listUser.add(use);
			}
			return listUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}	
	}

	@Override
	public List<Users> FindByListener() {
		//step1:sql语句
		String sql="SELECT * FROM users where uremarks!='no' order by uid limit 55";
		
		//step3:获取查询结果
		ResultSet rs=db.execQuery(sql, new Object[]{});
		//step4:创建Users实体类对象
		Users use=null;
		//step5:创建list集合
		List<Users> listUser=new ArrayList<Users>();
		try {
			while (rs.next()) {
				//step6:获取结果对象
				use=new Users();
				use.setUid(rs.getInt("uid"));
			    use.setUname(rs.getString("uname"));
			    use.setUpwd(rs.getString("upwd"));
			    use.setUnickname(rs.getString("unickname"));
			    use.setUsex(rs.getString("usex"));
			    use.setUaddress(rs.getString("uaddress"));
			    use.setUdate(rs.getString("udate"));
			    use.setUpic(rs.getString("upic"));
			    use.setUqq(rs.getString("uqq"));
			    use.setUemail(rs.getString("uemail"));
			    use.setUedu(rs.getString("uedu"));
			    use.setUques(rs.getString("uques"));
			    use.setUrealname(rs.getString("urealname"));
			    use.setUremarks(rs.getString("uremarks"));
			    //结合添加对象
				listUser.add(use);
			}
			return listUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
	}		
	@Override
	public List<Users> FindByOverInterest(int uid) {
		return null;
	}
	public static void main(String[] args) {
		IUserDao u=new UserDaoImpl();
		List<Users> listUser=u.FindByListener();
		System.out.println("s "+listUser.size());
	}
}
