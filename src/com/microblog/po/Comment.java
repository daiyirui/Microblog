package com.microblog.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Comment implements Serializable {
    private Integer cid;
    private Integer c_wid;
    private Integer c_uid;
    private String ccontent;
    private String cdate;
    private String cremarks;
    private String cimages;
    private Weibo weibo=new Weibo();
    private Users use=new Users();
    public Comment(){}
    public Comment(Integer cid,Integer c_wid,Integer c_uid, String ccontent,
    		String cdate, String cremarks,String cimages,Weibo weibo, Users use){
    	this.cid=cid;
    	this.c_wid=c_wid;
    	this.c_uid=c_uid;
    	this.ccontent=ccontent;
    	this.cdate=cdate;
    	this.cremarks=cremarks;    	
    	this.cimages=cimages;
    	this.weibo=weibo;
    	this.use=use;
    }
    public Comment(Integer c_wid,Integer c_uid, String ccontent,
    		String cdate, String cremarks,String cimages,Weibo weibo, Users use){
    	this.c_wid=c_wid;
    	this.c_uid=c_uid;
    	this.ccontent=ccontent;
    	this.cdate=cdate;
    	this.cremarks=cremarks;    	
    	this.cimages=cimages;
    	this.weibo=weibo;
    	this.use=use;
    }
	public String getCimages() {
		return cimages;
	}
	public void setCimages(String cimages) {
		this.cimages = cimages;
	}
	public Weibo getWeibo() {
		return weibo;
	}
	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}
	public Users getUse() {
		return use;
	}
	public void setUse(Users use) {
		this.use = use;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getC_wid() {
		return c_wid;
	}
	public void setC_wid(Integer c_wid) {
		this.c_wid = c_wid;
	}
	public Integer getC_uid() {
		return c_uid;
	}
	public void setC_uid(Integer c_uid) {
		this.c_uid = c_uid;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getCremarks() {
		return cremarks;
	}
	public void setCremarks(String cremarks) {
		this.cremarks = cremarks;
	}
}
