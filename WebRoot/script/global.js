//Javascript Document
function myBrowser() {
	//��ȡ�����userAgent�ַ���
	var userAgent=navigator.userAgent;
	var isOpera=userAgent.indexOf("Opera") > -1;
	if(isOpera){
		return "Opera";
	}
	//�ж��Ƿ���Opera�����
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
