package com.microblog.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Percontent implements Serializable {
   private Integer pcid;
   private Integer pc_pid;
   private String pcitems;
   private String pcurl;
   private String pcremarks;
	public Integer getPcid() {
		return pcid;
	}
	public void setPcid(Integer pcid) {
		this.pcid = pcid;
	}
	public Integer getPc_pid() {
		return pc_pid;
	}
	public void setPc_pid(Integer pc_pid) {
		this.pc_pid = pc_pid;
	}
	public String getPcitems() {
		return pcitems;
	}
	public void setPcitems(String pcitems) {
		this.pcitems = pcitems;
	}
	public String getPcurl() {
		return pcurl;
	}
	public void setPcurl(String pcurl) {
		this.pcurl = pcurl;
	}
	public String getPcremarks() {
		return pcremarks;
	}
	public void setPcremarks(String pcremarks) {
		this.pcremarks = pcremarks;
	}
	@Override
	public String toString() {
		return "Percontent [pcid=" + pcid + ", pc_pid=" + pc_pid + ", pcitems=" + pcitems + ", pcurl=" + pcurl
				+ ", pcremarks=" + pcremarks + "]";
	}
	
}
