package com.microblog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.microblog.dao.ICommentDao;
import com.microblog.dbutil.DBConn;
import com.microblog.filter.PageBean;
import com.microblog.po.Comment;
import com.microblog.po.Users;
import com.microblog.po.Weibo;
public class CommentDaoImpl implements ICommentDao {
    DBConn db=null;
    public CommentDaoImpl(){
    	db=new DBConn();
    }  
	@Override
	public int DeleteComment(int cid) {
		// TODO Auto-generated method stub
		String sql="delete from comment where cid=?";
		int a=db.execOther(sql, new Object[]{cid});
		return a;
	}
	@Override
	public PageBean FuzzyFindCommentByPage(int wid, String content,String strSQL, int currentPage, int pageSize) {
		//step1:创建pagebean对象,为其五个属性赋值
		PageBean pb=new PageBean();
		//step2:sql语句，用来获取weibo表中记录数量 count(*)   SELECT * FROM weibo order by wdate desc
		String strSql1=strSQL;
		strSql1=strSql1.substring(strSql1.toLowerCase().indexOf("from"));
		strSql1 = "select count(*) "+strSql1;
		System.out.println("SqlCount:"+strSql1);
		//step3:执行sql语句得到结果并将其结果赋值给pb的totalRows变量；
		ResultSet rs=db.execQuery(strSql1, new Object[]{wid,"%"+content+"%"});
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
		rs=db.execQuery(strSQL, new Object[]{wid,"%"+content+"%",start,pageSize});
		//step6:获取data结果集合
		List<Comment> lisComment=new ArrayList<Comment>();
		Comment comm=null;
		try {
			while (rs.next()) {
				comm=new Comment();
				comm.setCid(rs.getInt("cid"));
				comm.setCcontent(rs.getString("ccontent"));
				comm.setCdate(rs.getString("cdate"));
				comm.setCremarks(rs.getString("cremarks"));
				comm.setCimages(rs.getString("cimages"));
				comm.setC_uid(rs.getInt("c_uid"));
				comm.setC_wid(rs.getInt("c_wid"));			
				ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("c_uid")});
				if(re.next()){
					    Users use=new Users();
					    use.setUid(re.getInt("uid"));
					    use.setUname(re.getString("uname"));
					    use.setUpwd(re.getString("upwd"));
					    use.setUnickname(re.getString("unickname"));
					    use.setUsex(re.getString("usex"));
					    use.setUaddress(re.getString("uaddress"));
					    use.setUdate(re.getString("udate"));
					    use.setUpic(re.getString("upic"));
					    use.setUqq(re.getString("uqq"));
					    use.setUedu(re.getString("uedu"));
					    use.setUques(re.getString("uques"));
					    use.setUrealname(re.getString("urealname"));					    
					    use.setUremarks(re.getString("uremarks"));
					    comm.setUse(use);
				}
				re=db.execQuery("SELECT * FROM weibo where wid=?", new Object[]{rs.getInt("c_wid")});
				if(re.next()){
					Weibo weibo=new Weibo();		
					weibo.setWid(re.getInt("wid"));
					weibo.setWcontent(re.getString("wcontent"));
					weibo.setWdate(re.getString("wdate"));
					weibo.setWimage(re.getString("wimage"));
					weibo.setWtimes(re.getInt("wtimes"));
					weibo.setWremarks(re.getString("wremarks"));
					weibo.setWcountcomment(re.getInt("wcountcomment"));
					weibo.setW_uid(re.getInt("w_uid"));
					re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{re.getInt("w_uid")});
					if(re.next()){
						    Users use=new Users();
						    use.setUid(re.getInt("uid"));
						    use.setUname(re.getString("uname"));
						    use.setUpwd(re.getString("upwd"));
						    use.setUnickname(re.getString("unickname"));
						    use.setUsex(re.getString("usex"));
						    use.setUaddress(re.getString("uaddress"));
						    use.setUdate(re.getString("udate"));
						    use.setUpic(re.getString("upic"));
						    use.setUqq(re.getString("uqq"));
						    use.setUedu(re.getString("uedu"));
						    use.setUques(re.getString("uques"));
						    use.setUrealname(re.getString("urealname"));
						    use.setUremarks(re.getString("uremarks"));
						    weibo.setUse(use);
					   }
					comm.setWeibo(weibo);
				}				
				lisComment.add(comm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:赋值相应属性
		pb.setData(lisComment);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:返回结果
		return pb;
	}
    @Override
	public int InsertComment(Comment comm) {
    	String sql="insert into  comment values(null,?,?,?,now(),null,?);";
    	int flag=db.execOther(sql, new Object[]{comm.getC_wid(),comm.getC_uid(),comm.getCcontent(),comm.getCimages()});
    	if(flag==1){
    		String sql1="update weibo set wcountcomment=wcountcomment+1 where wid=?";
    		int up=db.execOther(sql1, new Object[]{comm.getC_wid()});
    		if(up==1){
    			return up;
    		}else{
    			return 0;
    		}
    	}else{
    		return 0;	
    	}		
	}
	@Override
	public PageBean FindByPageComment(int cid, String strSQL, int currentPage,int pageSize) {
		//step1:创建pagebean对象,为其五个属性赋值
		PageBean pb=new PageBean();
		//step2:sql语句，用来获取weibo表中记录数量 count(*)   SELECT * FROM weibo order by wdate desc
		String strSql1=strSQL;
		strSql1=strSql1.substring(strSql1.toLowerCase().indexOf("from"));
		strSql1 = "select count(*) "+strSql1;
		System.out.println("SqlCount:"+strSql1);
		//step3:执行sql语句得到结果并将其结果赋值给pb的totalRows变量；
		ResultSet rs=db.execQuery(strSql1, new Object[]{cid});
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
		rs=db.execQuery(strSQL, new Object[]{cid,start,pageSize});
		//step6:获取data结果集合
		List<Comment> lisComment=new ArrayList<Comment>();
		Comment comm=null;
		try {
			while (rs.next()) {
				comm=new Comment();
				comm.setCid(rs.getInt("cid"));
				comm.setCcontent(rs.getString("ccontent"));
				comm.setCdate(rs.getString("cdate"));
				comm.setCremarks(rs.getString("cremarks"));
				comm.setCimages(rs.getString("cimages"));
				comm.setC_uid(rs.getInt("c_uid"));
				comm.setC_wid(rs.getInt("c_wid"));			
				ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("c_uid")});
				if(re.next()){
					    Users use=new Users();
					    use.setUid(re.getInt("uid"));
					    use.setUname(re.getString("uname"));
					    use.setUpwd(re.getString("upwd"));
					    use.setUnickname(re.getString("unickname"));
					    use.setUsex(re.getString("usex"));
					    use.setUaddress(re.getString("uaddress"));
					    use.setUdate(re.getString("udate"));
					    use.setUpic(re.getString("upic"));
					    use.setUqq(re.getString("uqq"));
					    use.setUedu(re.getString("uedu"));
					    use.setUques(re.getString("uques"));
					    use.setUrealname(re.getString("urealname"));					    
					    use.setUremarks(re.getString("uremarks"));
					    comm.setUse(use);
				}
				re=db.execQuery("SELECT * FROM weibo where wid=?", new Object[]{rs.getInt("c_wid")});
				if(re.next()){
					Weibo weibo=new Weibo();		
					weibo.setWid(re.getInt("wid"));
					weibo.setWcontent(re.getString("wcontent"));
					weibo.setWdate(re.getString("wdate"));
					weibo.setWimage(re.getString("wimage"));
					weibo.setWtimes(re.getInt("wtimes"));
					weibo.setWremarks(re.getString("wremarks"));
					weibo.setWcountcomment(re.getInt("wcountcomment"));
					weibo.setW_uid(re.getInt("w_uid"));
					re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{re.getInt("w_uid")});
					if(re.next()){
						    Users use=new Users();
						    use.setUid(re.getInt("uid"));
						    use.setUname(re.getString("uname"));
						    use.setUpwd(re.getString("upwd"));
						    use.setUnickname(re.getString("unickname"));
						    use.setUsex(re.getString("usex"));
						    use.setUaddress(re.getString("uaddress"));
						    use.setUdate(re.getString("udate"));
						    use.setUpic(re.getString("upic"));
						    use.setUqq(re.getString("uqq"));
						    use.setUedu(re.getString("uedu"));
						    use.setUques(re.getString("uques"));
						    use.setUrealname(re.getString("urealname"));
						    use.setUremarks(re.getString("uremarks"));
						    weibo.setUse(use);
					   }
					comm.setWeibo(weibo);
				}				
				lisComment.add(comm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:赋值相应属性
		pb.setData(lisComment);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:返回结果
		return pb;
	}
}
