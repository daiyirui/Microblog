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
    //辨别是不是回复哪一条评论还是评论微博信息的，0代表评论微博，不为0代表回复哪一条评论信息
    private Integer c_cid;
    //辨别该评论是不是被用户删除,用户只对自己的微博的评论信息有删除权利，0代表没有删除，-1代表删除
    private Integer flag;
    private Weibo weibo=new Weibo();
    private Users use=new Users();
  
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
	public Integer getC_cid() {
		return c_cid;
	}
	public void setC_cid(Integer c_cid) {
		this.c_cid = c_cid;
	}
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", c_wid=" + c_wid + ", c_uid=" + c_uid
				+ ", ccontent=" + ccontent + ", cdate=" + cdate + ", cremarks="
				+ cremarks + ", cimages=" + cimages + ", c_cid=" + c_cid
				+ ", flag=" + flag + ", weibo=" + weibo + ", use=" + use + "]";
	}
	
}
