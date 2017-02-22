package com.microblog.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Bloghot implements Serializable {
    private Integer bid;
    private Integer bstate;
    private String btitle;
    private List<Bloghotitem> bitems = new ArrayList<Bloghotitem>();
    private String bimages;
    private Integer bvote;
    private String bremarks;
    public Bloghot(){    	
    }
    public Bloghot(Integer bid, Integer bstate,String btitle,String bitems,Integer bvote,String bremarks){
    	this.bid=bid;
    	this.bstate=bstate;
    	this.btitle=btitle;
    	this.bremarks=bremarks;
    }
    public Bloghot( Integer bstate,String btitle,String bitems,Integer bvote,String bremarks){    	
    	this.bstate=bstate;
    	this.btitle=btitle;
    	this.bremarks=bremarks;
    }
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getBstate() {
		return bstate;
	}
	public void setBstate(Integer bstate) {
		this.bstate = bstate;
	}
	
	public String getBimages() {
		return bimages;
	}
	public void setBimages(String bimages) {
		this.bimages = bimages;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	
	public List<Bloghotitem> getBitems() {
		return bitems;
	}
	public void setBitems(List<Bloghotitem> bitems) {
		this.bitems = bitems;
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
	@Override
	public String toString() {
		return "Bloghot [bid=" + bid + ", bstate=" + bstate + ", btitle=" + btitle + ", bitems=" + bitems + ", bimages="
				+ bimages + ", bvote=" + bvote + ", bremarks=" + bremarks + "]";
	}
	
	
}
