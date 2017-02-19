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
		//����1������һ��PageBean����		
		PageBean pb = new PageBean();
		//����2������һ��SQL��䣬������ȡemp���м�¼�ĸ���
		String strSQL1 = strSQL;
		strSQL1 = strSQL1.substring(strSQL1.toLowerCase().indexOf("from"));
		strSQL1 = "select count(*) "+strSQL1;
		System.out.println("$$"+strSQL);
		//����3��ִ��SQL���õ�������������ֵ��pb�����totalRows;
		ResultSet rs = db.execQuery(strSQL1, new Object[]{uid});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//����4����ҪΪpb�����data���Ը�ֵ,���Ȼ�ȡ��ҳ�ĵ�һ�����ݵ��б�
		int start = (currentPage-1)*pageSize;
		//����5��������̬��SQL���
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{uid,start,pageSize});
		//����6������ȡ�Ľ�������з�װ
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
		//����7������ȡ���ı�ҳ���ݸ�ֵ��pb�����data����
		pb.setData(lstWeibo);		
		//����8��Ϊ�������Ը�ֵ,����ΪtotalPages��ֵ����ΪtotalRows��pageSize�Ѿ�����ֵ
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//����9������װ�õ�pb���󷵻�
		return pb;
	}	
	@Override
	public PageBean FuzzyFindWeiboByPage(int uid, String content,String strSQL, int currentPage, int pageSize) {
		//����1������һ��PageBean����		
		PageBean pb = new PageBean();
		//����2������һ��SQL��䣬������ȡemp���м�¼�ĸ���
		String strSQL1 = strSQL;
		strSQL1 = strSQL1.substring(strSQL1.toLowerCase().indexOf("from"));
		strSQL1 = "select count(*) "+strSQL1;
		System.out.println("$$"+strSQL);
		//����3��ִ��SQL���õ�������������ֵ��pb�����totalRows;
		ResultSet rs = db.execQuery(strSQL1, new Object[]{"%"+content+"%",uid,uid});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//����4����ҪΪpb�����data���Ը�ֵ,���Ȼ�ȡ��ҳ�ĵ�һ�����ݵ��б�
		int start = (currentPage-1)*pageSize;
		//����5��������̬��SQL���
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{"%"+content+"%",uid,uid,start,pageSize});
		//����6������ȡ�Ľ�������з�װ
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
		//����7������ȡ���ı�ҳ���ݸ�ֵ��pb�����data����
		pb.setData(lstWeibo);		
		//����8��Ϊ�������Ը�ֵ,����ΪtotalPages��ֵ����ΪtotalRows��pageSize�Ѿ�����ֵ
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//����9������װ�õ�pb���󷵻�
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
		// ��ȡ����΢����Ϣ����
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
	/* ת��΢��˼·���̣�
	 * 1.�жϴ�΢����Ϣ�Ƿ��Ѿ�ת������select * from weibo where wcontent='������' and w_uid=1
	 * 2.updateת���Ĵ�΢����Ϣ����һ��ת������    update weibo set wtimes=wtimes+1 where wid=2
	 * 3.���һ�������ݵ�һ��΢����Ϣ������ĵ�½�߱���uid����Ϊ�Լ���һ��΢����Ϣ�洢��
	 */
	@Override
	public int ForWardMicroblog(int uid, String wcontent, int wid, String wimage) {
		//step1:sql��䣬�жϴ�΢����Ϣ�Ƿ��Ѿ�ת����
		String sql="select * from weibo where wremarks!='no' and  wcontent=? and w_uid=?";
		//step2�����ؽ����
		ResultSet rs=db.execQuery(sql, new Object[]{wcontent,uid});
		try {
			if(rs.next()){//֤����΢����Ϣ�Ѿ�ת����
				return 0;
			}else{
				//step3:updateת���Ĵ�΢����Ϣ����һ��ת������
				String sql1="update weibo set wtimes=wtimes+1 where wid=?";
				int flag=db.execOther(sql1, new Object[]{wid});
				if(flag==1){  //֤��update�ɹ�
					//step4:���һ�������ݵ�һ��΢����Ϣ
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
						return 1;//֤���������Ĳ����ɹ���
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
		//step1:����pagebean����,Ϊ��������Ը�ֵ
		PageBean pb=new PageBean();
		//step2:sql��䣬������ȡweibo���м�¼���� count(*)   SELECT * FROM weibo order by wdate desc
		String sqlcount=strSQL;
		sqlcount=sqlcount.substring(sqlcount.toLowerCase().indexOf("from"));
		sqlcount = "select count(*) "+sqlcount;
		System.out.println("SqlCount:"+sqlcount);
		//step3:ִ��sql���õ��������������ֵ��pb��totalRows������
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
		//step4:Ϊpb��data���Ը�ֵ������Ϊ��ȡ��ҳ��һ����¼�����б�
		int start=(currentPage-1)*pageSize;
		//step5:������ֵdata��sql���
		strSQL=strSQL+" limit ?,? ";
		rs=db.execQuery(strSQL, new Object[]{uid,uid,start,pageSize});
		//step6:��ȡdata�������
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
		//step7:��ֵ��Ӧ����
		pb.setData(lisWeibo);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:���ؽ��
		return pb;
	}
	@Override
	public PageBean FindByPageOnlyOwn(int uid,String strSQL, int currentPage, int pageSize) {
		//step1:����pagebean����,Ϊ��������Ը�ֵ
		PageBean pb=new PageBean();
		//step2:sql��䣬������ȡweibo���м�¼���� count(*)   SELECT * FROM weibo order by wdate desc
		String sqlcount=strSQL;
		sqlcount=sqlcount.substring(sqlcount.toLowerCase().indexOf("from"));
		sqlcount = "select count(*) "+sqlcount;
		System.out.println("SqlCount:"+sqlcount);
		//step3:ִ��sql���õ��������������ֵ��pb��totalRows������
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
		//step4:Ϊpb��data���Ը�ֵ������Ϊ��ȡ��ҳ��һ����¼�����б�
		int start=(currentPage-1)*pageSize;
		//step5:������ֵdata��sql���
		strSQL=strSQL+" limit ?,? ";
		rs=db.execQuery(strSQL, new Object[]{uid,start,pageSize});
		//step6:��ȡdata�������
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
		//step7:��ֵ��Ӧ����
		pb.setData(lisWeibo);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:���ؽ��
		return pb;
	}
    //��ʾ��¼�ߺ�������ע�˵�΢����Ϣ
	@Override
	public List<Weibo> FindByLogin(int uid) {
		// TODO Auto-generated method stub
		//step1:sql���
		String sql="SELECT * FROM weibo where wremarks!='no' and w_uid= any(select g_id from relations where r_id=?) or w_uid=? order by wdate desc";
		//step3:��ȡ����ֵ�����
		ResultSet rs=db.execQuery(sql, new Object[]{uid,uid});
		//step4:����weiboʵ�������
		Weibo weibo=null;
		//step5:����List��϶���
		List<Weibo> lisWeibo=new ArrayList<Weibo>();
		//step6:���������
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
			   //step6:��ӵ�list����
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
	//��ʾ��¼��΢������
	@Override
	public int CountByMicroblog(int uid) {
		//step1�� sql���
		String sql="SELECT count(*) FROM weibo where wremarks!='no' and  w_uid=?";		
		//step3:��ȡ����ֵ�����
		ResultSet rs=db.execQuery(sql, new Object[]{uid});
		//step4:int ����
		int countblog=0;
		try {
			if(rs.next()){
				//step6:���������
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