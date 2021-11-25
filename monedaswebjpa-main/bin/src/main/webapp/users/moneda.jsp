<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moneda</title>
</head>
<body>
<form action="gestormonedas" method="POST">
	<input style="display:none;" type="text" name="idmoneda" value="${moneda.getIdmoneda()}"/>
	<input type="text" name="nombremoneda" value="${moneda.getNombre()}"/>
	<input type="text" name="paismoneda" value="${moneda.getPais()}"/>
	<input type="submit" name="borrar" value="Borrar"/>
	<input type="submit" name="modificar" value="Modificar"/>
</form>
	
</body>
</html>