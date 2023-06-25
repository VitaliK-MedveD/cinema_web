<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 16.06.2023
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Access</title>
</head>
<body>
<table>
    <form action="/user/update/access" method="post">
        <thead>
        <tr>
            <th>Login</th>
            <th>Access</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${userUpdateAccess.login}</td>
            <td>${userUpdateAccess.access}</td>
            <td><input name="id" type="hidden" value=${userUpdateAccess.id}></td>
            <td><select name="access">
                <option selected="selected" value="USER">User</option>
                <option value="MANAGER">Manager</option>
                <option value="ADMIN">Admin</option></select></td>
            <td><input type="submit" value="Update Access"></td>
        </tr>
    </form>
    </tbody>
</table>

</body>
</html>
