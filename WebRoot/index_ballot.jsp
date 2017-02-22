<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<STYLE>
A.menuitem {
COLOR: menutext; TEXT-DECORATION: none
}
A.menuitem:hover {
COLOR: highlighttext; BACKGROUND-COLOR: highlight
}
DIV.contextmenu {
BORDER-RIGHT: 2px outset; BORDER-TOP: 2px outset; Z-INDEX: 999; VISIBILITY: hidden; BORDER-LEFT: 2px outset; BORDER-BOTTOM: 2px outset; POSITION: absolute; BACKGROUND-COLOR: buttonface
}
</STYLE> 

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
<title>微博 - ${sessionScope.user.u_account}的主页</title>
<script type="text/javascript" src="script/home.js"></script>

 <link rel="stylesheet" type="text/css" href="css/index.css" />
<script type="text/javascript" src="script/global.js"></script>
<script type="text/javascript" src="script/index.js"></script>

<script language="javascript"> 
 
function countChar(textareaName,spanName)
{  
 
document.getElementById(spanName).innerHTML = 140 - document.getElementById(textareaName).value.length;
}  

function show(id){

	var obj=document.getElementById(id);
	obj.style.display="block";
}
function hide(id){
	var obj=document.getElementById(id);
	obj.style.display="none";
}
</script>  


</head>
<body>
 <div>
 ${bloghots}
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

	
<!-- header结束-->
<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
  <tr>
    <td width="670" height="600" valign="top">
   
       <table width="709" border="0" cellpadding="0" >
  <tr>
    <td width="705" height="46"><span class="STYLE6"><h1>微博热议：</h1></span></td>
  </tr>
  <tr>
    <td height="166">
    <form id="form1" name="form1" method="post" action="BallotServlet">
    <table width="707" border="1" cellpadding="0">
      <tr>
        <td height="35" colspan="2">1、${sessionScope.title1}：</td>
        </tr>
      <tr>
        <td height="84" colspan="2" align="left">
        <c:if test="${!empty sessionScope.bol1}">
          <c:forEach items="${sessionScope.bol1}" var="bo">
             <label><input type="radio" name="tennis" value="${bo.bitems}" />${bo.bitems}</label>  
          </c:forEach>
        </c:if>        
	   </td>
        </tr>    
      <tr>
        <td width="462" height="37" align="right"><input type="submit" value="投票" /></td>
        <td width="239" align="right"><input type="button" onclick="show('staResult')" value="查看投票结果" /></td>
      </tr>
      
    </table></form></td>
  </tr>
  <tr>
    <td height="114">
    <form id="form2" name="form2" method="post" action="BallotServlet">
    <table width="706" border="1" cellpadding="0">
      <tr>
        <td height="42" colspan="2">2、${sessionScope.title2}：</td>
        </tr>
      <tr>
        <td height="91" colspan="2">
         <c:if test="${!empty sessionScope.bol2}">
          <c:forEach items="${sessionScope.bol2}" var="bo">
              <label><input type="radio" name="tennis" value="${bo.bitems}" />${bo.bitems}</label>
          </c:forEach>
          </c:if>      
         </td>
        </tr>
      <tr>
       <td width="462" height="37" align="right"><input type="submit" value="投票" /></td>
         <td width="238" align="right"><input type="button" onclick="show('staResult2')" value="查看投票结果" /></td>
      </tr>
    </table>
    </form></td>
  </tr>
</table>
       <!-- weibo 开始-->
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
		