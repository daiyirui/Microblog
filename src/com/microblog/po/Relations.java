package com.microblog.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Relations implements Serializable {
   private Integer rid;
   private Integer r_id;
   private Integer g_id;
   private Integer rstate;
   private String remarks;
   private Users ridUser = new Users();
   private Users gidUser = new Users();
   
	public String getRemarks() {
	    return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public Integer getG_id() {
		return g_id;
	}
	public void setG_id(Integer g_id) {
		this.g_id = g_id;
	}
	public Integer getRstate() {
		return rstate;
	}
	public void setRstate(Integer rstate) {
		this.rstate = rstate;
	}
	public Users getRidUser() {
		return ridUser;
	}
	public void setRidUser(Users ridUser) {
		this.ridUser = ridUser;
	}
	public Users getGidUser() {
		return gidUser;
	}
	public void setGidUser(Users gidUser) {
		this.gidUser = gidUser;
	}
	@Override
	public String toString() {
		return "Relations [rid=" + rid + ", r_id=" + r_id + ", g_id=" + g_id + ", rstate=" + rstate + ", remarks="
				+ remarks + ", ridUser=" + ridUser + ", gidUser=" + gidUser + "]";
	}
   
}
