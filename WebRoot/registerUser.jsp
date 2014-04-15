<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
 <head>
     <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>圈圈通讯录注册页面</title>
	 <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/register.css" rel="stylesheet">
    <script type="text/javascript" src="../script/jquery-1.8.3.min.js"></script>
    <script src="../script/jquery.validate.js" type="text/javascript" ></script>
    <script type="text/javascript" src="../script/register.js"></script>
 
 </head>
 
 <body >

	
    <div class="container">
      <div class="header">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="user_login">Sign in</a></li>
          
        </ul>
        <h3 class="text-muted">Register</h3>
      </div>
      </div>
    <div class="container">
    	
      	
      	<div class="form-horizontal">
		<form id="register">
			<fieldset> 
				<div class="control-group warning">
					<div class="control-label" for="username">用户名</div>
					<div class="controls">
						<input class="input-xlarge  focused"  id="username" name="user.username" type="text" placeholder="Username" required autofocus >
					</div>	
				</div>
				<div class="control-group warning">
					<div class="control-label">密码</div>
					<div class="controls">
						<input type="password" id="pw" name ="user.password" class="input-xlarge  focused" placeholder="Password" required>
						
					</div>
				</div>
				<div class="control-group warning">
					<div class="control-label" >确认密码</div>
					<div class="controls">
					<input class="input-xlarge foused"  id="pw1"  type="password" placeholder="Password again" required>
					</div>
				</div>
				<div class="control-group ">
					<div class="control-label" >姓名</div>
					<div class="controls">
					<input class="input-xlarge foused"  type="text" name="user.detailInfor.name" placeholder="Name" >
					</div>
				</div>
				<div class="control-group ">
					<div class="control-label" >性别</div>
					<div class="controls" >
						<select id="selectError" name="user.detailInfor.gender"   class="input-xlarge foused" >
							<option>male</option>
							<option>female</option>
						</select>
					</div>
				</div>
				<div class="control-group warning">
					<div class="control-label" >手机号码</div>
					<div class="controls" >
					<input class="input-xlarge foused"  type="number" name="user.detailInfor.cellphoneNumber" placeholder="CellphoneNumber" required number>
				
					</div>
				</div>
				<div class="control-group ">
					<div class="control-label" >电话号码</div>
					<div class="controls" >
					<input class="input-xlarge foused"  type="number" name="user.detailInfor.telephoneNumber" placeholder="TelephoneNumber"  number>
					</div>
				</div>
				<div class="control-group ">
					<div class="control-label" >地址</div>
					<div class="controls" >
					<input class="input-xlarge foused"  type="text" name="user.detailInfor.address" placeholder="Address" >
					</div>
				</div>
				<div class="control-group ">
					<div class="control-label" >QQ号码</div>
					<div class="controls" >
					<input class="input-xlarge foused"  type="number" name="user.detailInfor.QQ" placeholder="QQ" >
					</div>
				</div>
				<div class="control-group warning">
					<div class="control-label" >Email</div>
					<div class="controls" >
					<input class="input-xlarge foused"  type="email" name="user.detailInfor.email" placeholder="Email" >
					
					</div>
				</div>
			<div class="form-actions">
			<button type="submit" class="btn btn-primary">注册</button>
			<button class="btn" type="reset">重置</button>
			</div>
		</fieldset>
	</form>
	</div>
</div>


    
 </body>