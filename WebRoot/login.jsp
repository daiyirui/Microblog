<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c" %>
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
	  <td>&nbsp;
		
	  </td>
	  <td style="margin-top: 100px; margin-left: 400px">
		<table id="header" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		  <td>&nbsp;</td>
			<td width="780" align="right">
				<font color="#6f7171" size="2">已经有账号了:&nbsp;&nbsp;&nbsp; </font>
				<a href="login.jsp" style="text-decoration: none;"><font color="#000000" size="3">登陆</font></a>
					<div class="loginin" id="55">	
						<p><font color="#6f7171" size="2">免费： </font>&nbsp;
							<a href="register.jsp?flag=false" style="text-decoration: none;">
							  <font color="#000000" size="3">注册</font>	</a>
						</p>
					</div>									
			</td>
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
         <table width="70%" border="0" cellpadding="0" cellspacing="0" id="content">
  <tr>
    <td class="phototitle" align="center">
      <font size="5">What do you think is the best Blog in the world?</font>
      <a href="register.jsp" style="text-decoration:none;"><font size="4" color="#6699cc">&nbsp;立即加入我们</font></a>
      <font size="3" style="font-family:幼圆; font-weight:bold">开始微博之旅</font><a href="index.jsp" style="text-decoration:none;"><font size="4" color="#6699cc">&nbsp;首页&nbsp;</font></a><a href="BallotServlet?action=0" style="text-decoration:none;"><font size="4" color="#6699cc">&nbsp;热门话题
      </font></a>
    </td>    
  </tr>
  <tr>
    <td height="515">
    <center>
			<!-- container -->
			<form action="LoginServlet" method="post" onsubmit="return checkForm()" id="Checkform">
			<table width="22%" border="0">
				<tr>
				 <td height="163">
				  <table width="42%" height="161" border="0" cellpadding="5" cellspacing="0" id="register_content">								
					<tr>
						<td width="103" height="103" align="center" valign="middle">账号:</td>
						<td width="742" align="left">
						 <input name="usn" type="text" class="input1" id="userid" onblur="" /></td>						
					</tr>		
					<tr>					
						<td width="53" height="45" align="left">密码:</td>
					    <td width="742" align="left">
					     <input name="pwd" type="password" class="input1" id="password" /></td>         				
					</tr>					
					<tr align="left">
					<td width="53" height="83" align="left"><input type="submit" value="登录" id="post" /></td>
                        <td align="left" nowrap><input type="checkbox" name="keep" value="on" />自动登录
                      &nbsp;&nbsp;&nbsp;<a href="findpassword.jsp">忘记密码</a>                        
                        </td>   
					</tr>
				   </table>
				 </td>
			    </tr>
			 	</table>						
		</form>
		</center>
    </td>    
  </tr>
</table>

       <!--content 结束-->
    </center>
	
	<!--container结束-->
<!--footer开始-->
<table border="0" cellpadding="5" cellspacing="0" id="index_footer" align="center">
 <tr>
   <td width="304" height="50" align="left">微博系统 版权所有</td>
   <td width="166" align="right">语言
     <select name="select" id="select">
       <option>中文(简体)</option>
              <option>英文</option>
     </select>
   
   </td>
 </tr> 
</table>
		      <!--footer结束-->
	</body>
</html>