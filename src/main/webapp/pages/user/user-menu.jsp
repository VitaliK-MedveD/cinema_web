<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 20.06.2023
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Menu</title>
</head>
<body>
<form action="/user/menu" method="post" >
    <p><u><b>Здравствуйте ${user.firstName}</b></u></p>
    <p><button name="action" value="showListMovies" >Show list Movies</button></p>
    <p><button name="action" value="editProfile" >Edit profile</button></p>
    <p><button name="action" value="exit" >Exit</button></p>
</form>
</body>
</html>
