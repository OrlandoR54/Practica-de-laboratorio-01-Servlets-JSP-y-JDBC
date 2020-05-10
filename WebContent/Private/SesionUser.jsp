<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- DATATABLES -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<!-- BOOTSTRAP -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
</head>
<body>

	<nav>
		<ul>
			<li>
				<a href="">Nuevo Telefono</a></li>
			<li>
				<a href="/Practica-de-laboratorio-01-Servlets-JSP-y-JDBC/CerrarSesion">Cerrar Sesion</a>
			</li>
		</ul>
	</nav>
	<c:set var="usuario" value="${requestScope['usuario']}" />
	<h1>Usuario: ${usuario.nombre} ${usuario.apellido}</h1>
	<h1>Registrar, modificar, eliminar, buscar y listar sus teléfonos
	</h1>

	<form action="RegistrarTelefonos" method="post"
		name="formularioTelefonos">

		<input type="hidden" value="${usuario.cedula}" id="cedula"
			name="num_ced">
		<div class="input-group input-group-lg">
			<label for="numero">Numero:</label> <span class="input-group-addon"
				id="sizing-addon1"><i class="glyphicon glyphicon-envelope"></i></span>
			<input type="text" class="form-control" name="numero"
				placeholder="Numero" id="numero" aria-describedby="sizing-addon1"
				required>
		</div>
		<br>



		<div id="selectorOperadora">
			<label for="operadora">Operadora: </label> 
			<select id="operadora" name="operadora">
				<option value="movistar">MOVISTAR</option>
				<option value="claro">CLARO</option>
				<option value="cnt">CNT</option>
				<option value="tuenti">TUENTI</option>
				
			</select>
		</div>
		<br>

		<div id="selectorTipo">
			<label for="tipo">Tipo: </label> <select id="tipo" name="tipo">
				<option value="movil">MOVIL</option>
				<option value="fijo">FIJO</option>
			</select>
		</div>
		<br> <br>
		<button class="btn btn-lg btn-primary btn-block btn-signin"
			id="Registrarce" type="submit">Registrar nuevo telefono</button>

	</form>


	<!--INICIO BUSCAR A OTROS USUARIOS EN BASE A SU NUMERO DE CEDULA O CORREO-->
	<h1>------------------------------------------------------</h1>
	<form action="BuscarOtherUsuarios" method="post"
		name="buscarUsuariosCedCorr">
		<h1>Ingresa un num de celuda o correo</h1>
		<div class="input-group input-group-lg">
			<label for="criterio">Buscar a otros Usuarios:</label> <span
				class="input-group-addon" id="sizing-addon1"><i
				class="glyphicon glyphicon-envelope"></i></span> <input type="text"
				class="form-control" name="criterio"
				placeholder="Ingresa un numero de celuda o correo" id="criterio"
				aria-describedby="sizing-addon1" required>
		</div>
		<br>
		<button class="btn btn-lg btn-primary btn-block btn-signin"
			id="Registrarce" type="submit">Buscar</button>

	</form>
	<h1>------------------------------------------------------</h1>
	<!-- FIN DE BUSCAR A USUARIOS CON SU NUMERO DE CEDULA O CORREO -->




	<!-- DESDE AQUI VA LAS TABLAS -->

	<div class="container" style="margin-top: 25px; padding: 10px">
		<table id="tablax" class="table table-striped table-bordered"
			style="width: 100%">
			<thead>
				<th>Numero</th>
				<th>tipo</th>
				<th>operadora</th>
				<th>opciones</th>

			</thead>
			<tbody>
				<c:forEach var="telefonos" items="${usuario.telefonos}">
					<tr>
						<td>${telefonos.numero}</td>
						<td>${telefonos.tipo}</td>
						<td>${telefonos.operadora}</td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<!-- JQUERY -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous">
		
	</script>
	<!-- DATATABLES -->
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js">
		
	</script>
	<!-- BOOTSTRAP -->
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js">
		
	</script>
	<script>
		$(document)
				.ready(
						function() {
							$('#tablax')
									.DataTable(
											{
												language : {
													processing : "Tratamiento en curso...",
													search : "Buscar&nbsp;:",
													lengthMenu : "Agrupar de _MENU_ items",
													info : "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
													infoEmpty : "No existen datos.",
													infoFiltered : "(filtrado de _MAX_ elementos en total)",
													infoPostFix : "",
													loadingRecords : "Cargando...",
													zeroRecords : "No se encontraron datos con tu busqueda",
													emptyTable : "No hay datos disponibles en la tabla.",
													paginate : {
														first : "Primero",
														previous : "Anterior",
														next : "Siguiente",
														last : "Ultimo"
													},
													aria : {
														sortAscending : ": active para ordenar la columna en orden ascendente",
														sortDescending : ": active para ordenar la columna en orden descendente"
													}
												},
												scrollY : 400,
												lengthMenu : [ [ 10, 25, -1 ],
														[ 10, 25, "All" ] ],
											});
						});
	</script>
</body>
</html>