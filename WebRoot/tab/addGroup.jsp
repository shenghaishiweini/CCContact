<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE3 {
	font-size: 12px;
	color: #033d61;
}

a:link {
	color: #033d61;
	text-decoration: none;
}

a:visited {
	text-decoration: none;
	color: #033d61;
}

a:hover {
	text-decoration: none;
	color: #0000FF;
}

a:active {
	text-decoration: none;
}
-->
</style>
<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #ffffff;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #FFCC00;
	POSITION: relative;
	TOP: 2px
}
</style>
<!--<script>
	var he = document.body.clientHeight - 105
	document.write("<div id=tt style=height:"+he+";overflow:hidden>")
</script>

--><script src="../script/client_validate.js"></script>
<script type="text/javascript">
	
	var msg="${requestScope.tipMessage}";
	if(msg!=""){
		alert(msg);
	}
	
	function validate(){
		var nameField =document.getElementsByName("groupName")[0];
		if(trim(nameField.value) == "")
		{
			alert("分组名不能为空");
			nameField.focus();
       		return false;
		}
		return true;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<s:form action="newGroup2.action" theme="simple" onsubmit = "return validate();">
<table width="165" height="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="28" background="../images/main_40.gif"><table
				width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="19%">&nbsp;</td>
					<td width="81%" height="20"><span class="STYLE1"></span></td>
				</tr>
			</table></td>
	</tr>
	<tr>
		<td valign="top"><table width="151" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td><table width="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td height="23" background="../images/main_47.gif" id="imgmenu1"
									class="menu_title" onMouseOver="this.className='menu_title2';"
									onClick=""
									onMouseOut="this.className='menu_title';" style="cursor:hand"><table
										width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="18%">&nbsp;</td>
											<td width="82%" class="STYLE1"><s:a href="listAllContactors.action" target="contactorFrame">全部分组[<s:property value="#request.numbers.get(0)"/>]</s:a></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td background="../images/main_51.gif" id="submenu1">
									<div class="sec_menu">
										
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td><table width="90%" border="0" align="center"
														cellpadding="0" cellspacing="0">

														<s:iterator value="#request.list" id="group">
															<tr>
															<td width="16%" height="25"><div align="center">
																	<img src="../images/left.gif" width="10" height="10" />
																</div></td>
															<td width="84%" height="23"><table width="95%"
																	border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="20" style="cursor:hand"
																			onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																			onmouseout="this.style.borderStyle='none'"><span
																			class="STYLE3"><s:a href="listContactorsOfGroup.action?id=%{#group.id}&groupName=%{#group.groupName}" target="contactorFrame"><s:property value="groupName"/></s:a>[<s:property value="#request.numbers.get(#group.id)"/>] </span></td>
																	</tr>
																</table></td>
															</tr>
														</s:iterator>
														
															<tr>
																<td width="16%" height="25"><div align="center">
																		<img src="../images/left.gif" width="10" height="10" />
																	</div></td>
																<td width="84%" height="23"><table width="95%"
																		border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'">
																					<s:textfield cssStyle="width:100px" name="groupName" />
																			</td>
																		</tr>
																	</table></td>
															</tr>
														
													</table></td>
											</tr>
											<tr>
											<td align="center">
												<s:submit value="确定" cssStyle="width:60px"></s:submit>
												<input align="middle" type="button" value="取消" style="width:60px;" onclick="window.location.href='listAllGroups.action'"/>
											</td>
											</tr>
											
											<tr>
												<td height="5"><img src="../images/main_52.gif"
													width="151" height="5" /></td>
											</tr>
										</table>
										
									</div></td>
							</tr>

						</table></td>
				</tr>
			</table>
			</td>
	</tr>
	<tr>
		<td height="18" background="../images/main_58.gif"><table
				width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="18" valign="bottom"><div align="center"
							class="STYLE3">版本：2014V1.0</div></td>
				</tr>
			</table></td>
	</tr>
</table>
</s:form>
<script>
	function showsubmenu(sid) {
		whichEl = eval("submenu" + sid);
		imgmenu = eval("imgmenu" + sid);
		if (whichEl.style.display == "none") {
			eval("submenu" + sid + ".style.display=\"\";");
			imgmenu.background = "../images/main_47.gif";
		} else {
			eval("submenu" + sid + ".style.display=\"none\";");
			imgmenu.background = "../images/main_48.gif";
		}
	}
</script>
