//�����ͣͼƬЧ��
function xianshi(img){
	if(myBrowser()=="IE"){
		img.filters.alpha.opacity=100;
	}
	if(myBrowser()=="FF"){
		img.style.opacity=1;
	}
}
//����뿪ͼƬЧ��
function huifu(img){
	if(myBrowser()=="IE"){
		img.filters.alpha.opacity=70;
	}
	if(myBrowser()=="FF"){
		img.style.opacity=0.7;
	}
}
//����֤
function checkForm(){
	if(document.getElementById("userid").value==""){
		alert("�û���������д��");
		document.getElementById("userid").focus();
		return false;
	}
	if(document.getElementById("password").value==""){
		alert("���������д��");
		document.getElementById("password").focus();
		return false;
	}
}