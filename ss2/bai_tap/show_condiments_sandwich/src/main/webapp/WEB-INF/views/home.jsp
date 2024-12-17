<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sandwich Condiments</title>
    <!-- Thêm liên kết đến Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin-left: 0;
            margin-right: 0;
        }
        .container {
            width: auto; /* Đảm bảo rằng container không có chiều rộng tự động */
            padding-left: 0;
            padding-right: 0;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-left">Sandwich Condiments</h1>
    <form action="/save" method="post" class="mt-4">
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="condiments" value="Lettuce" id="lettuce">
            <label class="form-check-label" for="lettuce">Lettuce</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="condiments" value="Tomato" id="tomato">
            <label class="form-check-label" for="tomato">Tomato</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="condiments" value="Mustard" id="mustard">
            <label class="form-check-label" for="mustard">Mustard</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="condiments" value="Sprouts" id="sprouts">
            <label class="form-check-label" for="sprouts">Sprouts</label>
        </div>
        <div class="mt-4">
            <button type="submit" class="btn btn-primary" id="save" name="userAction">Save</button>
        </div>
    </form>
</div>

<!-- Thêm link đến JavaScript của Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
