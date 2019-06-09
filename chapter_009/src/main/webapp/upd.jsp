<%@ page import="ru.job4j.usersmodel.User" %>
<%@ page import="ru.job4j.usersmodel.logic.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: alexanderlobachev
  Date: 2019-06-10
  Time: 00:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update of user</title>
</head>
<body>
    <br/>
    <form action='<%=request.getContextPath()%>/index.jsp' method='get'>
        <input type='submit' value='Вернуться к списку пользователей.'>
    </form>
    <br/>
    <% User user = ValidateService.getInstance().findById(request.getParameter("id")); %>
    <table>
        <tr>
            <td>Parameter's  user:<%=user.getLogin()%>
                <form action = '<%=request.getContextPath()%>/update' method='post'>
                    Name : <input type = 'text' value='<%=user.getName()%>' name='name'/>
                    Login : <input type = 'text' value='<%=user.getLogin()%>' name='login'/>
                    Email : <input type = 'text' value='<%=user.getEmail()%>' name='email'/>
                    <button name='id' type='hidden' value='<%=user.getId()%>'>Продолжить
                    </button>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>



