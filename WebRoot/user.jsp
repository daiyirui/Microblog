<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
<title>微博 - 阿迪ada的微博</title>
<script type="text/javascript" src="script/user.js"></script>
</head>
<body>
	<!-- header开始-->
	<table id="header" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td width="20%" align="center"><img src="images/logo.png"
				width="178" height="62" /></td>


		</tr>
	</table>
	<!-- header结束-->
	<!-- container 开始-->
	<table border="0" align="center" cellpadding="0" cellspacing="0"
		id="container">
		<tr>
			<td width="670" height="600" valign="top"><table width="90%"
					border="0" align="center" cellpadding="0" cellspacing="0"
					id="curruser">
					<tr>
						<td width="24%"><img src="${sessionScope.UsersDetail.upic}"
							width="120" height="120" class="userphoto" /></td>
						<td width="76%" valign="top"><table width="100%" border="0"
								cellpadding="0" cellspacing="0" id="curruserdetail">
								<tr>
									<td class="title">${sessionScope.UsersDetail.uname}</td>
								</tr>
								<tr>
									<td><p>
											地址：${sessionScope.UsersDetail.uaddress}<br />
											签名：${sessionScope.UsersDetail.unickname}！<br />
											QQ：${sessionScope.UsersDetail.uqq}！<br /> <a href="#">+添加关注</a>
										</p></td>
								</tr>
							</table></td>
					</tr>
				</table></td>

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
