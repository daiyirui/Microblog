package com.microblog.test;

import org.junit.Test;

import com.microblog.dao.IRelationsDao;
import com.microblog.dao.impl.RelationsDaoImpl;

public class MyTest {
	IRelationsDao relatinDao = new RelationsDaoImpl();
	@Test
    public void test(){
		
		System.out.println(relatinDao.FindAllMyInterestByuid(2));
	}
	public static void main(String[] args) {
		System.out.println(ToJson("北京市 四川省 云南省 浙江省 河北省 山西省 内蒙古自治区 辽宁省 吉林省 黑龙江省 上海市 江苏省 浙江省 安徽省 福建省 江西省 山东省 河南省 湖北省 湖南省 广东省 广西壮族自治 海南省 重庆市 贵州省 西藏自治区 陕西省 甘肃省 青海省 宁夏回族自治区 新疆维吾尔自治区 台湾省 香港特别行政区 澳门特别行政区 "));
	}
	
	/**
	 * 举例："东城区 西城区 崇文区 宣武区 朝阳区 海淀区 丰台区 石景山区 顺义区 昌平区 门头沟区 通州区 房山区 大兴区 怀柔区 平谷区 延庆县 密云县"
	 * 转换为['东城区','西城区','崇文区','宣武区','朝阳区','海淀区','丰台区','石景山区','顺义区','昌平区','门头沟区','通州区','房山区','大兴区','怀柔区','平谷区','延庆县','密云县']
	 * 
	 * 天津市
	 * 
	 * 
	 * @param str
	 * @return
	 */
	public static String ToJson(String str){
		StringBuilder sb =new StringBuilder();
		String[] strs = str.split(" ");
		sb.append("[\"");
		for(int i = 0;i<strs.length-1;i++) {
			sb.append(strs[i]);
			sb.append("\",\"");
		}
		sb.append(strs[strs.length-1]);
		sb.append("\"]");
		return sb.toString();
		
	}
}
