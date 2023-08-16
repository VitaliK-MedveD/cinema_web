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
    <title>Update Role</title>
</head>
<body>
<table>
    <form action="/user/update/role" method="post">
        <thead>
        <tr>
            <th>Login</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${selectedUser.login}</td>
            <td>${selectedUser.role}</td>
            <td><select name="role">
                <option selected="selected" value="USER">User</option>
                <option value="MANAGER">Manager</option>
                <option value="ADMIN">Admin</option></select></td>
            <td><input type="submit" value="Update role"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
