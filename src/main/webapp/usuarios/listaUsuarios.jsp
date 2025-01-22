<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unu.examenFinal.beans.usuario"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado Usuarios</title>
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
				location.href = "usuarioController?op=eliminar&id=" + id;
			}
		}
	</script>

	<script>
		function modificar(id) {
			if (confirm("¿Desea modificar el registro?") == true) {
				location.href = "usuarioController?op=obtener&id=" + id;
			}
		}
	</script>

	<div class="container">
		<a type="button" class="btn btn-primary"
			href="<%=url%>usuarioController?op=nuevo">NUEVO USER</a>
	
			<a type="button" class="btn btn-warning"
				href="<%=url%>rolController?op=listar">lista  rol</a>

			<div style="display: flex; align-items: center;">
				<form action="<%=url%>/usuarioController" method="get"
					style="margin-right: 5px;">
					BUSCAR <input type="text" name="query"
						placeholder="Buscar por username" /> <input type="hidden"
						name="op" value="buscar" />
					<button type="submit" class="btn btn-primary">Buscar</button>
				</form>

				<form action="<%=url%>usuarioController" method="get">
					<input type="hidden" name="op" value="listar" />
					<button type="submit" class="btn btn-danger">listar</button>
				</form>
			</div>
			<table class="table table-dark table-striped" border="1">
				<thead>

					<th>Codigo</th>
					<th>Username</th>
					<th>Password</th>
					<th>Rol</th>
					<th>Operaciones</th>


				</thead>

				<tbody>
					<%
					List<usuario> listaUsuarios = (List<usuario>) request.getAttribute("listaUsuarios");

					if (listaUsuarios != null) {
						for (usuario user : listaUsuarios) {
					%>

					<tr>
						<td><%=user.getId()%></td>
						<td><%=user.getUsername()%></td>
						<td><%=user.getPassword()%></td>
						<td><%=user.getNamerol()%></td>

						<td><a href="javascript:modificar('<%=user.getId()%>')"
							class="btn btn-warning ">Modificar</a> <a
							href="javascript:eliminar('<%=user.getId()%>')"
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