<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update of user</title>
</head>
<body>
    <br/>
    <form action='${pageContext.servletContext.contextPath}/' method='get'>
        <input type='submit' value='Вернуться к списку пользователей.'>
    </form>
    <br/>
    <table>
        <tr>
            <td>Parameter's  user:<c:out value=" ${user.id}"/>
                <form action = '${pageContext.servletContext.contextPath}/update' method='post'>
                    Name : <input type = 'text' value='<c:out value="${user.name}"/>' name='name'/>
                    Login : <input type = 'text' value='<c:out value="${user.login}"/>' name='login'/>
                    Email : <input type = 'text' value='<c:out value="${user.email}"/>' name='email'/>
                    <button name='id' type='hidden' value='<c:out value="${user.id}"/>'>Продолжить
                    </button>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>



