<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Email Settings</title>
</head>
<body>
<h1>Settings</h1>

<form action="${pageContext.request.contextPath}/update-settings" method="post">
    <label>Languages:</label>
    <select name="language">
        <option value="English" ${mailConfig.language == 'English' ? 'selected' : ''}>English</option>
        <option value="Vietnamese" ${mailConfig.language == 'Vietnamese' ? 'selected' : ''}>Vietnamese</option>
        <option value="Japanese" ${mailConfig.language == 'Japanese' ? 'selected' : ''}>Japanese</option>
        <option value="Chinese" ${mailConfig.language == 'Chinese' ? 'selected' : ''}>Chinese</option>
    </select>
    <br/><br/>

    <label>Page Size:</label>
    <select name="pageSize">
        <option value="5" ${mailConfig.pageSize == 5 ? 'selected' : ''}>5</option>
        <option value="10" ${mailConfig.pageSize == 10 ? 'selected' : ''}>10</option>
        <option value="15" ${mailConfig.pageSize == 15 ? 'selected' : ''}>15</option>
        <option value="25" ${mailConfig.pageSize == 25 ? 'selected' : ''}>25</option>
        <option value="50" ${mailConfig.pageSize == 50 ? 'selected' : ''}>50</option>
        <option value="100" ${mailConfig.pageSize == 100 ? 'selected' : ''}>100</option>
    </select>
    <br/><br/>

    <label>Spams Filter:</label>
    <input type="checkbox" name="spamFilter" ${mailConfig.spamFilter ? 'checked' : ''}/>
    Enable spams filter
    <br/><br/>

    <label>Signature:</label>
    <br/>
    <textarea name="signature" rows="5" cols="30">${mailConfig.signature}</textarea>
    <br/><br/>

    <button type="submit">Update</button>
    <button type="reset">Cancel</button>
</form>
</body>
</html>
