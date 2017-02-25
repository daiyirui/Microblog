package com.microblog.po;

import java.util.Date;

@SuppressWarnings("serial")
public class Admins implements java.io.Serializable{
    private Integer aid;
    private Integer a_pid;//���
    private String aname;
    private String apwd;
    private Date adate;
    private String arealname;
    private String asex;
    private String aremarks;
   
    
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getA_pid() {
		return a_pid;
	}
	public void setA_pid(Integer a_pid) {
		this.a_pid = a_pid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public Date getAdate() {
		return adate;
	}
	public void setAdate(Date adate) {
		this.adate = adate;
	}
	public String getArealname() {
		return arealname;
	}
	public void setArealname(String arealname) {
		this.arealname = arealname;
	}
	public String getAsex() {
		return asex;
	}
	public void setAsex(String asex) {
		this.asex = asex;
	}
	public String getAremarks() {
		return aremarks;
	}
	public void setAremarks(String aremarks) {
		this.aremarks = aremarks;
	}
	@Override
	public String toString() {
		return "Admins [aid=" + aid + ", a_pid=" + a_pid + ", aname=" + aname + ", apwd=" + apwd + ", adate=" + adate
				+ ", arealname=" + arealname + ", asex=" + asex + ", aremarks=" + aremarks + "]";
	}
	
}
