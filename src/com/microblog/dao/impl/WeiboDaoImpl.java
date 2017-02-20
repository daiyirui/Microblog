package com.microblog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dao.IWeiboDao;
import com.microblog.dbutil.DBConn;
import com.microblog.filter.PageBean;
import com.microblog.po.Users;
import com.microblog.po.Weibo;

public class WeiboDaoImpl implements IWeiboDao {
	DBConn db=null;
	public WeiboDaoImpl(){
		db=new DBConn();
	}
	@Override
	public PageBean FindWeiboByuid(int uid, String strSQL, int currentPage,int pageSize) {
		//步骤1：创建一个PageBean对象		
		PageBean pb = new PageBean();
		//步骤2：创建一个SQL语句，用来获取emp表中记录的个数
		String strSQL1 = strSQL;
		strSQL1 = strSQL1.substring(strSQL1.toLowerCase().indexOf("from"));
		strSQL1 = "select count(*) "+strSQL1;
		System.out.println("$$"+strSQL);
		//步骤3：执行SQL语句得到结果并将结果赋值给pb对象的totalRows;
		ResultSet rs = db.execQuery(strSQL1, new Object[]{uid});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//步骤4：需要为pb对象的data属性赋值,首先获取本页的第一条数据的行标
		int start = (currentPage-1)*pageSize;
		//步骤5：创建动态的SQL语句
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{uid,start,pageSize});
		//步骤6：将获取的结果集进行封装
		List<Weibo> lstWeibo = new ArrayList<Weibo>();
		Weibo weibo=null;
		try {
			while(rs.next()){
				weibo=new Weibo();		
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getString("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("w_uid")});
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
				lstWeibo.add(weibo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//步骤7：将获取到的本页数据赋值给pb对象的data属性
		pb.setData(lstWeibo);		
		//步骤8：为其余属性赋值,无需为totalPages赋值，以为totalRows和pageSize已经被赋值
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//步骤9：将封装好的pb对象返回
		return pb;
	}	
	@Override
	public PageBean FuzzyFindWeiboByPage(int uid, String content,String strSQL, int currentPage, int pageSize) {
		//步骤1：创建一个PageBean对象		
		PageBean pb = new PageBean();
		//步骤2：创建一个SQL语句，用来获取emp表中记录的个数
		String strSQL1 = strSQL;
		strSQL1 = strSQL1.substring(strSQL1.toLowerCase().indexOf("from"));
		strSQL1 = "select count(*) "+strSQL1;
		System.out.println("$$"+strSQL);
		//步骤3：执行SQL语句得到结果并将结果赋值给pb对象的totalRows;
		ResultSet rs = db.execQuery(strSQL1, new Object[]{"%"+content+"%",uid,uid});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//步骤4：需要为pb对象的data属性赋值,首先获取本页的第一条数据的行标
		int start = (currentPage-1)*pageSize;
		//步骤5：创建动态的SQL语句
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{"%"+content+"%",uid,uid,start,pageSize});
		//步骤6：将获取的结果集进行封装
		List<Weibo> lstWeibo = new ArrayList<Weibo>();
		Weibo weibo=null;
		try {
			while(rs.next()){
				weibo=new Weibo();		
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getString("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("w_uid")});
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
				lstWeibo.add(weibo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//步骤7：将获取到的本页数据赋值给pb对象的data属性
		pb.setData(lstWeibo);		
		//步骤8：为其余属性赋值,无需为totalPages赋值，以为totalRows和pageSize已经被赋值
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//步骤9：将封装好的pb对象返回
		return pb;
	}
	@Override
	public int InsertWeibo(Weibo weibo, int uid) {
		// TODO Auto-generated method stub
		String sql="";
		int a=0;		
	    sql="insert into weibo values(null,?,now(),?,0,?,'null',0)";
		a=db.execOther(sql, new Object[]{weibo.getWcontent(),weibo.getWimage(),uid});		
		return a;
	}
	@Override
	public Weibo FindBywid(int wid) {
		// 获取单个微博信息对象
		Weibo weibo=new Weibo();
		String sql="SELECT * FROM weibo where wremarks!='no' and  wid=?";
		ResultSet rs=db.execQuery(sql, new Object[]{wid});
		try {
			if(rs.next()){
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getString("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("w_uid")});
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
			}
			return weibo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
	}
	/* 转发微博思路流程：
	 * 1.判断此微博信息是否已经转发过：select * from weibo where wcontent='北风网' and w_uid=1
	 * 2.update转发的此微博信息增加一次转发次数    update weibo set wtimes=wtimes+1 where wid=2
	 * 3.添加一样的内容的一条微博信息，外键的登陆者本人uid，作为自己的一条微博信息存储。
	 */
	@Override
	public int ForWardMicroblog(int uid, String wcontent, int wid, String wimage) {
		//step1:sql语句，判断此微博信息是否已经转发过
		String sql="select * from weibo where wremarks!='no' and  wcontent=? and w_uid=?";
		//step2：返回结果集
		ResultSet rs=db.execQuery(sql, new Object[]{wcontent,uid});
		try {
			if(rs.next()){//证明此微博信息已经转发过
				return 0;
			}else{
				//step3:update转发的此微博信息增加一次转发次数
				String sql1="update weibo set wtimes=wtimes+1 where wid=?";
				int flag=db.execOther(sql1, new Object[]{wid});
				if(flag==1){  //证明update成功
					//step4:添加一样的内容的一条微博信息
					String sql2="";
					int fla=0;
					if(wimage.equals("")){
						sql2="insert into weibo values(null,?,now(),'',0,?,null,0)";
						fla=db.execOther(sql2, new Object[]{wcontent,uid});
					}else{
						sql2="insert into weibo values(null,?,now(),?,0,?,null,0)";
						fla=db.execOther(sql2, new Object[]{wcontent,wimage,uid});
					}
					if(fla==1){
						return 1;//证明三部核心操作成功！
					}else{
						return 0;
					}
				}else{
					return 0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally{
			db.closeConn();
		}		
	}
	@Override
	public PageBean FindByPage(int uid,String strSQL, int currentPage, int pageSize) {
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
		List<Weibo> lisWeibo=new ArrayList<Weibo>();
		Weibo weibo=null;
		try {
			while (rs.next()) {
				 weibo=new Weibo();		
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getString("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("w_uid")});
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
				lisWeibo.add(weibo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:赋值相应属性
		pb.setData(lisWeibo);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:返回结果
		return pb;
	}
	@Override
	public PageBean FindByPageOnlyOwn(int uid,String strSQL, int currentPage, int pageSize) {
		//step1:创建pagebean对象,为其五个属性赋值
		PageBean pb=new PageBean();
		//step2:sql语句，用来获取weibo表中记录数量 count(*)   SELECT * FROM weibo order by wdate desc
		String sqlcount=strSQL;
		sqlcount=sqlcount.substring(sqlcount.toLowerCase().indexOf("from"));
		sqlcount = "select count(*) "+sqlcount;
		System.out.println("SqlCount:"+sqlcount);
		//step3:执行sql语句得到结果并将其结果赋值给pb的totalRows变量；
		ResultSet rs=db.execQuery(sqlcount, new Object[]{uid});
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
		rs=db.execQuery(strSQL, new Object[]{uid,start,pageSize});
		//step6:获取data结果集合
		List<Weibo> lisWeibo=new ArrayList<Weibo>();
		Weibo weibo=null;
		try {
			while (rs.next()) {
				 weibo=new Weibo();		
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getString("wdate"));
				   weibo.setWimage(rs.getString("wimage"));
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("w_uid")});
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
				lisWeibo.add(weibo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:赋值相应属性
		pb.setData(lisWeibo);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:返回结果
		return pb;
	}
    //显示登录者和其所关注人的微博信息
	@Override
	public List<Weibo> FindByLogin(int uid) {
		// TODO Auto-generated method stub
		//step1:sql语句
		String sql="SELECT * FROM weibo where wremarks!='no' and w_uid= any(select g_id from relations where r_id=?) or w_uid=? order by wdate desc";
		//step3:获取返回值结果集
		ResultSet rs=db.execQuery(sql, new Object[]{uid,uid});
		//step4:创建weibo实体类对象
		Weibo weibo=null;
		//step5:创建List结合对象
		List<Weibo> lisWeibo=new ArrayList<Weibo>();
		//step6:遍历结果集
		try {
			while (rs.next()) {
		       weibo=new Weibo();		
			   weibo.setWid(rs.getInt("wid"));
			   weibo.setWcontent(rs.getString("wcontent"));
			   weibo.setWdate(rs.getString("wdate"));
			   weibo.setWimage(rs.getString("wimage"));
			   weibo.setWtimes(rs.getInt("wtimes"));
			   weibo.setWremarks(rs.getString("wremarks"));
			   weibo.setWcountcomment(rs.getInt("wcountcomment"));
			   weibo.setW_uid(rs.getInt("w_uid"));
			   ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("w_uid")});
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
			   //step6:添加到list集合
			   lisWeibo.add(weibo);
			}
			return lisWeibo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
	}
	//显示登录者微博数量
	@Override
	public int CountByMicroblog(int uid) {
		//step1： sql语句
		String sql="SELECT count(*) FROM weibo where wremarks!='no' and  w_uid=?";		
		//step3:获取返回值结果集
		ResultSet rs=db.execQuery(sql, new Object[]{uid});
		//step4:int 变量
		int countblog=0;
		try {
			if(rs.next()){
				//step6:遍历结果集
				countblog=rs.getInt(1);
				return countblog;
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
}