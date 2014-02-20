<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="bms.sysmgr.domain.*" %>   
<%@ page import="bms.sysmgr.manager.*" %>

<%
    //解决乱码问题
	request.setCharacterEncoding("UTF-8");
	
	String command = request.getParameter("command");
	String readerId = "";
	String readerName = "";
	String gender = "";
	String email = "";
	String mesg1 = "";
	String mesg2 = "";
	if ("add".equals(command)) {
		if (ReaderManager.getInstance().findReaderById(request.getParameter("readerId")) == null) {
			Reader reader = new Reader();
			reader.setReaderId(request.getParameter("readerId"));
			reader.setReaderName(request.getParameter("readerName"));
			reader.setGender(request.getParameter("gender"));
			reader.setEmail(request.getParameter("email"));
			reader.setBorrowedBooks(0);
			reader.setRegisterDate(new Date());
			
			ReaderManager.getInstance().addReader(reader);
			mesg1 = "添加读者成功";
		}else {
			readerId = request.getParameter("readerId");
			readerName = request.getParameter("readerName");
			gender = request.getParameter("gender");
			email = request.getParameter("email");
			mesg1 = "添加读者失败";
			mesg2 = "读者编号 "+readerId+" 已存在";
		}
	}
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
<script type="text/javascript">

	function addReader() {
		var readerIdField = document.getElementById("readerId");
		//读者代码不能为空
		if (trim(readerIdField.value) == "") {
			document.getElementById("readerIdSpan").innerHTML = "<font color='red'>" + "读者编号不能为空" + "</font>";
			readerIdField.focus();
			return;
		}
		
		
		var readerNameField = document.getElementById("readerName");
		if(trim(readerNameField.value) == ""){
			document.getElementById("readerNameSpan").innerHTML = "<font color='red'>" + "读者姓名不能为空" + "</font>";
			readerNameField.focus();
			return;
		}
		
		
		//提交表单
		with(document.getElementById("readerForm")) {
			method="post";
			action="addReader.jsp?command=add";
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

	function init(){
		document.getElementById("readerId").focus();
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

<body onload="init()">
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[业务中心]-[<a href="readertab.jsp">读者管理</a>]-[增加读者]</td>
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
          <p><span id="mesg"><font color='red'><%=mesg1 %></font></span></p>
          <form id="readerForm" name="readerForm">
          <p>编&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="readerId" id="readerId" value="<%=readerId %>" /><font color="#FF0000">*</font>&nbsp;<span id="readerIdSpan"><font color='red'><%=mesg2 %></font></p>
          <p>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="readerName" id="readerName" value="<%=readerName %>"/><font color="#FF0000">*</font>&nbsp;<span id="readerNameSpan"></p>
          <p>性&nbsp;&nbsp;&nbsp;&nbsp;别：<input type="text" name="gender" id="gender" value="<%=gender %>"/>&nbsp;<span id="genderSpan"></p>
          <p>电子邮件：<input type="text" name="email" id="email" value="<%=email %>"/>&nbsp;<span id="emailSpan"></p>
		  <p><input name="addBtn" class="button1" type="button" id="addBtn" value="添加" onClick="addReader()">&nbsp;&nbsp;&nbsp;<input type="reset" value="清空" /></p>
		  <p>注：<font color="#FF0000">*</font>号为必填项</p>
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
