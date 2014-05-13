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

    <title>圈圈通讯录</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

      <div style="background-image: url(https://dn-st.qbox.me/pages/images/login-cover.jpg);" >
      	<div class="aside-bar"></div>
    </div>
    <div class="container">
      <form class="form-signin" role="form" action="user_checkLogin" method="post">
        <h1 class="form-signin-heading">圈圈通讯录</h1>
        <input type="text" name="user.username" class="form-control" placeholder="Username" required autofocus>
        <input type="password" name="user.password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
<!--         <button class="btn btn-lg btn-primary btn-block" style="width: 220px;" type="submit">登陆</button> -->
          <button type="submit" class="btn btn-primary btn-large">登录</button>
            
            <a href="../registerUser.jsp" class="signup">注册</a><span>
            |</span><a href="/forgot" class="forget">忘记密码</a>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
 
  </body>
</html>
