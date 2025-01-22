<%@page import="com.unu.examenFinal.beans.rol"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nuevoUsuario</title>
<%
// URL base para formularios
String url = "http://localhost:8080/examenFinal/";
%>

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<script src="assets/js/bootstrap.min.js"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">


<script>
	function validarFormulario() {
		const username = document.getElementById("username").value.trim();
		const password = document.getElementById("password").value.trim();

		if (username === "") {
			alert("Ingresa el user del usuario.");
			document.getElementById('username').focus();
			return false;
		}

		if (password === "") {
			alert("Ingresa el password del usuario.");
			document.getElementById('password').focus();
			return false;
		}

		return true;
	}

	function togglePasswordVisibility() {
		const passwordField = document.getElementById("password");
		const toggleButton = document.getElementById("togglePasswordBtn");

		// Cambiar tipo de input
		if (passwordField.type === "password") {
			passwordField.type = "text"; // Hacer visible la contraseña
			toggleButton.textContent = "Ocultar"; // Cambiar texto del botón a "Ocultar"
		} else {
			passwordField.type = "password"; // Ocultar la contraseña
			toggleButton.textContent = "Mostrar"; // Cambiar texto del botón a "Mostrar"
		}
	}
</script>


</head>


<body>
	<div class="container">
		<h3>NUEVO AUTOR</h3>
		<form class="row g-3" role="form" action="<%=url%>usuarioController"
			method="POST" onsubmit="return validarFormulario()">
			<!-- onsubmit="return validateForm();"> -->
			<input type="hidden" name="op" value="insertar"> <label
				for="username">Nombre del usuario:</label> <input type="text"
				class="form-control" name="username" id="username"
				placeholder="Ingresa el user del usuario"> <span
				class="input-group-addon"><span
				class="glyphicon 
glyphicon-asterisk"></span></span> <label for="password"
				class="form-label">Password del usuario:</label> <input
				type="password" class="form-control" id="password" name="password"
				placeholder="Ingrese el password del usuario">
			<div class="col-12 text-right">
				<button type="button" class="btn btn-secondary"
					id="togglePasswordBtn" onclick="togglePasswordVisibility()">Mostrar</button>
			</div>

			<div class="container mt-4">

				<!--Sirve para mostrar el combo cargado desde la base de datos-->
				<div class="mb-4 form-floating">
					<label for="idrol" class="form-label">Rol:</label> <select
						id="idrol" name="idrol" class="form-select" required>
						<option value="" disabled selected>Seleccionar</option>
						<%
						List<rol> listarol = (List<rol>) request.getAttribute("listarol");

						if (listarol != null && !listarol.isEmpty()) {
							for (rol role : listarol) {
						%>
						<option value="<%=role.getIdrol()%>">
							<%=role.getNamerol()%>
						</option>
						<%
						}
						} else {
						%>
						<option disabled>No hay roles disponibles</option>
						<%
						}
						%>
					</select>
				</div>




				<!--Sirve para mostrar un combo definido  -->
				<div class="mb-4 form-floating">
					<label for="estado" class="form-label">Estado:</label> <select
						id="estado" name="estado" class="form-select" required>
						<option value="" disabled selected>Seleccionar Estado</option>
						<option value="1">ACTIVO</option>
						<option value="0">INACTIVO</option>
					</select>
				</div>

			</div>


			<br> <br> <br>
			<div class="col-12 text-center">
				<button type="submit" class="btn btn-info">Guardar</button>
				<a class="btn btn-danger" href="<%=url%>usuarioController?op=listar">Cancelar</a>
			</div>

		</form>
	</div>




</body>
</html>