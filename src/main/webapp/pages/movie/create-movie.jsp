<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 25.06.2023
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adding Movie</title>
</head>
<body>
<table>
    <form action="/movie/create" method="post">
        <tr><u><b>Adding Movie</b></u></tr>
        <tr>
            <td>Title</td>
            <td><input name="movieTitle" value="" type="text"></td>
            <td><p style="color: red"><b></b></p></td>
        </tr>
        <tr>
            <td>Show Date</td>
            <td><input name="showDate" value="" type="text"></td>
            <td><p style="color: red"><b></b></p></td>
        </tr>
        <tr>
            <td>Show Time</td>
            <td><input name="showTime" value="" type="text"></td>
            <td><p style="color: red"><b></b></p></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input name="price" value="" type="text"></td>
            <td><p style="color: red"><b></b></p></td>
        </tr>
        <tr>
            <td>Age Limit</td>
            <td><input name="ageLimit"  value="" type="text"></td>
            <td><p style="color: red"><b></b></p></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"></td>
        </tr>
    </form>
    <tr>
        <td><button onclick="location.href='/admin/menu'">Go back</button></td>
    </tr>
</table>
</body>
</html>