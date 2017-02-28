<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>微博 - 我的主页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
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
						<td width="20%">我的首页</td>
						<td width="20%"><a href="WeiboServlet?action=allweibo&uid=${user.uid}">我的微博</a></td>
						<td width="20%"><a href="CollectionServlet?action=allcollection&uid=${user.uid}">我的收藏</a></td>
						<td width="20%"><a href="BallhotServlet?action=allBollhot&uid=${user.uid}">微博热议</a></td>
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
				<form action="WeiboServlet?action=insertweibo&uid=${user.uid }" method="post"
					enctype="multipart/form-data">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id="input">
						<tr>
							<td width="160" height="48">&nbsp;</td>
							<td width="479">&nbsp;</td>
							<td width="31">&nbsp;</td>
						</tr>
						<tr>
							<td height="84">&nbsp;</td>
							<td><textarea id="inputbox" name="weibotext" cols="45"
									rows="5"></textarea></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="right" valign="top"><img
								src="images/imgupload.png" width="47" height="22" /> <input
								type="file" name="upfile" /> <input name="dosubmit"
								type="submit" id="dosubmit" value=""
								style="background: url(images/btn_input.png); border-style: none; width: 100px; height: 26px; background-repeat: no-repeat;" /></td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</form>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="menu">
					<tr>
						<td width="33%" align="center"><table width="165" border="0"
								align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center">全部</td>
									<td align="center"><a href="friend.jsp">微博收听</a></td>
								</tr>
							</table></td>
						<td width="18%" align="right">&nbsp;</td>
						<td width="49%" align="center">
							<form id="form1" name="form1" method="post"
								action="FuzzyWeiboServlet">
								<input name="textweibo" type="text" class="input" id="textfield" />
								<input name="button" type="submit" class="btnsearch" id="button"
									value="搜索" />
							</form>
						</td>
					</tr>
				</table> <!-- weibo 开始--> <c:if test="${!empty weibos}">
					<c:forEach items="${weibos}" var="weibo">
						<table id="weibo" width="90%" border="0" align="center"
							cellpadding="3" cellspacing="0">
							<tr>
								<td rowspan="3" align="center" valign="top"><img
									src="${weibo.use.upic}" width="50" height="50" /></td>
								<td width="88%" class="content"><a href="user.jsp">${weibo.use.uname}</a><img
									src="icon/v.gif" width="11" height="10" align="middle" />：
									${weibo.wcontent}</td>
							</tr>
							<tr>
								<td><c:if test="${weibo.wimage ne null}">
										<img src="${weibo.wimage}" width="89" height="120" />
									</c:if></td>
							</tr>
							<tr>
								<td height="25"><table width="100%" border="0"
										cellpadding="0" cellspacing="0" id="weibo_status">
										<tr>
											<td>${weibo.wdate}</td>
											<td align="right">
											  <c:if test="${weibo.w_uid == user.uid}">
											   <a
												href="WeiboServlet?uid=${user.uid}&wid=${weibo.wid}&action=deleteweibo">删除</a>
												&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
											  </c:if>
											 <a
												href="WeiboServlet?wid=${weibo.wid}&action=forwardweibo&uid=${user.uid}">转发(${weibo.wtimes})</a>
												&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
												<!-- 收藏和取消收藏判断 --> 
												<c:choose>
												    <c:when test="${ weibo.flag == 1}">
												    <a
											     	href="CollectionServlet?wid=${weibo.wid}&uid=${user.uid}&action=cancelcollection">取消收藏</a>
												   &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; 
												    </c:when>
												    <c:otherwise>
												    	<a
											    	href="CollectionServlet?wid=${weibo.wid}&uid=${user.uid}&action=collection">收藏</a>
											      	&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                                    </c:otherwise> 
												</c:choose>
												
												<a
												href="#">评论(${weibo.wcountcomment})</a>
											</td>
										</tr>
									</table>
									<!-- 发布评论部分 -->
									<!-- 评论部分 -->
									  <c:if test="${! empty weibo.comments}">
									  <c:forEach items="${weibo.comments}" var="comment">
									   <c:if test="${comment.flag == 0}">
									  <c:choose>
										<c:when test="${ comment.c_cid== 0}">
									       <p><a href="#">${comment.use.uname}</a>：  
									    </c:when>
										<c:when test="${ comment.c_cid !=0}">
										  <a href="#">weibo.use.uname</a> 回复<a href="#">${comment.use.uname}</a>：  
										</c:when>
										</c:choose>
									     &nbsp;&nbsp;${comment.ccontent}&nbsp;&nbsp;
									     <c:if test="${! empty comment.cimages}"><img alt="" src="${comment.cimages}" width="30" height="30"></c:if>
									     <c:if test="${weibo.w_uid == user.uid}"><a href="#">回复</a> &nbsp;<a href="CommentServlet?action=deleteComment&uid=${user.uid }&cid=${comment.cid}">删除</a></c:if>
									      &nbsp;<br/>${comment.cdate}
									      </c:if>
									 </c:forEach>
									</c:if>
									</td>
							</tr>
						</table>
					</c:forEach>
				</c:if> <br />

			</td>
			<td width="280" align="center" valign="top" class="pageright">
					<!-- userinfo 开始-->
				<table align="center" id="userinfo">
					<tr>
						<td width="25%" rowspan="2"><img
							src="${user.upic}" width="50" height="50" /></td>
						<td width="75%"><a href="#">${user.uname }</a></td>
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
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="split1"><a href="UserServlet?action=shiftUserInfo&uid=${user.uid}">个人账户设置</a></td>
					</tr>
				</table>
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					id="userlist">
					<tr>
						<td class="title" height="29">可能感兴趣的人</td>
						<td align="right" class="title"><a
							href="UserServlet?page=1&action=userChange&uid=${user.uid}">[换一换]</a></td>
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
												href="RelationServlet?uid=${user.uid}&gid=${usl.uid}&action=guanzhu"
												style="border: 0px;"> <span class="btnguanzhu"
													style="width: 50px; height: 3px;" id="button3">+关注</span></a> <br />
											<font color="#333333" size="2px"> ${usl.uaddress}</font><br />
											<font color="#333333" size="2px">Time：${usl.udate}</font></td>
										</tr>
									</table></td>
							</tr>
						</c:forEach>
					</c:if>
				</table> <!-- user 结束-->
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
