<%@ page import="enitities.PersonDTO" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Pavel-PC
  Date: 4/5/2018
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
    <style>
        <%@include file="/css/myStyle.css"%>
    </style>
</head>

<div class="wrapper">
    <div>
        <ul id="menu">
            <li><a href="/">Home</a></li>
            <li><a href="/ShowPersonsTable">Show Persons</a></li>
            <li><a href="/AddPersonController">Add Persons</a></li>
            <li><a href="/DeleteWithoutSchool">Delete persons without school</a></li>
            <li><a href="/CalculatingSchoolRatingController">Schools rating</a></li>

        </ul>
    </div>
    <div>
        <form action="/EditPersonController" method="post">
            <jsp:useBean id="Person" class="enitities.PersonDTO"></jsp:useBean>
            <% PersonDTO person = (PersonDTO) application.getAttribute("Person");%>
            <%=person.id%>
            <input type="hidden" name="id" id="id" value="<%=person.id.toString()%>">
            <table>
                <tr>
                    <td><label class = "add-Label" for="first-name">First name</label></td>
                    <td><input type="text" name="firstName" id="first-name" value="<%=person.firstName%>"></td>
                </tr>
                <tr>
                    <td><label class = "add-Label" for="middle-name" >Middle Name</label></td>
                    <td><input type="text" name="middleName" id="middle-name" value="<%=person.middleName%>"></td>
                </tr>
                <tr>
                    <td><label class = "add-Label" for="second-name">Second Name</label></td>
                    <td><input type="text" name="secondName" id="second-name" value="<%=person.secondName%>"></td>
                </tr>
                <tr>
                    <td><label class = "add-Label" for="school">School</label></td>
                    <td><input type="text" name="school" id="school" value="<%=(person.school!=null)?person.school:""%>"></td>
                </tr>
                <tr>
                    <td><label class = "add-Label" for="attend-date">Attend date</label></td>
                    <td><input type="text" name="attendDate" id="attend-date" value="<%=(person.attendDate!=null)?person.attendDate.get(Calendar.YEAR):""%>"></td>
                </tr>
                <tr>
                    <td><label class = "add-Label" for="end-date">End date</label></td>
                    <td><input type="text" name="endDate" id="end-date" value="<%=(person.endDate!=null)?person.endDate.get(Calendar.YEAR):""%>"></td>
                </tr>
            </table>

            <input type="submit" value="Edit">
        </form>
    </div>
</div>

</html>
