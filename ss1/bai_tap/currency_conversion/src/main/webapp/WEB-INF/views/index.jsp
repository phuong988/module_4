<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12/15/2024
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Currency Converter: USD to VND</h1>
<form action="convert" method="post">
    <label>Amount in USD:</label>
    <input type="number" step="0.01" name="usd" required/><br/>

    <input type="submit" value="Convert"/>
</form>
</body>
</html>
