<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>


<%
	request.setCharacterEncoding("UTF-8");
	String pageNoString = request.getParameter("pageNo");
	String mesg = "";
	String command = request.getParameter("command");
	if ("del".equals(command)) {
		//out.println(request.getParameter("selectFlag"));
		String[] userIds = request.getParameterValues("selectFlag");
		//for (int i=0; i<userIds.length; i++) {
		//	UserManager.getInstance().delUser(userIds[i]);
		//}
		//UserManager.getInstance().delUser(userIds);
		//out.println("<font color='red'>删除用户成功！</font>");
		mesg = "删除用户成功！";
	}

	int pageNo = 1;
	if (pageNoString != null && !"".equals(pageNoString)) {
		pageNo = Integer.parseInt(pageNoString);
	}
	int pageSize = 4;
	//PageModel<User> pageModel = null;
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

		<script src="../script/client_validate.js"></script>
		<script>
	
	function init(){
		var mesg = "<%=mesg%>";
		if(mesg != ""){
			alert(mesg);
		}
	} 
	
	//查询用户
	function search(){
		var searchField = document.getElementById("searchText");
		//用户代码不能为空
		if (trim(searchField.value) == "") {
			alert("查询条件不能为空");
			searchField.focus();
			return;
		}else{
			with(document.getElementById("searchForm")) {
				method="get";
				action="usertab.jsp";
				submit();
			}
		}
	}
	
	//修改用户信息
	function modifyUser() {
		var selectFlags = document.getElementsByName("selectFlag");
		//计数器
		var count = 0;
		//记录选中的checkbox索引号
		var index = 0;
		for (var i=0; i<selectFlags.length; i++) {
			if (selectFlags[i].checked) {
			    //记录选中的checkbox
				count++;
				index = i;
			}
		}
		if(count == 0) {
			alert("请选择需要修改的数据！");
			return;
		}
		if (count > 1) {
			alert("一次只能修改一个用户！");
			return;
		}
		//alert(selectFlags[index].value);
		
		window.self.location = "editUser.jsp?userId=" + selectFlags[index].value;
	}
	
	function deleteUser() {
		var selectFlags = document.getElementsByName("selectFlag");
		var flag = false;
		for (var i=0; i<selectFlags.length; i++) {
			if (selectFlags[i].checked) {
			    //已经有选中的checkbox
				flag = true;
				break;
			}
		}
		if (!flag) {
			alert("请选择需要删除的数据！");
			return;
		}	
		//删除提示
		if (window.confirm("确认删除？")) {
			with(document.getElementById("userform")) {
				action="usertab.jsp";
				method="post";
				submit();
			}
		}
	}
		
	function checkAll() {
		var selectFlags = document.getElementsByName("selectFlag");
		for (var i=0; i<selectFlags.length; i++) {
			selectFlags[i].checked = document.getElementById("ifAll").checked;
			//采用getElementsByName代替getElementById
			//selectFlags[i].checked = document.getElementsByName("ifAll")[0].checked;
		}
	}
	
	function topCheckAll() {
		var selectFlags = document.getElementsByName("selectFlag");
		for (var i=0; i<selectFlags.length; i++) {
			selectFlags[i].checked = document.getElementById("topIfAll").checked;
			//采用getElementsByName代替getElementById
			//selectFlags[i].checked = document.getElementsByName("ifAll")[0].checked;
		}
	}

	function formSubmit(){
		document.getElementById("myForm").submit()
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

	<body onLoad="init()">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" background="../images/tab_05.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30">
								<img src="../images/tab_03.gif" width="12" height="30" />
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
															<img src="../images/tb.gif" width="16" height="16" />
														</div>
													</td>
													<td width="95%" class="STYLE1">
														<span class="STYLE3">你当前的位置</span>：[系统管理]-[系统维护]
													</td>
												</tr>
											</table>
										</td>
										<td width="54%">
											<table border="0" align="right" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="60">
														<table width="87%" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td class="STYLE1">
																	<div align="center">
																		<input type="checkbox" name="topIfAll" id="topIfAll"
																			onClick="topCheckAll()" />
																	</div>
																</td>
																<td class="STYLE1">
																	<div align="center">
																		全选
																	</div>
																</td>
															</tr>
														</table>
													</td>
													<td width="60">
														<table width="90%" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td class="STYLE1">
																	<div align="center">
																		<img src="../images/22.gif" width="14" height="14" />
																	</div>
																</td>
																<td class="STYLE1">
																	<div align="center">
																		<a href="addUser.jsp">新增</a>
																	</div>
																</td>
															</tr>
														</table>
													</td>
													<td width="60">
														<table width="90%" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td class="STYLE1">
																	<div align="center">
																		<img src="images/33.gif" width="14" height="14" />
																	</div>
																</td>
																<td class="STYLE1">
																	<div align="center">
																		<a href="javascript:modifyUser()">修改</a>
																	</div>
																</td>
															</tr>
														</table>
													</td>
													<td width="52">
														<table width="88%" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td class="STYLE1">
																	<div align="center">
																		<img src="images/11.gif" width="14" height="14" />
																	</div>
																</td>
																<td class="STYLE1">
																	<div align="center">
																		<a href="javascript:deleteUser()">删除</a>
																	</div>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="16">
								<img src="../images/tab_07.gif" width="16" height="30" />
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
								<table width="100%" border="0" cellpadding="0" cellspacing="1">
									<tr>
										<td  height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">用户名</span>
											</div>
										</td>
										<td >
											<div align="left">
												<span class="STYLE1"><input name="user.username" value="${user.username }"/></span>
											</div>
										</td>
									</tr>
										<tr>
										<td  height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">姓名</span>
											</div>
										</td>
										<td >
											<div align="left">
												<span class="STYLE1"><input name="user.detailInfor.name" value="${user.detailInfor.name }"/></span>
											</div>
										</td>
									</tr>
											<tr>
										<td width="13%" height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">性别</span>
											</div>
										</td>
												<td >
											<div align="left">
												<span class="STYLE1"><input name="user.detailInfor.gender" value="${user.detailInfor.gender }"/></span>
											</div>
										</td>
									</tr>
											<tr>
										<td width="13%" height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">手机号码</span>
											</div>
										</td>
												<td >
											<div align="left">
												<span class="STYLE1"><input name="user.detailInfor.cellphoneNumber" value="${user.detailInfor.cellphoneNumber} "/></span>
											</div>
										</td>
									</tr>
											<tr>
										<td width="13%" height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">电话号码</span>
											</div>
										</td>
												<td >
											<div align="left">
												<span class="STYLE1"><input name="user.detailInfor.telephoneNumber" value="${user.detailInfor.telephoneNumber}"/></span>
											</div>
										</td>
									</tr>
											<tr>
										<td width="13%" height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">邮箱地址</span>
											</div>
										</td>
												<td >
											<div align="left">
												<span class="STYLE1"><input name="user.detailInfor.email" value="${user.detailInfor.email}"/></span>
											</div>
										</td>
									</tr>
											<tr>
										<td width="13%" height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">家庭住址</span>
											</div>
										</td>
												<td >
											<div align="left">
												<span class="STYLE1"><input name="user.detailInfor.address" value="${user.detailInfor.address}"/></span>
											</div>
										</td>
									</tr>
											<tr>
										<td width="13%" height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">QQ号</span>
											</div>
										</td>
												<td >
											<div align="left">
												<span class="STYLE1"><input name="user.detailInfor.QQ" value="${user.detailInfor.QQ}"/></span>
											</div>
										</td>
									</tr>
										<tr>
														<td width="20%" height="22" background="../images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">备注</span>
											</div>
										</td>
												<td >
											<div align="left">
												<span class="STYLE1"><input name="user.detailInfor.comments" value="${user.detailInfor.comments}"/></span>
											</div>
										</td>
										</tr>
								</table>
							</td>
							<td width="8" background="images/tab_15.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
