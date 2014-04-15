<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.sys.model.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html >
	<head>
		<title>无标题文档</title>
		<script src="../script/jquery-1.8.3.min.js" type="text/javascript"></script>

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
  
function init(){
  //  document.getElementById("autolink").click();
  }
  
</script>



	</head>

	<body onLoad="init()">
		<!--	<s:a href="../sys/getConversationList" id="autolink" target="shortMsgMiddleFrame"></s:a>-->
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
																		<a href="newShortMsg.jsp">新增</a>
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

					String reciver = "", name = "";

					if (list != null && list.size() > 0) {
						User user = (User) request.getSession().getAttribute("user");

						if (list.get(0).getIfSender() != 1) {
							reciver = list.get(0).getFrom();
							name = list.get(0).getFromName();
						} else {
							reciver = list.get(0).getTo();
							name = list.get(0).getToName();
						}

						for (int i = 0; i < list.size(); i++) {
						
				%>
				<tr>
					<%
						if (list.get(i).getIfSender() != 1) {
					%>
					<td align="center" style="width: 350px; height: 100px;">
						<div
							style="margin-top: 30px; background-image: url('../images/bmsg.jpg')">
						<input type="checkbox" value="<%=list.get(i).getId() %>" name="selectedShortMsg"> 	
							<span style=""></span>
							<span style="font-size: 20px; padding-left: 10px;"><%=list.get(i).getContent()%></span>
							<br />
							<br />
							<span style="font-size: 12px;"> 发件人：<%=name%> 接收时间：<%=list.get(i).getCreateTime()%></span>
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
								
								 <input type="checkbox" value="<%=list.get(i).getId() %>" name="selectedShortMsg"> 	
								<span style="font-size: 20px; padding-left: 10px;"><%=list.get(i).getContent()%></span>
								<br />
								<br />
								<span style="font-size: 12px;"> 发件人：我 发送时间：<%=list.get(i).getCreateTime()%></span>
							</div>
						</td>
					</td>
						<%
							}
						%>
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
			<form action="../sys/sendShortMsg">
				<div style="padding-left: 20px;">
					收件人：
					<span>'<%=name%>'<%=reciver%></span>
					<input value="<%=reciver%>" type="hidden" name="shortMsg.to" />
					<input value="${ user.detailInfor.cellphoneNumber}" type="hidden"
						name="shortMsg.from" />
					<input value="1" type="hidden" name="shortMsg.msgType" />
					<input value="1" type="hidden" name="shortMsg.ifSender" />
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
