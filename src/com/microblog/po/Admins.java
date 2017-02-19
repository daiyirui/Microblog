package com.microblog.po;

@SuppressWarnings("serial")
public class Admins implements java.io.Serializable{
    private Integer aid;
    private Integer a_pid;//Íâ¼ü
    private String aname;
    private String apwd;
    private String adate;
    private String arealname;
    private String asex;
    private String aremarks;
    public Admins(){    	
    }
    public Admins(Integer aid,Integer a_pid,String aname,String apwd,
    		String adate,String arealname,String asex,String aremarks){
    	this.a_pid=a_pid;
    	this.aid=aid;
    	this.aname=aname;
    	this.apwd=apwd;
    	this.adate=adate;
    	this.arealname=arealname;
    	this.asex=asex;
    	this.aremarks=aremarks;
    }
    public Admins(Integer a_pid,String aname,String apwd,
    		String adate,String arealname,String asex,String aremarks){
    	this.a_pid=a_pid;    	
    	this.aname=aname;
    	this.apwd=apwd;
    	this.adate=adate;
    	this.arealname=arealname;
    	this.asex=asex;
    	this.aremarks=aremarks;
    }
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
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
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
}
