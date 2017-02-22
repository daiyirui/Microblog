<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/profile.css" />
<title>微博 - 个人评论主页</title>
<script type="text/javascript" src="script/profile.js"></script>
<script type="text/javascript">
	function deleteComment(cid) {
		if (confirm("确定删除此条评论?")) {
			location.href = "DeleteCommentServlet?cid=" + cid;
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
						<td width="20%"><a href="ReturnHomeServlet">我的首页</font></a></td>
						<td width="20%"><a href="profile.html">我的博客</a></td>
						<td width="20%"><a href="friend.html">我的好友</a></td>
						<td width="20%"><a href="user.html">随便看看</a></td>
					</tr>
				</table>
			</td>
			<td width="25%" align="right">
				<table id="welcome" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="30" height="30" rowspan="2" class="userface_bg"><img
							src="face/9.jpg" border="0" width="20" height="20" /></td>
						<td>欢迎您，${sessionScope.userinfo.uname }！</td>
					</tr>
					<tr>
						<td><a href="index.html">[ 退出 ]</a></td>
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
			<td width="670" height="600" valign="top"><table width="90%"
					border="0" align="center" cellpadding="0" cellspacing="0"
					id="curruser">
					<tr>
						<td width="24%"><img
							src="${sessionScope.WeiboDetail.use.upic}" width="120"
							height="120" class="userphoto" /></td>
						<td width="76%" valign="top"><table width="100%" border="0"
								cellpadding="0" cellspacing="0" id="curruserdetail">
								<tr>
									<td class="title">${sessionScope.WeiboDetail.use.uname}</td>
								</tr>
								<tr>
									<td><p>
											微博信息：${sessionScope.WeiboDetail.wcontent}<br />
											${sessionScope.WeiboDetail.use.uaddress}<br />
											签名：${sessionScope.WeiboDetail.use.unickname}！<br />
										</p></td>
								</tr>
							</table></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="menu">
					<tr>
						<td width="33%" align="center"><table width="165" border="0"
								align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center" width="100%">相关评论</td>

								</tr>
							</table></td>
						<td width="18%" align="right">&nbsp;</td>
						<td width="49%" align="center">
							<form id="form1" name="form1" method="post"
								action="FuzzyCommentServlet">
								<input name="textcomment" type="text" class="input"
									id="textfield" /> <input type="hidden" id="wid" name="wid"
									value="${sessionScope.WeiboDetail.wid}"> <input
									name="button" type="submit" class="btnsearch" id="button"
									value="搜索" />
							</form>
						</td>
					</tr>
				</table> <!-- weibo 开始--> <c:if test="${!empty sessionScope.CommentList}">
					<c:forEach items="${sessionScope.CommentList.data}" var="comm">
						<table id="weibo" width="90%" border="0" align="center"
							cellpadding="3" cellspacing="0">
							<tr>
								<td width="11%" rowspan="3" align="center" valign="top"><img
									src="${comm.use.upic}" width="50" height="50" /></td>
								<td width="23%" class="content"><a href="user.html">${comm.use.uname}</a><img
									src="icon/v.gif" width="11" height="10" align="middle" />：</td>
								<td width="66%" rowspan="2" align="left" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
									class="content">${comm.ccontent}</span></td>
							</tr>
							<tr>
								<td><c:if test="${comm.cimages ne null}">
										<img src="${comm.cimages}" width="89" height="120" />
									</c:if></td>
							</tr>
							<tr>
								<td height="25" colspan="2"><table width="100%" border="0"
										cellpadding="0" cellspacing="0" id="weibo_status">
										<tr>
											<td>${comm.cdate}</td>
											<td align="right">&nbsp;&nbsp;&nbsp; <c:if
													test="${comm.use.uid eq sessionScope.userinfo.uid}">
													<a href="javascript:deleteComment(${comm.cid})">删除</a>
												</c:if>
											</td>
										</tr>
									</table></td>
							</tr>
						</table>
					</c:forEach>
				</c:if>
				<table align="center" id="page">
					<tr>
						<td align="left">第${sessionScope.CommentList.currentPage}页&nbsp;共${sessionScope.CommentList.totalRows}条评论
						</td>
						<td align="right"><c:if
								test="${sessionScope.CommentList.currentPage==1}">首页</c:if> <c:if
								test="${sessionScope.CommentList.currentPage!=1}">
								<a href="CommentServlet">首页</a>
             &nbsp;<a
									href="CommentServlet?np=${sessionScope.CommentList.currentPage-1}">上一页</a>
							</c:if> <c:if
								test="${sessionScope.CommentList.currentPage!=sessionScope.CommentList.totalPages}">
             &nbsp;<a
									href="CommentServlet?np=${sessionScope.CommentList.currentPage+1}">下一页</a>
             &nbsp;<a
									href="CommentServlet?np=${sessionScope.CommentList.totalPages}">尾页</a>
							</c:if> <c:if
								test="${sessionScope.CommentList.currentPage==sessionScope.CommentList.totalPages}">&nbsp;尾页</c:if>
						</td>
					</tr>
				</table>
				<form action="InsertCommentServlet" method="post"
					enctype="multipart/form-data">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id="input">
						<tr>
							<td width="55" height="34">&nbsp;</td>
							<td width="555">发表你的评论：</td>
							<td width="60">&nbsp;</td>
						</tr>
						<tr>
							<td height="135">&nbsp;</td>
							<td><textarea id="inputbox" name="commenttext" cols="75"
									rows="7"></textarea></td>
							<td><input type="hidden" id="wid" name="wid"
								value="${sessionScope.WeiboDetail.wid }" /> <input type="hidden"
								id="uid" name="uid" value="${sessionScope.userinfo.uid }" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="right" valign="top"><img
								src="images/imgupload.png" width="47" height="22"
								align="absmiddle" /><input type="file" name="upfile" /> <input
								name="dosubmit" type="submit" id="dosubmit" value=""
								style="background: url(images/btn_input.png); border-style: none; width: 100px; height: 26px; background-repeat: no-repeat;" />
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</form> <!-- weibo 结束--></td>
			<td width="280" align="center" valign="top" class="pageright">
				<!-- userinfo 开始-->
				<table align="center" id="userinfo">
					<tr>
						<td width="75%" align="left"><table width="80%" border="0"
								align="left" cellpadding="3" cellspacing="0">
								<tr>
									<td align="center" class="split2"><a href="3">关注</a><br>${sessionScope.countRlation}</td>
									<td align="center" class="split2"><a href="3">粉丝</a><br>${sessionScope.countVeri}</td>
									<td align="center"><a href="#">微博</a><br>${sessionScope.countBlog}</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td class="split1"><a href="userinfo.html">个人账户设置</a></td>
					</tr>
				</table>
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					id="userlist">
					<tr>
						<td class="title" height="29">可能感兴趣的人</td>
						<td align="right" class="title"><a
							href="ChangeUserServlet?change=1">[换一换]</a></td>
					</tr>
					<c:if test="${!empty sessionScope.userList }">
						<c:forEach items="${sessionScope.userList}" var="usl">
							<tr>
								<td colspan="2"><table border="0" cellpadding="0"
										cellspacing="0" class="userdetail">
										<tr>
											<td width="26%"><a href="user.jsp"><img
													src="${usl.upic}" width="50" height="50" border="0" /></a></td>
											<td width="74%"><a href="user.jsp">${usl.uname}</a> <input
												name="button3" type="button" class="btnguanzhu" id="button3"
												value="+关注" /> <br /> ${usl.uaddress}<br />注册时间：${usl.udate}</td>
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
			<td width="447" align="right">Copyright: 2011-2015<a href="#">
					微博系统 版权所有</a></td>
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
