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
	
	//判断是否为空
	if(password.value==""){
		passwordmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>密码不能为空</font>";
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
