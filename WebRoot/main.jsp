<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<!--
		CircleCircle v1.0.0

		Copyright 2012 Muhammad Usman
		Licensed under the Apache License v2.0
		http://www.apache.org/licenses/LICENSE-2.0

		http://usman.it
		http://twitter.com/halalit_usman
	-->
	<meta charset="utf-8">
	<title>圈圈通讯录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="CircleCircle address book">
	<meta name="author" content="Muhammad Usman">

	<!-- The styles -->
	<link id="bs-css" href="<%=path %>/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="<%=path %>/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="<%=path %>/css/charisma-app.css" rel="stylesheet">
	<link href="<%=path %>/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='<%=path %>/css/fullcalendar.css' rel='stylesheet'>
	<link href='<%=path %>/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='<%=path %>/css/chosen.css' rel='stylesheet'>
	<link href='<%=path %>/css/uniform.default.css' rel='stylesheet'>
	<link href='<%=path %>/css/colorbox.css' rel='stylesheet'>
	<link href='<%=path %>/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='<%=path %>/css/jquery.noty.css' rel='stylesheet'>
	<link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet'>
	<link href='<%=path %>/css/elfinder.min.css' rel='stylesheet'>
	<link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet'>
	<link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='<%=path %>/css/opa-icons.css' rel='stylesheet'>
	<link href='<%=path %>/css/uploadify.css' rel='stylesheet'>
	
	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
		
</head>

<body>
		<!-- topbar starts -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="<%=path %>/index.html"> <img alt="CircleCircle Logo" src="<%=path %>/img/logo20.png" /> <span>圈圈通讯录</span></a>
				
				<!-- theme selector starts -->
				<div class="btn-group pull-right theme-container" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-tint"></i><span class="hidden-phone"> Change Theme / Skin</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="themes">
						<li><a data-value="classic" href="#"><i class="icon-blank"></i> Classic</a></li>
						<li><a data-value="cerulean" href="#"><i class="icon-blank"></i> Cerulean</a></li>
						<li><a data-value="cyborg" href="#"><i class="icon-blank"></i> Cyborg</a></li>
						<li><a data-value="redy" href="#"><i class="icon-blank"></i> Redy</a></li>
						<li><a data-value="journal" href="#"><i class="icon-blank"></i> Journal</a></li>
						<li><a data-value="simplex" href="#"><i class="icon-blank"></i> Simplex</a></li>
						<li><a data-value="slate" href="#"><i class="icon-blank"></i> Slate</a></li>
						<li><a data-value="spacelab" href="#"><i class="icon-blank"></i> Spacelab</a></li>
						<li><a data-value="united" href="#"><i class="icon-blank"></i> United</a></li>
					</ul>
				</div>
				<!-- theme selector ends -->
				
				<!-- user dropdown starts -->
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> ${user.username}</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">个人中心</a></li>
						<li class="divider"></li>
						<li><a href="login.html">退出</a></li>
					</ul>
				</div>
				<!-- user dropdown ends -->
				
				<div class="top-nav nav-collapse">
					<ul class="nav">
						<li><a href="listAllGroups.action">通讯录</a></li>
						<li><a href="http://www.baidu.com">短信</a></li>
						<li><a href="http://www.baidu.com">其它</a></li>
						<li>
							<form class="navbar-search pull-left">
								<input placeholder="Search" class="search-query span2" name="query" type="text">
							</form>
						</li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">联系人分组</li>
						<s:iterator value="#request.list" id="group">
							<li><a class="ajax-link" href="listContactorsOfGroup.action?id={#group.id}&groupName=%{#group.groupName}"><i class="icon-align-justify"></i><span class="hidden-tablet"> <s:property value="groupName"/></span></a></li>
						</s:iterator>
					</ul>
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
			
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">Tables</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i> Members</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>姓名</th>
								  <th>移动电话</th>
								  <th>QQ号</th>
								  <th>对应分组</th>
								  <th>操作</th>
							  </tr>
						  </thead>   
						  <tbody>
						  	<s:iterator value="#request.contatorList" id="contactor">
								<tr>
									<td><s:property value="name" /></td>
									<td class="center"><s:property value="cellphoneNumber" /></td>
									<td class="center"><s:property value="QQ" /></td>
									<td class="center">
										<s:property value="#request.groupsOfContactor.get(#contactor.getId())" />
									</td>
									<td class="center">
										<a class="btn btn-success" href="#">
											<i class="icon-zoom-in icon-white"></i>  
											查看                                            
										</a>
										<a class="btn btn-info" href="#">
											<i class="icon-edit icon-white"></i>  
											编辑                                            
										</a>
										<a class="btn btn-danger" href="#">
											<i class="icon-trash icon-white"></i> 
											删除
										</a>
									</td>
								</tr>						  		
						  	</s:iterator>
							
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->			
			
					<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
		<hr>

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Settings</h3>
			</div>
			<div class="modal-body">
				<p>Here settings can be configured...</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary">Save changes</a>
			</div>
		</div>

		<footer>
			<p class="pull-left">&copy; <a href="http://usman.it" target="_blank">Muhammad Usman</a> 2012</p>
			<p class="pull-right">Powered by: <a href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
		</footer>
		
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	
	<!-- jQuery -->
	<script src="<%=path %>/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="<%=path %>/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="<%=path %>/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="<%=path %>/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="<%=path %>/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="<%=path %>/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="<%=path %>/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="<%=path %>/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="<%=path %>/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="<%=path %>/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="<%=path %>/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="<%=path %>/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="<%=path %>/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="<%=path %>/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="<%=path %>/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="<%=path %>/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='<%=path %>/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=path %>/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="<%=path %>/js/excanvas.js"></script>
	<script src="<%=path %>/js/jquery.flot.min.js"></script>
	<script src="<%=path %>/js/jquery.flot.pie.min.js"></script>
	<script src="<%=path %>/js/jquery.flot.stack.js"></script>
	<script src="<%=path %>/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->
	
	<!-- select or dropdown enhancer -->
	<script src="<%=path %>/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="<%=path %>/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="<%=path %>/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="<%=path %>/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="<%=path %>/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="<%=path %>/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="<%=path %>/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="<%=path %>/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="<%=path %>/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="<%=path %>/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=path %>/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=path %>/js/charisma.js"></script>
	</body>
</html>
