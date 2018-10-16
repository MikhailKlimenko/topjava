<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<div align="center">
<section>
    <jsp:useBean id="data" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <c:if test="${data.id!=0}">
    <form action="meals?action=update" method="post">
        </c:if>
        <c:if test="${data.id==0}">
        <form action="meals?action=create" method="post">
            </c:if>
            <table border="1" cellpadding="5"  bgcolor="#f0ffff" >
                <caption>
                    <h2>
                        <c:if test="${data!=null}">
                            Edit Meal
                        </c:if>
                        <c:if test="${data==null}">
                            Add New Meal
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${data != null}">
                    <input type="hidden" name="id" value="<c:out value='${data.id}' />"/>
                </c:if>
                <c:if test="${data == null}">
                    <input type="hidden" name="id" value="<c:out value='${data.id=0}' />"/>
                </c:if>
                <tr>
                    <th>Description:</th>
                    <td>
                        <input type="text" name="description" size="45" value="<c:out value='${data.description}'/>"/>

                    </td>
                </tr>
                <tr>
                    <th>Calories:</th>
                    <td>
                        <input type="number" name="calories" value="<c:out value='${data.calories}'/>"/>
                    </td>
                </tr>
                <tr>
                    <th>DateTime:</th>
                    <td>
                        <input type="datetime-local" name="dateTime" value="<c:out value='${data.dateTime}'/>"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
    </form>
</section>
</div>
</body>
</html>