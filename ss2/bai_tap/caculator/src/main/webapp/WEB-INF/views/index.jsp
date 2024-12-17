<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Calculator</h1>
    <form action="/calculate" method="get" class="mt-4">
        <div class="mb-3">
            <input type="number" class="form-control" name="num1" required placeholder="Enter first number" value="${param.num1}" />
        </div>
        <div class="mb-3">
            <input type="number" class="form-control" name="num2" required placeholder="Enter second number" value="${param.num2}" />
        </div>
        <div class="mb-3">
            <button type="submit" name="operation" value="add" class="btn btn-outline-primary">Addition (+)</button>
            <button type="submit" name="operation" value="subtract" class="btn btn-outline-secondary">Subtraction (-)</button>
            <button type="submit" name="operation" value="multiply" class="btn btn-outline-success">Multiplication (X)</button>
            <button type="submit" name="operation" value="divide" class="btn btn-outline-danger">Division (/)</button>
        </div>
        <div class="mt-3">
            <h3>Result:
                <c:choose>
                    <c:when test="${not empty result}">
                        ${result}
                    </c:when>
                    <c:when test="${not empty error}">
                        <span class="text-danger">${error}</span>
                    </c:when>
                    <c:otherwise>
                        Result 
                    </c:otherwise>
                </c:choose>
            </h3>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>