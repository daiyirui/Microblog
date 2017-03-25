<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/friend.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
<title>微博 - ${sessionScope.user.u_account}的主页</title>
<script type="text/javascript" src="script/home.js"></script>



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
					   <td width="20%"><a href="HomeServlet?uid=${user.uid}&action=home">我的首页</a></td>
						<td width="20%"><a href="WeiboServlet?action=allweibo&uid=${user.uid}">我的微博</a></td>
						<td width="20%"><a href="CollectionServlet?action=allcollection&uid=${user.uid}">我的收藏</a></td>
						<td width="20%">微博热议</td>
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
	<!-- header结束--> <!-- container 开始-->
	<table border="0" align="center" cellpadding="0" cellspacing="0"
		id="container">
		<tr>
			<td width="670" height="600" valign="top">
				<table width="600" border="0" cellpadding="0">
				
					<tr>
						<td width="600" height="46"><span class="STYLE6"><h1>微博热议：</h1></span>&nbsp;&nbsp;</td>
				       
					</tr>
					<c:forEach items="${bloghots}" var="bloghot" begin="0" end="5">
						<tr>
							<td height="166">
								<form id="form1" name="form1" method="post" action="login.jsp">
									<table width="707" border="1" cellpadding="0">
										<tr>
											<td height="35" colspan="2">${bloghot.btitle}：</td>
										</tr>
										<c:forEach items="${bloghot.bitems}" var="bitem">
											<tr>
												<td height="20" colspan="1" align="left"><label><input
														type="radio" name="tennis" value="${bitem.bitemName}" />${bitem.bitemName}</label>

												</td>
												<td height="20" colspan="1" align="left"><label>当前票数:${bitem.bvote}</label>
												</td>

											</tr>
											<c:if test="${ !empty bitem.bitemimage }">
												<tr>
													<td height="60" colspan="2" align="left">
														${bitem.bitemimage}</td>
												</tr>
											</c:if>
										</c:forEach>
										<tr>
											<td width="462" height="37" align="right"><input
												type="submit" value="投票" /></td>
											<td width="239" align="right"><input type="button"
												onclick="show('staResult')" value="查看投票结果" /></td>
										</tr>

									</table>
								</form>
							</td>

						</tr>
					</c:forEach>
				</table> <!-- weibo 开始-->
			</td>
			<td width="280" align="center" valign="top" class="pageright">
				<!-- userinfo 开始-->
				<table align="center" id="userinfo">
					<tr>
						<td width="25%" rowspan="2"><img
							src="${user.upic}" width="50" height="50" /></td>
						<td width="75%"><a href="profile.jsp">${user.uname }</a></td>
					</tr>
					<tr>
						<td valign="top">天津</td>
					</tr>
					<tr>
						<td colspan="2" align="left"><table width="80%" border="0"
								align="left" cellpadding="3" cellspacing="0">
								<tr>
							     	<td align="center" class="split2"><a href="RelationServlet?action=showGuanzhu&uid=${user.uid }">关注</a><br>${countRlation}</td>
									<td align="center" class="split2"><a href="RelationServlet?action=showFans&uid=${user.uid }">粉丝</a><br>${countVeri}</td>
									<td align="center"><a href="#">微博</a><br>${countBlog}</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td colspan="2" class="split1"><a href="userinfo.jsp">个人账户设置</a></td>
					</tr>
				</table>
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					id="userlist">
					<tr>
						<td class="title" height="29">可能感兴趣的人</td>
						<td align="right" class="title"><a
							href="UserServlet?page=4&action=userChange&uid=${user.uid}">[换一换]</a></td>
					</tr>

					<c:if test="${!empty userList }">
						<c:forEach items="${userList}" var="usl">
							<tr>
								<td colspan="2"><table border="0" cellpadding="0"
										cellspacing="0" class="userdetail">
										<tr>
											<td width="26%"><a href="HomeServlet?uid=${usl.uid}&action=userdetail"><img
													src="${usl.upic}" width="50" height="50" border="0" /></a></td>
											<td width="74%"><a href="HomeServlet?uid=${usl.uid}&action=userdetail">${usl.uname}</a> <a
												href="RelationServlet?page=4&uid=${user.uid}&gid=${usl.uid}&action=guanzhu"
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
	<!-- container 结束--> <!--footer开始-->
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
