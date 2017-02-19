package com.microblog.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Weibo implements Serializable {
   private Integer wid;
   private String wcontent;
   private String wdate;
   private String wimage;
   private String wremarks;
   private Integer wtimes;
   private Integer w_uid;//Íâ¼ü
   private Integer wcountcomment;
   private Users use=new Users();

   public Weibo(){}
   public Weibo(Integer wid,String wcontent,String wdate,String wimage,String wremarks,Integer wtimes,Integer w_uid,Integer wcountcomment){
	   this.wid=wid;
	   this.wcontent=wcontent;
	   this.wdate=wdate;
	   this.wimage=wimage;
	   this.wremarks=wremarks;
	   this.wtimes=wtimes;
	   this.w_uid=w_uid;
	   this.wcountcomment=wcountcomment;
   }
   public Weibo(String wcontent,String wdate,String wimage,String wremarks,Integer wtimes,Integer w_uid,Integer wcountcomment){	
	   this.wcontent=wcontent;
	   this.wdate=wdate;
	   this.wimage=wimage;
	   this.wremarks=wremarks;
	   this.wtimes=wtimes;
	   this.w_uid=w_uid;
	   this.wcountcomment=wcountcomment;
   }
    public Users getUse() {
		return use;
	}
	public void setUse(Users use) {
		this.use = use;
	}
	public Integer getWcountcomment() {
	     return wcountcomment;
    }
    public void setWcountcomment(Integer wcountcomment) {
	     this.wcountcomment = wcountcomment;
    }
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public String getWcontent() {
		return wcontent;
	}
	public void setWcontent(String wcontent) {
		this.wcontent = wcontent;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getWimage() {
		return wimage;
	}
	public void setWimage(String wimage) {
		this.wimage = wimage;
	}
	public String getWremarks() {
		return wremarks;
	}
	public void setWremarks(String wremarks) {
		this.wremarks = wremarks;
	}
	public Integer getWtimes() {
		return wtimes;
	}
	public void setWtimes(Integer wtimes) {
		this.wtimes = wtimes;
	}
	public Integer getW_uid() {
		return w_uid;
	}
	public void setW_uid(Integer w_uid) {
		this.w_uid = w_uid;
	}
}
