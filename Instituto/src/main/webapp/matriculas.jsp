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
			<div id="seccion" class="container-fluid"><h2>Matrículas</h2></div>
      <br/>
      <div id="general" class="flex-container">
        <div id="agregar" class="flex-item">
        <h3>Agregar matrícula</h3>
        <form>
          <label for="dni">*DNI alumno:</label>
          <input type="text" name="dniAgregar"></input><br>
          <label for="año">*Año:</label>
          <input type="text" name="añoAgregar"></input><br>
          <label for="asignaturas">*Asignaturas:</label>
          <input type="text" name="asignaturasAgregar"></input><br>
          <br>
          <span><button name="agregarButton" class="btn btn-primary" type="submit">Agregar</button></span>
        </form>
      </div>

        <div id="borrar" class="flex-item">
          <h3>Borrar matrícula</h3>
          <form>
            <label for="idMatricula">*ID Matrícula:</label>
            <input type="text" name="idMatriculaBorrar"></input><br>
            <br>
            <button name="borrarButton" class="btn btn-primary" type="submit">Borrar</button>
          </form>
        </div>

        <div id="editar" class="flex-item">
        <h3>Editar matrícula</h3>
        <form>
          <label for="idMatricula">*ID Matrícula:</label>
          <input type="text" name="idMatriculaEditar"></input><br>
          <label for="dni">*DNI alumno:</label>
          <input type="text" name="dniEditar"></input><br>
          <label for="año">*Año:</label>
          <input type="text" name="añoEditar"></input><br>
          <label for="asignaturas">*Asignaturas:</label>
          <input type="text" name="asignaturas"></input><br>
          <br>
          <button name="editarButton" class="btn btn-primary" type="submit">Editar</button>
        </form>
      </div>

      <div id="mostrar" class="flex-item">
        <h3>Mostrar matrícula</h3>
        <label>(Escribir en uno de los campos únicamente)</label>
        <form>
          <label for="dni">*DNI alumno:</label>
          <input type="text" name="dniMostrar"></input><br>
          <label for="año">*Año:</label>
          <input type="text" name="añoMostrar"></input><br>
          <br>
          <button name="mostrarButton" class="btn btn-primary" type="submit">Mostrar</button>
        </form>
      </div>
      <h6>Nota:los campos marcados con asterisco son obligatorios.</h6>
    </div>
    <textarea id="" name="mostrarTextArea" rows="17" cols="100" readonly>
      Aquí se mostrará la consulta.
      </textarea>
  </div>
  <div>
    <a href="#" class="btn btn-secondary btn-lg" role="button">Volver</a>
  </div>

  </body>
</html>