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
<div id="staResult" style="display:none;width:100%;height:1000px;position:absolute;top:0;left:0;background-image:url(images/white_trans.png); ">
<iframe src="ShowBallotServlet" scrolling="no" frameborder="0" style=" width:600px;height:400px;position:absolute;left:200px;top:100px;" name="showResult"></iframe>
<button onclick="hide('staResult')" style="position:absolute;left:710px; width:60px; top:110px;">关闭</button>
</div>
<div id="staResult2" style="display:none;width:100%;height:1000px;position:absolute;top:50;left:0;background-image:url(images/white_trans.png); ">
<iframe src="ShowBallot3DServlet" scrolling="no" frameborder="0" style=" width:600px;height:400px;position:absolute;left:200px;top:400px;" name="showResult"></iframe>
<button onclick="hide('staResult2')" style="position:absolute;left:710px; width:60px; top:410px;">关闭</button>
</div>
<layer NAME="a0" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <layer NAME="a1" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer > <layer NAME="a2" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <layer NAME="a3" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <layer NAME="a4" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <laye rNAME="a5" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer> <layer NAME="a6" LEFT=10 TOP=10 VISIBILITY=SHOW BGCOLOR="#ffff00" CLIP="0,0,3,3"></layer>


	
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
    <td width="55%" align="right">
      <table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
        <tr>
           <td width="20%"><a href="HomeServlet">我的首页</a></td>
          <td width="20%"><a href="MyBlogServlet">我的微博</a></td>
          <td width="20%"><a href="MyCollectionServlet">我的收藏</a></td>
          <td width="20%">微博热议</td>
        </tr>
      </table>
    </td>
  <td width="25%" align="right">
      <table id="welcome" border="0" cellspacing="0" cellpadding="0">
        <tr>          
          <td width="30" height="30" rowspan="2" class="userface_bg"><img src="${sessionScope.userinfo.upic}" border="0" width="20" height="20" /></td>
          <td>欢迎您, ${sessionScope.userinfo.uname }！</td>
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
    <td width="670" height="600" valign="top">
    <form action="servlet/AddBlogServlet" enctype="multipart/form-data" method="post">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="input">
      <tr>
        <td width="165" height="32">&nbsp;</td>
        <td width="479">&nbsp;</td>
        <td width="31">&nbsp;</td>
      </tr>
     <tr>
        <td height="84">&nbsp;</td>
        <td align="right"><br/>

<textarea id="status"  name="blogtext" rows="10" cols="40" onkeydown='countChar("status","counter");' onkeyup='countChar("status","counter");'>
</textarea>
        </td>
        
        <td>&nbsp;</td>
      </tr>
      <tr><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td></tr><tr><td valign="top"><br /></td><td valign="top"><br /></td><td valign="top"><br /></td></tr><tr>
        <td>&nbsp;</td>
        <td align="right" valign="top"><input type="file" name ="upfile"/>
        <input name="dosubmit" type="submit" id="dosubmit" value="" style="background:url(images/btn_input.png); border-style:none; width:100px; height:26px; background-repeat:no-repeat;" />
       </td>
        <td>&nbsp;</td>
      </tr>
    </table></form>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="menu">
          <tr>
            <td width="33%" align="center"></td>
            <td width="18%" align="right">&nbsp;</td>
            <td width="49%" align="center">
</td>
          </tr>
      </table>
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
       
				

    <!-- weibo 结束-->
    </td>
	    <td width="280" align="center" valign="top" class="pageright">
       <!-- userinfo 开始-->
		 <table align="center" id="userinfo">
          <tr>
            <td width="25%" rowspan="2"><img src="${sessionScope.userinfo.upic}" width="50" height="50" /></td>
            <td width="75%"><a href="profile.jsp">${sessionScope.userinfo.uname }</a></td>
          </tr>
          <tr>
            <td valign="top">天津</td>
          </tr>
          <tr>
            <td colspan="2" align="left"><table width="80%" border="0" align="left" cellpadding="3" cellspacing="0">
              <tr>
                <td align="center" class="split2"><a href="FollowServlet">关注</a><br/>${sessionScope.countRlation}</td>
                <td align="center" class="split2"><a href="MyFansServlet">粉丝</a><br/>${sessionScope.countVeri}</td>
                <td align="center"><a href="#">微博</a><br/>${sessionScope.countBlog}</td>
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
		