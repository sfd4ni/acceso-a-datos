<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html >
<html>
	<head>
	
		<meta charset="utf-8" />
		<meta name="author" content="juan carlos p.r."/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	
		<title>Consulta lotería</title>
		
	

	</head>
   	<body>
   		
   		<h1> Consulta de lotería</h1>
   		<p>Desde aquí puedes consultar si tu número ha sido premiado</p>
   		<p>Ahora mismo hay: ${listapremiados.size() } números premiados </p>
   		<p>Pulsa en el enlace para introducir tu número</p>
   		<a href="enviopost.jsp" >Consultar número</a>

   	</body>
</html>