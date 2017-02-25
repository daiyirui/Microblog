//Javascript Document
function myBrowser() {
	//获取浏览器userAgent字符集
	var userAgent=navigator.userAgent;
	var isOpera=userAgent.indexOf("Opera") > -1;
	if(isOpera){
		return "Opera";
	}
	//判断是否是Opera浏览器
	if(userAgent.indexOf("Firefox") > -1){
		return "FF";
	}
	if(userAgent.indexOf("Safari") > -1){
		return "Safari";
	}
	if(userAgent.indexOf("compatible") > -1&&userAgent.indexOf("MSIE") > -1&&!isOpera){
		return "IE";
	}
}
