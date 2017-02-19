<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="css/register.css" />
		<title>Catalyst微博 - 快速注册</title>
		<script type="text/javascript" src="script/register.js"></script>

	<script type="text/javascript">
$(function(){
	$(".registerform:last").Validform({
		tiptype:2
	});
	//调取公用方法显示信息提示框，可用于全站统一的弹出框提示效果;

	//$.Showmsg("Test Info here!");  //公用方法显示信息提示框;
})
</script>
<script> 
$(document).ready(function () { 
setInterval("startRequest()",1000); 
}); 
function startRequest() 
{ 
$("#date").text((new Date()).toString()); 
} 

 function changeimg()   
  {   
  var cn = document.getElementById("cn");   
  cn.src="image.jsp?"+Math.random();//随机生成一个数字，让图片缓冲区认为不是同一个缓冲区    
  }   
</script> 
<script language="javascript">
 ///用数组作级联
 function selectProvincesArray(){
  var cityList=new Array();
  cityList['天津市']=['和平','河西','南开','河东','塘沽'];
  cityList['北京市']=['东城','西城','崇文','宣武','朝阳'];
  cityList['四川省']=['成都','绵阳','眉山','雅安','自贡'];
  cityList['云南省']=['昆明','丽江','大理'];
  cityList['浙江省']=['杭州','嘉兴','义乌'];
  var pIndex=document.forms.selectProvinceArray.value;
  var newpotion3;
     document.forms.selectCityArray.options.length=0;
     for(var j in cityList[pIndex]){
      newpotion3=new Option(cityList[pIndex][j],cityList[pIndex][j]);
      document.forms.selectCityArray.options.add(newpotion3);
  }
  }
  function formsub(){
      var f=document.getElementById("for");
      if(confirm("确认提交注册详细?")){
        f.submit();
      }
  }
</script>
	<link rel="stylesheet" href="css/css.css" type="text/css"></link>
  </head>
 <body onload="init()">
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
    <td width="55%" align="left">用户注册</td>
    <td width="25%" align="right">&nbsp;</td>
  </tr>
</table>
<!-- header结束-->
<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" valign="top">
    <form action="RegisterServlet" name="forms" method="post" id="for">
      <table border="0" align="center" cellpadding="0" cellspacing="0" id="register">
        <tr>
          <td class="title">30秒快速开通微博</td>
        </tr>
        <tr>
          <td><table width="90%" border="0" cellpadding="5" cellspacing="0" id="register_content">
            <tr>
              <td width="20%" align="right">登陆名称：</td>
              <td width="53%"><input name="uname" type="text" class="input1" id="email" onblur="checkemail()" /></td>
              <td width="27%"><span id="emailmsg"><span></td>
            </tr>
            <tr>
              <td align="right">创建密码：</td>
              <td><input name="upwd" type="password" class="input1" id="password" /></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="right">确认密码：</td>
              <td><input name="rpassword" type="password" class="input1" id="rpassword" /></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="right">昵称：</td>
              <td><input name="unickname" type="text" class="input1" id="nickname" /></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="right">性别：</td>
              <td><input type="radio" name="usex" id="radio" value="男" checked="checked" />男
                  &nbsp; &nbsp; &nbsp; &nbsp;
                  <input type="radio" name="usex" id="radio2" value="女" />女
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td align="right">所在省份：</td>
              <td> <select name="selectProvinceArray" onChange="selectProvincesArray()">
 <option>--请选择所在省份--</option>
 <option value="天津市">天津市</option>
 <option value="北京市">北京市</option>
 <option value="四川省">四川省</option>
 <option value="云南省">云南省</option>
 <option value="浙江省">浙江省</option></select>
                 </td>
              <td><div class="Validform_checktip"></div></td>
            </tr>
            <tr>
              <td align="right">所在城市：</td>
              <td><select name="selectCityArray">
 <option>--请选择所在城市--</option>
 </select>
              </td>
              <td><div class="Validform_checktip"></div></td>
            </tr>
    
            <tr>
              <td align="right">验证码：</td>
              <td><input type="text" name="checkcode" value="" class="input2" id="checkcode" onblur="checkyzm(this)"> <img src="images/yanzhengma/1.png" id="img" width="70" height="25"  style=" border:#999 1px solid;" />
               <a href="javascript:changeCode()">换一张</a></td>
              <td><span id="checkcode_alt" class="alt"></span></td>
            </tr>
            <tr>
              <td align="center">&nbsp;</td>
              <td align="left">
              <a href="javascript:formsub()"><img src="images/regbtn.png" width="150" height="37" border="0" /></a></td>
              <td align="center">&nbsp;</td>
              </tr>
            <tr>
              <td align="center">&nbsp;</td>
              <td align="left">
               <input type="checkbox" checked="checked" name="sel" value="select" />自动登录个人主页
              </td>
              <td align="center">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
    </form>
     
    </td>
	    <td width="280" align="center" valign="top" class="pageright">
        <!-- userlogin 开始-->
        <form id="form1" name="form1" method="post" action="LoginServlet" onsubmit="return checkForm()">
        <table id="login" width="220" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2" class="title">已有微博登录账号？</td>
          </tr>
          <tr>
            <td colspan="2" align="left">邮箱/会员账号/手机号：<br />
              <input name="usn" type="text" class="logininput" id="userid" onmouseover="this.focus()" />
            </td>
          </tr>
          <tr>
            <td colspan="2" align="left">输入你的登录密码：<br />
              <input name="pwd" type="password" class="logininput" id="password" onmouseover="this.focus()"/>
            </td>
          </tr>
          <tr>
            <td width="120"><input name="save" type="checkbox" id="save" value="yes" />下次自动登录</td>
            <td width="100"><a href="#" style="text-decoration:none"><font color="#0066CC">找回登录密码</font></a></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input name="Submit" type="submit" class="loginbtn" id="Submit" value="  登录微博  " /></td>
          </tr>
        </table>
        </form>
    	<!-- userlogin 结束-->
        <table id="msnlogin">
         <tr>
          <td align="center"><img src="images/msnlogin.png" width="143" height="32" /></td></tr>
        </table>
    </td>
  </tr>
	</table>
<!-- container 结束-->

<!--footer开始-->
<table id="footer" border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="534" align="left"><a href="#">帮助</a>&nbsp;&nbsp; <a href="#">意见反馈</a>&nbsp;&nbsp; <a href="#">微博认证及合作</a>&nbsp;&nbsp; <a href="#">开放平台</a>&nbsp;&nbsp; <a href="#">微博招聘</a>&nbsp;&nbsp; <a href="#">微博导航</a></td>        
    <td width="447" align="right">Copyright: 2011-2015<a href="#"> 微博系统 版权所有</a></td>
  </tr>
  <tr>
    <td align="left">客服电话：400 123 12345（按当地市话标准收费）</td>
    <td align="right">语言：
      <select name="select" id="select">
        <option>中文(简体)</option>
        <option>中文(繁体)</option>
    </select></td>
  </tr>        
</table>
<!--footer结束-->
</body>
</html>
