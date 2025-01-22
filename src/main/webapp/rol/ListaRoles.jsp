<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unu.examenFinal.beans.rol"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado Roles</title>
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<script src="assets/js/bootstrap.min.js"></script>

</head>

<%
// URL base para formularios
String url = "http://localhost:8080/examenFinal/";
%>

<body>
	<script>
		function eliminar(id) {
			if (confirm("Desea eliminar el registro?") == true) {
				location.href = "rolController?op=eliminar&id=" + id;
			}
		}
	</script>

	<script>
		function modificar(id) {
			if (confirm("¿Desea modificar el registro?") == true) {
				location.href = "rolController?op=obtener&id=" + id;
			}
		}
	</script>

	<div class="container">

		<a type="button" class="btn btn-primary"
			href="<%=url%>rolController?op=nuevo">NUEVO rol</a>


		<form action="<%=url%>rolController" method="get">
			<input type="hidden" name="op" value="listar" />
			<button type="submit" class="btn btn-danger">listar</button>
		</form>

		<table class="table table-dark table-striped" border="1">
			<thead>

				<th>idrol</th>
				<th>nameRol</th>
				<th>Operaciones</th>


			</thead>

			<tbody>
				<%
				List<rol> listaRoles = (List<rol>) request.getAttribute("ListaRoles");

				if (listaRoles != null) {
					for (rol role : listaRoles) {
				%>

				<tr>
					<td><%=role.getIdrol()%></td>
					<td><%=role.getNamerol()%></td>
					<td><a href="javascript:modificar('<%=role.getIdrol()%>')"
						class="btn btn-warning ">Modificar</a> <a
						href="javascript:eliminar('<%=role.getIdrol()%>')"
						class="btn btn-danger">Eliminar</a></td>

					<%
					}
					}

					else {
					%>
				
				<tr>
					<td>No hay datos</td>
					<td>No hay datos</td>
					<td>No hay datos</td>

				</tr>
				<%
				}
				%>
			</tbody>


		</table>



	</div>


</body>
</html>