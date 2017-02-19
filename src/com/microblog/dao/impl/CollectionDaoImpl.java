package com.microblog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dao.ICollectionDao;
import com.microblog.dbutil.DBConn;
import com.microblog.filter.PageBean;
import com.microblog.po.Collection;
import com.microblog.po.Users;

public class CollectionDaoImpl implements ICollectionDao {
    DBConn db=null;
	public CollectionDaoImpl() {
		// TODO Auto-generated constructor stub
    	db=new DBConn();
	}	
	@Override
	public PageBean FuzzyFindCollectionByuid(int uid, String content,String strSQL, int currentPage, int pageSize) {		
		//step1:����pagebean����,Ϊ��������Ը�ֵ
		PageBean pb=new PageBean();
		//step2:sql��䣬������ȡweibo���м�¼����
		String strSql1=strSQL;
		strSql1=strSql1.substring(strSql1.toLowerCase().indexOf("from"));
		strSql1 = "select count(*) "+strSql1;
		System.out.println("SqlCount:"+strSql1);
		//step3:ִ��sql���õ��������������ֵ��pb��totalRows������
		ResultSet rs=db.execQuery(strSql1, new Object[]{"%"+content+"%",uid});
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
		rs=db.execQuery(strSQL, new Object[]{"%"+content+"%",uid,start,pageSize});
		//step6:��ȡdata�������
		List<Collection> lisColl=new ArrayList<Collection>();
		Collection coll=null;
		try {
			while (rs.next()) {
				coll=new Collection();
				coll.setLid(rs.getInt("lid"));
				coll.setLcontent(rs.getString("lcontent"));
				coll.setLdate(rs.getString("ldate"));
				coll.setLimages(rs.getString("limages"));
				coll.setLremarks(rs.getString("lremarks"));
				coll.setL_uid(rs.getInt("l_uid"));			
				ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("l_uid")});
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
					    coll.setUse(use);
				}			
				lisColl.add(coll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:��ֵ��Ӧ����
		pb.setData(lisColl);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:���ؽ��
		return pb;
	}
	@Override
	public int DeleteCollection(int lid) {
		// TODO Auto-generated method stub
		String sql="delete from collection where lid=?";
		int a=db.execOther(sql, new Object[]{lid});
		return a;
	}

	@Override
	public int CountCollectionByLid(int lid) {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM collection where l_uid=?";
		ResultSet rs=db.execQuery(sql, new Object[]{lid});
		int count=0;
		try {
			if(rs.next()){
				count=rs.getInt(1);
				return count;
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
	public int InsertCollection(int uid, Collection coll) {
		String sql="insert into  collection values(null,?,?,now(),?,null)";
		int a=db.execOther(sql, new Object[]{uid,coll.getLcontent(),coll.getLimages()});
		return a;
	}

	@Override
	public PageBean FindCollectionByPage(int uid, String strSQL,int currentPage, int pageSize) {
		//step1:����pagebean����,Ϊ��������Ը�ֵ
		PageBean pb=new PageBean();
		//step2:sql��䣬������ȡweibo���м�¼���� count(*)   SELECT * FROM weibo order by wdate desc
		String strSql1=strSQL;
		strSql1=strSql1.substring(strSql1.toLowerCase().indexOf("from"));
		strSql1 = "select count(*) "+strSql1;
		System.out.println("SqlCount:"+strSql1);
		//step3:ִ��sql���õ��������������ֵ��pb��totalRows������
		ResultSet rs=db.execQuery(strSql1, new Object[]{uid});
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
		List<Collection> lisColl=new ArrayList<Collection>();
		Collection coll=null;
		try {
			while (rs.next()) {
				coll=new Collection();
				coll.setLid(rs.getInt("lid"));
				coll.setLcontent(rs.getString("lcontent"));
				coll.setLdate(rs.getString("ldate"));
				coll.setLimages(rs.getString("limages"));
				coll.setLremarks(rs.getString("lremarks"));
				coll.setL_uid(rs.getInt("l_uid"));			
				ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("l_uid")});
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
					    coll.setUse(use);
				}			
				lisColl.add(coll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:��ֵ��Ӧ����
		pb.setData(lisColl);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:���ؽ��
		return pb;
	}
	//ͳ�Ƶ�½��΢���ղص�����
	@Override
	public int CountCollectionByUid(int uid) {
		
		String sql="SELECT count(*) FROM collection where l_uid=?";
		ResultSet rs=db.execQuery(sql, new Object[]{uid});
		int count=0;
		try {
			if(rs.next()){
				count=rs.getInt(1);
				return count;
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
