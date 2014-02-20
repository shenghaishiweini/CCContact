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
		Book book = new Book();
		book.setBookId(request.getParameter("bookId"));
		book.setBookName(request.getParameter("bookName"));
		book.setISBN(request.getParameter("ISBN"));
		book.setTotalNumbers(Integer.parseInt(request.getParameter("totalNumbers")));
		
		BookManager.getInstance().modifyBook(book);
		mesg1 = "资料修改成功！";
	}
	
	String bookId = request.getParameter("bookId");
	Book book = BookManager.getInstance().findBookById(bookId);
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

	function modifyBook() {
		//图书名称不能为空
		var bookNameField = document.getElementById("bookName");
		if (trim(bookNameField.value).length == 0) {
			alert("图书书名不能为空！");
			bookNameField.focus();
			return;
		} 
		
		//ISBN不能为空
		var ISBNField = document.getElementById("ISBN");
		if (trim(ISBNField.value).length == 0) {
			alert("图书ISBN不能为空！");
			ISBNField.focus();
			return;
		}
		
		//库存量不能为空，不能大于9位，必须每一位都为数字，不能小于已借数量
		var totalNumbersField = document.getElementById("totalNumbers");
		var borrowed = <%=book.getBorrowedNumbers() %>;//图书的已被借数量
		var editedNumbers = trim(totalNumbersField.value);//管理员修改后的库存量
		if (trim(totalNumbersField.value).length == 0) {
			alert("图书库存量不能为空！");
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
		if(editedNumbers<borrowed){
			alert("库存量不能小于已被借数量");
			totalNumbersField.focus();
			totalNumbersField.select();
			return;
		}

		//提交表单
		with(document.getElementById("bookForm")) {
			action="editBook.jsp";
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[业务中心]-[<a href="booktab.jsp">图书管理</a>]-[修改图书]</td>
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
          <form name="bookForm" id="bookForm">
	          <input type="hidden" name="command" value="modify">
	          <p>修改图书资料：<span id="mesg1Span"><font color='red'><%=mesg1 %></font></span></p>
	          <input type="hidden" name="bookId" id="bookId" value="<%=book.getBookId() %>">
	          <p>编&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="showBookId" id="showBookId" value="<%=book.getBookId() %>" disabled/></p>
	          <p>书&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="bookName" id="bookName" value="<%=book.getBookName() %>"/></p>
	          <p>&nbsp;I&nbsp;S&nbsp;B&nbsp;N：<input type="text" name="ISBN" id="ISBN" value="<%=book.getISBN() %>"/></p>
	          <p>库&nbsp;存&nbsp;量：<input type="text" name="totalNumbers" id="totalNumbers" value="<%=book.getTotalNumbers() %>"/></p>
			  <p><input type="button" value="确定" onClick="modifyBook()"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="取消" /></p>
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
