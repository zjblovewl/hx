<%@ page isELIgnored="false"%> <!-- 部署环境解析不了原因 https://blog.csdn.net/baidu_23177933/article/details/50812584 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${ctx}/static/css/test.css">
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    值:${ctx}
    文件：<input type="file" class="test" name="file"/> <br/>
    <input type="hidden" name="dictionaryName" value="photo" />
    <input type="submit" value="上传" />
</form>
</body>
</html>
