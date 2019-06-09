<%--
  Created by IntelliJ IDEA.
  User: alexanderlobachev
  Date: 2019-06-09
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    Добавление нового пользователя:
    <title>Добавление</title>
</head>
    <body>
        <form action ='<%=request.getContextPath()%>/create' method ='post'>
            <br/>
            <table>
                <tr><td>Name : <input type='text' name = 'name'/></td></tr>
                <tr><td>Login : <input type='text' name = 'login'/></td></tr>
                <tr><td>Email : <input type='text' name = 'email'/></td></tr>
            </table>
            <input type='submit'>
        </form>
    </body>
</html>