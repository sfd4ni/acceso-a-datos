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

    <title>Instituto</title>
  </head>
  <body>
    <div class="container-fluid fondo">
      <div id="titulo" class="container-fluid"><h1>Gestionar instituto</h1></div>
			<div id="seccion" class="container-fluid"><h2>Asignaturas</h2></div>
      <br/>
      <div id="general" class="flex-container">
        <div id="agregar" class="flex-item">
        <h3>Agregar asignatura</h3>
        <form action="gestorasignaturas" method="post">
			<label for="nombreAgregar">*Nombre:</label>
          	<input type="text" name="nombreAgregar"></input><br>
			<label for="cursoAgregar">*Curso:</label>
          	<input type="text" name="cursoAgregar"></input><br>
          <br>
          <span><button name="button" class="btn btn-primary" type="submit" value="agregar">Agregar</button></span>
        </form>
      </div>

        <div id="borrar" class="flex-item">
          <h3>Borrar asignatura</h3>
          <form action="gestorasignaturas" method="post">
				<label for="idBorrar">*ID:</label>
				<input type="text" name="idBorrar"></input><br>
            <br>
            <button name="button" class="btn btn-primary" type="submit" value="borrar">Borrar</button>
          </form>
        </div>

        <div id="editar" class="flex-item">
        <h3>Editar asignatura</h3>
        <form action="gestorasignaturas" method="post">
          <label for="nombreEditar">*Nombre:</label>
          <input type="text" name="nombreEditar"></input><br>
		  <label for="cursoEditar">*Curso:</label>
          <input type="text" name="cursoEditar"></input><br>
          <label for="idEditar">*ID:</label>
          <input type="text" name="idEditar"></input><br>
          <br>
          <button name="button" class="btn btn-primary" type="submit" value="editar">Editar</button>
        </form>
      </div>

      <div id="mostrar" class="flex-item">
        <h3>Mostrar asignatura</h3>
        <label>(Escribir en uno de los campos ??nicamente)</label>
        <form action="gestorasignaturas" method="post">
					<label for="nombreMostrar">*Nombre:</label>
          <input type="text" name="nombreMostrar"></input><br>
          <label for="idMostrar">*ID:</label>
          <input type="text" name="idMostrar"></input><br>
          <br>
          <button name="button" class="btn btn-primary" type="submit" value="mostrar">Mostrar</button>
        </form>
      </div>
      <h6>Nota:los campos marcados con asterisco son obligatorios.</h6>
    </div>
    <textarea id="" name="mostrarTextArea" rows="17" cols="100" readonly>
      <c:forEach items="${listaAsignaturas}" var="asignatura">
			<c:out value="${asignatura.imprimir()}"/>
	</c:forEach>
      </textarea>
  </div>
  <div>
    <a href="principal" class="btn btn-secondary btn-lg" role="button">Volver</a>
  </div>

  </body>
</html>