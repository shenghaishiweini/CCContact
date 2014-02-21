<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>


		<script>

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
			action="../sys/user_modifyPwd";
			method="get";
			submit();
		}
	}

</script>

	</head>

	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" background="images/tab_05.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30">
								<img src="images/tab_03.gif" width="12" height="30" />
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="46%" valign="middle">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="5%">
														<div align="center">
															<img src="images/tb.gif" width="16" height="16" />
														</div>
													</td>
													<td width="95%" class="STYLE1">
														<span class="STYLE3">你当前的位置</span>：[安全]-[修改密码]
													</td>
												</tr>
											</table>
										</td>
										<td width="54%">
											<table border="0" align="right" cellpadding="0"
												cellspacing="0">

											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="16">
								<img src="images/tab_07.gif" width="16" height="30" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="8" background="images/tab_12.gif">
								&nbsp;
							</td>
							<td>
								<!--          <form name="userForm" id="userForm">-->
								<!--	          <input type="hidden" name="command" value="modify">-->
								<!--	          <p>修改用户资料：<span id="mesg1Span"><font color='red'></font></span></p>-->
								<!--	          <input type="hidden" name="userId" id="userId" value="">-->
								<!--	          <p>编&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="showUserId" id="showUserId" value="" disabled/></p>-->
								<!--	          <input type="hidden" name="pwd" id="pwd" value="">               -->
								<!--	          <p>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="userName" id="userName" value=""/></p>-->
								<!--	          <p>联系电话：<input type="text" name="contactTel" id="contactTel" value=""/></p>-->
								<!--	          <p>电子邮件：<input type="text" name="email" id="email" value=""/></p>-->
								<!--			  <p><input type="button" value="确定" onClick="modifyUser()"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="取消" /></p>-->
								<!--		  </form>-->
								<form id="pwdForm">
									<input type="hidden" name="user.id" id="userId" value="${user.id }">
		
									<p>
										修改密码
										<span id="mesg2Span"><font color='red'>
										${msg }
										</font>
									</p>
								
									<p>
										原&nbsp;密&nbsp;码：
										<input type="password" name="user.password" id="oldPwd" />
										&nbsp;
										<span id="oldPwdSpan"></span>
									</p>
									<p>
										新&nbsp;密&nbsp;码：
										<input type="password" name="user.detailInfor.other1" id="newPwd" />
										&nbsp;
										<span id="newPwdSpan"></span>
									</p>
									<p>
										确认密码：
										<input type="password"  id="confirmPwd" />
										&nbsp;
										<span id="confirmPwdSpan"></span>
									</p>
									<p>
										<input type="button" value="确定" onClick="modifyPwd()" />
										&nbsp;&nbsp;&nbsp;
										<input type="reset" value="取消" />
									</p>
								</form>
							</td>
							<td width="8" background="images/tab_15.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="35" background="images/tab_19.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="35">
								<img src="images/tab_18.gif" width="12" height="35" />
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="STYLE4">
											&nbsp;&nbsp;
										</td>
										<td>
											<table border="0" align="right" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="40">
														&nbsp;
													</td>
													<td width="45">
														&nbsp;
													</td>
													<td width="45">
														&nbsp;
													</td>
													<td width="40">
														&nbsp;
													</td>
													<td width="100">
														&nbsp;
													</td>
													<td width="40">
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="16">
								<img src="images/tab_20.gif" width="16" height="35" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
