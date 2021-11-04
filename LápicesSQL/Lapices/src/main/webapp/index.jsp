<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="vista.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Lápices</title>
  </head>
  <body>
    <div class="container-fluid fondo">
      <div id="titulo" class="container-fluid"><h1>Gestionar lápices</h1></div>
			<div id="seccion" class="container-fluid"><h2>Lápices</h2></div>
      <br/>
      <div id="general" class="flex-container">
        <div id="agregar" class="flex-item">
        <h3>Agregar lapiz</h3>
        <form action="principal" method="POST">
			<label for="idAgregar">*id:</label>
          <input type="text" name="idAgregar"></input><br>
					<label for="marcaAgregar">*Marca:</label>
          <input type="text" name="marcaAgregar"></input><br>
					<label for="numeroAgregar">*Número:</label>
          <input type="text" name="numeroAgregar"></input><br>
          <br>
          <span><button name="button" class="btn btn-primary" type="submit" value="agregar">Agregar</button></span>
        </form>
      </div>

        <div id="borrar" class="flex-item">
          <h3>Borrar lápiz</h3>
          <form action="principal" method="POST">
			<label for="idBorrar">*Id:</label>
			<input type="text" name="idBorrar"></input><br>
            <br>
            <button name="button" class="btn btn-primary" type="submit" value="borrar">Borrar</button>
          </form>
        </div>

        <div id="editar" class="flex-item">
        <h3>Editar lápiz:</h3>
        <form action="principal" method="POST">
          <label for="idEditar">*Id:</label>
          <input type="text" name="idEditar"></input><br>
					<label for="marcaEditar">*Marca:</label>
          <input type="text" name="marcaEditar"></input><br>
					<label for="numeroEditar">*Número:</label>
          <input type="text" name="numeroEditar"></input><br>
          <br>
          <button name="button" class="btn btn-primary" type="submit" value="editar">Editar</button>
        </form>
      </div>

      <div id="mostrar" class="flex-item">
        <h3>Mostrar lápices</h3>
			<form action="principal" method="POST">
			  <label for="marca">Introduce el nombre de la marca de lapiz:</label><br>
			  <input type="text" id="marca" name="marca"><br>
			   <button name="button" class="btn btn-primary" type="submit" value="mostrar">Mostrar</button>
			</form>
      </div>
      <h6>Nota:los campos marcados con asterisco son obligatorios.</h6>
    </div>
    <textarea id="" name="mostrarTextArea" rows="17" cols="100" readonly><c:forEach items="${lapicesList}" var="lapiz">
			<c:out value="${lapiz.imprimirLapiz()}"/>
			<br />
		</c:forEach></textarea>
  </div>
  <div>
  </div>

  </body>
</html>
