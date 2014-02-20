<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Timestamp" %>   
<%@ page import="java.text.*" %>
<%@ page import="bms.util.*" %>
<%@ page import="bms.sysmgr.domain.*" %>    
<%@ page import="bms.sysmgr.manager.*" %>

<%
  request.setCharacterEncoding("UTF-8");
  String mesg1 = "";
  String mesg2 = "";
  String borrowMesg = "";
  String text1 = "";
  String text2 = "";
  String readerId = "";
  String readerName = "";
  String gender = "";
  String email = "";
  String borrowedBooks = "";
  String registerDate = "";
  String bookId = "";
  String bookName = "";
  String ISBN = "";
  String totalNumbers = "";
  String borrowedNumbers = "";
  String storeDate = ""; 
  String command = request.getParameter("command");
  Book book = null;
  
  if("search".equals(command)){
  	String searchReader = request.getParameter("searchReaderText");
  	String searchBook = request.getParameter("searchBookText");
  	Reader reader = ReaderManager.getInstance().findReaderById(searchReader);
  	if(!"".equals(searchReader)){
  		if(reader != null){
	  		readerId = reader.getReaderId();
	  		readerName = reader.getReaderName();
	  		gender = reader.getGender();
	  		email = reader.getEmail();
	  		borrowedBooks = String.valueOf(reader.getBorrowedBooks());
	  		registerDate = String.valueOf(reader.getRegisterDate());
	  		text1 = searchReader;  		
  		}else{
  			mesg1 = "不存在该读者";
  			text1 = searchReader;
  		}	
  	}
  	
  	book= BookManager.getInstance().findBookById(searchBook);
  	if(!"".equals(searchBook)){
  		if(book != null){
	  		bookId = book.getBookId();
	  		bookName = book.getBookName();
	  		ISBN = book.getISBN();
	  		totalNumbers = String.valueOf(book.getTotalNumbers());
	  		borrowedNumbers = String.valueOf(book.getBorrowedNumbers());
	  		storeDate = String.valueOf(book.getStoreDate());
	  		text2 = searchBook;
  		}else{
	  		mesg2 = "不存在该书";
	  		text2 = searchBook;
  		}
  	}
  	
  }else if("borrow".equals(command)){
  	String searchReader = request.getParameter("searchReaderText");
  	String searchBook = request.getParameter("searchBookText");
  	Borrow borrow = new Borrow().findRecordById(searchReader,searchBook);
  	if(borrow == null){
  		Borrow addBorrow = new Borrow();
  		addBorrow.setReaderId(searchReader);
  		addBorrow.setBookId(searchBook);
  		Timestamp borrowTime = new Timestamp(new Date().getTime());
  		addBorrow.setBorrowTime(borrowTime);
  		
  		long l = borrowTime.getTime()+30*24*60*60*1000L;
  		Timestamp expiredTime = new Timestamp(l);
  		addBorrow.setExpiredTime(expiredTime);
  		
  		addBorrow.setReturnTime(new Timestamp(0L));//0L时间表示未还，转换成SQLserver的数据为1970/1/1 8:00:00
  		new Borrow().addBorrowRecord(addBorrow);
  		ReaderManager.getInstance().updateBorrowedBooks(searchReader, "increase");
  		BookManager.getInstance().updateBorrowedNumbers(searchBook, "increase");
  		borrowMesg = "借阅成功";
  	}else{
  		borrowMesg = "该读者已借此书，并且尚未归还！";
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


<script>
 
  function init(){
  	document.getElementById("searchReaderText").focus();
    if("<%=borrowMesg %>" != ""){
    	alert("<%=borrowMesg %>");
    }
  } 
  
  //查询读者
/*   function search(){
    var searchField = document.getElementById("searchText");
    //读者代码不能为空
    if (trim(searchField.value) == "") {
      alert("查询条件不能为空");
      searchField.focus();
      return;
    }else{
      with(document.getElementById("searchForm")) {
        method="get";
        action="readertab.jsp";
        submit();
      }
    }
  } */
  
  function searchReader(){
    var searchReaderField = document.getElementById("searchReaderText");
    //读者代码不能为空
    if (trim(searchReaderField.value) == "") {
      alert("读者编号不能为空");
      searchReaderField.focus();
      return;
    }
    
    with(document.getElementById("searchForm")) {
        method="get";
        action="borrowtab.jsp";
        submit();
	}
  }
  
  function searchBook(){
    var searchBookField = document.getElementById("searchBookText");
    //读者代码不能为空
    if (trim(searchBookField.value) == "") {
      alert("图书编号不能为空");
      searchBookField.focus();
      return;
    }
    with(document.getElementById("searchForm")) {
        method="get";
        action="borrowtab.jsp";
        submit();
	}
  }
  
  function startBorrow(){
  	var searchReaderField = document.getElementById("searchReaderText");
    //读者代码不能为空
    if (trim(searchReaderField.value) == "") {
      alert("请输入读者编号并确认！");
      searchReaderField.focus();
      return;
    }
    var searchBookField = document.getElementById("searchBookText");
    //读者代码不能为空
    if (trim(searchBookField.value) == "") {
      alert("请输入图书编号并确认！");
      searchBookField.focus();
      return;
    }
    
    var readerIdField = document.getElementById("readerIdSpan");
    if (readerIdField.outerText == "") {
      alert("没有读者记录不允许借书");
      return;
    }
    
    var bookIdField = document.getElementById("bookIdSpan");
    if (bookIdField.outerText == "") {
      alert("没有图书记录不允许借书");
      return;
    }
    
    var borrowedBooksField = document.getElementById("borrowedBooksSpan");
    if (parseInt(borrowedBooksField.outerText) == 8) {
      alert("借阅失败！！读者已借数量已达到最大值8本");
      return;
    }
    
    var totalNumbersField = document.getElementById("totalNumbersSpan");
    var borrowedNumbersField = document.getElementById("borrowedNumbersSpan");
    if (parseInt(borrowedNumbersField.outerText) == parseInt(totalNumbersField.outerText)) {
      alert("借阅失败！！已无可借图书");
      return;
    }
    
    with(document.getElementById("borrowForm")) {
        method="get";
        action="borrowtab.jsp";
        submit();
    }
  }

/*   function formSubmit(){
    document.getElementById("searchForm").submit()
  } */
  
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
<body onLoad="init()">
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[业务中心]-[借书]</td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center">
                      
                    </div></td>
                    <td class="STYLE1"><div align="center"></div></td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"></div></td>
                    <td class="STYLE1"><div align="center"></div></td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"></div></td>
                    <td class="STYLE1"><div align="center"></div></td>
                  </tr>
                </table></td>
                <td width="52"><table width="88%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"></div></td>
                    <td class="STYLE1"><div align="center"></div></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
    <form name="searchForm" id="searchForm">
    <input type="hidden" name="command" value="search"/>
      <tr>
        <td width="8" background="images/tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
          &nbsp;<span class="STYLE1">请输入读者编号（精确） ：<input type="text" name="searchReaderText" id="searchReaderText" value="<%=text1 %>"/>
          <input name="searchBtn" class="button1" type="button" id="searchBtn" value="确认" onClick="searchReader()"/>
          <span><font color="red"><%=mesg1 %></font></span>
          </span>
            
          <tr>
            <td width="14%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">读者编号</span></div></td>
            <td width="15%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">读者姓名</span></div></td>
            <td width="20%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">性别</span></div></td>
            <td width="20%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">邮箱地址</span></div></td>
            <td width="10%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">已借数量</span></div></td>
            <td width="20%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">注册日期</span></div></td>
          </tr>

          <tr>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="readerIdSpan" class="STYLE1"><%=readerId %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="readerNameSpan" class="STYLE1"><%=readerName %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="genderSpan" class="STYLE1"><%=gender %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="emailSpan" class="STYLE1"><%=email %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="borrowedBooksSpan" class="STYLE1"><%=borrowedBooks %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="registerDateSpan" class="STYLE1"><%=registerDate %></span></div></td>
          </tr>  
        </table>
        </td>
        <td width="8" background="images/tab_15.gif">&nbsp;</td>
      </tr>
      

      <tr>
        <td width="8" background="images/tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
            
            &nbsp;<span class="STYLE1">请输入图书编号（精确） ：<input type="text" name="searchBookText" id="searchBookText" value="<%=text2%>"/>
            <input name="searchBtn" class="button1" type="button" id="searchBtn" value="确认" onClick="searchBook()"></span>
            <span class="STYLE1"><font color="red">&nbsp;<%=mesg2 %></font></span>
          <tr>
            <td width="14%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">图书编号</span></div></td>
            <td width="15%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">图书书名</span></div></td>
            <td width="20%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">ISBN</span></div></td>
            <td width="20%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">库存数量</span></div></td>
            <td width="10%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">已被借数量</span></div></td>
            <td width="20%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">入库日期</span></div></td>
          </tr>

          <tr>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="bookIdSpan" class="STYLE1"><%=bookId %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="bookNameSpan" class="STYLE1"><%=bookName %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="ISBNSpan" class="STYLE1"><%=ISBN %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="totalNumbersSpan" class="STYLE1"><%=totalNumbers %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="borrowedNumbersSpan" class="STYLE1"><%=borrowedNumbers %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span id="storeDateSpan" class="STYLE1"><%=storeDate %></span></div></td>
          </tr>
        </table></td>
        <td width="8" background="images/tab_15.gif">&nbsp;</td>
      </tr>
    </form>  
    </table></td>
  </tr>
  <tr>
    <td height="35" background="images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td><table border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <form name="borrowForm" id="borrowForm">
                  <input type="hidden" name="command" value="borrow"/>
                  <input type="hidden" name="searchReaderText" value="<%=readerId %>"/>
                  <input type="hidden" name="searchBookText" value="<%=bookId %>"/>
                  <td width="40"><input type="button"  value="开始借阅" onClick="startBorrow()"/></td>
                  </form>
                  <td width="100"><div align="center"><span class="STYLE1"></span></div></td>
                  <td width="40"></td>
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
