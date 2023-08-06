<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 30.07.2023
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Tickets</title>
</head>
<body>
<form action="/ticket/return" method="post">
    <table>
        <thead>
        <tr>
            <th></th>
            <th>Movie title</th>
            <th>Show date and time</th>
            <th>Number of place</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ticket" items="${tickets}">
            <tr>
                <td><input type="radio" name="ticketId" value="${ticket.id}"/></td>
                <td>${ticket.movieTitle}</td>
                <td>${ticket.showDateTime}</td>
                <td>${ticket.numberOfPlace}</td>
                <td>${ticket.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table>
        <tbody>
        <tr>
            <td><input type="submit" name="Return ticket" value="Return ticket" /></td>
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
                    <td><button onclick="location.href='/manager/menu'">Go main menu</button></td>
                </c:when>
                <c:when test="${user.role == 'USER'}">
                    <td><button onclick="location.href='/user/menu'">Go main menu</button></td>
                </c:when>
            </c:choose>
        </tr>
    </tbody>
</table>
</body>
</html>