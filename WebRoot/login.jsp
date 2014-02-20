<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bms.sysmgr.domain.*" %>     
<%@ page import="bms.sysmgr.manager.*" %>  
<%
	String mesg = "";
	String command = request.getParameter("command");
	if ("login".equals(command)){
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		try {
			User user = UserManager.getInstance().login(userId, password);
			//登陆成功将用户信息放到session中
			if(user == null){
				mesg = "用户名或密码错误";
			}else{
				session.setAttribute("login_user", user);
			
				//设置超时，单位:秒
				session.setMaxInactiveInterval(6000);
				
				//重定向到主控页面
				response.sendRedirect("main.html");
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

%>

    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>图书管理系统_用户登录</title>

<script src="script/client_validate.js"></script>
<SCRIPT language=JavaScript>

	function keyDown(){
		var e = event.srcElement;
		if(event.keyCode == 13){
			document.getElementById("subButn").focus();
			document.getElementById("subButn").click();
		}
	}

	function init(){
		document.getElementById("userId").focus();
	}
	
	function submitForm() {
		var flag = false;
		if (trim(loginForm.userId.value).length == 0) {
			flag = true;
			document.getElementById("errorSpan").innerHTML = "<font color='red'>用户名或密码不能为空</font>";
		}
		if (trim(loginForm.password.value).length == 0) {
			flag = true;
			document.getElementById("errorSpan").innerHTML = "<font color='red'>用户名或密码不能为空</font>";
		}
		//if (document.getElementById("userIdSpan").innerHTML != "" || document.getElementById("passwordSpan").innerHTML != "") {
		//	return;				
		//} 
		if (!flag) {
			loginForm.action = "login.jsp";
			loginForm.method = "post";
			loginForm.submit();
		}
	}
</SCRIPT>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #016aa9;
	overflow:hidden;
}
.STYLE1 {
	color: #000000;
	font-size: 12px;
}
-->
</style>

</head>

<body onload="init()">
<form name="loginForm">
	<input type="hidden" name="command" value="login">
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="235" background="images/login_03.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="53"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="394" height="53" background="images/login_05.gif">&nbsp;</td>
            <td width="206" background="images/login_06.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16%" height="25"><div align="right"><span class="STYLE1">帐号</span></div></td>
                <td width="57%" height="25"><div align="center">
                  <input type="text" name="userId" id="userId" onkeydown="keyDown();" style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
                </div></td>
                <td width="27%" height="25">&nbsp;</td>
              </tr>
              <tr>
                <td height="25"><div align="right"><span class="STYLE1">密码</span></div></td>
                <td height="25"><div align="center">
                  <input type="password" name="password" id="password" onkeydown="keyDown();" style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
                </div></td>
                <td height="25"><div align="left"><input type="button" id="subButn" onClick="submitForm()" tabindex="5" style="background:url(images/dl.gif); border-style:none; width:49px; height:18px; background-repeat:no-repeat;" />
                <!-- <a href="javascript:submitForm()"><img src="images/dl.gif" width="49" height="18" border="0"></a> --></div></td>
              </tr>
            </table></td>
            <td width="362" background="images/login_07.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr align="center" valign="top">
        <td height="213" background="images/login_08.gif">&nbsp;<span id="errorSpan"><font color='red'><%=mesg%></font></span></td>
      </tr>	  
    </table></td>
  </tr>
</table>
</form>

</body>
</html>