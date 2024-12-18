<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Update Result</title>
</head>
<body>

<h2>Updated Settings:</h2>
<ul>
  <li>Language: ${mailConfig.language}</li>
  <li>Page Size: ${mailConfig.pageSize}</li>
  <li>Spam Filter: ${mailConfig.spamFilter ? 'Enabled' : 'Disabled'}</li>
  <li>Signature: <pre>${mailConfig.signature}</pre></li>
</ul>

<a href="${pageContext.request.contextPath}/">Back to Settings</a>
</body>
</html>
