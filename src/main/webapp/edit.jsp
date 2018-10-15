<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<section>
    <jsp:useBean id="data" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <form method="post" action="meals?action=submit">
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" name="description" value="${data.description}" placeholder="${data.description}"/></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" name="calories" value="${data.calories}" placeholder="${data.calories}"/></dd>
        </dl>
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>