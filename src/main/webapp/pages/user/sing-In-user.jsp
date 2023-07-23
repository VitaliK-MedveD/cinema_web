<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 13.06.2023
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sing In Menu</title>
</head>
<body>
<table>
    <form action="/user/singIn" method="post">
        <tr><u><b>Sing In</b></u></tr>
        <tr><p style="color: red"><b>${errorMessage}</b></p></tr>
        <tr>
            <td>Login</td>
            <td><input name="login" value="${enteredLogin}" type="text"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input name="password" type="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Sing In"></td>
        </tr>
    </form>
    <tr>
        <td><button onclick="location.href='/'">Exit</button></td>
    </tr>
</table>
</body>
</html>
