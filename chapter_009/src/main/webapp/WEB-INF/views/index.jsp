<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Список</title>
        Список пользователей:
    </head>
    <body>
        <form action ='${pageContext.servletContext.contextPath}/create' method = 'get'>
        <input type= 'submit' value='Добавить'>
        </form>
        <br/>
        <table style="border: 1px solid darkgrey;" cellpadding="1" cellspacing="1" border="1">
            <tr>
                <th>#</th>
                <th>id</th>
                <th>name</th>
                <th>login</th>
                <th>email</th>
                <th>Create date</th>
                <th>Изменить</th>
                <th>Удалить</th>
            </tr>

            <c:forEach var="user" items="${users}" varStatus="i">
            <tr>
                <td>${i.count}</td>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td>
                    <form action='${pageContext.servletContext.contextPath}/update' method='get'>
                        <button name='id' type='hidden' value='<c:out value="${user.id}"></c:out>'>Изменить
                        </button>
                    </form>
                </td>
                <td>
                    <form action='${pageContext.servletContext.contextPath}/' method='post'>
                        <button name='id' type='hidden' value='<c:out value="${user.id}"></c:out>'>Удалить
                        </button>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
