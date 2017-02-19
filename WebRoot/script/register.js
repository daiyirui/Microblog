//JavaScript Document
function checkUserName() {
	var name=document.getElementById("username");
	var namemsg=document.getElementById("usernamemsg");
	//�ж������û���
	if(name.value==""){
		namemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>�������û���</font>";
		name.select();
	}else if(name.value.length<4||name.value.length>16){
		namemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>������4-16���ַ�</font>";
		name.select();
	}
	for(var i=0;i<name.value.length;i++){
		var charText=name.value.toLowerCase().charAt(i);
		if((!(charText>='0'&&charText<='9'))&&(!(charText>='a'&&charText<='z'))&&(charText!='_')){
			namemsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>��Ա�������Ƿ��ַ���ֻ�ܰ���a-z��0-9���»���</font>";
			name.select();
		}
	}
	namemsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>�û�����ȷ!</font>";
}
function checkPassword(){
	var password=document.getElementById("password");
	var passwordmsg=document.getElementById("passwordmsg");
	//�ж��Ƿ�Ϊ��
	if(password==""){
		passwordmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>���벻��Ϊ��</font>";
		password.select();
	}else{
		passwordmsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>�����ʽ��ȷ</font>";
	}
	if(password.value.length<6||name.value.length>12){
		passwordmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>���볤��Ϊ6-12���ַ�</font>";
		password.select();
	}
	var rpassword=document.getElementById("rpassword");
	var rpasswordmsg=document.getElementById("rpasswordmsg");
	if((rpassword.value!=password.value)&&(rpassword.value!="")){
		rpasswordmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>�����������벻һ��</font>";
		rpassword.select();
		return false;
	}
	if(rpassword.value!=""){
		rpasswordmsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>����������ȷ</font>";
		return true;
	}
}
function checkEmail(){
	var email=document.getElementById("email");
	var emailmsg=document.getElementById("emailmsg");
	if(email.value.length==0){
		emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email����Ϊ��</font>";
		email.select();
		return false;
	}else if(email.value.indexOf("@",0)==-1){
		emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email��ʽ����ȷ\n�������@</font>";
		email.select();
		return false;
	}else if(email.value.indexOf(".",0)==-1){
		emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email��ʽ����ȷ\n�������.</font>";
		email.select();
		return false;
	}else if(email.value.length<6){
		emailmsg.innerHTML="<img src='icon/err.png' align='absmiddle'><font color='red'>Email���Ȳ���С��6</font>";
		email.select();
		return false;
	}
	emailmsg.innerHTML="<img src='icon/ok.png' align='absmiddle'><font color='green'>Email��ʽ��ȷ</font>";	
	return true;
}
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
	if(document.getElementById("userid").value == ""){
		alert("�û���������д��");
		document.getElementById("userid").focus();
		return false;
	}else if(document.getElementById("password").value==""){
		alert("���������д��");
		document.getElementById("password").focus();
		return false;
	}else{
		var form=document.getElementById("Checkform").value;		
		form.submit();
	}
}

