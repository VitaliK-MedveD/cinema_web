<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 24.06.2023
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Admin Menu</title>
</head>
<body>
<form action="/admin/menu" method="post" >
  <table>
    <tr>
      <td><u><b>Hello ${user.firstName}</b></u></td>
    </tr>
    <tr>
      <td><p style="color: red"><b>${errorMessage}</b></p></td>
      <td><p style="color: green"><b>${message}</b></p></td>
    </tr>
    <tr>
      <td><button name="action" value="showListUsers" >Show list users</button></td>
    </tr>
    <tr>
      <td><button name="action" value="editProfile" >Edit profile</button></td>
    </tr>
    <tr>
      <td><button name="action" value="showListMovies" >Show list Movies</button></td>
    </tr>
    <tr>
      <td><button name="action" value="addMovie" >Add Movie</button></td>
    </tr>
    <tr>
      <td><button name="action" value="exit" >Exit</button></td>
    </tr>
  </table>
</form>
</body>
</html>
