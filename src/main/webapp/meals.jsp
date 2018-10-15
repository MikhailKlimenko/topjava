<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://localhost:8080/topjava/meals" prefix="f" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <jsp:useBean id="data" scope="request" type="java.util.List"/>
    <tr>
        <td>ID:</td>
        <td>Description:</td>
        <td>DateTime:</td>
        <td>Calories:</td>
    </tr>
    <c:forEach items="${data}" var="dat">

        <tr>
            <td><c:out value="${dat.id}"/></td>
            <td><c:out value="${dat.description}"/></td>
            <td><c:out value="${f:formatLocalDateTime(dat.dateTime,'dd.MM.yyyy')}"/></td>
            <td>
                <c:if test="${dat.exceed}">
                <span style="color: red;"><c:out value="${dat.calories}"/></c:if></span>
                <c:if test="${!dat.exceed}">
                <span style="color: #24ff00;"><c:out value="${dat.calories}"/></c:if></span>
            </td>
        </tr>
    </c:forEach>
    <td><a href="meals?action=update">Update</a></td>

</table>
</body>
</html>