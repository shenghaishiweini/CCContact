<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="bms.sysmgr.domain.*" %>   
<%@ page import="bms.sysmgr.manager.*" %>

<%
    //解决乱码问题
	request.setCharacterEncoding("UTF-8");
	
	String command = request.getParameter("command");
	String userId = "";
	String userName = "";
	String password = "";
	String contactTel = "";
	String email = "";
	String mesg1 = "";
	String mesg2 = "";
	if ("add".equals(command)) {
		if (UserManager.getInstance().findUserById(request.getParameter("userId")) == null) {
			User user = new User();
			user.setUserId(request.getParameter("userId"));
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			user.setContactTel(request.getParameter("contactTel"));
			user.setEmail(request.getParameter("email"));
			user.setCreateDate(new Date());
			
			UserManager.getInstance().addUser(user);
			mesg1 = "添加用户成功";
		}else {
			userId = request.getParameter("userId");
			userName = request.getParameter("userName");
			password = request.getParameter("password");
			contactTel = request.getParameter("contactTel");
			email = request.getParameter("email");
			mesg1 = "添加用户失败";
			mesg2 = "用户编号 "+userId+" 已存在";
		}
	}
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
<script type="text/javascript">

	function addUser() {
		var userIdField = document.getElementById("userId");
		//用户代码不能为空
		if (trim(userIdField.value) == "") {
			document.getElementById("userIdSpan").innerHTML = "<font color='red'>" + "用户编号不能为空" + "</font>";
			userIdField.focus();
			return;
		}
		
		var passwordField = document.getElementById("password");
		if(passwordField.value == ""){
			document.getElementById("passwordSpan").innerHTML = "<font color='red'>" + "密码不能为空" + "</font>";
			passwordField.focus();
			return;
		}
		
		var userNameField = document.getElementById("userName");
		if(trim(userNameField.value) == ""){
			document.getElementById("userNameSpan").innerHTML = "<font color='red'>" + "用户姓名不能为空" + "</font>";
			userNameField.focus();
			return;
		}
		
		//验证两次密码输入必须得相同
		var confirmPwdField = document.getElementById("confirmPwd");
		if(confirmPwdField.value != passwordField.value){
			document.getElementById("confirmPwdSpan").innerHTML = "<font color='red'>" + "两次密码输入不同" + "</font>";
			confirmPwdField.focus();
			return;
		}
		
		
		//提交表单
		with(document.getElementById("userForm")) {
			method="post";
			action="addUser.jsp?command=add";
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

	function init(){
		document.getElementById("userId").focus();
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

<body onload="init()">
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[系统管理]-[<a href="usertab.jsp">系统维护</a>]-[增加用户]</td>
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
          <p><span id="mesg"><font color='red'><%=mesg1 %></font></span></p>
          <form id="userForm" name="userForm">
          <p>编&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="userId" id="userId" value="<%=userId %>" /><font color="#FF0000">*</font>&nbsp;<span id="userIdSpan"><font color='red'><%=mesg2 %></font></p>
          <p>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" id="password" /><font color="#FF0000">*</font>&nbsp;<span id="passwordSpan"></p>
          <p>确认密码：<input type="password" name="confirmPwd" id="confirmPwd" />&nbsp;<span id="confirmPwdSpan"></p>          
          <p>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="userName" id="userName" value="<%=userName %>"/><font color="#FF0000">*</font>&nbsp;<span id="userNameSpan"></p>
          <p>联系电话：<input type="text" name="contactTel" id="contactTel" value="<%=contactTel %>"/>&nbsp;<span id="contactTelSpan"></p>
          <p>电子邮件：<input type="text" name="email" id="email" value="<%=email %>"/>&nbsp;<span id="emailSpan"></p>
		  <p><input name="addBtn" class="button1" type="button" id="addBtn" value="添加" onClick="addUser()">&nbsp;&nbsp;&nbsp;<input type="reset" value="清空" /></p>
		  <p>注：<font color="#FF0000">*</font>号为必填项</p>
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
