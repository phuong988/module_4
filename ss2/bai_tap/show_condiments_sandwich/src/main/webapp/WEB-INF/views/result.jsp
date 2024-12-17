<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Result</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Selected Condiments</h1>
    <c:if test="${not empty condiments}">
        <ul class="list-group mt-3">
            <c:forEach var="condiment" items="${condiments}">
                <li class="list-group-item">${condiment}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${not empty message}">
        <div class="alert alert-danger mt-3">
            <h3>${message}</h3>
        </div>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
