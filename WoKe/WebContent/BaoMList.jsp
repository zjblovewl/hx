<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table width="800" border="1" cellpadding="0" cellspacing="0">
  	<tr>
  		<td>编号</td><td>姓名</td><td>电话</td><td>地址</td><td>品牌</td>
  	</tr>
  	
  	<s:iterator id="baoming" value="bm">
 	<tr>
  		<td>${baoming.Id }</td>
  		<td>${baoming.Name }</td>
  		<td>${baoming.Tel }</td>
  		<td>${baoming.Address }</td>
  		<td>${baoming.Brand }</td>
  		<td><a href="#">编辑</a> | <a href="delete!delete?bm.Id=${baoming.Id }">删除</a></td>
  	</tr> 
  	</s:iterator>

    </table>
</body>
</html>