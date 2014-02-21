<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsf/core"%>
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

		<title>圈圈通讯录</title>

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
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE2 {
	font-size: 9px
}

.STYLE3 {
	color: #033d61;
	font-size: 12px;
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

	</head>

	<body>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="70" background="images/main_05.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="24">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="270" height="24" background="images/main_03.gif">
											&nbsp;
										</td>
										<td width="505" background="images/main_04.gif">
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td width="21">
											<img src="images/main_07.gif" width="21" height="24">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="38">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="270" height="38" background="images/main_09.gif">
											&nbsp;
										</td>
										<td>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="77%" height="25" valign="bottom">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="50" height="19">
																	<div align="center"></div>
																</td>
																<td width="50">
																	<div align="center"></div>
																</td>
																<td width="50">
																	<div align="center"></div>
																</td>
																<td width="50">
																	<div align="center"></div>
																</td>
																<td width="50">
																	<div align="center"></div>
																</td>
																<td width="26">
																	<div align="center"></div>
																</td>
																<td width="100">
																	<div align="center"></div>
																</td>
																<td>
																	&nbsp;
																</td>
															</tr>
														</table>
													</td>
													<td width="220" valign="bottom" nowrap="nowrap">
														<div align="right">
															<span class="STYLE1"><span class="STYLE2">■</span>
																2014年2月18日</span>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="21">
											<img src="images/main_11.gif" width="21" height="38">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="8" style="line-height: 8px;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="270" background="images/main_29.gif"
											style="line-height: 8px;">
											&nbsp;
										</td>
										<td width="505" background="images/main_30.gif"
											style="line-height: 8px;">
											&nbsp;
										</td>
										<td style="line-height: 8px;">
											&nbsp;
										</td>
										<td width="21" style="line-height: 8px;">
											<img src="images/main_31.gif" width="21" height="8">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="28" background="images/main_36.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="177" height="28" background="images/main_32.gif">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="20%" height="22">
											&nbsp;
										</td>
										<td width="59%" valign="bottom">
											<div align="center" class="STYLE1">
<!--												当前用户：xxxxx-->
										尊敬的：<a href="./usermiddle.html" target="mainFrame">	${user.username}</a>
											</div>
										</td>
										<td width="21%">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="65" height="28">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="23" valign="bottom">
														<table width="58" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td height="20" style="cursor: hand"
																	onMouseOver=
	this.style.backgroundImage = 'url(images/bg.gif)';
	this.style.borderStyle = 'solid';
	this.style.borderWidth = '1';
	borderColor = '#a6d0e7';;
onmouseout=
	this.style.backgroundImage = 'url()';
	this.style.borderStyle = 'none';
>
																	<div align="center" class="STYLE3">
																		<a href="./contactormiddle.html" target="mainFrame">通讯录</a>
																	</div>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
										<td width="3">
											<img src="images/main_34.gif" width="3" height="28">
										</td>
										<td width="63">
											<table width="58" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td height="20" style="cursor: hand"
														onMouseOver=
	this.style.backgroundImage = 'url(images/bg.gif)';
	this.style.borderStyle = 'solid';
	this.style.borderWidth = '1';
	borderColor = '#a6d0e7';;
onmouseout=
	this.style.backgroundImage = 'url()';
	this.style.borderStyle = 'none';
>
														<div align="center" class="STYLE3">
															<a href="./shortMsgmiddle.html" target="mainFrame">短信</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="3">
											<img src="images/main_34.gif" width="3" height="28">
										</td>
										<td width="63">
											<table width="58" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td height="20" style="cursor: hand"
														onMouseOver=
	this.style.backgroundImage = 'url(images/bg.gif)';
	this.style.borderStyle = 'solid';
	this.style.borderWidth = '1';
	borderColor = '#a6d0e7';; 
onmouseout=
	this.style.backgroundImage = 'url()';
	this.style.borderStyle = 'none';
>
														<div align="center" class="STYLE3">
															<a href="./borrowmiddle.html" target="mainFrame">其他</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="3">
											<img src="images/main_34.gif" width="3" height="28">
										</td>
										<td width="63">
											<table width="58" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td height="20" style="cursor: hand"
														onMouseOver=
	this.style.backgroundImage = 'url(images/bg.gif)';
	this.style.borderStyle = 'solid';
	this.style.borderWidth = '1';
	borderColor = '#a6d0e7';;
onmouseout=
	this.style.backgroundImage = 'url()';
	this.style.borderStyle = 'none';
>
														<div align="center" class="STYLE3">
															<a href="sys/user_logout" target="_parent">退出 
														</div>
													</td>
												</tr>
											</table>
										</td>
										<!-- <td width="3"><img src="images/main_34.gif" width="3" height="28"></td> -->
										<!-- <td width="63"><table width="58" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="20" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#a6d0e7'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'"><div align="center" class="STYLE3">升级维护</div></td>
              </tr>
            </table></td> -->
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="21">
								<img src="images/main_37.gif" width="21" height="28">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</body>
</html>
