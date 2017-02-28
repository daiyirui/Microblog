package com.microblog.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Weibo implements Serializable {
   private Integer wid;
   private String wcontent;
   private Date wdate;
   private String wimage;
   private String wremarks;
   private Integer wtimes;
   private Integer w_uid;//
   private Integer wcountcomment;
   //判断该微博是否是转发过来的，0代表不是，假如是代表转发微博的wid
   private Integer w_wid;
   //判断是否被登陆用户收藏标记
   private Integer flag;
   private Users use=new Users();

 
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
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
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
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public Integer getW_wid() {
		return w_wid;
	}
	public void setW_wid(Integer w_wid) {
		this.w_wid = w_wid;
	}
	
	@Override
	public String toString() {
		return "Weibo [wid=" + wid + ", wcontent=" + wcontent + ", wdate="
				+ wdate + ", wimage=" + wimage + ", wremarks=" + wremarks
				+ ", wtimes=" + wtimes + ", w_uid=" + w_uid
				+ ", wcountcomment=" + wcountcomment + ", w_wid=" + w_wid
				+ ", flag=" + flag + ", use=" + use + "]";
	}
	
}
