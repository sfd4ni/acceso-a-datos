<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<
	<form method="post" action="login">
		<input type="text" name="user" id="user" placeholder="nick usuario" /><br>
		<input type="password" name="password" id="password" placeholder="contraseña" /><br>
		<input type="submit" name="submit" id="submit" />
	</form>
</body>
</html>