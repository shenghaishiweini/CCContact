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

		<title>My JSP 'contactortab.jsp' starting page</title>

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

		<script src="../script/client_validate.js"></script>
		<script language="javascript">
  
	var msg="${requestScope.tipMessage}";
	if(msg!=""){
		alert(msg);
	}
  
  function init(){
    document.getElementById("autolink").click();
  } 
  
  //查询读者
  function search(){
    var searchField = document.getElementById("searchText");
    //读者代码不能为空
    if (trim(searchField.value) == "") {
      alert("查询条件不能为空");
      searchField.focus();
      return;
    }else{
      with(document.getElementById("searchForm")) {
        method="get";
        action="contactortab.jsp";
        submit();
      }
    }
  }
  
  function deleteContactor() {
    var selectedContactors = document.getElementsByName("selectedContactor");
    var flag = false;
    for (var i=0; i<selectedContactors.length; i++) {
      if (selectedContactors[i].checked) {
          //已经有选中的checkbox
        flag = true;
        break;
      }
    }
    if (!flag) {
      alert("请选择需要删除的联系人！");
      return;
    } 
    
    if (window.confirm("确认删除？")) {
			with(document.getElementById("contactorform")) {
				action="sys/deleteContactor.action";
				method="post";
				submit();
			}
	}
  }
    
  function checkAll() {
    var selectedContactors = document.getElementsByName("selectedContactor");
    for (var i=0; i<selectedContactors.length; i++) {
      selectedContactors[i].checked = document.getElementById("ifAll").checked;
      //采用getElementsByName代替getElementById
      //selectedContactors[i].checked = document.getElementsByName("ifAll")[0].checked;
    }
  }
  
  function topCheckAll() {
    var selectedContactors = document.getElementsByName("selectedContactor");
    for (var i=0; i<selectedContactors.length; i++) {
      selectedContactors[i].checked = document.getElementById("topIfAll").checked;
      //采用getElementsByName代替getElementById
      //selectedContactors[i].checked = document.getElementsByName("ifAll")[0].checked;
    }
  }

  function formSubmit(){
    document.getElementById("myForm").submit()
  }
  
  function topPage() {
    window.location = "contactortab.jsp?pageNo=1"
  }
  
  function previousPage() {
    window.location = "contactortab.jsp?pageNo=1"
  } 
  
  function nextPage() {
    window.location = "contactortab.jsp?pageNo=1"
  }
  
  function bottomPage() {
    window.location = "contactortab.jsp?pageNo=1";
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
		<s:a href="sys/listAllGroups.action" id="autolink" target="groupFrame"></s:a>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" background="tab/images/tab_05.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30">
								<img src="tab/images/tab_03.gif" width="12" height="30" />
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
															<img src="tab/images/tb.gif" width="16" height="16" />
														</div>
													</td>
													<td width="95%" class="STYLE1">
														<span class="STYLE3">你当前的位置</span>：[通讯录]
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
																		<img src="tab/images/22.gif" width="14" height="14" />
																	</div>
																</td>
																<td class="STYLE1">
																	<div align="center">
																		<a href="sys/addContactor1.action">新增</a>
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
																		<img src="tab/images/11.gif" width="14" height="14" />
																	</div>
																</td>
																<td class="STYLE1">
																	<div align="center">
																		<a href="javascript:deleteContactor()">删除</a>
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
								<img src="tab/images/tab_07.gif" width="16" height="30" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="8" background="tab/images/tab_12.gif">
								&nbsp;
							</td>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="1"
									bgcolor="b5d6e6" onMouseOver="changeto()"
									onmouseout="changeback()">

									<form name="searchForm" id="searchForm">
										<span class="STYLE1"><input type="text" name="searchText" id="searchText" />&nbsp;
											<input name="searchBtn" class="button1" type="button"
												id="searchBtn" value="查询" onClick="search()"> </span>
									</form>

									<tr>
										<td width="3%" height="22" background="tab/images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<input type="checkbox" name="ifAll" id="ifAll"
													onClick="checkAll()" />
											</div>
										</td>
										<td width="14%" height="22" background="tab/images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">姓名</span>
											</div>
										</td>
										<td width="15%" height="22" background="tab/images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">移动电话</span>
											</div>
										</td>
										<td width="20%" height="22" background="tab/images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">QQ号</span>
											</div>
										</td>
										<td width="20%" height="22" background="tab/images/bg.gif"
											bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">对应分组</span>
											</div>
										</td>
										<!-- <td width="15%" height="22" background="images/bg.gif" bgcolor="#FFFFFF" class="STYLE1"><div align="center">基本操作</div></td> -->
									</tr>
									<s:form name="contactorform" id="contactorform" theme="simple">
										<%-- <%
          List<Reader> readerList = pageModel.getList();
          for(Iterator<Reader> iter=readerList.iterator(); iter.hasNext();) {
            Reader reader = iter.next();
          %> --%>
										<s:iterator value="#request.list" id="contactor">
											<tr>

												<td height="20" bgcolor="#FFFFFF">
													<div align="center">
														<!-- <input type="checkbox" name="selectedContactor" value="1" /> -->
														<s:checkbox name="selectedContactor" value="false" theme="simple" fieldValue="%{#contactor.id}"></s:checkbox>
													</div>
												</td>
												<td height="20" bgcolor="#FFFFFF">
													<div align="center" class="STYLE1">
														<div align="center">
															<s:a href="sys/getSingleContactor.action?id=%{#contactor.id}"><s:property value="name" /></s:a>
														</div>
													</div>
												</td>
												<td height="20" bgcolor="#FFFFFF">
													<div align="center">
														<span class="STYLE1"><s:property
																value="cellphoneNumber" /> </span>
													</div>
												</td>
												<td height="20" bgcolor="#FFFFFF">
													<div align="center">
														<span class="STYLE1"><s:property value="QQ" /> </span>
													</div>
												</td>
												<td bgcolor="#FFFFFF">
													<div align="center">
														<span class="STYLE1"><s:property
																value="#request.groupsOfContactor.get(#contactor.getId())" />
														</span>
													</div>
												</td>

											</tr>
										</s:iterator>
										<%-- <%
            }
          %> --%>
									</s:form>
								</table>
							</td>
							<td width="8" background="tab/images/tab_15.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="35" background="tab/images/tab_19.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="35">
								<img src="tab/images/tab_18.gif" width="12" height="35" />
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="STYLE4">
											&nbsp;&nbsp;共有
											<%-- <%=pageModel.getTotalRecords()%>
										条记录，当前第 <%=pageModel.getPageNo()%>/<%=pageModel.getTotalPages()%> --%>
											页
										</td>
										<td>
											<table border="0" align="right" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="40">
														<input type="button" onClick="topPage()" tabindex="5"
															style="background: url(tab/images/first.gif); border-style: none; width: 37px; height: 15px; background-repeat: no-repeat;" />
													</td>
													<td width="45">
														<input type="button" onClick="previousPage()" tabindex="5"
															style="background: url(tab/images/back.gif); border-style: none; width: 43px; height: 15px; background-repeat: no-repeat;" />
													</td>
													<td width="45">
														<input type="button" onClick="nextPage()" tabindex="5"
															style="background: url(tab/images/next.gif); border-style: none; width: 43px; height: 15px; background-repeat: no-repeat;" />
													</td>
													<td width="40">
														<input type="button" onClick="bottomPage()" tabindex="5"
															style="background: url(tab/images/last.gif); border-style: none; width: 37px; height: 15px; background-repeat: no-repeat;" />
													</td>
													<form id="myForm" action="contactortab.jsp" method="get">
														<td width="100">
															<div align="center">
																<span class="STYLE1">转到第 <input name="pageNo"
																		type="text" size="4"
																		style="height: 16px; width: 20px; border: 1px solid #999999;" />
																	页 </span>
															</div>
														</td>
														<td width="40">
															<input type="button" onClick="formSubmit()" tabindex="5"
																style="background: url(tab/images/go.gif); border-style: none; width: 37px; height: 15px; background-repeat: no-repeat;" />
														</td>
													</form>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="16">
								<img src="tab/images/tab_20.gif" width="16" height="35" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>

</html>
