<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 25.06.2023
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Movies</title>
</head>
<body>
<form action="/movie/select" method="post">
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
        <c:forEach var="movie" items="${movies}">
            <tr>
                <td><input type="radio" name="movieId" value="${movie.id}"/></td>
                <td>${movie.movieTitle}</td>
                <td>${movie.showDate}</td>
                <td>${movie.showTime}</td>
                <td>${movie.price}</td>
                <td>${movie.ageLimit}</td>
                <td>${movie.freeTickets}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table>
        <tbody>
        <tr>
            <td><input type="submit" name="continue" value="Continue" /></td>
        </tr>
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
        </tbody>
    </table>
</form>
</body>
</html>