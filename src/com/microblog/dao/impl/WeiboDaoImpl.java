package com.microblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.common.JDBCUtil;
import com.microblog.dao.ICollectionDao;
import com.microblog.dao.IWeiboDao;
import com.microblog.po.Users;
import com.microblog.po.Weibo;

public class WeiboDaoImpl implements IWeiboDao {
	//uid仅仅是用来判断我收藏了这条微博没有
	@SuppressWarnings("resource")
	@Override
	public Weibo FindBywid(int uid,int wid) {
		Connection connection = null;
	    PreparedStatement statement = null;
		Weibo weibo = new Weibo();
		try {
			//step1： sql语句
			String sql="SELECT * FROM weibo where  wid=?";	
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, wid);
			//step3:获取返回值结果集
			ResultSet rs=statement.executeQuery();
			//step4:int 变量
			if(rs.next()){
				//step6:遍历结果集
				  weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getDate("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getDate("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   weibo.setW_wid(rs.getInt("w_wid"));				   
				   //判断该微博是否被用户收藏了
				   ICollectionDao collectiondao = new CollectionDaoImpl();
				   if(collectiondao.judgeColletionBywid(uid, rs.getInt("wid"))==1) {
					   weibo.setFlag(1);
				   }
				   sql = "SELECT * FROM users where uid=?";
				   statement = connection.prepareStatement(sql);
				   statement.setInt(1, rs.getInt("w_uid"));
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
					    weibo.setUse(use);
				   }
			}			 
		} catch (SQLException e) {
	           e.printStackTrace();
	           return null;
	       } finally {
	           JDBCUtil.closeDB(connection, statement, null);
	       }
		return weibo;
	}
	@Override
	public int InsertWeibo(Weibo weibo, int uid) {
		   Connection connection = null;
	       PreparedStatement statement = null;
	       int  a = 0;
	        try {
	        	String sql="insert into weibo(wcontent,wdate,wimage,wtimes,w_uid,wremarks,wcountcomment) values(?,now(),?,0,?,null,0)";
	            connection = JDBCUtil.getConn();
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, weibo.getWcontent());
	            statement.setString(2, weibo.getWimage());
	            statement.setInt(3, uid);
	            a=statement.executeUpdate();
	        } catch (SQLException e) {
	        	a=0;
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeDB(connection, statement, null);
	        }
		return a;
	}
	
    //显示登录者和其所关注人的微博信息
	@Override
	public List<Weibo> FindByLogin(int uid) {
		//step5:创建List结合对象
		List<Weibo> lisWeibo=new ArrayList<Weibo>();
		Connection connection = null;
	    PreparedStatement statement = null;
	   try {
			String sql="SELECT * FROM weibo where w_uid= any(select g_id from relations where r_id=?) or w_uid=? order by wdate desc";
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, uid);
	        statement.setInt(2, uid);
	        ResultSet rs = statement.executeQuery();
			
			//step6:遍历结果集
				while (rs.next()) {
				   Weibo weibo=new Weibo();		
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getDate("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   weibo.setW_wid(rs.getInt("w_wid"));	
				   //判断该微博是否被用户收藏了
				   ICollectionDao collectiondao = new CollectionDaoImpl();
				   System.out.println(collectiondao.judgeColletionBywid(uid, rs.getInt("wid")));
				   if(collectiondao.judgeColletionBywid(uid, rs.getInt("wid"))==1) {
					   weibo.setFlag(1);
				   }
				   sql = "SELECT * FROM users where uid=?";
				   statement = connection.prepareStatement(sql);
				   statement.setInt(1, rs.getInt("w_uid"));
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
					    weibo.setUse(use);
				   }
				   //step6:添加到list集合
				   lisWeibo.add(weibo);
				}
	   } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           JDBCUtil.closeDB(connection, statement, null);
       }
	   return lisWeibo;
	}
	//显示登录者微博数量
	@Override
	public int CountByMicroblog(int uid) {
		Connection connection = null;
	    PreparedStatement statement = null;
		int countblog=0;
		try {
			//step1： sql语句
			String sql="SELECT count(*) FROM weibo where  w_uid=?";	
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, uid);
			//step3:获取返回值结果集
			ResultSet rs=statement.executeQuery();
			//step4:int 变量
			if(rs.next()){
				//step6:遍历结果集
				countblog=rs.getInt(1);
			}			 
		} catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           JDBCUtil.closeDB(connection, statement, null);
	       }
		return countblog;
	}
	@Override
	public int DeleteWeibo(int wid, int w_uid) {
		   Connection connection = null;
	       PreparedStatement statement = null;
           int a = 0;
           try {
        	   connection = JDBCUtil.getConn();
               String sql = "DELETE FROM weibo where wid=? and w_uid=?";
               System.out.println(sql);
               statement = connection.prepareStatement(sql);
               statement.setInt(1, wid);
               statement.setInt(2, w_uid);
               a=statement.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
               JDBCUtil.closeDB(connection, statement, null);
           }
		return a;
	}

	@Override
	public List<Weibo> FindWeiboByuid(int uid ) {
		//step5:创建List结合对象
				List<Weibo> lisWeibo=new ArrayList<Weibo>();
				Connection connection = null;
			    PreparedStatement statement = null;
			    try {
					String sql="SELECT * FROM weibo where w_uid=? order by wdate desc";
					connection = JDBCUtil.getConn();
			        statement = connection.prepareStatement(sql);
			        statement.setInt(1, uid);
			        System.out.println("uid:"+uid);
			        System.out.println("sql:"+sql);
			        ResultSet rs = statement.executeQuery();
					//step6:遍历结果集
						while (rs.next()) {
						   Weibo weibo=new Weibo();		
						   weibo.setWid(rs.getInt("wid"));
						   weibo.setWcontent(rs.getString("wcontent"));
						   weibo.setWdate(rs.getDate("wdate"));
						   weibo.setWimage(rs.getString("wimage"));
						   weibo.setWtimes(rs.getInt("wtimes"));
						   weibo.setWremarks(rs.getString("wremarks"));
						   weibo.setWcountcomment(rs.getInt("wcountcomment"));
						   weibo.setW_uid(rs.getInt("w_uid"));
						   weibo.setW_wid(rs.getInt("w_wid"));	
						   //判断该微博是否被用户收藏了
						   ICollectionDao collectiondao = new CollectionDaoImpl();
						   if(collectiondao.judgeColletionBywid(uid, rs.getInt("wid"))==1) {
							   weibo.setFlag(1);
						   }else {
							   weibo.setFlag(0);
						   }
						   sql = "SELECT * FROM users where uid=?";
						   statement = connection.prepareStatement(sql);
						   statement.setInt(1, rs.getInt("w_uid"));
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
							    weibo.setUse(use);
						   }
						   //step6:添加到list集合
						   lisWeibo.add(weibo);
						}
			   } catch (SQLException e) {
		           e.printStackTrace();
		       } finally {
		           JDBCUtil.closeDB(connection, statement, null);
		       }
			   return lisWeibo;
	}
	//实现微博转发需要三个方法(方法一：插入一条微博，标记w_wid  方法二：通过w_wid获取上一条微博  方法三：更新所有相关微博转发次数)
	//方法一：
	//实现微博转发功能,注意这个功能的这条转发记录没有实现转发次数加1
	@Override
	public int forwardWeibo(Weibo weibo, int uid) {
	       Connection connection = null;
	       PreparedStatement statement = null;
	       int  a = 0;
	        try {
	        	String sql="insert into weibo(wcontent,wdate,wimage,wtimes,w_uid,wremarks,wcountcomment,w_wid) values(?,now(),?,?,?,null,0,?)";
	            connection = JDBCUtil.getConn();
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, weibo.getWcontent());
	            statement.setString(2, weibo.getWimage());
	            statement.setInt(3, weibo.getWtimes()+1);
	            statement.setInt(4, uid);
	            statement.setInt(5, weibo.getWid());
	            a=statement.executeUpdate();
	        } catch (SQLException e) {
	        	a=0;
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeDB(connection, statement, null);
	        }
		return a;
	}
	//方法二  通过w_wid获取原创微博,uid的作用仅仅是判断我是不是收藏了该微博信息
	@Override
	public Weibo FindByw_wid(int uid,int w_wid) {
		Connection connection = null;
	    PreparedStatement statement = null;
		Weibo weibo = new Weibo();
		try {
			//step1： sql语句
			String sql="SELECT * FROM weibo where wid=?";	
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, w_wid);
			//step3:获取返回值结果集
			ResultSet rs=statement.executeQuery();
			//step4:int 变量
			if(rs.next()){
				//step6:遍历结果集
				  weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getDate("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getDate("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   weibo.setW_wid(rs.getInt("w_wid"));				   
				   //判断该微博是否被用户收藏了
				   ICollectionDao collectiondao = new CollectionDaoImpl();
				   if(collectiondao.judgeColletionBywid(uid, rs.getInt("wid"))==1) {
					   weibo.setFlag(1);
				   }
				   sql = "SELECT * FROM users where uid=?";
				   statement = connection.prepareStatement(sql);
				   statement.setInt(1, rs.getInt("w_uid"));
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
					    weibo.setUse(use);
				   }
			}			 
		} catch (SQLException e) {
	           e.printStackTrace();
	           return null;
	       } finally {
	           JDBCUtil.closeDB(connection, statement, null);
	       }
		return weibo;
	}
	//方法三：这个weibo是原创的，更新相关微博转发次数
	@Override
	public int updateWtimes(Weibo weibo) {
		  Connection connection = null;
	       PreparedStatement statement = null;
          int a = 0;
          try {
       	   connection = JDBCUtil.getConn();
              String sql = "UPDATE weibo SET wtimes=? where wid=? or w_wid =?";
              System.out.println(sql);
              statement = connection.prepareStatement(sql);
              statement.setInt(1, weibo.getWtimes()+1);
              statement.setInt(2, weibo.getWid());
              statement.setInt(3, weibo.getWid());
              a=statement.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              JDBCUtil.closeDB(connection, statement, null);
          }
		return a;
	}
	//获取所有是转发这条wid的原创微博的所有微博
	@Override
	public List<Weibo> FindWeibosByw_wid(int uid, int wid) {
		List<Weibo> lisWeibo=new ArrayList<Weibo>();
		Connection connection = null;
	    PreparedStatement statement = null;
	    try {
			String sql="SELECT * FROM weibo where w_wid=? order by wdate desc";
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, wid);
	        System.out.println("uid:"+uid);
	        System.out.println("sql:"+sql);
	        ResultSet rs = statement.executeQuery();
			//step6:遍历结果集
				while (rs.next()) {
				   Weibo weibo=new Weibo();		
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getDate("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   weibo.setW_wid(rs.getInt("w_wid"));	
				   //判断该微博是否被用户收藏了
				   ICollectionDao collectiondao = new CollectionDaoImpl();
				   if(collectiondao.judgeColletionBywid(uid, rs.getInt("wid"))==1) {
					   weibo.setFlag(1);
				   }else {
					   weibo.setFlag(0);
				   }
				   sql = "SELECT * FROM users where uid=?";
				   statement = connection.prepareStatement(sql);
				   statement.setInt(1, rs.getInt("w_uid"));
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
					    weibo.setUse(use);
				   }
				   //step6:添加到list集合
				   lisWeibo.add(weibo);
				}
	   } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           JDBCUtil.closeDB(connection, statement, null);
       }
	   return lisWeibo;
	}
	

}