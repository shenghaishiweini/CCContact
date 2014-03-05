<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.sys.model.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>无标题文档</title>
		<script src="../script/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script type="text/javascript">

		$(document).ready(function() {
		
			var sendX=$(window).height();
			
			
		
		});
		
</script>

		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url('../images/msgb2.png');
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
  
  
  //查询图书
  function search(){
    var searchField = document.getElementById("searchText");
    //图书代码不能为空
    if (trim(searchField.value) == "") {
      alert("查询条件不能为空");
      searchField.focus();
      return;
    }else{
      with(document.getElementById("searchForm")) {
        method="get";
        action="booktab.jsp";
        submit();
      }
    }
  }
  
  //修改图书信息
  function modifyBook() {
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
      alert("一次只能修改一个图书！");
      return;
    }
    //alert(selectFlags[index].value);
    
    window.self.location = "editBook.jsp?bookId=" + selectFlags[index].value;
  }
  
  function deleteShortMsg() {
    var selectFlags = document.getElementsByName("selectedShortMsg");
    var flag = false;
    for (var i=0; i<selectFlags.length; i++) {
      if (selectFlags[i].checked) {
          //已经有选中的checkbox
        flag = true;
        break;
      }
    }
    if (!flag) {
      alert("请选择需要删除的短信！");
      return;
    } 
    //删除提示
    if (window.confirm("确认删除？")) {
        //window.self.location = "../sys/deleteShortMsg";
       with(document.getElementById("delform")) {
				method="post";
				action="../sys/deleteShortMsg";
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
														<span class="STYLE3">你当前的位置</span>：[短信]-[详细会话]
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
																		<img src="images/22.gif" width="14" height="14" />
																	</div>
																</td>
																<td class="STYLE1">
																	<div align="center">
																		<a href="addBook.jsp">新增</a>
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
																		<a href="javascript:modifyBook()">修改</a>
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
																		<a href="javascript:deleteShortMsg()">删除</a>
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
								<img src="images/tab_07.gif" width="16" height="30" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<form id="delform">
			<table align="right"
				style="padding-right: 120px; background-image: url('../images/msgb.jpg');">
				<%
					List<ShortMsg> list = (List<ShortMsg>) request.getSession()
							.getAttribute("conversationDetailMsgs");
					if (list != null && list.size() > 0) {
						User user = (User) request.getSession().getAttribute("user");
						ShortMsg t = null;
						int j = 0;
				
							for (int i = 0; i < list.size(); i++) {
								t = list.get(i);
				%>
				<tr>
					<%
						if (list.get(i).getFrom().equals(
											user.getDetailInfor().getCellphoneNumber())) {
					%>
					<td align="center" style="width: 350px; height: 100px;">
						<div
							style="margin-top: 30px; background-image: url('../images/bmsg.jpg')">
							<s:checkbox name="selectedShortMsg" value="false" theme="simple"
								fieldValue="{#t}"></s:checkbox>
							<span style=""></span>
							<span style="font-size: 20px; padding-left: 10px;"><%=list.get(i).getContent()%></span>
							<br />
							<br />
							<span style="font-size: 12px;"> 发件人：<%=list.get(i).getConversation()%>
								接收时间：<%=list.get(i).getCreateTime()%></span>
						</div>
					</td>
					<td></td>
					<%
						} else {
					%>
					<td align="left" style="width: 350px; height: 100px;">
						<div style="margin-top: 30px;"></div>
					</td>
					<td align="right">
						<td align="left" style="width: 350px; height: 100px;">
							<div
								style="margin-top: 30px; background-image: url('../images/bmsg.jpg')">
								<s:checkbox name="selectedShortMsg" value="false" theme="simple"
									fieldValue="{#t}"></s:checkbox>
								<span style="font-size: 20px; padding-left: 10px;"><%=list.get(i).getContent()%></span>
								<br />
								<br />
								<span style="font-size: 12px;"> 发件人：我 发送时间：<%=list.get(i).getCreateTime()%></span>
							</div>
						</td>


						<%
							}
						%>
					</td>
				</tr>
				<%
					}
						
						
					} else {
				%>
				<tr>
					<td>
						<span>在电脑上也可以查看手机上的短信啦，亲，还是实时的哦。。。</span>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
		<%
			if (list != null) {
		%>
		<div style="position: fixed; margin-top: 80px;">
			<form action="sendShortMsg">
				<div style="padding-left: 20px;">
					收件人： name:
					<span>${user.detailInfor.cellphoneNumber }</span>
					<input value="${ user.detailInfor.cellphoneNumber}" type="hidden"
						name="shortMsg.to" />
					<input value="${ user.detailInfor.cellphoneNumber}" type="hidden"
						name="shortMsg.from" />
					<input value="1" type="hidden" name="shortMsg.msgType" />
				</div>
				<br />
				<div style="padding-left: 20px;">
					<s:textarea cols="30" rows="10" name="shortMsg.content">
					</s:textarea>
					<br>

					<div align="right">
						<input type="submit" value="发送"
							style="width: 150px; height: 50px;" />
					</div>
				</div>
			</form>
		</div>
		<%
			}
		%>
	</body>


</html>
