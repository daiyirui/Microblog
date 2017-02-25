//JavaScript Document
function checkUserName() {
	var name=document.getElementById("username");
	var namemsg=document.getElementById("usernamemsg");
	//判断输入用户名
	if(name.value==""){
		namemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>请输入用户名</font>";
		return false;
	}else if(name.value.length<4||name.value.length>16){
		namemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>请输入4-16个字符</font>";
		return false;
	}else {
		namemsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>用户名正确!</font>";
		return true;
	}
}
function checkPassword(){
	var password=document.getElementById("password");
	var passwordmsg=document.getElementById("passwordmsg");
	if(password.value.length<6||name.value.length>12){
		passwordmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>密码长度为6-12个字符</font>";
		return false;
	}
	//判断是否为空
	if(password==""){
		passwordmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>密码不能为空</font>";
		return false;
	}else{
		passwordmsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>密码格式正确</font>";
		return true;
	}
}

function checkRpassword(){
	var password=document.getElementById("password");
	var rpassword=document.getElementById("rpassword");
	var rpasswordmsg=document.getElementById("rpasswordmsg");
	if((rpassword.value!=password.value)&&(rpassword.value!="")){
		rpasswordmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>两次密码输入不一致</font>";
	    return false;
	}
	if(rpassword.value!=""){
		rpasswordmsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>密码输入正确</font>";
		return true;
	}
}

function checkRealName(){
	var name=document.getElementById("urealname");
	var namemsg=document.getElementById("urealnamemsg");
	//判断输入真实姓名
	if(name.value==""){
		namemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>请输入真实姓名</font>";
		return false;
	}else if(name.value.length<2||name.value.length>6){
		namemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>请输入2-6个字符</font>";
		return false;
	}else {
		namemsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>输入格式正确!</font>";
		return true;
	}
	
}


function checkQQ(){
	var qq = document.getElementById("qq");
	var qqmsg = document.getElementById("qqmsg");
	if(qq.value.length==0){
		qqmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>qq不能为空</font>";
        return false;
	}
	var reg =/^[1-9]\d{4,8}$/;
	qq_Flag =  reg.test(qq.value);
	if(qq_Flag){
		qqmsg.innerHTML = "<img src='icon/ok.png' align='absmiddle'><font color='green'>qq格式正确</font>";
		return true;
	}else{
		qqmsg.innerHTML = "<img src='icon/err.png' align='absmiddle'><font color='red'>对不起，您输入的QQ号码格式错误</font>";
		return false;
	}
}

function checkEmail(){
	var email=document.getElementById("email");
	var emailmsg=document.getElementById("emailmsg");
	if(email.value.length==0){
		emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email不能为空</font>";
		return false;
	}else if(email.value.indexOf("@",0)==-1){
		emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email格式不正确\n必须包含@</font>";
		return false;
	}else if(email.value.indexOf(".",0)==-1){
		emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email格式不正确\n必须包含.</font>";
		return false;
	}else if(email.value.length<6){
		emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email长度不能小于6</font>";
		return false;
	}
	var reg=/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;
	if(reg.test(email.value)) {
	    emailmsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>Email格式正确</font>";
		return true;
	}else {
		 emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email格式不正确</font>";
		 return false;
	}

}

function checkNickname(){
	var nickname=document.getElementById("nickname");
	var namemsg=document.getElementById("nicknamemsg");
	//判断输入用户名
	if(nickname.value==""){
		nicknamemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>请输入用户昵称</font>";
		return false;
	}else if(nickname.value.length<4||nickname.value.length>16){
		nicknamemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>请输入4-16个字符</font>";
		return false;
	}else {
		nicknamemsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>用户昵称格式正确!</font>";
		return true;
	}
}
//省份验证出错了，上面这些验证没有问题
function CheckProvince(){
	var flag = 0;
	var province=document.getElementById("province");
	var provincemsg=document.getElementById("provincemsg");
	var provinces=["北京市","四川省","云南省","浙江省","河北省","山西省","内蒙古自治区","辽宁省","吉林省","黑龙江省","上海市","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","广西壮族自治","海南省","重庆市","贵州省","西藏自治区","陕西省","甘肃省","青海省","宁夏回族自治区","新疆维吾尔自治区","台湾省","香港特别行政区","澳门特别行政区"];
    for(var i=0;i < provinces.size();i++) {
    	if(provinces[i]==province) {
    		flag =1;
    	}
    }
    if(flag==1) {
    	provincemsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>省份输入正确!</font>";
    }else {
    	provincemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>可能不存在这个省份（城市）或者格式输入错误，正确格式如：北京市，上海市，江西省等</font>";
    }
}
//鼠标悬停图片效果
function xianshi(img){
	if(myBrowser()=="IE"){
		img.filters.alpha.opacity=100;
	}
	if(myBrowser()=="FF"){
		img.style.opacity=1;
	}
}
//鼠标离开图片效果
function huifu(img){
	if(myBrowser()=="IE"){
		img.filters.alpha.opacity=70;
	}
	if(myBrowser()=="FF"){
		img.style.opacity=0.7;
	}
}		
//表单验证
function checkForm(){
	if(document.getElementById("userid").value == ""){
		alert("用户名必须填写！");
		document.getElementById("userid").focus();
		return false;
	}else if(document.getElementById("password").value==""){
		alert("密码必须填写！");
		document.getElementById("password").focus();
		return false;
	}else{
		var form=document.getElementById("Checkform").value;		
		form.submit();
	}
}

