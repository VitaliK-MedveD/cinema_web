<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 31.07.2023
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User tickets</title>
</head>
<body>
<form action="/ticket/return_userTicket" method="post">
  <table>
    <thead>
    <tr>
      <th></th>
      <th>Movie title</th>
      <th>Show date and time</th>
      <th>Number of place</th>
      <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ticket" items="${userTickets}">
      <tr>
        <td><input type="radio" name="ticketId" value="${ticket.id}"/></td>
        <td>${ticket.movieTitle}</td>
        <td>${ticket.showDateTime}</td>
        <td>${ticket.numberOfPlace}</td>
        <td>${ticket.price}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <table>
    <tbody>
    <tr>
      <td><input type="submit" name="Return ticket" value="Return ticket" /></td>
    </tr>
    <tr>
      <td><input type="button" onclick="location.href='/admin/menu'" value="Go Back"/></td>
    </tr>
    </tbody>
  </table>
</form>
</body>
</html>
