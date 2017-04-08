<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/mypassword.css" />
<title>微博 - 账号管理</title>
<script type="text/javascript" src="script/mypassword.js"></script>
<script type="text/javascript">
	function pwd() {
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
			<td width="55%" align="left">邮箱找回密码</td>
			<td width="25%" align="right">&nbsp;</td>
		</tr>
	</table>
	<!-- header结束-->
	<!-- container 开始-->
	<table border="0" align="center" cellpadding="0" cellspacing="0"
		id="container">
		<tr>
			<td width="670" valign="top">

				<form action="MailServlet" method="post" id="for">
					<table border="0" align="center" cellpadding="0" cellspacing="0"
						id="userinfo">
						<tr>
							<td class="title">个人资料</td>
						</tr>
						<tr>
							<td class="menu">找回密码</td>
						</tr>
						<tr>
							<td align="center"><table width="90%" border="0"
									cellpadding="5" cellspacing="0" id="userinfo_content">
									<c:if test="${ !empty emailError }">
											<tr>
											    <td style="color:#0F0" colspan="3">邮箱不存在！</td>
											</tr>
									</c:if>
									<c:if test="${ !empty emailSuccess }">
											<tr>
											    <td style="color:#0F0" colspan="3">密码已经发送至邮箱！</td>
											</tr>
									</c:if>
									<tr>
										<td width="20%" align="right">输入邮箱：</td>
										<td width="60%"><input name="mail" value="" type="text"
											class="input1" id="password" />邮箱为注册时提交的邮箱</td>
									</tr>
									<tr>
									    <td align="right"></td>
										<td align="left"><input type="submit" value=" 找回密码 " />
										<a href="javascript:history.back();">》》返回上一级</a>
										</td>
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