var where = new Array(35);
function comefrom(loca,locacity) { this.loca = loca; this.locacity = locacity; }
where[0]= new comefrom("��ѡ��ʡ����","��ѡ�������");
where[1] = new comefrom("����","|����|����|����|����|����|��̨|ʯ��ɽ|����|��ͷ��|��ɽ|ͨ��|˳��|��ƽ|����|ƽ��|����|����|����");  //ţͼ��JS��Ч��http://js.niutuku.com/
where[2] = new comefrom("�Ϻ�","|����|¬��|���|����|����|����|բ��|���|����|����|��ɽ|�ζ�|�ֶ�|��ɽ|�ɽ�|����|�ϻ�|����|����");//ţͼ��JS��Ч��http://js.niutuku.com/
where[3] = new comefrom("���","|��ƽ|����|�Ӷ�|����|����|����|�Ͽ�|����|�ӱ�|����|����|����|����|���|����|����|����|����");
where[4] = new comefrom("����","|����|����|����|��ɿ�|����|ɳƺ��|������|�ϰ�|����|��ʢ|˫��|�山|����|ǭ��|����|�뽭|����|ͭ��|����|�ٲ�|��ɽ|��ƽ|�ǿ�|�ᶼ|�潭|��¡|����|����|����|���|��ɽ|��Ϫ|ʯ��|��ɽ|����|��ˮ|����|�ϴ�|����|�ϴ�");
where[5] = new comefrom("�ӱ�","|ʯ��ׯ|����|��̨|����|�żҿ�|�е�|�ȷ�|��ɽ|�ػʵ�|����|��ˮ");
where[6] = new comefrom("ɽ��","|̫ԭ|��ͬ|��Ȫ|����|����|˷��|����|����|����|�ٷ�|�˳�");
where[7] = new comefrom("���ɹ�","|���ͺ���|��ͷ|�ں�|���|���ױ�����|��������|����ľ��|�˰���|�����첼��|���ֹ�����|�����׶���|��������");
where[8] = new comefrom("����","|����|����|��ɽ|��˳|��Ϫ|����|����|Ӫ��|����|����|�̽�|����|����|��«��");
where[9] = new comefrom("����","|����|����|��ƽ|��Դ|ͨ��|��ɽ|��ԭ|�׳�|�ӱ�");
where[10] = new comefrom("������","|������|�������|ĵ����|��ľ˹|����|�绯|�׸�|����|�ں�|˫Ѽɽ|����|��̨��|���˰���");
where[11] = new comefrom("����","|�Ͼ�|��|����|��ͨ|����|�γ�|����|���Ƹ�|����|����|��Ǩ|̩��|����");
where[12] = new comefrom("�㽭","|����|����|����|����|����|����|��|����|��ɽ|̨��|��ˮ");
where[13] = new comefrom("����","|�Ϸ�|�ߺ�|����|��ɽ|����|ͭ��|����|��ɽ|����|����|����|����|����|����|����|����|����");
where[14] = new comefrom("����","|����|����|����|����|Ȫ��|����|��ƽ|����|����");
where[15] = new comefrom("����","|�ϲ���|������|�Ž�|ӥ̶|Ƽ��|����|����|����|�˴�|����|����");
where[16] = new comefrom("ɽ��","|����|�ൺ|�Ͳ�|��ׯ|��Ӫ|��̨|Ϋ��|����|̩��|����|����|����|����|����|�ĳ�|����|����");
where[17] = new comefrom("����","|֣��|����|����|ƽ��ɽ|����|�ױ�|����|����|���|���|���|����Ͽ|����|����|����|�ܿ�|פ���|��Դ");
where[18] = new comefrom("����","|�人|�˲�|����|�差|��ʯ|����|�Ƹ�|ʮ��|��ʩ|Ǳ��|����|����|����|����|Т��|����");
where[19] = new comefrom("����","|��ɳ|����|����|��̶|����|����|����|����|¦��|����|����|����|����|�żҽ�");
where[20] = new comefrom("�㶫","|����|����|�麣|��ͷ|��ݸ|��ɽ|��ɽ|�ع�|����|տ��|ï��|����|����|÷��|��β|��Դ|����|��Զ|����|����|�Ƹ�");
where[21] = new comefrom("����","|����|����|����|����|����|���Ǹ�|����|���|����|��������|���ݵ���|����|��ɫ|�ӳ�");
where[22] = new comefrom("����","|����|����");
where[23] = new comefrom("�Ĵ�","|�ɶ�|����|����|�Թ�|��֦��|��Ԫ|�ڽ�|��ɽ|�ϳ�|�˱�|�㰲|�ﴨ|�Ű�|üɽ|����|��ɽ|����");
where[24] = new comefrom("����","|����|����ˮ|����|��˳|ͭ��|ǭ����|�Ͻ�|ǭ����|ǭ��");
where[25] = new comefrom("����","|����|����|����|��Ϫ|��ͨ|����|���|��ɽ|˼é|��˫����|��ɽ|�º�|����|ŭ��|����|�ٲ�");
where[26] = new comefrom("����","|����|�տ���|ɽ��|��֥|����|����|����");
where[27] = new comefrom("����","|����|����|����|ͭ��|μ��|�Ӱ�|����|����|����|����");
where[28] = new comefrom("����","|����|������|���|����|��ˮ|��Ȫ|��Ҵ|����|����|¤��|ƽ��|����|����|����");
where[29] = new comefrom("����","|����|ʯ��ɽ|����|��ԭ");
where[30] = new comefrom("�ຣ","|����|����|����|����|����|����|����|����");
where[31] = new comefrom("�½�","|��³ľ��|ʯ����|��������|����|��������|����|�������տ¶�����|��������|��³��|����|��ʲ|����|������");
where[32] = new comefrom("���","");
where[33] = new comefrom("����","");
where[34] = new comefrom("̨��","|̨��|����|̨��|̨��|����|��Ͷ|����|����|�û�|����|����|����|��԰|����|��¡|̨��|����|����|���");
where[35] = new comefrom("����","|������|������|����|����|ŷ��|������");
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
		altObj.innerHTML="��֤��ǿ�";
		return false;
		}
	else if(checkcode.toLowerCase()!=imgCode[i]){
		altObj.style.display = "inline";
		altObj.innerHTML = "��֤�벻��ȷ";
		return false;
		}
	else{
		altObj.style.display = "none";
		altObj.innerHTML = "";
		return true;
	}
}