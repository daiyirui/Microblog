package com.microblog.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bloghotitem implements Serializable{
	    private Integer bloghotitemid;//主键
	    private Integer bid;//热议内容外键
	    private String bitemName;//热议子选项
	    private String bitemimage;//热议子选项图片
	    private Integer bvote;//投票数
	    private String bremarks;//备注、
	    private Bloghot bloghot = new Bloghot();//该子主题对应的热议内容
		public Integer getBloghotitemid() {
			return bloghotitemid;
		}
		public void setBloghotitemid(Integer bloghotitemid) {
			this.bloghotitemid = bloghotitemid;
		}
		public Integer getBid() {
			return bid;
		}
		public void setBid(Integer bid) {
			this.bid = bid;
		}
		public String getBitemName() {
			return bitemName;
		}
		public void setBitemName(String bitemName) {
			this.bitemName = bitemName;
		}
		public String getBitemimage() {
			return bitemimage;
		}
		public void setBitemimage(String bitemimage) {
			this.bitemimage = bitemimage;
		}
		public Integer getBvote() {
			return bvote;
		}
		public void setBvote(Integer bvote) {
			this.bvote = bvote;
		}
		public String getBremarks() {
			return bremarks;
		}
		public void setBremarks(String bremarks) {
			this.bremarks = bremarks;
		}
		public Bloghot getBloghot() {
			return bloghot;
		}
		public void setBloghot(Bloghot bloghot) {
			this.bloghot = bloghot;
		}
		
		@Override
		public String toString() {
			return "Bloghotitem [bloghotitemid=" + bloghotitemid + ", bid=" + bid + ", bitemName=" + bitemName
					+ ", bitemimage=" + bitemimage + ", bvote=" + bvote + ", bremarks=" + bremarks + ", bloghot="
					+ bloghot + "]";
		}
	    
	    
}
