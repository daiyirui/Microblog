package com.microblog.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Percontent implements Serializable {
   private Integer pcid;
   private Integer pc_pid;
   private String pcitems;
   private String pcurl;
   private String pcremarks;
   public Percontent(){}
   public Percontent(Integer pcid, Integer pc_pid, String pcitems, String pcurl,String pcremarks){
	   this.pcid=pcid;
	   this.pc_pid=pc_pid;
	   this.pcitems=pcitems;
	   this.pcurl=pcurl;
	   this.pcremarks=pcremarks;
   }
   public Percontent(Integer pc_pid, String pcitems, String pcurl,String pcremarks){	   
	   this.pc_pid=pc_pid;
	   this.pcitems=pcitems;
	   this.pcurl=pcurl;
	   this.pcremarks=pcremarks;
   }
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
}
