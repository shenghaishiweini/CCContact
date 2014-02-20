<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bms.sysmgr.domain.*" %>    
<%@ page import="bms.sysmgr.manager.*" %>  
<%
    request.setCharacterEncoding("UTF-8");
	String command = request.getParameter("command");
	String mesg1 = "";
	String mesg2 = "";
	if ("modify".equals(command)) {
		Reader reader = new Reader();
		reader.setReaderId(request.getParameter("readerId"));
		reader.setReaderName(request.getParameter("readerName"));
		reader.setGender(request.getParameter("gender"));
		reader.setEmail(request.getParameter("email"));
		
		ReaderManager.getInstance().modifyReader(reader);
		mesg1 = "资料修改成功！";
	}
	
	String readerId = request.getParameter("readerId");
	Reader reader = ReaderManager.getInstance().findReaderById(readerId);
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
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>

<script src="../script/client_validate.js"></script>
<script>

	function modifyReader() {
		//读者名称不能为空
		var readerNameField = document.getElementById("readerName");
		if (trim(readerNameField.value).length == 0) {
			alert("读者姓名不能为空！");
			readerNameField.focus();
			return;
		} 
		
		//密码至少6位！
		/* var passwordField = document.getElementById("password");
		if (trim(passwordField.value).length < 6) {
			alert("密码至少6位！");
			passwordField.focus();
			return;
		}  */

		with(document.getElementById("readerForm")) {
			action="editReader.jsp";
			method="post";
			submit();
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

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><div align="center"><img src="images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[业务中心]-[<a href="readertab.jsp">读者管理</a>]-[修改读者]</td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="images/tab_12.gif">&nbsp;</td>
        <td>
          <form name="readerForm" id="readerForm">
	          <input type="hidden" name="command" value="modify">
	          <p>修改读者资料：<span id="mesg1Span"><font color='red'><%=mesg1 %></font></span></p>
	          <input type="hidden" name="readerId" id="readerId" value="<%=reader.getReaderId() %>">
	          <p>编&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="showReaderId" id="showReaderId" value="<%=reader.getReaderId() %>" disabled/></p>
	          <p>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="readerName" id="readerName" value="<%=reader.getReaderName() %>"/></p>
	          <p>性&nbsp;&nbsp;&nbsp;&nbsp;别：<input type="text" name="gender" id="gender" value="<%=reader.getGender() %>"/></p>
	          <p>电子邮件：<input type="text" name="email" id="email" value="<%=reader.getEmail() %>"/></p>
			  <p><input type="button" value="确定" onClick="modifyReader()"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="取消" /></p>
		  </form>
		</td>
        <td width="8" background="images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4">&nbsp;&nbsp;</td>
            <td><table border="0" align="right" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="40">&nbsp;</td>
                  <td width="45">&nbsp;</td>
                  <td width="45">&nbsp;</td>
                  <td width="40">&nbsp;</td>
                  <td width="100">&nbsp;</td>
                  <td width="40">&nbsp;</td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
