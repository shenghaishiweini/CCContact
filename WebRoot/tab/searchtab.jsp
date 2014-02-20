<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="java.text.*" %>
<%@ page import="bms.util.*" %>
<%@ page import="bms.sysmgr.domain.*" %>    
<%@ page import="bms.sysmgr.manager.*" %>

<%
  request.setCharacterEncoding("UTF-8");
  String pageNoString = request.getParameter("pageNo");
  String mesg = "";
  String command = request.getParameter("command");
  
  int pageNo = 1;
  if (pageNoString != null && !"".equals(pageNoString)) {
    pageNo = Integer.parseInt(pageNoString);
  }
  int pageSize = 10;
  PageModel<Borrow> pageModel = null;
  if("search".equals(command)){
    String searchType = request.getParameter("select");
    //解决了form表给jsp传递参数时的乱码问题
    String searchText = new String(request.getParameter("searchText").getBytes("ISO8859_1"),"UTF-8").trim();
    if("readerId".equals(searchType)){
      pageModel = new Borrow().searchBorrowById("readerId",searchText);
    }else if("bookId".equals(searchType)){
      pageModel = new Borrow().searchBorrowById("bookId",searchText);
    }
  }else{
    pageModel = new Borrow().findAllRecords(pageNo, pageSize);
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
    var mesg = "<%=mesg %>";
    if(mesg != ""){
      alert(mesg);
    }
  } 
  
  //查询读者
  function search(){
    var searchField = document.getElementById("searchText");
    //读者代码不能为空
    if (trim(searchField.value) == "") {
      alert("查询条件不能为空");
      searchField.focus();
      return;
    }else{
      with(document.getElementById("searchForm")) {
        method="get";
        action="searchtab.jsp";
        submit();
      }
    }
  }

  function formSubmit(){
    document.getElementById("myForm").submit()
  }
  
  function topPage() {
    window.location = "searchtab.jsp?pageNo=<%=pageModel.getTopPageNo()%>"
  }
  
  function previousPage() {
    window.location = "searchtab.jsp?pageNo=<%=pageModel.getPreviousPageNo()%>"
  } 
  
  function nextPage() {
    window.location = "searchtab.jsp?pageNo=<%=pageModel.getNextPageNo()%>"
  }
  
  function bottomPage() {
    window.location = "searchtab.jsp?pageNo=<%=pageModel.getBottomPageNo()%>";
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[业务中心]-[借还记录查询]</td>
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
      <tr>
        <td width="8" background="images/tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
            
            <form name="searchForm" id="searchForm">
            <input type="hidden" name="command" value="search">
            &nbsp;<span class="STYLE1">按
            <select name="select" id="select">
          <option value="readerId">读者编号</option>
          <option value="bookId">图书编号</option>
        </select>
        来查询 ：<input type="text" name="searchText" id="searchText"/>&nbsp;
            <input name="searchBtn" class="button1" type="button" id="searchBtn" value="查询" onClick="search()"></span>
            </form>
            
          <tr>
            
            <td width="8%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">读者编号</span></div></td>
            <td width="10%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">读者姓名</span></div></td>
            <td width="5%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">性别</span></div></td>
            <td width="8%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">图书编号</span></div></td>
            <td width="10%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">图书书名</span></div></td>
            <td width="10%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">ISBN</span></div></td>
            <td width="15%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">借阅日期</span></div></td>
            <td width="15%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">到期日期</span></div></td>
            <td width="5%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">是否归还</span></div></td>
            <td width="14%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">归还日期</span></div></td>
            <!-- <td width="15%" height="22" background="images/bg.gif" bgcolor="#FFFFFF" class="STYLE1"><div align="center">基本操作</div></td> -->
          </tr>
          <form name="readerform" id="readerform">
          <%
          List<Borrow> borrowList = pageModel.getList();
          for(Iterator<Borrow> iter=borrowList.iterator(); iter.hasNext();) {
            Borrow borrow = iter.next();
            Reader reader = ReaderManager.getInstance().findReaderById(borrow.getReaderId());
            Book book = BookManager.getInstance().findBookById(borrow.getBookId());
            String isReturn = "是";
            String returnDate = "";
            if(borrow.getReturnTime().getTime() == 0L){
            	isReturn = "否";
            	returnDate = "无";
            }else{
            	returnDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(borrow.getReturnTime());
            }        
          %>
          <tr>
            
            <%-- <td height="20" bgcolor="#FFFFFF"><div align="center">
              <input type="checkbox" name="selectFlag" value="<%=reader.getReaderId() %>" />
            </div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <div align="center"><%=reader.getReaderId() %></div>
            </div></td> --%>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=reader.getReaderId() %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=reader.getReaderName() %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=reader.getGender() %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=book.getBookId() %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=book.getBookName() %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=book.getISBN() %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(borrow.getBorrowTime())%> </span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(borrow.getExpiredTime())%></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=isReturn %></span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"><%=returnDate %></span></div></td>
            
          </tr>
          <%
            }
          %>
        </form>  
        </table></td>
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
            <td class="STYLE4">&nbsp;&nbsp;共有 <%=pageModel.getTotalRecords() %> 条记录，当前第 <%=pageModel.getPageNo() %>/<%=pageModel.getTotalPages() %> 页</td>
            <td><table border="0" align="right" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="40"><input type="button" onClick="topPage()" tabindex="5" style="background:url(images/first.gif); border-style:none; width:37px; height:15px; background-repeat:no-repeat;" /></td>
                  <td width="45"><input type="button" onClick="previousPage()" tabindex="5" style="background:url(images/back.gif); border-style:none; width:43px; height:15px; background-repeat:no-repeat;" /></td>
                  <td width="45"><input type="button" onClick="nextPage()" tabindex="5" style="background:url(images/next.gif); border-style:none; width:43px; height:15px; background-repeat:no-repeat;" /></td>
                  <td width="40"><input type="button" onClick="bottomPage()" tabindex="5" style="background:url(images/last.gif); border-style:none; width:37px; height:15px; background-repeat:no-repeat;" /></td>
                  <form id="myForm" action="searchtab.jsp" method="get">
                  <td width="100"><div align="center"><span class="STYLE1">转到第
                    <input name="pageNo" type="text" size="4" style="height:16px; width:20px; border:1px solid #999999;" /> 
              页 </span></div></td>
                  <td width="40"><input type="button" onClick="formSubmit()" tabindex="5" style="background:url(images/go.gif); border-style:none; width:37px; height:15px; background-repeat:no-repeat;" /></td>
                  </form>
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
