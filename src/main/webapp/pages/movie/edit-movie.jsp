<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 28.07.2023
  Time: 1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <form action="/movie/edit" method="post">
        <tr>
            <td><u><b>Edit Movie</b></u></td>
            <td><b style="color: green">${updateMovieSuccessful}</b></td>
        </tr>
        <tr>
            <td>Show Date</td>
            <td><input name="showDateTime" value="${selectedMovie.showDateTime}" type="text"></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input name="price" value="${selectedMovie.price}" type="text"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update Movie"></td>
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
        </c:choose>
    </tr>
</table>
</body>
</html>
