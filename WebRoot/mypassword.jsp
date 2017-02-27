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
			<td width="55%" align="left">账号设置</td>
			<td width="25%" align="right">&nbsp;</td>
		</tr>
	</table>
	<!-- header结束-->
	<!-- container 开始-->
	<table border="0" align="center" cellpadding="0" cellspacing="0"
		id="container">
		<tr>
			<td width="670" valign="top">

				<form action="CheckPasswordServlet" method="post" id="for">
					<table border="0" align="center" cellpadding="0" cellspacing="0"
						id="userinfo">
						<tr>
							<td class="title">个人资料</td>
						</tr>
						<tr>
							<td class="menu"><a href="UserServlet?action=shiftUserInfo&uid=${user.uid}">基本资料</a> | 修改密码 | <a
								href="UserServlet?action=shiftChangeFace&uid=${user.uid}">修改头像</a> |  <a
								href="HomeServlet?action=home&uid=${user.uid}">返回主页</a></td>
						</tr>
						<tr>
							<td align="center"><table width="90%" border="0"
									cellpadding="5" cellspacing="0" id="userinfo_content">
									<tr>
										<td width="20%" align="right">当前密码：</td>
										<td width="53%"><input name="nowpassword" value=""
											type="password" class="input1" id="password" /></td>
									</tr>
									<tr>
										<td align="right">新密码：</td>
										<td><input name="upwd" type="password" class="input1"
											id="rpassword" /></td>
									</tr>
									<tr>
										<td align="right">确认密码：</td>
										<td><input name="rpwd" type="password" class="input1"
											id="nickname" /></td>
									</tr>
									<tr>
										<td align="right">找回密码问题：</td>
										<td><select name="uques" style="width: 220px;">
												<option value="我的出生地?" selected="selected">我的出生地?</option>
												<option value="我父亲名字?">我父亲名字?</option>
												<option value="我母亲名字?">我母亲名字?</option>
												<option value="我妻子名字?">我妻子名字?</option>
										</select></td>
									</tr>
									<tr>
										<td align="right">答案：</td>
										<td><input name="urequest" type="text" class="input1"
											id="nickname" /></td>
									</tr>
									<tr>
										<td align="right"><input type="hidden" name="unickname"
											value="${param.unickname}" /> <input type="hidden"
											name="urealname" value="${param.urealname}" /> <input
											type="hidden" name="usex" value="${param.usex}" /> <input
											type="hidden" name="uedu" value="${param.uedu}" /> <input
											type="hidden" name="uqq" value="${param.uqq}" /> <input
											type="hidden" name="uremarks" value="${param.uremarks}" /></td>
										<td align="left"><a href="javascript:pwd()"><img
												border="0" src="images/editbtn.png" width="150" height="37" /></a>
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
