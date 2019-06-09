<%@ page import="ru.job4j.usersmodel.User" %>
<%@ page import="ru.job4j.usersmodel.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
<%--        <meta charset=\"UTF-8\">--%>
        <title>Список</title>
        Список пользователей:
    </head>
    <body>
        <form action ='<%=request.getContextPath()%>/cre.jsp' method = 'get'>
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
            <% ValidateService validateService =  ValidateService.getInstance();
                int i = 0;
                for (User user : validateService.findAll()) { %>
            <tr>
                <td><%=i %></td>
                <td><%=user.getId() %></td>
                <td><%=user.getName() %></td>
                <td><%=user.getLogin() %></td>
                <td><%=user.getEmail() %></td>
                <td><%=user.getCreateDate() %></td>
                <td>
                    <form action='<%=request.getContextPath()%>/upd.jsp' method='get'>
                        <button name='id' type='hidden' value='<%=user.getId()%>'>Update
                        </button>
                    </form>
                </td>
                <td>
                    <form action='<%=request.getContextPath()%>/list' method='post'>
                        <button name='id' type='hidden' value='<%=user.getId()%>'>Удалить
                        </button>
                    </form>
                </td>
            </tr>
            <% i++;
                } %>
        </table>
    </body>
</html>
