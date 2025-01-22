<%@page import="com.unu.examenFinal.beans.usuario"%>
<%@page import="com.unu.examenFinal.beans.rol"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editarUser</title>
<%
// URL base para formularios
String url = "http://localhost:8080/examenFinal/";
%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>


</head>
<body>
	<%
	usuario user;
	if (request.getAttribute("usuario") == null)
		user = new usuario();
	else {
		user = (usuario) request.getAttribute("usuario");
	}
	%>


	<div class="container">
		<form id="form" role="form" action="<%=url%>usuarioController"
			method="POST">
			<input type="hidden" name="op" value="modificar" /> <input
				type="hidden" name="iduser" value="<%=user.getId()%>" />
			<h1>EDITOR DE USER</h1>
			id: <input type="text" name="iduser" value="<%=user.getId()%>">
			<br> username: <input type="text" name="username"
				value="<%=user.getUsername()%>"> <br> password: <input
				type="text" name="password" value="<%=user.getPassword()%>">
			<br> <label for="idrol" class="form-label">Rol:</label> <select
				id="idrol" name="idrol" class="form-select">
				<option value="" disabled>Seleccionar</option>
				<%
				List<rol> listarol = (List<rol>) request.getAttribute("listarol");
				int idRolSeleccionado = user.getIdrol(); // ID del rol del usuario actual

				if (listarol != null && !listarol.isEmpty()) {
					for (rol role : listarol) {
				%>
				<option value="<%=role.getIdrol()%>"
					<%=(role.getIdrol() == idRolSeleccionado) ? "selected" : ""%>>
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
			</select> <br> <br> <br> <input type="submit"
				class="btn btn-info" value="Guardar" name="Guardar"> <a
				class="btn btn-danger" href="<%=url%>usuarioController?op=listar">Cancelar</a>


		</form>
	</div>



</body>
</html>