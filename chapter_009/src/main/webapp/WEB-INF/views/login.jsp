<%--
  Created by IntelliJ IDEA.
  User: alexanderlobachev
  Date: 2019-06-12
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sign in</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red"><c:out value="${error}"/></div>
</c:if>
<table>
    <tr>
        <td>Войдите в систему:
            <form action = '${pageContext.servletContext.contextPath}/signin' method='post'>
                Email : <input type='text' name = 'email'/>
                Password : <input type = 'password' name='password'/>
                <input type="submit">
            </form>
        </td>
    </tr>
</table>
</body>
</html>