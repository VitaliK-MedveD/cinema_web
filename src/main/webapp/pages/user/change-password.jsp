<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 11.08.2023
  Time: 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change password</title>
</head>
<body>
<table>
    <form action="/user/change_password" method="post">
        <tr>
            <td><u><b>Change password</b></u></td>
            <td><b style="color: green">${changePasswordSuccessful}</b></td>
            <td><p style="color: red"><b>${errorMessage}</b></p></td>
        </tr>
        <tr>
            <td>Current password</td>
            <td><input name="currentPassword" type="password"></td>
        </tr>
        <tr>
            <td>New password</td>
            <td><input name="newPassword" type="password"></td>
        </tr>
        <tr>
            <td>Repeat new password</td>
            <td><input name="repeatNewPassword" type="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Change password"></td>
        </tr>
    </form>
    <tr>
        <c:choose>
            <c:when test="${user.role == 'SUPER_ADMIN'}">
                <td><button onclick="location.href='/admin/menu'">Go main menu</button></td>
            </c:when>
            <c:when test="${user.role == 'ADMIN'}">
                <td><button onclick="location.href='/admin/menu'">Go main menu</button></td>
            </c:when>
            <c:when test="${user.role == 'MANAGER'}">
                <td><button onclick="location.href='/managers/menu'">Go main menu</button></td>
            </c:when>
            <c:when test="${user.role == 'USER'}">
                <td><button onclick="location.href='/user/menu'">Go main menu</button></td>
            </c:when>
        </c:choose>
    </tr>
</table>
</body>
</html>
