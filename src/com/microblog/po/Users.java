package com.microblog.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Users implements Serializable {
   private Integer uid;
   private String uname;
   private String upwd;
   private String unickname;
   private String usex; 
   private String uaddress;
   private Date udate;
   private String uqq;
   private String uedu;
   private String upic;
   private String uques;
   private String uemail;
   private String urealname;
   private String uremarks;
   public Users(){}
 
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
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
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
	
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", unickname=" + unickname + ", usex="
				+ usex + ", uaddress=" + uaddress + ", udate=" + udate + ", uqq=" + uqq + ", uedu=" + uedu + ", upic="
				+ upic + ", uques=" + uques + ", uemail=" + uemail + ", urealname=" + urealname + ", uremarks="
				+ uremarks + "]";
	}

}
