package com.microblog.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Users implements Serializable {
   private Integer uid;
   private String uname;
   private String upwd;
   private String unickname;
   private String usex; 
   private String uaddress;
   private String udate;
   private String uqq;
   private String uedu;
   private String upic;
   private String uques;
   private String urealname;
   private String uremarks;
   public Users(){}
   public Users(Integer uid,String uname,String upwd,String unickname,String usex,String uaddress,
             String udate,String uqq,String uedu,String upic,String uques,String urealname,String uremarks){
	   this.uid=uid;
	   this.uname=uname;
	   this.upwd=upwd;
	   this.unickname=unickname;
	   this.usex=usex;
	   this.uaddress=uaddress;
	   this.udate=udate;
	   this.uqq=uqq;
	   this.uedu=uedu;
	   this.upic=upic;
	   this.uques=uques;
	   this.urealname=urealname;
	   this.uremarks=uremarks;
   }
   public Users(String uname,String upwd,String unickname,String usex,String uaddress,
           String udate,String uqq,String uedu,String upic,String uques,String urealname,String uremarks){	   
	   this.uname=uname;
	   this.upwd=upwd;
	   this.unickname=unickname;
	   this.usex=usex;
	   this.uaddress=uaddress;
	   this.udate=udate;
	   this.uqq=uqq;
	   this.uedu=uedu;
	   this.upic=upic;
	   this.uques=uques;
	   this.urealname=urealname;
	   this.uremarks=uremarks;
   }
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUnickname() {
		return unickname;
	}
	public void setUnickname(String unickname) {
		this.unickname = unickname;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	public String getUqq() {
		return uqq;
	}
	public void setUqq(String uqq) {
		this.uqq = uqq;
	}
	public String getUedu() {
		return uedu;
	}
	public void setUedu(String uedu) {
		this.uedu = uedu;
	}
	public String getUpic() {
		return upic;
	}
	public void setUpic(String upic) {
		this.upic = upic;
	}
	public String getUques() {
		return uques;
	}
	public void setUques(String uques) {
		this.uques = uques;
	}
	public String getUrealname() {
		return urealname;
	}
	public void setUrealname(String urealname) {
		this.urealname = urealname;
	}
	public String getUremarks() {
		return uremarks;
	}
	public void setUremarks(String uremarks) {
		this.uremarks = uremarks;
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", unickname=" + unickname + ", usex="
				+ usex + ", uaddress=" + uaddress + ", udate=" + udate + ", uqq=" + uqq + ", uedu=" + uedu + ", upic="
				+ upic + ", uques=" + uques + ", urealname=" + urealname + ", uremarks=" + uremarks + "]";
	}
	   
}
