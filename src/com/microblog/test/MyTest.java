package com.microblog.test;

import org.junit.Test;

import com.microblog.dao.IRelationsDao;
import com.microblog.dao.impl.RelationsDaoImpl;

public class MyTest {
	IRelationsDao relatinDao = new RelationsDaoImpl();
	@Test
    public void test(){
		
		//System.out.println(relatinDao.FindRelationByuid(5,2));
		System.out.println(relatinDao.FindAllMyInterestByuid(2));
	}
	public static void main(String[] args) {
		System.out.println(ToJson("东城区 西城区 崇文区 宣武区 朝阳区 海淀区 丰台区 石景山区 顺义区 昌平区 门头沟区 通州区 房山区 大兴区 怀柔区 平谷区 延庆县 密云县 "));
	}
	
	/**
	 * 举例："东城区 西城区 崇文区 宣武区 朝阳区 海淀区 丰台区 石景山区 顺义区 昌平区 门头沟区 通州区 房山区 大兴区 怀柔区 平谷区 延庆县 密云县"
	 * 转换为['东城区','西城区','崇文区','宣武区','朝阳区','海淀区','丰台区','石景山区','顺义区','昌平区','门头沟区','通州区','房山区','大兴区','怀柔区','平谷区','延庆县','密云县']
	 * 
	 * @param str
	 * @return
	 */
	public static String ToJson(String str){
		StringBuilder sb =new StringBuilder();
		String[] strs = str.split(" ");
		sb.append("['");
		for(int i = 0;i<strs.length-1;i++) {
			sb.append(strs[i]);
			sb.append("','");
		}
		sb.append(strs[strs.length-1]);
		sb.append("']");
		return sb.toString();
		
	}
}
