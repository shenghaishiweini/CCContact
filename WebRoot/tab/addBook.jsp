<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="bms.sysmgr.domain.*" %>   
<%@ page import="bms.sysmgr.manager.*" %>

<%
    //解决乱码问题
	request.setCharacterEncoding("UTF-8");
	
	String command = request.getParameter("command");
	String bookId = "";
	String bookName = "";
	String ISBN = "";
	String totalNumbers = "";
	String mesg1 = "";
	String mesg2 = "";
	if ("add".equals(command)) {
		if (BookManager.getInstance().findBookById(request.getParameter("bookId")) == null) {
			Book book = new Book();
			book.setBookId(request.getParameter("bookId"));
			book.setBookName(request.getParameter("bookName"));
			book.setISBN(request.getParameter("ISBN"));
			book.setTotalNumbers(Integer.parseInt(request.getParameter("totalNumbers")));
			book.setBorrowedNumbers(0);
			book.setStoreDate(new Date());
			
			BookManager.getInstance().addBook(book);
			mesg1 = "添加图书成功";
		}else {
			bookId = request.getParameter("bookId");
			bookName = request.getParameter("bookName");
			ISBN = request.getParameter("ISBN");
			totalNumbers = request.getParameter("totalNumbers");
			mesg1 = "添加图书失败";
			mesg2 = "图书编号 "+bookId+" 已存在";
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

	function addBook() {
		var bookIdField = document.getElementById("bookId");
		//图书代码不能为空
		if (trim(bookIdField.value) == "") {
			document.getElementById("bookIdSpan").innerHTML = "<font color='red'>" + "图书编号不能为空" + "</font>";
			bookIdField.focus();
			return;
		}
		
		
		var bookNameField = document.getElementById("bookName");
		if(trim(bookNameField.value) == ""){
			document.getElementById("bookNameSpan").innerHTML = "<font color='red'>" + "图书书名不能为空" + "</font>";
			bookNameField.focus();
			return;
		}
		
		var totalNumbersField = document.getElementById("totalNumbers");
		if(trim(totalNumbersField.value) == ""){
			document.getElementById("totalNumbersSpan").innerHTML = "<font color='red'>" + "图书库存量不能为空" + "</font>";
			totalNumbersField.focus();
			return;
		}else if(trim(totalNumbersField.value).length>9){
			alert("库存量过大");
			totalNumbersField.focus();
			totalNumbersField.select();
			return;
		}else{
			var totalNumbersValue = trim(totalNumbersField.value);
			for (var i=0; i<totalNumbersValue.length; i++) {
				if (!(totalNumbersValue.charAt(i) >='0' && totalNumbersValue.charAt(i) <=9)) {
					alert("库存量必须是数字！");
					totalNumbersField.focus();
					totalNumbersField.select();
					return;
				}
			}
		}		
		
		//提交表单
		with(document.getElementById("bookForm")) {
			method="post";
			action="addBook.jsp?command=add";
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
		document.getElementById("bookId").focus();
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[业务中心]-[<a href="booktab.jsp">图书管理</a>]-[增加图书]</td>
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
          <form id="bookForm" name="bookForm">
          <p>编&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="bookId" id="bookId" value="<%=bookId %>" /><font color="#FF0000">*</font>&nbsp;<span id="bookIdSpan"><font color='red'><%=mesg2 %></font></p>
          <p>书&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="bookName" id="bookName" value="<%=bookName %>"/><font color="#FF0000">*</font>&nbsp;<span id="bookNameSpan"></p>
          <p>&nbsp;I&nbsp;S&nbsp;B&nbsp;N：<input type="text" name="ISBN" id="ISBN" value="<%=ISBN %>"/>&nbsp;<span id="ISBNSpan"></p>
          <p>库&nbsp;存&nbsp;量：<input type="text" name="totalNumbers" id="totalNumbers" value="<%=totalNumbers %>"/><font color="#FF0000">*</font>&nbsp;<span id="totalNumbersSpan"></p>
		  <p><input name="addBtn" class="button1" type="button" id="addBtn" value="添加" onClick="addBook()">&nbsp;&nbsp;&nbsp;<input type="reset" value="清空" /></p>
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
