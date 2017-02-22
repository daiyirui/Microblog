package com.microblog.common;

import java.util.HashSet;
import java.util.List;

public class CollectionUtils {
	
	 public   static   List  removeDuplicate(List list)   { 
		    HashSet h  =   new  HashSet(list); 
		    list.clear(); 
		    list.addAll(h); 
		    return list; 
		} 
}