var where = new Array(35);
function comefrom(loca,locacity) { this.loca = loca; this.locacity = locacity; }
where[0]= new comefrom("请选择省份名","请选择城市名");
where[1] = new comefrom("北京","|东城|西城|崇文|宣武|朝阳|丰台|石景山|海淀|门头沟|房山|通州|顺义|昌平|大兴|平谷|怀柔|密云|延庆");  //牛图库JS特效，http://js.niutuku.com/
where[2] = new comefrom("上海","|黄浦|卢湾|徐汇|长宁|静安|普陀|闸北|虹口|杨浦|闵行|宝山|嘉定|浦东|金山|松江|青浦|南汇|奉贤|崇明");//牛图库JS特效，http://js.niutuku.com/
where[3] = new comefrom("天津","|和平|东丽|河东|西青|河西|津南|南开|北辰|河北|武清|红挢|塘沽|汉沽|大港|宁河|静海|宝坻|蓟县");
where[4] = new comefrom("重庆","|万州|涪陵|渝中|大渡口|江北|沙坪坝|九龙坡|南岸|北碚|万盛|双挢|渝北|巴南|黔江|长寿|綦江|潼南|铜梁|大足|荣昌|壁山|梁平|城口|丰都|垫江|武隆|忠县|开县|云阳|奉节|巫山|巫溪|石柱|秀山|酉阳|彭水|江津|合川|永川|南川");
where[5] = new comefrom("河北","|石家庄|邯郸|邢台|保定|张家口|承德|廊坊|唐山|秦皇岛|沧州|衡水");
where[6] = new comefrom("山西","|太原|大同|阳泉|长治|晋城|朔州|吕梁|忻州|晋中|临汾|运城");
where[7] = new comefrom("内蒙古","|呼和浩特|包头|乌海|赤峰|呼伦贝尔盟|阿拉善盟|哲里木盟|兴安盟|乌兰察布盟|锡林郭勒盟|巴彦淖尔盟|伊克昭盟");
where[8] = new comefrom("辽宁","|沈阳|大连|鞍山|抚顺|本溪|丹东|锦州|营口|阜新|辽阳|盘锦|铁岭|朝阳|葫芦岛");
where[9] = new comefrom("吉林","|长春|吉林|四平|辽源|通化|白山|松原|白城|延边");
where[10] = new comefrom("黑龙江","|哈尔滨|齐齐哈尔|牡丹江|佳木斯|大庆|绥化|鹤岗|鸡西|黑河|双鸭山|伊春|七台河|大兴安岭");
where[11] = new comefrom("江苏","|南京|镇江|苏州|南通|扬州|盐城|徐州|连云港|常州|无锡|宿迁|泰州|淮安");
where[12] = new comefrom("浙江","|杭州|宁波|温州|嘉兴|湖州|绍兴|金华|衢州|舟山|台州|丽水");
where[13] = new comefrom("安徽","|合肥|芜湖|蚌埠|马鞍山|淮北|铜陵|安庆|黄山|滁州|宿州|池州|淮南|巢湖|阜阳|六安|宣城|亳州");
where[14] = new comefrom("福建","|福州|厦门|莆田|三明|泉州|漳州|南平|龙岩|宁德");
where[15] = new comefrom("江西","|南昌市|景德镇|九江|鹰潭|萍乡|新馀|赣州|吉安|宜春|抚州|上饶");
where[16] = new comefrom("山东","|济南|青岛|淄博|枣庄|东营|烟台|潍坊|济宁|泰安|威海|日照|莱芜|临沂|德州|聊城|滨州|菏泽");
where[17] = new comefrom("河南","|郑州|开封|洛阳|平顶山|安阳|鹤壁|新乡|焦作|濮阳|许昌|漯河|三门峡|南阳|商丘|信阳|周口|驻马店|济源");
where[18] = new comefrom("湖北","|武汉|宜昌|荆州|襄樊|黄石|荆门|黄冈|十堰|恩施|潜江|天门|仙桃|随州|咸宁|孝感|鄂州");
where[19] = new comefrom("湖南","|长沙|常德|株洲|湘潭|衡阳|岳阳|邵阳|益阳|娄底|怀化|郴州|永州|湘西|张家界");
where[20] = new comefrom("广东","|广州|深圳|珠海|汕头|东莞|中山|佛山|韶关|江门|湛江|茂名|肇庆|惠州|梅州|汕尾|河源|阳江|清远|潮州|揭阳|云浮");
where[21] = new comefrom("广西","|南宁|柳州|桂林|梧州|北海|防城港|钦州|贵港|玉林|南宁地区|柳州地区|贺州|百色|河池");
where[22] = new comefrom("海南","|海口|三亚");
where[23] = new comefrom("四川","|成都|绵阳|德阳|自贡|攀枝花|广元|内江|乐山|南充|宜宾|广安|达川|雅安|眉山|甘孜|凉山|泸州");
where[24] = new comefrom("贵州","|贵阳|六盘水|遵义|安顺|铜仁|黔西南|毕节|黔东南|黔南");
where[25] = new comefrom("云南","|昆明|大理|曲靖|玉溪|昭通|楚雄|红河|文山|思茅|西双版纳|保山|德宏|丽江|怒江|迪庆|临沧");
where[26] = new comefrom("西藏","|拉萨|日喀则|山南|林芝|昌都|阿里|那曲");
where[27] = new comefrom("陕西","|西安|宝鸡|咸阳|铜川|渭南|延安|榆林|汉中|安康|商洛");
where[28] = new comefrom("甘肃","|兰州|嘉峪关|金昌|白银|天水|酒泉|张掖|武威|定西|陇南|平凉|庆阳|临夏|甘南");
where[29] = new comefrom("宁夏","|银川|石嘴山|吴忠|固原");
where[30] = new comefrom("青海","|西宁|海东|海南|海北|黄南|玉树|果洛|海西");
where[31] = new comefrom("新疆","|乌鲁木齐|石河子|克拉玛依|伊犁|巴音郭勒|昌吉|克孜勒苏柯尔克孜|博尔塔拉|吐鲁番|哈密|喀什|和田|阿克苏");
where[32] = new comefrom("香港","");
where[33] = new comefrom("澳门","");
where[34] = new comefrom("台湾","|台北|高雄|台中|台南|屏东|南投|云林|新竹|彰化|苗栗|嘉义|花莲|桃园|宜兰|基隆|台东|金门|马祖|澎湖");
where[35] = new comefrom("其它","|北美洲|南美洲|亚洲|非洲|欧洲|大洋洲");
function select() {
with(document.form.province) { var loca2 = options[selectedIndex].value; }
for(i = 0;i < where.length;i ++) {
if (where[i].loca == loca2) {
loca3 = (where[i].locacity).split("|");
for(j = 0;j < loca3.length;j++) { with(document.form.city) { length = loca3.length; options[j].text = loca3[j]; options[j].value = loca3[j]; var loca4=options[selectedIndex].value;}}
break;
}}
document.form.newlocation.value=loca2+loca4;
} 
function init() {	
with(document.form.province) {
length = where.length;
for(k=0;k<where.length;k++) { options[k].text = where[k].loca; options[k].value = where[k].loca; }
options[selectedIndex].text = where[0].loca; options[selectedIndex].value = "";
}
with(document.form.city) {
loca3 = (where[0].locacity).split("|");
length = loca3.length;
for(l=0;l<length;l++) { options[l].text = loca3[l]; options[l].value = loca3[l]; }
options[selectedIndex].text = loca3[0]; options[selectedIndex].value = "";
}} 
var imgCode = Array("u42r","7txd","tm7r","dmmr","7335","uxyr","yrae","tu42","kaet","3kn2");
var i = 1;
function changeCode(){
	i = Math.floor( Math.random( )*10);
	var imgObj = document.getElementById("img");
	imgObj.src = "images/yanzhengma/"+i+".png";
}
function checkyzm(obj){
	var checkcode = obj.value;
	var altObj = document.getElementById("checkcode_alt");
	//alert(imgCode[i-1]);
	if(checkcode=="" || checkcode==null){
		altObj.style.display = "inline";
		altObj.innerHTML="验证码非空";
		return false;
		}
	else if(checkcode.toLowerCase()!=imgCode[i]){
		altObj.style.display = "inline";
		altObj.innerHTML = "验证码不正确";
		return false;
		}
	else{
		altObj.style.display = "none";
		altObj.innerHTML = "";
		return true;
	}
}