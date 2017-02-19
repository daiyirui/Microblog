package com.microblog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub
    	String sql="SELECT * FROM users where uremarks!='no' and uname=? and uques=?";
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
		// TODO Auto-generated method stub
    	String sql="insert into users values(null,?,?,?,?,?,now(),'1','本科',null,'','','null')";
     	int a=db.execOther(sql, new Object[]{use.getUname(),use.getUpwd(),use.getUnickname(),use.getUsex(),use.getUaddress()});
		return a;
	}
	@Override
	public Users FindByObject(String uname, String upwd, String sex) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM users where uremarks!='no' and uname=? and upwd=? and usex=?";
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
	public Users FindByuid(int uid) {
		//SELECT * FROM users where uid=5
    	String sql="SELECT * FROM users where uremarks!='no' and uid=?";
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
	public PageBean FindFansByPage(int uid, String strSQL, int currentPage,int pageSize) {
    	//step1:创建pagebean对象,为其五个属性赋值
		PageBean pb=new PageBean();
		//step2:sql语句，用来获取weibo表中记录数量 count(*)   SELECT * FROM weibo order by wdate desc
		String sqlcount=strSQL;
		sqlcount=sqlcount.substring(sqlcount.toLowerCase().indexOf("from"));
		sqlcount = "select count(*) "+sqlcount;
		System.out.println("SqlCount:"+sqlcount);
		//step3:执行sql语句得到结果并将其结果赋值给pb的totalRows变量；
		ResultSet rs=db.execQuery(sqlcount, new Object[]{uid,uid});
		try {
			if(rs.next()){
				pb.setTotalRows(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pb.setTotalRows(0);
		}
		//step4:为pb的data属性赋值，首先为获取本页第一条记录设置行标
		int start=(currentPage-1)*pageSize;
		//step5:创建赋值data的sql语句
		strSQL=strSQL+" limit ?,? ";
		rs=db.execQuery(strSQL, new Object[]{uid,uid,start,pageSize});
		//step6:获取data结果集合
		List<Users> lisUser=new ArrayList<Users>();
		Users use=null;
		try {
			while (rs.next()) {
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
					    use.setUedu(rs.getString("uedu"));
					    use.setUques(rs.getString("uques"));
					    use.setUrealname(rs.getString("urealname"));
					    use.setUremarks(rs.getString("uremarks"));
					    lisUser.add(use);	 				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:赋值相应属性
		pb.setData(lisUser);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:返回结果
		return pb;
	}
    @Override
	public PageBean FindByOverInterest(int uid, String strSQL, int currentPage,	int pageSize) {
    	//step1:创建pagebean对象,为其五个属性赋值
		PageBean pb=new PageBean();
		//step2:sql语句，用来获取weibo表中记录数量 count(*)   SELECT * FROM weibo order by wdate desc
		String sqlcount=strSQL;
		sqlcount=sqlcount.substring(sqlcount.toLowerCase().indexOf("from"));
		sqlcount = "select count(*) "+sqlcount;
		System.out.println("SqlCount:"+sqlcount);
		//step3:执行sql语句得到结果并将其结果赋值给pb的totalRows变量；
		ResultSet rs=db.execQuery(sqlcount, new Object[]{uid,uid});
		try {
			if(rs.next()){
				pb.setTotalRows(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pb.setTotalRows(0);
		}
		//step4:为pb的data属性赋值，首先为获取本页第一条记录设置行标
		int start=(currentPage-1)*pageSize;
		//step5:创建赋值data的sql语句
		strSQL=strSQL+" limit ?,? ";
		rs=db.execQuery(strSQL, new Object[]{uid,uid,start,pageSize});
		//step6:获取data结果集合
		List<Users> lisUser=new ArrayList<Users>();
		Users use=null;
		try {
			while (rs.next()) {
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
					    use.setUedu(rs.getString("uedu"));
					    use.setUques(rs.getString("uques"));
					    use.setUrealname(rs.getString("urealname"));
					    use.setUremarks(rs.getString("uremarks"));
					    lisUser.add(use);	 				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:赋值相应属性
		pb.setData(lisUser);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:返回结果
		return pb;
	}
	@Override
	public Users UserLoginCheck(String usn, String pwd) {
		//step1:创建查询数据库sql语句
		String sql="SELECT * FROM users where uremarks!='no' and uname=? and upwd=?";
		
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
		String sql="SELECT * FROM users where uremarks!='no' and uid!=? and uid not in (select g_id from relations where r_id=?)";
		
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
	public static void main(String[] args) {
		IUserDao u=new UserDaoImpl();
		List<Users> listUser=u.FindByListener();
		System.out.println("s "+listUser.size());
	}
}
