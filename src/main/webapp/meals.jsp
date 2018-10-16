<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://localhost:8080/topjava/meals" prefix="f" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html"> < Home</a></h3>
<div style="text-align: center">
    <h1>Meals</h1>
    <h2><a href="meals?action=create">Add New Meal</a></h2>
</div>
<div align="center">
    <table border="1" cellpadding="5" bgcolor="#f0ffff">
    <jsp:useBean id="data" scope="request" type="java.util.List"/>
    <tr>
        <td>Description:</td>
        <td>DateTime:</td>
        <td>Calories:</td>
        <td>Edit/Delete</td>
    </tr>
    <c:forEach items="${data}" var="dat">

        <tr>
            <td><c:out value="${dat.description}"/></td>
            <td><c:out value="${f:formatLocalDateTime(dat.dateTime,'dd.MM.yyyy')}"/></td>
            <td>
                <c:if test="${dat.exceed}">
                <span style="color: red;"><c:out value="${dat.calories}"/></c:if></span>
                <c:if test="${!dat.exceed}">
                <span style="color: #24ff00;"><c:out value="${dat.calories}"/></c:if></span>
            </td>
            <td><a href="meals?action=edit&dat.id=${dat.id}">edit</a> /
            <a href="meals?action=delete&dat.id=${dat.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>



</div>
</body>
</html>