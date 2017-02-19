//Javascript Document
function myBrowser() {
	//»ñÈ¡ä¯ÀÀÆ÷userAgent×Ö·û¼¯
	var userAgent=navigator.userAgent;
	var isOpera=userAgent.indexOf("Opera") > -1;
	if(isOpera){
		return "Opera";
	}
	//ÅĞ¶ÏÊÇ·ñÊÇOperaä¯ÀÀÆ÷
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
