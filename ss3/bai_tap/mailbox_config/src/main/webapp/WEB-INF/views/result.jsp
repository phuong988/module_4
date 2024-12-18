<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Result</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <div class="card">
    <div class="card-header bg-primary text-white">
      <h2>Updated Settings</h2>
    </div>
    <div class="card-body">
      <ul class="list-group">
        <li class="list-group-item">
          <strong>Language:</strong> ${mailConfig.language}
        </li>
        <li class="list-group-item">
          <strong>Page Size:</strong> ${mailConfig.pageSize}
        </li>
        <li class="list-group-item">
          <strong>Spam Filter:</strong> ${mailConfig.spamFilter ? 'Enabled' : 'Disabled'}
        </li>
        <li class="list-group-item">
          <strong>Signature:</strong>
          <pre class="bg-light p-2">${mailConfig.signature}</pre>
        </li>
      </ul>
    </div>
    <div class="card-footer text-end">
      <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Back to Settings</a>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
