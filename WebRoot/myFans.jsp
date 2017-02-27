<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/friend.css" />
<title>微博 - 我的粉丝</title>
<script type="text/javascript" src="script/friend.js"></script>
<script type="text/javascript">
	function deleteFriend(uid) {
		if (confirm("确定此好友微博信息不再关注?")) {
			location.href = "DeleteAttentionServlet?gid=" + uid;
		}
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
			<td width="55%" align="right">
				<table border="0" align="right" cellpadding="0" cellspacing="0"
					id="daohang">
					<tr>
						<td width="20%"><a href="HomeServlet">我的首页</a></td>
						<td width="20%"><a href="MyBlogServlet">我的微博</a></td>
						<td width="20%"><a href="MyCollectionServlet">我的收藏</a></td>
						<td width="20%"><a href="BallhotShowServlet">微博热议</a></td>
					</tr>
				</table>
			</td>
			<td width="25%" align="right">
				<table id="welcome" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="30" height="30" rowspan="2" class="userface_bg"><img
							src="${user.upic}" border="0" width="20"
							height="20" /></td>
						<td>欢迎您, ${user.uname }！</td>
					</tr>
					<tr>
						<td><a href="index.jsp">[ 退出 ]</a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<!-- header结束-->
	<!-- container 开始-->
	<table border="0" align="center" cellpadding="0" cellspacing="0"
		id="container">
		<tr>
			<td width="670" height="600" valign="top">
				<table border="0" align="center" cellpadding="5" cellspacing="0"
					id="guanzhu">
					<tr>
						<td>共有 ${countVeri} 人是我的粉丝</td>
						<td align="right">&nbsp;</td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="menu">
					<tr>
						<td width="33%" align="center"><table width="165" border="0"
								align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center">我可爱的粉丝们</td>
								</tr>
							</table></td>
						<td width="18%" align="right">&nbsp;</td>
						<td width="49%" align="center">&nbsp;</td>
					</tr>
				</table> <!-- weibo 开始--> <c:if test="${!empty fansList}">
					<c:forEach items="${fansList}" var="fan">
						<table id="weibo" width="90%" border="0" align="center"
							cellpadding="3" cellspacing="0">
							<tr>
								<td rowspan="3" align="center" valign="top"><img
									src="${fan.upic}" width="50" height="50" /></td>
								<td width="88%" class="content"><a href="user.html">${fan.uname}</a><br/>昵称：${fan.unickname}&nbsp;&nbsp;&nbsp;&nbsp;<br/>QQ:${fan.uqq}
									<br />地址：${fan.uaddress}<br/>注册日期：${fan.udate}</td>
							</tr>
							<tr>
								<c:choose>
										<c:when test="${fan.iGtflag==1 }">
										       <tr><td><a href="DeleteAttentionServlet?gid=fan.uid&action=1">取消关注</a></td></tr>
										</c:when>
										<c:when test="${fan.iGtflag==0}">
										        <tr><td><a href="InsertAttentionServlet?gid=fan.uid&action=1">+关注</a></td></tr>
										</c:when>
								</c:choose>
									
							</tr>
						</table>
					</c:forEach>
				</c:if>
			
			</td>
			<td width="280" align="center" valign="top" class="pageright">
				<!-- userinfo 开始-->
				<table align="center" id="userinfo">
					<tr>
						<td width="25%" rowspan="2"><img src="face/9.jpg" width="50"
							height="50" /></td>
						<td width="75%"><a href="profile.html">${user.uname }</a></td>
					</tr>
					<tr>
						<td valign="top">天津</td>
					</tr>
					<tr>
						<td colspan="2" align="left"><table width="80%" border="0"
								align="left" cellpadding="3" cellspacing="0">
								<tr>
									<td align="center" class="split2"><a href="FollowServlet">关注</a><br>${countRlation}</td>
									<td align="center" class="split2"><a href="MyFansServlet">粉丝</a><br>${countVeri}</td>
									<td align="center"><a href="#">微博</a><br>${countBlog}</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td colspan="2" class="split1"><a href="userinfo.html">个人账户设置</a></td>
					</tr>
				</table>
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					id="userlist">
					<tr>
						<td class="title" height="29">可能感兴趣的人</td>
						<td align="right" class="title"><a
							href="ChangeUserServlet?change=6">[换一换]</a></td>
					</tr>

					<c:if test="${!empty userList }">
						<c:forEach items="${userList}" var="usl">
							<tr>
								<td colspan="2"><table border="0" cellpadding="0"
										cellspacing="0" class="userdetail">
										<tr>
											<td width="26%"><a href="user.jsp"><img
													src="${usl.upic}" width="50" height="50" border="0" /></a></td>
											<td width="74%"><a href="user.jsp">${usl.uname}</a> <a
												href="InsertAttentionServlet?gid=${usl.uid}"
												style="border: 0px;"> <span class="btnguanzhu"
													style="width: 50px; height: 3px;" id="button3">+关注</span></a> <br />
											<font color="#333333" size="2px"> ${usl.uaddress}</font><br />
											<font color="#333333" size="2px">Time：${usl.udate}</font></td>
										</tr>
									</table></td>
							</tr>
						</c:forEach>
					</c:if>
				</table> <!-- userinfo 结束-->
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
			<td width="447" align="right">2011-2015<a href="#"> 微博系统
					版权所有</a></td>
		</tr>
		<tr>
			<td align="left">客服电话：400 123 12345（按当地市话标准收费）</td>
			<td align="right">语言： <select name="select" id="select">
					<option>中文(简体)</option>
					<option>英文</option>
			</select></td>
		</tr>
	</table>
	<!--footer结束-->
</body>
</html>
