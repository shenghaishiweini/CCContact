<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bms.sysmgr.domain.*" %>    
<%@ page import="bms.sysmgr.manager.*" %>  
<%
    request.setCharacterEncoding("UTF-8");
	String command = request.getParameter("command");
	String mesg1 = "";
	String mesg2 = "";
	if ("modify".equals(command)) {
		User user = new User();
		user.setUserId(request.getParameter("userId"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("pwd"));
		user.setContactTel(request.getParameter("contactTel"));
		user.setEmail(request.getParameter("email"));
		
		UserManager.getInstance().modifyUser(user);
		mesg1 = "资料修改成功！";
	}else if("modifyPwd".equals(command)){
		String userId = request.getParameter("userId");
		String oldPwd = UserManager.getInstance().findUserById(userId).getPassword();
		if(!request.getParameter("oldPwd").equals(oldPwd)){
			mesg2 = "原密码不正确";
		}else{
			UserManager.getInstance().modifyPassword(userId, request.getParameter("newPwd"));
			mesg2 = "密码修改成功";
		}
	}
	
	String userId = request.getParameter("userId");
	User user = UserManager.getInstance().findUserById(userId);
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>

<script src="../script/client_validate.js"></script>
<script>

	function modifyUser() {
		//用户名称不能为空
		var userNameField = document.getElementById("userName");
		if (trim(userNameField.value).length == 0) {
			alert("用户姓名不能为空！");
			userNameField.focus();
			return;
		} 
		
		//密码至少6位！
		/* var passwordField = document.getElementById("password");
		if (trim(passwordField.value).length < 6) {
			alert("密码至少6位！");
			passwordField.focus();
			return;
		}  */

		with(document.getElementById("userForm")) {
			action="editUser.jsp";
			method="post";
			submit();
		}
	}
	
	function modifyPwd(){
	
		var oldPwdField = document.getElementById("oldPwd");
		if(oldPwdField.value == ""){
			document.getElementById("oldPwdSpan").innerHTML = "<font color='red'>" + "原密码不能为空" + "</font>";
			oldPwdField.focus();
			return;
		}else{
			document.getElementById("oldPwdSpan").innerHTML = "";
		}
		
		var newPwdField = document.getElementById("newPwd");
		if(newPwdField.value == ""){
			document.getElementById("newPwdSpan").innerHTML = "<font color='red'>" + "新密码不能为空" + "</font>";
			newPwdField.focus();
			return;
		}else{
			document.getElementById("newPwdSpan").innerHTML = "";
		}
		
		//验证两次密码输入必须得相同
		var confirmPwdField = document.getElementById("confirmPwd");
		if(confirmPwdField.value != newPwdField.value){
			document.getElementById("confirmPwdSpan").innerHTML = "<font color='red'>" + "两次密码输入不同" + "</font>";
			confirmPwdField.focus();
			return;
		}else{
			document.getElementById("confirmPwdSpan").innerHTML = "";
		}
		
		with(document.getElementById("pwdForm")) {
			action="editUser.jsp";
			method="post";
			submit();
		}
	}

var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><div align="center"><img src="images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[系统管理]-[<a href="usertab.jsp">系统维护</a>]-[修改用户]</td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="images/tab_12.gif">&nbsp;</td>
        <td>
          <form name="userForm" id="userForm">
	          <input type="hidden" name="command" value="modify">
	          <p>修改用户资料：<span id="mesg1Span"><font color='red'><%=mesg1 %></font></span></p>
	          <input type="hidden" name="userId" id="userId" value="<%=user.getUserId() %>">
	          <p>编&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="showUserId" id="showUserId" value="<%=user.getUserId() %>" disabled/></p>
	          <input type="hidden" name="pwd" id="pwd" value="<%=user.getPassword() %>">               
	          <p>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="userName" id="userName" value="<%=user.getUserName() %>"/></p>
	          <p>联系电话：<input type="text" name="contactTel" id="contactTel" value="<%=user.getContactTel() %>"/></p>
	          <p>电子邮件：<input type="text" name="email" id="email" value="<%=user.getEmail() %>"/></p>
			  <p><input type="button" value="确定" onClick="modifyUser()"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="取消" /></p>
		  </form>
		  <form name="pwdForm" id="pwdForm">
		  	  <input type="hidden" name="command" value="modifyPwd">
			  <p>修改密码：<span id="mesg2Span"><font color='red'><%=mesg2 %></font></p>
			  <input type="hidden" name="userId" id="userId" value="<%=user.getUserId() %>">
			  <p>原&nbsp;密&nbsp;码：<input type="text" name="oldPwd" id="oldPwd" />&nbsp;<span id="oldPwdSpan"></span></p>
			  <p>新&nbsp;密&nbsp;码：<input type="text" name="newPwd" id="newPwd" />&nbsp;<span id="newPwdSpan"></span></p>
	          <p>确认密码：<input type="text" name="confirmPwd" id="confirmPwd" />&nbsp;<span id="confirmPwdSpan"></span></p>
	          <p><input type="button" value="确定" onClick="modifyPwd()" />&nbsp;&nbsp;&nbsp;<input type="reset" value="取消" /></p>
		  </form>
		</td>
        <td width="8" background="images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4">&nbsp;&nbsp;</td>
            <td><table border="0" align="right" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="40">&nbsp;</td>
                  <td width="45">&nbsp;</td>
                  <td width="45">&nbsp;</td>
                  <td width="40">&nbsp;</td>
                  <td width="100">&nbsp;</td>
                  <td width="40">&nbsp;</td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
