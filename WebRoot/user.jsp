<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
<title>微博 -${user.uname}的微博注册信息</title>
<script type="text/javascript" src="script/user.js"></script>
  </head>
  <body>
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
   
    
  </tr>
</table>
<!-- header结束-->
<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" height="600" valign="top"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" id="curruser">
      <tr>
        <td width="24%"><img src="${user.upic} " width="120" height="120" class="userphoto" /></td>
        <td width="76%" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" id="curruserdetail">
          
          <tr>
            <td><br/>
            <br />
            
            </td>
          </tr>
           <tr>
            <td><p><br/>
            <br />
           <br />
            </p></td>
          </tr>
          <tr>
            <td class="title">${user.uname}<br><br/><br/></td>
          </tr>
          <tr>
            <td><p>昵称：${user.unickname}<br/><br/>
            性别：${user.usex}<br/><br/>
            地址：${user.uaddress }<br/>
           <br/></p></td>
          </tr>
          <tr>
            <td><p>注册日期：${user.udate }<br/><br/>
            教育信息：${user.uedu }<br /><br/>
            QQ：${user.uqq }<br /><br/>
            <c:if test="!empty user.uemail">
            邮箱：${user.uemail }<br /><br/>
            </c:if>
            
            <br/></p></td>
          </tr>
          
           <tr>
            <td><a href="login.jsp">+关注</a>&nbsp;&nbsp;&nbsp;<a href="index.jsp">返回</a>&nbsp;&nbsp;&nbsp;<br><br/><br/></td>
           </tr>
          <tr>
            <td><p><br/>
            <br />
            <br />
            <p></td>
          </tr>
          <tr>
            <td><p><br/>
            <br />
            <br />
            </p></td>
          </tr>
        <tr>
            <td><br/>
            <br />
            <br />
            </p></td>
          </tr>
        </table></td>
      </tr>
    </table>
     
    </td>
	  
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
