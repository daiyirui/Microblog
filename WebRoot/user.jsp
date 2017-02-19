<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
    <td width="55%" align="right">
      <table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
        <tr>
          <td width="20%"><a href="home.jsp">我的首页</a></td>
          <td width="20%"><a href="profile.jsp">我的博客</a></td>
          <td width="20%"><a href="friend.jsp">我的好友</a></td>
          <td width="20%"><a href="user.jsp">微博热议</a></td>
        </tr>
      </table>
    </td>
    <td width="25%" align="right">
      <table id="welcome" border="0" cellspacing="0" cellpadding="0">
        <tr>          
          <td width="30" height="30" rowspan="2" class="userface_bg"><img src="face/9.jpg" border="0" width="20" height="20" /></td>
          <td>${sessionScope.UsersDetail.uname}个人主页！</td>
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
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" height="600" valign="top"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" id="curruser">
      <tr>
        <td width="24%"><img src="${sessionScope.UsersDetail.upic}" width="120" height="120" class="userphoto" /></td>
        <td width="76%" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" id="curruserdetail">
          <tr>
            <td class="title">${sessionScope.UsersDetail.uname}</td>
          </tr>
          <tr>
            <td><p>地址：${sessionScope.UsersDetail.uaddress}<br />
            签名：${sessionScope.UsersDetail.unickname}！<br />
            QQ：${sessionScope.UsersDetail.uqq}！<br />
            <a href="#">+添加关注</a></p></td>
          </tr>
        </table></td>
      </tr>
    </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="menu">
          <tr>
            <td width="33%" align="center"><table width="165" border="0" align="center" cellpadding="0" cellspacing="0">
              
            </table></td>
            <td width="18%" align="right">&nbsp;</td>
            <td width="49%" align="center"><form id="form1" name="form1" method="post" action="">
              <input name="textfield" type="text" class="input" id="textfield" />
              <input name="button" type="submit" class="btnsearch" id="button" value="搜索" />
            </form></td>
          </tr>
      </table>
        <!-- weibo 开始-->
    <c:if test="${!empty sessionScope.UsersDetailWeibo}">
      <c:forEach items="${sessionScope.UsersDetailWeibo.data}" var="weibo">
          <table id="weibo" width="90%" border="0" align="center" cellpadding="3" cellspacing="0">
          <tr>
            <td rowspan="3" align="center" valign="top"><img src="${weibo.use.upic}" width="50" height="50" /></td>
            <td width="88%" class="content"><a href="user.jsp">${weibo.use.uname}</a><img src="icon/v.gif" width="11" height="10" align="middle" />： ${weibo.wcontent}</td>
          </tr>
          <tr>
            <td>
            <c:if test="${weibo.wimage ne null}">
               <img src="${weibo.wimage}" width="89" height="120" />   
            </c:if>
           </td>
          </tr>
          <tr>
            <td height="25"><table width="100%" border="0" cellpadding="0" cellspacing="0" id="weibo_status">
              <tr>
                <td>${weibo.wdate}</td>
                <td align="right">
                  <a href="ForWardServlet?wid=${weibo.wid}&wcontent=${weibo.wcontent}&wimage=${weibo.wimage}">转发(${weibo.wtimes})</a>  
                  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                  <a href="CollectionWeiboServlet?wcontent=${weibo.wcontent}&wimage=${weibo.wimage}">收藏</a> 
                  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                  <a href="CommentServlet?wid=${weibo.wid}">评论(${weibo.wcountcomment})</a>
                </td>
              </tr>
            </table></td>
          </tr>
    </table>
      </c:forEach>
    </c:if>  
   <br/>   
     
    <table align="center" id="page">
      <tr>
        <td align="left">第${sessionScope.UsersDetailWeibo.currentPage}页&nbsp;共${sessionScope.UsersDetailWeibo.totalRows}条微博 </td>            
        <td align="right">
           <c:if test="${sessionScope.UsersDetailWeibo.currentPage==1}">首页</c:if>
           <c:if test="${sessionScope.UsersDetailWeibo.currentPage!=1}">
             <a href="UserChangePageServlet">首页</a>
             &nbsp;<a href="UserChangePageServlet?np=${sessionScope.UsersDetailWeibo.currentPage-1}">上一页</a>
           </c:if>
           <c:if test="${sessionScope.UsersDetailWeibo.currentPage!=sessionScope.UsersDetailWeibo.totalPages}">
             &nbsp;<a href="UserChangePageServlet?np=${sessionScope.UsersDetailWeibo.currentPage+1}">下一页</a>
             &nbsp;<a href="UserChangePageServlet?np=${sessionScope.UsersDetailWeibo.totalPages}">尾页</a>
           </c:if>
           <c:if test="${sessionScope.UsersDetailWeibo.currentPage==sessionScope.UsersDetailWeibo.totalPages}">&nbsp;尾页</c:if>
         </td>  
       </tr>
    </table>
    <!-- weibo 结束-->
    </td>
	    <td width="280" align="center" valign="top" class="pageright">
        <!-- userinfo 开始-->
        <table align="center" id="userinfo">
          <tr>
            <td width="25%" rowspan="2"><img src="face/9.jpg" width="50" height="50" /></td>
            <td width="75%"><a href="profile.jsp">${sessionScope.UsersDetail.uname}</a></td>
          </tr>
          <tr>
            <td valign="top">天津</td>
          </tr>
          <tr>
            <td colspan="2" align="left"><table width="80%" border="0" align="left" cellpadding="3" cellspacing="0">
              <tr>
                <td align="center" class="split2"><a href="3">关注</a><br>${sessionScope.countDRlation}</td>
                <td align="center" class="split2"><a href="3">粉丝</a><br>${sessionScope.countDVeri}</td>
                <td align="center"><a href="#">微博</a><br>${sessionScope.countDBlog}</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td colspan="2" class="split1"><a href="userinfo.jsp">个人账户设置</a></td>
          </tr>
        </table>
       <table border="0" align="center" cellpadding="0" cellspacing="0" id="userlist">
          <tr>
            <td class="title" height="29">可能感兴趣的人</td>
            <td align="right" class="title"><a href="ChangeUserServlet?change=5">[换一换]</a></td>
          </tr>
          
          <c:if test="${!empty sessionScope.userList }">
            <c:forEach items="${sessionScope.userList}" var="usl">
               <tr>
            <td colspan="2"><table border="0" cellpadding="0" cellspacing="0" class="userdetail">
              <tr>
                <td width="26%"><a href="user.jsp"><img src="${usl.upic}" width="50" height="50" border="0" /></a></td>
                <td width="74%"><a href="user.jsp">${usl.uname}</a>
                <a href="InsertAttentionServlet?gid=${usl.uid}" style="border: 0px;" >
                <span class="btnguanzhu" style="width:50px;height: 3px;" id="button3">+关注</span></a>                 
                <br /><font  color="#333333" size="2px">
                ${usl.uaddress}</font><br /><font  color="#333333" size="2px">Time：${usl.udate}</font></td>
              </tr>
            </table></td>
          </tr>
            </c:forEach>
          </c:if>        
        </table>
        <!-- userinfo 结束--></td>
  </tr>
	</table>
<!-- container 结束-->

<!--footer开始-->
<table id="footer" border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="534" align="left"><a href="#">帮助</a>&nbsp;&nbsp; <a href="#">意见反馈</a>&nbsp;&nbsp; <a href="#">微博认证及合作</a>&nbsp;&nbsp; <a href="#">开放平台</a>&nbsp;&nbsp; <a href="#">微博招聘</a>&nbsp;&nbsp; <a href="#">微博导航</a></td>        
    <td width="447" align="right">Copyright: 2011-2015<a href="#"> 微博系统 版权所有</a></td>
  </tr>
  <tr>
    <td align="left">客服电话：400 123 12345（按当地市话标准收费）</td>
    <td align="right">语言：
      <select name="select" id="select">
        <option>中文(简体)</option>
        <option>中文(繁体)</option>
    </select></td>
  </tr>        
</table>
<!--footer结束-->
</body>
</html>
