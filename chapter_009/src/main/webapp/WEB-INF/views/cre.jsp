<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    Добавление нового пользователя:
    <title>Добавление</title>
</head>
    <body>
        <form action ='${pageContext.servletContext.contextPath}/create' method ='post'>
            <br/>
            <table>
                <tr><td>Name : <input type='text' name = 'name'/></td></tr>
                <tr><td>Login : <input type='text' name = 'login'/></td></tr>
                <tr><td>Email : <input type='text' name = 'email'/></td></tr>
                <tr><td>Password : <input type='text' name = 'password'/></td></tr>
                <tr><td>Admin : <select name="admin">
                                    <option value="yes">yes</option>
                                    <option value="no">no</option>
                                </select> </td></tr>
            </table>
            <input type='submit'>
        </form>
    </body>
</html>