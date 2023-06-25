<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 25.06.2023
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Movie action</title>
</head>
<body>
<form action="/movie/action" method="post">
    <table>
        <thead>
        <tr>
            <th></th>
            <th>Movie Title</th>
            <th>Show Date</th>
            <th>Show Time</th>
            <th>Price</th>
            <th>Age Limit</th>
            <th>Free Tickets</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${selectedMovie.movieTitle}</td>
                <td>${selectedMovie.showDate}</td>
                <td>${selectedMovie.showTime}</td>
                <td>${selectedMovie.price}</td>
                <td>${selectedMovie.ageLimit}</td>
                <td>${selectedMovie.freeTickets}</td>
            </tr>
        </tbody>
    </table>
    <table>
        <tbody>
        <tr>
            <td><button name="action" value="buyTicket" >Buy Ticket</button></td>
        </tr>
        <c:choose>
            <c:when test="${user.access == 'MANAGER'} || ${user.access == 'ADMIN'}">
                <tr>
                    <td><button name="action" value="editMovie" >Edit Movie</button></td>
                </tr>
            </c:when>
            <c:when test="${user.access == 'ADMIN'}">
                <tr>
                    <td><button name="action" value="deleteMovie" >Delete Movie</button></td>
                </tr>
            </c:when>
        </c:choose>
        <tr>
            <c:choose>
                <c:when test="${user.access == 'USER'}">
                    <td><button onclick="location.href='/user/menu'">Go back</button></td>
                </c:when>
                <c:when test="${user.access == 'MANAGER'}">
                    <td><button onclick="location.href='/manager/menu'">Go back</button></td>
                </c:when>
                <c:when test="${user.access == 'ADMIN'}">
                    <td><button onclick="location.href='/admin/menu'">Go back</button></td>
                </c:when>
            </c:choose>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>