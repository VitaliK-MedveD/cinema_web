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
        <th>Access</th>
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
            <td>${user.access}</td>
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
        <td><input type="submit" name="updateAccess" value="Change User Access" /></td>
        <td><input type="submit" name="delete" value="Delete User" /></td>
        <td><input type="button" onclick="location.href='/admin/menu'" value="Go Back"/></td>
    </tr>
    </tbody>
</table>
</form>
</body>
</html>
