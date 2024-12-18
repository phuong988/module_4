<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Settings</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Settings</h1>
    <form action="${pageContext.request.contextPath}/update-settings" method="post">
        <div class="mb-3">
            <label for="language" class="form-label">Languages:</label>
            <select id="language" name="language" class="form-select">
                <option value="English" ${mailConfig.language == 'English' ? 'selected' : ''}>English</option>
                <option value="Vietnamese" ${mailConfig.language == 'Vietnamese' ? 'selected' : ''}>Vietnamese</option>
                <option value="Japanese" ${mailConfig.language == 'Japanese' ? 'selected' : ''}>Japanese</option>
                <option value="Chinese" ${mailConfig.language == 'Chinese' ? 'selected' : ''}>Chinese</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="pageSize" class="form-label">Page Size:</label>
            <select id="pageSize" name="pageSize" class="form-select">
                <option value="5" ${mailConfig.pageSize == 5 ? 'selected' : ''}>5</option>
                <option value="10" ${mailConfig.pageSize == 10 ? 'selected' : ''}>10</option>
                <option value="15" ${mailConfig.pageSize == 15 ? 'selected' : ''}>15</option>
                <option value="25" ${mailConfig.pageSize == 25 ? 'selected' : ''}>25</option>
                <option value="50" ${mailConfig.pageSize == 50 ? 'selected' : ''}>50</option>
                <option value="100" ${mailConfig.pageSize == 100 ? 'selected' : ''}>100</option>
            </select>
        </div>

        <div class="mb-3 form-check">
            <input type="checkbox" id="spamFilter" name="spamFilter" class="form-check-input" ${mailConfig.spamFilter ? 'checked' : ''}/>
            <label class="form-check-label" for="spamFilter">Enable spam filter</label>
        </div>

        <div class="mb-3">
            <label for="signature" class="form-label">Signature:</label>
            <textarea id="signature" name="signature" rows="5" class="form-control">${mailConfig.signature}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
        <button type="reset" class="btn btn-secondary">Cancel</button>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
