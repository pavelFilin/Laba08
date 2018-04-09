<%--
  Created by IntelliJ IDEA.
  User: Pavel-PC
  Date: 4/1/2018
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>

<%@page import="java.util.List" %>
<%@ page import="enitities.PersonDTO" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display person</title>
    <style>
        <%@include file="/css/myStyle.css"%>
    </style>
</head>
<body>
<div class="wrapper">
    <div>
        <ul id="menu">
            <li><a href="/">Home</a></li>
            <li><a href="/ShowPersonsTable">Show persons</a></li>
            <li><a href="/AddPersonController">Add persons</a></li>
            <li><a href="/DeleteWithoutSchool">Delete persons without school</a></li>
            <li><a href="/CalculatingSchoolRatingController">Schools rating</a></li>
        </ul>
    </div>
    <div>
        <form action="/ShowPersonsTable" method="post" class = search-persons>
            <input type="text" name = "school">
            <input type="number" name ="year">
            <input type="submit" value="search">
        </form>
    </div>
    <h1>Display students</h1>
    <% List<PersonDTO> personDTOS = (List<PersonDTO>) (application.getAttribute("Persons"));%>
    <div>
        <div class="wrapper-table">
            <table class="person-table table_dark">
                <tr>
                    <th>First Name</th>
                    <th>Middle Name</th>
                    <th>Second Name</th>
                    <th>School</th>
                    <th>Attend year</th>
                    <th>End year</th>
                    <th></th>
                </tr>
                <% for (int i = 0; i < personDTOS.size(); i++) { %>
                <tr>
                    <td><%=personDTOS.get(i).firstName%></td>
                    <td><%=personDTOS.get(i).middleName%></td>
                    <td><%=personDTOS.get(i).secondName%></td>
                    <%if (personDTOS.get(i).school!=null && personDTOS.get(i).school!="") {%>
                    <td><%=personDTOS.get(i).school%></td>
                    <td><%=personDTOS.get(i).getAttendDate().get(Calendar.YEAR)%></td>
                    <td><%=personDTOS.get(i).getEndDate().get(Calendar.YEAR)%></td>
                    <%}else {%>
                    <td></td>
                    <td></td>
                    <td></td>
                    <%}%>
                    <td><a href="/EditPersonController?id=<%=personDTOS.get(i).id%>" class = "edit">edit</a><br><a href="/DeleteController?id=<%=personDTOS.get(i).id%>" class = "delete">delete</a> </td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</div>
</body>
</html>
