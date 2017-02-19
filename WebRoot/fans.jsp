<!-- 该页面不用 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/friend.css" />
<title>微博 - fans</title>
<script type="text/javascript" src="script/friend.js"></script>
  </head>
  <!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
    <td width="55%" align="right">
      <table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
        <tr>
          <td width="20%"><a href="home.html">我的首页</a></td>
          <td width="20%"><a href="profile.html">我的博客</a></td>
          <td width="20%"><a href="friend.html">我的好友</a></td>
          <td width="20%"><a href="user.html">随便看看</a></td>
        </tr>
      </table>
    </td>
    <td width="25%" align="right">
      <table id="welcome" border="0" cellspacing="0" cellpadding="0">
        <tr>          
          <td width="30" height="30" rowspan="2" class="userface_bg"><img src="face/9.jpg" border="0" width="20" height="20" /></td>
          <td>欢迎您，${sessionScope.userinfo.uname}！</td>
          
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
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" height="600" valign="top">
    <table border="0" align="center" cellpadding="5" cellspacing="0" id="guanzhu">
      <tr>
        <td>共有 ${sessionScope.countVeri} 人是我的粉丝</td><td align="right">&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="menu">
          <tr>
            <td width="33%" align="center"><table width="165" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center"><a href="friend.jsp">微博</a></td>
                <td align="center"><a href="friend.jsp">关注</a></td>
                <td align="center" class="menu_btn">粉丝</td>
              </tr>
            </table></td>
            <td width="18%" align="right">&nbsp;</td>
            <td width="49%" align="center">&nbsp;</td>
          </tr>
      </table>
        <!-- weibo 开始-->
    <c:if test="${!empty sessionScope.fansList}">
       <c:forEach items="${sessionScope.fansList.data}" var="fan">
           <table id="weibo" width="90%" border="0" align="center" cellpadding="3" cellspacing="0">
          <tr>
            <td rowspan="3" align="center" valign="top"><img src="${fan.upic}" width="50" height="50" /></td>
            <td width="88%" class="content"><a href="user.html">${fan.uname}</a><br/>昵称：${fan.unickname}&nbsp;&nbsp;&nbsp;&nbsp;QQ:${fan.uqq}
              <br/>地址：${fan.uaddress}<br/>            
            </td>
          </tr>         
          <tr>
            <td height="25"><table width="100%" border="0" cellpadding="0" cellspacing="0" id="weibo_status">
              <tr>
                <td>${fan.udate}</td>
                <td align="right">&nbsp;</td>
              </tr>
            </table></td>
          </tr>
    </table>
       </c:forEach>
    </c:if>    
    <table align="center" id="page">
      <tr>
        <td align="left">第${sessionScope.fansList.currentPage}页&nbsp;你共${sessionScope.fansList.totalRows}位粉丝朋友 </td>            
        <td align="right">
           <c:if test="${sessionScope.fansList.currentPage==1}">首页</c:if>
           <c:if test="${sessionScope.fansList.currentPage!=1}">
             <a href="FansServlet">首页</a>
             &nbsp;<a href="FansServlet?np=${sessionScope.fansList.currentPage-1}">上一页</a>
           </c:if>
           <c:if test="${sessionScope.fansList.currentPage!=sessionScope.fansList.totalPages}">
             &nbsp;<a href="FansServlet?np=${sessionScope.fansList.currentPage+1}">下一页</a>
             &nbsp;<a href="FansServlet?np=${sessionScope.fansList.totalPages}">尾页</a>
           </c:if>
           <c:if test="${sessionScope.fansList.currentPage==sessionScope.fansList.totalPages}">&nbsp;尾页</c:if>
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
            <td width="75%"><a href="profile.html">${sessionScope.userinfo.uname }</a></td>
          </tr>
          <tr>
            <td valign="top">天津</td>
          </tr>
          <tr>
            <td colspan="2" align="left"><table width="80%" border="0" align="left" cellpadding="3" cellspacing="0">
              <tr>            
                <td align="center" class="split2"><a href="3">关注</a><br>${sessionScope.countRlation}</td>
                <td align="center" class="split2"><a href="3">粉丝</a><br>${sessionScope.countVeri}</td>
                <td align="center"><a href="#">微博</a><br>${sessionScope.countBlog}</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td colspan="2" class="split1"><a href="userinfo.html">个人账户设置</a></td>
          </tr>
        </table>
      <table border="0" align="center" cellpadding="0" cellspacing="0" id="userlist">
          <tr>
            <td class="title" height="29">可能感兴趣的人</td>
            <td align="right" class="title"><a href="ChangeUserServlet?change=6">[换一换]</a></td>
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
