<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/userinfo.css" />
<title>微博 - 账号管理</title>
<script type="text/javascript" src="script/userinfo.js"></script>
<script type="text/javascript">
	function mypassword() {
		var s = document.getElementById("for");
		s.submit();
	}
</script>
</head>
<body>
	<!-- header开始-->
	<table id="header" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td width="20%" align="center"><img src="images/logo.png"
				width="178" height="62" /></td>
			<td width="55%" align="left">账号设置</td>
			<td width="25%" align="right">&nbsp;</td>
		</tr>
	</table>
	<!-- header结束-->
	<!-- container 开始-->
	<table border="0" align="center" cellpadding="0" cellspacing="0"
		id="container">
		<tr>
			<td width="670" height="600" valign="top">

				<form action="mypassword.jsp" method="post" id="for">
					<table border="0" align="center" cellpadding="0" cellspacing="0"
						id="userinfo">
						<tr>
							<td class="title">个人资料</td>
						</tr>
						<tr>
							<td class="menu">基本资料 | <a href="UserServlet?action=shiftPassword&uid=${user.uid}">修改密码</a> |
								<a href="UserServlet?action=shiftChangeFace&uid=${user.uid}">修改头像</a>  |  <a
								href="HomeServlet?action=home&uid=${user.uid}">返回主页</a></td>
						</tr>
						<tr>
							<td align="center"><table width="90%" border="0"
									cellpadding="5" cellspacing="0" id="userinfo_content">
									<tr>
										<td width="20%" align="right">登陆姓名：</td>
										<td width="53%">${user.uname}</td>
									</tr>
									<tr>
										<td align="right">昵称：</td>
										<td><input name="unickname"
											value="${user.unickname}" type="text"
											class="input1" id="password" /></td>
									</tr>
									<tr>
										<td align="right">真实姓名：</td>
										<td width="53%">${user.urealname}</td>
									</tr>
									<tr>
										<td align="right">所在地：</td>
										<td><input name="uaddress"
											value="${user.uaddress}" type="text"
											class="input1" id="nickname" readonly="readonly" /></td>
									</tr>
									<tr>
										<td align="right">性别：</td>
										<td><c:if test="${user.usex eq '男'}">
												<input type="radio" name="usex" checked="checked" id="radio"
													value="男" />男
                &nbsp; &nbsp; &nbsp; &nbsp; 
               <input type="radio" name="usex" id="radio2" value="女" />女
             </c:if> <c:if test="${user.usex eq '女'}">
												<input type="radio" name="usex" id="radio" value="男" />男
                &nbsp; &nbsp; &nbsp; &nbsp; 
               <input type="radio" name="usex" checked="checked"
													id="radio2" value="女" />女
             </c:if></td>
									</tr>
									<tr>
										<td align="right">注册日期：</td>
										<td>${user.udate}</td>
									</tr>
									<tr>
										<td align="right">QQ：</td>
										<td><input name="uqq" type="text"
											value="${user.uqq}" class="input1"
											id="nickname2" /></td>
									</tr>
									<tr>
										<td align="right">学历：</td>
										<td align="left"><select name="uedu">
												<option value="本科" selected="selected">本科</option>
												<option value="专科">专科</option>
												<option value="职专">职专</option>
												<option value="MBA">MBA</option>
										</select></td>
									</tr>
									<tr>
										<td align="right">邮箱：</td>
										<td><input name="uemail" type="text" class="input1"
											id="email" class="email"  value = "${user.uemail }" onblur="checkEmail()"/><br>一个邮箱只能注册一个账号</td>
										<td width="27%"><span id="emailmsg"></span></td>
									</tr>
									<tr>
										<td align="right">备注：</td>
										<td align="left"><input name="uremarks"
											value="${user.uremarks}" type="text"
											class="input1" id="nickname4" /></td>
									</tr>
									<tr>
										<td align="right">&nbsp;</td>
										<td align="left"><a href="javascript:mypassword()"><img
												border="0" src="images/editbtn.png" width="150" height="37" /></a></td>
									</tr>
								</table></td>
						</tr>
					</table>
				</form>

			</td>
		</tr>
	</table>
	<!-- container 结束-->

	<!--footer开始-->
	<table id="footer" border="0" align="center" cellpadding="3"
		cellspacing="0">
		<tr>
			<td width="534" align="left"><a href="#">帮助</a>&nbsp;&nbsp; <a
				href="#">意见反馈</a>&nbsp;&nbsp; <a href="#">微博认证及合作</a>&nbsp;&nbsp; <a
				href="#">开放平台</a>&nbsp;&nbsp; <a href="#">微博招聘</a>&nbsp;&nbsp; <a
				href="#">微博导航</a></td>
			<td width="447" align="right">Copyright: 2011-2015<a href="#">
					微博系统 版权所有</a></td>
		</tr>
		<tr>
			<td align="left">客服电话：400 123 12345（按当地市话标准收费）</td>
			<td align="right">语言： <select name="select" id="select">
					<option>中文(简体)</option>
					<option>中文(繁体)</option>
			</select></td>
		</tr>
	</table>
	<!--footer结束-->
</body>
</html>
