<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 25.06.2023
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Edit user Menu</title>
</head>
<body>
<form action="/admin/edit/user" method="post" >
  <p><u><b>Edit user Menu</b></u></p>
  <table>
    <tr>
      <td>${selectedUser.login}</td>
      <td>${selectedUser.access}</td>
    </tr>
  </table>
  <p><button name="action" value="editAccess" >Edit Access</button></p>
  <p><button name="action" value="delete" >Delete</button></p>
  <p><button name="action" value="back" >Go back</button></p>
</form>
</body>
</html>