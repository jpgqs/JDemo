<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <header class="navbar navbar-static-top" id="top" role="banner">

</header>
<head>
 <base href="${base_path}/" />
 </head>
<body>
    <form action="/jDemo/user/update" method="post">
    <input type="hidden" name="user.id" value="${user.id}">
    <label>姓名</label>
    <input type="text" name="user.name" value="${user.name}">${nameMsg}
    <label>年龄</label>
    <input type="text" name="user.age" value="${user.age}">
    <div >
    <label>性别</label>
    <div>
    <input type="radio"  name="user.sex" <c:if test="${user.sex=='男'}">checked="checked"</c:if>value="男"> 男
    </div>
    <div>
    <input type="radio"   name="user.sex" <c:if test="${user.sex=='女'}">checked="checked"</c:if>value="女"> 女
    </div>
    </div>
    <label>备注</label>
    <textarea rows="10" cols="20" name="user.remark">${user.remark}</textarea>
    <button type="submit">提交</button>
    </form>
</body>
