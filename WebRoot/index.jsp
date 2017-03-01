<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>欢迎来到微博 微博-随时随地发现新鲜事</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<script type="text/javascript" src="script/global.js"></script>
<script type="text/javascript" src="script/index.js"></script>

</head>
<body>
	<div>
		<table style="margin-left: 150px; margin-top: 40px">
			<tr>
				<td>&nbsp;</td>
				<td style="margin-top: 100px; margin-left: 400px">
					<table id="header" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>&nbsp;</td>
							<td width="780" align="right"><font color="#6f7171" size="2">已经有账号了:&nbsp;&nbsp;&nbsp;
							</font> <a href="login.jsp" style="text-decoration: none;"><font
									color="#000000" size="3">登陆</font></a>
								<div class="loginin" id="55">
									<p>
										<font color="#6f7171" size="2">免费： </font>&nbsp; <a
											href="register.jsp?flag=false" style="text-decoration: none;">
											<font color="#000000" size="3">注册</font>
										</a>
									</p>
								</div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<!--header 结束-->
	<!--container 开始-->
	<center>
		<!--content 开始-->
		<table width="70%" border="0" cellpadding="0" cellspacing="0"
			id="content">
			<tr>
				<td class="phototitle" align="center"><font size="5">What
						do you think is the best Blog in the world?</font> <a href="register.jsp"
					style="text-decoration: none;"><font size="4" color="#6699cc">&nbsp;立即加入我们</font></a>
					<font size="3" style="font-family: 幼圆; font-weight: bold">开始微博之旅</font><a
					href="index.jsp" style="text-decoration: none;"><font size="4"
						color="#6699cc">&nbsp;首页&nbsp;</font></a><a
					href="HomeServlet?action=ballot" style="text-decoration: none;"><font
						size="4" color="#6699cc">&nbsp;热门话题 </font></a></td>
			</tr>
			<tr>
				<td height="515">
					<!--phototlist开始-->
					<div align="center">
						<table border="0" cellpadding="0" cellspacing="4" id="photolist"
							align="center">
							<tr>
								<c:forEach items="${applicationScope.userListListener}"
									var="users" varStatus="su">
									<td><a href="HomeServlet?uid=${users.uid}&action=userdetail"
										target="_blank"> <img src="${users.upic}"
											id="${users.uid}" title="${users.uname}"
											onMouseOver="xianshi(this)" onMouseOut="huifu(this)" />
									</a></td>
									<c:if test="${su.count%11==0}">
										<tr>
										</tr>
									</c:if>
								</c:forEach>
							</tr>
						</table>

					</div>
				</td>
			</tr>
		</table>

		<!--content 结束-->
	</center>

	<!--container结束-->
	<!--footer开始-->
	<table border="0" cellpadding="5" cellspacing="0" id="index_footer"
		align="center">
		<tr>
			<td width="304" height="50" align="left">微博系统 版权所有</td>
			<td width="166" align="right">语言 <select name="select"
				id="select">
					<option>中文(简体)</option>
					<option>英文</option>
			</select>

			</td>
		</tr>
	</table>
	<!--footer结束-->
</body>
</html>