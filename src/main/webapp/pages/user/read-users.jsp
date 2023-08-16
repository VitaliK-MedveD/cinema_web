<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 14.06.2023
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form action="/user/select" method="post">
<table>
    <thead>
    <tr>
        <th></th>
        <th>Id</th>
        <th>Role</th>
        <th>Login</th>
        <th>First Name</th>
        <th>Email</th>
        <th>Date Birthday</th>
        <th>Date Created</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><input type="radio" name="userId" value="${user.id}"/></td>
            <td>${user.id}</td>
            <td>${user.role}</td>
            <td>${user.login}</td>
            <td>${user.firstName}</td>
            <td>${user.email}</td>
            <td>${user.dateBirthday}</td>
            <td>${user.dateCreated}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<table>
    <tbody>
    <tr>
        <td><input type="submit" name="continue" value="Continue" /></td>
    </tr>
    </tbody>
</table>
</form>
<table>
    <tbody>
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
        </c:choose>
    </tr>
    </tbody>
</table>
</body>
</html>
