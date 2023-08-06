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
            <th>Movie Title</th>
            <th>Show Date and Time</th>
            <th>Price</th>
            <th>Age Limit</th>
            <th>Free Tickets</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${selectedMovie.movieTitle}</td>
                <td>${selectedMovie.showDateTime}</td>
                <td>${selectedMovie.price}</td>
                <td>${selectedMovie.ageLimit}</td>
                <td>${selectedMovie.countFreeTickets}</td>
            </tr>
        </tbody>
    </table>
    <table>
        <tbody>
        <tr>
            <td><button name="action" value="buyTicket" >Buy ticket</button></td>
        </tr>
        <c:choose>
            <c:when test="${user.role == 'MANAGER'}">
                <tr>
                    <td><button name="action" value="editMovie" >Edit movie</button></td>
                </tr>
            </c:when>
            <c:when test="${user.role == 'ADMIN'}">
                <tr>
                    <td><button name="action" value="editMovie" >Edit movie</button></td>
                </tr>
                <tr>
                    <td><button name="action" value="deleteMovie" >Delete movie</button></td>
                </tr>
            </c:when>
            <c:when test="${user.role == 'SUPER_ADMIN'}">
                <tr>
                    <td><button name="action" value="editMovie" >Edit movie</button></td>
                </tr>
                <tr>
                    <td><button name="action" value="deleteMovie" >Delete movie</button></td>
                </tr>
            </c:when>
        </c:choose>
        <tr>
            <td><input type="button" onclick="history.back();" value="Go back"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>