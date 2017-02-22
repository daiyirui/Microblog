package com.microblog.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Collection implements Serializable {
	private Integer lid;
	private Integer l_uid;
	private String lcontent;
	private String ldate;
	private String limages;
	private String lremarks;
	private Users use = new Users();

	public Collection() {
	}

	public Collection(Integer lid, Integer l_uid, String lcontent, String ldate, String limages, String lremarks) {
		this.lid = lid;
		this.l_uid = l_uid;
		this.lcontent = lcontent;
		this.ldate = ldate;
		this.limages = limages;
		this.lremarks = lremarks;
	}

	public Collection(Integer l_uid, String lcontent, String ldate, String limages, String lremarks) {
		this.l_uid = l_uid;
		this.lcontent = lcontent;
		this.ldate = ldate;
		this.limages = limages;
		this.lremarks = lremarks;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public Integer getL_uid() {
		return l_uid;
	}

	public void setL_uid(Integer l_uid) {
		this.l_uid = l_uid;
	}

	public String getLcontent() {
		return lcontent;
	}

	public void setLcontent(String lcontent) {
		this.lcontent = lcontent;
	}

	public String getLdate() {
		return ldate;
	}

	public void setLdate(String ldate) {
		this.ldate = ldate;
	}

	public String getLimages() {
		return limages;
	}

	public void setLimages(String limages) {
		this.limages = limages;
	}

	public String getLremarks() {
		return lremarks;
	}

	public void setLremarks(String lremarks) {
		this.lremarks = lremarks;
	}

	public Users getUse() {
		return use;
	}

	public void setUse(Users use) {
		this.use = use;
	}

	@Override
	public String toString() {
		return "Collection [lid=" + lid + ", l_uid=" + l_uid + ", lcontent=" + lcontent + ", ldate=" + ldate
				+ ", limages=" + limages + ", lremarks=" + lremarks + ", use=" + use + "]";
	}

}
