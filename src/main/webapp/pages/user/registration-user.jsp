<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 12.06.2023
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration Menu</title>
</head>
<body>
<table>
    <form action="/user/create" method="post">
    <tr><u><b>Registration User</b></u></tr>
    <tr>
        <td>Login</td>
        <td><input name="login" value="${verifiedUser.login}" type="text"></td>
        <td><p style="color: red"><b>${verifiedUser.notValidLogin}</b></p></td>
    </tr>
    <tr>
        <td>Password</td>
        <td><input name="password" value="${verifiedUser.password}" type="password"></td>
        <td><p style="color: red"><b>${verifiedUser.notValidPassword}</b></p></td>
    </tr>
    <tr>
        <td>Email</td>
        <td><input name="email" value="${verifiedUser.email}" type="text"></td>
        <td><p style="color: red"><b>${verifiedUser.notValidEmail}</b></p></td>
    </tr>
    <tr>
        <td>First Name</td>
        <td><input name="firstName" value="${verifiedUser.firstName}" type="text"></td>
        <td><p style="color: red"><b>${verifiedUser.notValidFirstName}</b></p></td>
    </tr>
    <tr>
        <td>Date Birthday</td>
        <td><input name="dateBirthday" value="${verifiedUser.dateBirthday}" title="Date Format YYYY-MM-DD" type="text"></td>
        <td><p style="color: red"><b>${verifiedUser.notValidDateBirthday}</b></p></td>
    </tr>
    <tr>
        <td><input type="submit" value="Register"></td>
    </tr>
</form>
    <tr>
        <td><button onclick="location.href='/'">Exit</button></td>
    </tr>
</table>
</body>
</html>
