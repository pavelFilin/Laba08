<%@ page import="java.util.Map" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.io.IOException" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavel-PC
  Date: 4/9/2018
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schoolsr ating</title>
    <style>
        <%@include file="/css/myStyle.css"%>
    </style>
</head>
<body>
<div>
    <ul id="menu">
        <li><a href="/">Home</a></li>
        <li><a href="/ShowPersonsTable">Show persons</a></li>
        <li><a href="/AddPersonController">Add persons</a></li>
        <li><a href="/DeleteWithoutSchool">Delete persons without school</a></li>
        <li><a href="/CalculatingSchoolRatingController">Schools rating</a></li>
    </ul>
</div>
<div class=wrapper>
    <% Map<String, Integer> schools = (Map<String, Integer>) (application.getAttribute("Schools"));%>
    <table class="table_dark table-rating">
        <tr>
            <th>School</th>
            <th>Rating</th>
        </tr>
        <%for (Map.Entry<String, Integer> entry : schools.entrySet()) {%>
        <tr>
            <td><%=entry.getKey()%></td>
            <td><%=entry.getValue()%></td>
        </tr>
        <% }%>
    </table>
</div>
</body>
</html>
