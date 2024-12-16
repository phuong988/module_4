<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <style>
        body {
            background-color: #e9ecef; /* Màu nền nhẹ nhàng */
        }
        .card {
            border-radius: 1rem; /* Bo góc cho card */
        }
        .card-header {
            background-color: #28a745; /* Màu nền header xanh lá cây */
            color: white; /* Màu chữ trắng */
        }
        .card-body {
            font-size: 1.5rem; /* Kích thước chữ lớn hơn */
            padding: 2rem; /* Tăng khoảng cách trong card */
        }
        .btn-primary {
            background-color: #007bff; /* Màu nút */
            border: none; /* Bỏ viền */
            padding: 10px 20px; /* Tăng kích thước nút */
            font-size: 1.25rem; /* Kích thước chữ trong nút */
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow">
                <div class="card-header text-center">
                    <h1 class="h3">Conversion Result</h1>
                </div>
                <div class="card-body text-center">
                    <p class="text-success fs-4">The amount converted to VND: <strong>${vnd}</strong></p>
                </div>
                <div class="card-footer text-center">
                    <a href="/" class="btn btn-primary">Back to Home</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>