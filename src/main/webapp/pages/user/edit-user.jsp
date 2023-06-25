<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 16.06.2023
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<table>
    <form action="/user/edit" method="post">
        <tr>
            <td><u><b>Edit Profile</b></u></td>
            <td><b style="color: green">${updateProfileSuccessful}</b></td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><input name="firstName" value="${user.firstName}" type="text"></td>
            <td><p style="color: red"><b>${editUser.notValidFirstName}</b></p></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input name="email" value="${user.email}" type="text"></td>
            <td><p style="color: red"><b>${editUser.notValidEmail}</b></p></td>
        </tr>
        <tr>
            <td>Date Birthday</td>
            <td><input name="dateBirthday" value="${user.dateBirthday}" title="Date Format YYYY-MM-DD" type="text"></td>
            <td><p style="color: red"><b>${editUser.notValidDateBirthday}</b></p></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update Profile"></td>
        </tr>
    </form>
        <tr>
        <c:choose>
            <c:when test="${user.access == 'ADMIN'}">
                <td><button onclick="location.href='/admin/menu'">Go back</button></td>
            </c:when>
            <c:when test="${user.access == 'MANAGER'}">
                <td><button onclick="location.href='/manager/menu'">Go back</button></td>
            </c:when>
            <c:when test="${user.access == 'USER'}">
                <td><button onclick="location.href='/user/menu'">Go back</button></td>
            </c:when>
        </c:choose>
        </tr>
</table>
</body>
</html>
