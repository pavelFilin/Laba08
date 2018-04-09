<%--
  Created by IntelliJ IDEA.
  User: Pavel-PC
  Date: 4/5/2018
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/css/myStyle.css"%>
    </style>
</head>
<body>

<div>
    <ul id="menu">
        <li><a href="/">Home</a></li>
        <li><a href="/ShowPersonsTable">Show Persons</a></li>
        <li><a href="/AddPersonController">Add Persons</a></li>
        <li><a href="/DeleteWithoutSchool">Delete persons without school</a></li>
        <li><a href="/CalculatingSchoolRatingController">Schools rating</a></li>
    </ul>
</div>

<p><a href="\ShowPersonsTable">Show table SCHOOLS</a></p>
<p><a href="\AddPersonController">Add person</a></p>
<p>generated in jsp</p>
</body>

</html>
