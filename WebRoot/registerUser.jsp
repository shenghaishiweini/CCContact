<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML >
<html >
<head>
<script src="script/jquery-1.8.3.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media = "screen" href="css/basic_new.css"/>
<link rel="stylesheet" type="text/css" media = "screen " href="css/pagination.css"/>
<link rel="stylesheet" type="text/css" media = "screen " href="css/ucenter.css"/>
<script src="script/jquery.validate.js" type="text/javascript" ></script>
<script type="text/javascript" src="script/sha1.js"></script>
<script type="text/javascript" src="script/register.js"></script>
<title>用户注册</title>
</head>
<body>
<div id="wrap"> 


<!--header -->
  <!--head -->
  <div id="head">
    <div class="logo"><a href="http://my.ifeng.com"><img src="http://ucimg.ifeng.com/resource/imagess/pic_logo.jpg" width="150" height="60" title="凤凰网个人中心" alt="凤凰网个人中心"/></a></div>
    <div class="login u_blue"><br />
    <a href="sys/user_login">登录</a> 丨 <a href="registerUser.jsp">注册</a></div>
  </div><!--header end-->
<!--main -->
  <div class="loginBox lh20" style="height:800px;">
	<form id="register">
      <table width="610" border="0" cellspacing="0" cellpadding="0" style="margin:20px 0 0 160px; color:#5E5E5E">
        <tr>
          <td colspan="4">&nbsp;</td>
          </tr>
        <tr>
          <td width="100" height="35" class="u_blue bold" align="right"><span class="cred">
          </span>用户名</td>
          <td width="10"></td>
          <td width="268">
          <input type="text" id="uname" name="username" class="btn4" style="width:250px;" required="required"/></td>
          <td width="283" id="tip_uname">&nbsp;</td>
        </tr>
        <tr>
          <td width="100" height="35" class="u_blue bold" align="right"><span class="cred">
          </span>密码</td>
          <td width="10"></td>
          <td width="268">
          <input type="password"  id="pass" class="btn4" style="width:250px;" required="required"/></td>
          <td width="283" id="tip_pass">&nbsp;</td>
        </tr>
        <tr>
        <td width="100" height="35" class="u_blue bold" align="right">
        <span class="cred"></span>确认密码</td>
          <td width="10"></td>
          <td width="268"><input type="password" id="cpass" class="btn4" style="width:250px;" required="required"/></td>
          <td width="283" id="tip_cpass">&nbsp;</td>
        </tr>
        
           <tr>
          <td width="90" height="35" class="u_blue bold" align="right"><span class="cred"></span>姓名</td>
          <td width="10"></td>
          <td width="268">
          <input type="text" id="true_name" maxlength="60" class="btn4" style="width:250px;"/></td>
          <td width="286" id="tip_true_name">&nbsp;</td>
        </tr>
        
           <tr>
          <td width="90" height="35" class="u_blue bold" align="right">
          <span class="cred"></span>性别</td>
          <td width="10"></td>
          <td width="268">
          <input type="text" id="pid" maxlength="60" class="btn4" style="width:250px;"/></td>
          <td width="286" id="tip_pid">&nbsp;</td>
        </tr>
        
           <tr>
          <td width="90" height="35" class="u_blue bold" align="right">
          <span class="cred"></span>手机号码</td>
          <td width="10"></td>
          <td width="268">
          <input type="text" id="code" name="cellnumber" maxlength="60" class="btn4" style="width:250px;" required number/></td>
          <td width="286" id="tip_code">&nbsp;</td>
        </tr>
        
           <tr>
          <td width="90" height="35" class="u_blue bold" align="right">
          <span class="cred"></span>电话号码</td>
          <td width="10"></td>
          <td width="268">
          <input type="text" id="tnumber" maxlength="60" class="btn4" style="width:250px;" number/></td>
          <td width="286" id="tip_tnumber">&nbsp;</td>
        </tr>
        
           <tr>
          <td width="90" height="35" class="u_blue bold" align="right"><span class="cred">
          </span>地址</td>
          <td width="10"></td>
          <td width="268"><input type="text" id="address" maxlength="60" class="btn4" style="width:250px;"/></td>
          <td width="286" id="tip_address">&nbsp;</td>
        </tr>
        
              <tr>
          <td width="90" height="35" class="u_blue bold" align="right"><span class="cred">
          </span>QQ号码</td>
          <td width="10"></td>
          <td width="268"><input type="text" id="contact" maxlength="60" class="btn4" style="width:250px;" number/></td>
          <td width="286" id="tip_contact">&nbsp;</td>
        </tr>
        
         <tr>
          <td width="90" height="35" class="u_blue bold" align="right"><span class="cred">
          </span>电子邮箱</td>
          <td width="10"></td>
          <td width="268"><input type="text" id="email" maxlength="60" class="btn4" style="width:250px;" required="required"/></td>
          <td width="286" id="tip_email">&nbsp;</td>
        </tr>

		   <tr>
          <td width="90" height="35" class="u_blue bold" align="right"><span class="cred">
          </span>其他</td>
          <td width="10"></td>
          <td width="268">
          <input type="text" id="idc" maxlength="60" class="btn4" style="width:250px;"/></td>
          <td width="286" id="tip_idc">&nbsp;</td>
        </tr>
		
        <tr>
          <td width="100" height="30" class="u_blue bold" align="right"></td>
          <td width="10"></td>
          <td height="44" colspan="3" style="padding-top:6px">
          <input type="submit"  width="122" height="33" value="注册">
          </td>
        </tr>
      </table>
      </form>
  </div>
  </div>
</body>
</html>