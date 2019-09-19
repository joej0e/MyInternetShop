<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users list</title>
    <style type="text/css">
        table {
            border-spacing: 1em .5em;
            padding: 0 2em 1em 0;
            border: 1px solid orange;
        }

        td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<h3>Items</h3>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<a href="shop">Shop&#8594;</a>
</body>
</html>