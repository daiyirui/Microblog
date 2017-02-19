<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="css/register.css" />
		<title>Catalyst微博 - 快速注册</title>
		<script type="text/javascript" src="script/register.js"></script>

	<script type="text/javascript" src="script/Validform.js"></script>
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
	<link rel="stylesheet" href="css/css.css" type="text/css"></link>
	</head>
	<body onload="init()">

		<!-- header开始-->
		<table id="header" align="center" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td width="20%" align="center">
					<img src="images/logo.png" width="178" height="62" />
				</td>
				<td width="55%" align="left" >
					用户注册
				</td>
				<td width="25%" align="right">&nbsp;
					
				</td>
			</tr>
		</table>
		<!-- header结束-->
		<!-- container 开始-->

		<table border="0" align="center" cellpadding="0" cellspacing="0"
			id="container">
		
			<tr>
				<td width="670" valign="top">
	
                 <form action="servlet/UserRegServlet" method="post" name="form" class="registerform" >
 
    <div align="center"><h3>注册信息</h3></div>
   <table  style="table-layout: fixed; left: 0px; " height="328" align="left">
           <tr>
            <td width="10" align="right" class="need">*</td>
            <td width="90">Email:</td>
            <td width="182" align="left"><input name="u_email" type="text" class="inputxt" id="userName" title="Email" value=""  datatype="e"  nullmsg="请输入您常用的邮箱！" ajaxurl="servlet/ServiceServlet" errormsg="邮箱格式不正确！" /></td>
            <td width="178" align="left"><div class="Validform_checktip">请输入您常用的邮箱</div></td>
        </tr>
        <tr>
            <td align="right" class="need">*</td>
            <td>密码：</td>
            <td align="left"><input type="password" value="" name="u_password" title="密码" class="inputxt" datatype="*4-16" nullmsg="请设置密码！" errormsg="密码范围在4~16位之间,不能使用空格！" /></td>
            <td width="178" align="left"><div class="Validform_checktip">密码范围在4~16位</div></td>
        </tr>
        <tr>
            <td align="right" class="need">*</td>
            <td>确认密码：</td>
            <td align="left"><input type="password" value="" name="u_password2" title="确认密码" class="inputxt" datatype="*4-16" recheck="u_password" nullmsg="请再输入一次密码！" errormsg="两次输入密码不一致！" /></td>
            <td width="178" align="left"><div class="Validform_checktip">两次输入密码需一致</div></td>
        </tr>
        <tr>
            <td  align="right" class="need">*</td>
            <td style="width:90px;">昵称：</td>
            <td align="left"><input type="text" value="" name="u_nickname" title="昵称" class="inputxt" datatype="*4-16"  errormsg="昵称4~8个字符！" /></td>
            <td width="178" align="left"><div class="Validform_checktip">昵称4~8个字符！</div></td>
        </tr>
  
        <tr>
            <td align="right" class="need">*</td>
            <td>性别：</td>
            <td align="left"><input type="radio" value="1" name="u_gender" id="male" class="pr1" datatype="radio" errormsg="请选择性别！" /><label for="male">男</label> <input type="radio" value="2" name="u_gender" id="female" class="pr1" /><label for="female">女</label></td>
            <td width="178" align="left"><div class="Validform_checktip"></div></td>
        </tr>
        <tr>
            <td class="need" >*</td>
            <td>所在省份：</td>
            <td >
             <select name="province" datatype="select" errormsg="请选择省份！" onchange = "select()"></select>
            </td>
            <td width="178" align="left"><div class="Validform_checktip"></div></td>
        </tr>
         <tr>
            <td class="need" >*</td>
            <td>所在城市：</td>
            <td ><select name="city" datatype="select" errormsg="请选择城市！" onchange = "select()"></select>
            </td>
            <td width="178" align="left"><div class="Validform_checktip"></div></td>
        </tr>
         <tr>
            <td align="right" class="need"></td>
            <td>验证码：</td>
            <td align="left">
               <img src="images/yanzhengma/1.png" id="img" width="70" height="25"  style=" border:#999 1px solid;" />
               <a href="javascript:changeCode()">点击，换下一张</a>
            </td>
            <td width="178" align="left"><div class="Validform_checktip"></div></td>
        </tr>
        <tr>
            <td align="right" class="need">*</td>
            <td>输入验证码：</td>
           <td align="left">
           <input type="text" name="checkcode" value="" class="input2" id="checkcode" onblur="checkyzm(this)" /></td>
            <td width="178" align="left"><div class="Validform_checktip"></div></td>
        </tr>
        <tr>
            <td class="need"></td>
            <td></td>
            <td colspan="2" style="padding:10px 0 18px 0;">
            <input type="hidden" name="type" value="add"/>
            <input type="submit" class="loginbtn" id="Submit" value="立即开通" />
                <!--<input type="image" src="images/regbtn1.png" width="150" height="37" onclick="document.form.submit();" />
            --></td>
        </tr>
    </table>
  
</form>

				</td>
				<td width="280" align="center" valign="top" class="pageright">
					<!-- userlogin 开始-->
					<form id="form1" name="form1" method="post" action="servlet/LoginServlet"
						onsubmit="return checkForm()">
						<table id="login" width="220" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td colspan="2" class="title">
									已有微博登录账号？
								</td>
							</tr>
							 <tr>
            <td colspan="2" align="left">邮箱：<br />
              <input name="u_email" type="text" class="logininput" id="userid" onmouseover="this.focus()" />
            </td>
          </tr>
          <tr>
            <td colspan="2" align="left">输入你的登录密码：<br />
              <input name="u_password" type="password" class="logininput" id="password" onmouseover="this.focus()"/>
            </td>
          </tr>
          <tr>
            <td width="120"><input type="checkbox" name="autologin"/>两周内自动登录</td>
            <td width="100"><a href="findpwd.jsp" style="text-decoration:none"><font color="#0066CC">找回登录密码</font></a></td>
          </tr>
          <tr>
							<tr>
								<td colspan="2" align="center">
									<input name="Submit" type="submit" class="loginbtn" id="Submit"
										value="  登录微博  " />
								</td>
							</tr>
						</table>
					</form>
					<!-- userlogin 结束-->
					<table id="msnlogin">
						<tr>
							<td align="center">
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>&nbsp; 
		<!-- container 结束-->

		<!--footer开始-->
	  <table id="footer" border="0" align="center" cellpadding="3"
			cellspacing="0">
			<tr>
				<td width="534" align="left">
					<a href="page/about.jsp">帮助</a>&nbsp;&nbsp;	
					<a href="page/taobao.jsp">微博认证及合作</a>&nbsp;&nbsp;
					<a href="page/open.jsp">开放平台</a>&nbsp;&nbsp;
				</td>
				<td width="447" align="right">Copyright: 2011<a href="page/about.jsp"> 微博系统 版权所有</a></td>
			</tr>
			<tr>
				<td align="left">
					客服电话：400 123 12345（按当地市话标准收费）
				</td>
				<td align="right">
					<div id="date"></div>
				</td>
			</tr>
		</table>
		<!--footer结束-->
<%
				if (request.getParameter("msg")!=null){
					int res = Integer.parseInt(request.getParameter("msg"));
					if(res == 111)
					out.print("<script>alert('验证码错误！');</script>");
				}
			%>
	</body>
</html>
