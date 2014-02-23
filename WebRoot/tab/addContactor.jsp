<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'contactorInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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

<script src="script/client_validate.js"></script>
<script type="text/javascript">
	
	function validate(){
		var nameField =document.getElementsByName("contactor.name")[0];
		if(trim(nameField.value) == "")
		{
			document.getElementById("nameSpan").innerHTML = "<font color='red'>" + "联系人姓名不能为空" + "</font>";
			nameField.focus();
       		return false;
		}
		return true;
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
		document.getElementById("readerId").focus();
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
			<td height="30" background="tab/images/tab_05.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="12" height="30"><img src="tab/images/tab_03.gif"
							width="12" height="30" />
						</td>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="46%" valign="middle"><table width="100%"
											border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="5%"><div align="center">
														<img src="tab/images/tb.gif" width="16" height="16" />
													</div>
												</td>
												<td width="95%" class="STYLE1"><span class="STYLE3">添加联系人												
												</td>
											</tr>
										</table>
									</td>
									<td width="54%"><table border="0" align="right"
											cellpadding="0" cellspacing="0">

										</table>
									</td>
								</tr>
							</table>
						</td>
						<td width="16"><img src="tab/images/tab_07.gif" width="16"
							height="30" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="8" background="tab/images/tab_12.gif">&nbsp;</td>
						<td>
						<s:form action="sys/addContactor2.action" theme="simple" onsubmit = "return validate();">
								<p>
									姓&nbsp;&nbsp;&nbsp;&nbsp;名：<s:textfield name="contactor.name" />
									<font color="#FF0000">*</font>&nbsp;<span id="nameSpan"></span>
								</p>
								<p>
									分&nbsp;&nbsp;&nbsp;&nbsp;组：
									<s:checkboxlist name="selectedGroups" list="#session.groups.{groupName}" value="" />  								
								</p>
								<p>
									性&nbsp;&nbsp;&nbsp;&nbsp;别：<s:textfield name="contactor.gender" />&nbsp;<span id="genderSpan">
								</p>
								<p>
									移动电话：<s:textfield name="contactor.cellphoneNumber" />
								</p>
								<p>
									固定电话：<s:textfield name="contactor.telephoneNumber" />
								</p>
								<p>
									电子邮件：<s:textfield name="contactor.email" />
								</p>
								<p>
									地&nbsp;&nbsp;&nbsp;&nbsp;址：<s:textfield name="contactor.address" />
								</p>
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;QQ号：<s:textfield name="contactor.QQ" />
								</p>
								<p>
									备&nbsp;&nbsp;&nbsp;&nbsp;注：<s:textfield name="contactor.comments" />
								</p>
								<p>
									&nbsp;&nbsp;other1：<s:textfield name="contactor.other1" />
								</p>
								<p>
									&nbsp;&nbsp;other2：<s:textfield name="contactor.other2" />
								</p>
								<p>
									<s:submit value="保存"></s:submit>&nbsp;&nbsp;&nbsp;<s:reset value="清空"></s:reset>
								</p>
								<p>
									注：<font color="#FF0000">*</font>号为必填项
								</p>
							</s:form></td>
						<td width="8" background="tab/images/tab_15.gif">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="35" background="tab/images/tab_19.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="12" height="35"><img src="tab/images/tab_18.gif"
							width="12" height="35" />
						</td>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td class="STYLE4">&nbsp;&nbsp;</td>
									<td><table border="0" align="right" cellpadding="0"
											cellspacing="0">
											<tr>
												<td width="40">&nbsp;</td>
												<td width="45">&nbsp;</td>
												<td width="45">&nbsp;</td>
												<td width="40">&nbsp;</td>
												<td width="100">&nbsp;</td>
												<td width="40">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td width="16"><img src="tab/images/tab_20.gif" width="16"
							height="35" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
