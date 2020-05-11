<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar Telefono</title>
</head>
<body>
	<c:set var="telefono" value="${requestScope['telefono']}"/><!-- PILAS -->
	
	<h1>AQUI ESTA LA EDICION DEL PINCHE TELEFONO</h1>
	<%-- <h1>${telefono.operadora}</h1>
	<h1>${telefono.numero}</h1>
	<h1>${telefono.tipo}</h1>
	<h1>${telefono.id_tel}</h1> --%>

	<form action="ModificarTelefono" method="post"
		name="editarParamTelefonos">
		<input type="hidden" value="${telefono.id_tel}" name="id_tel" >
		<div class="input-group input-group-lg">		
			<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-envelope"></i></span>
			<label for="numero">Numero:</label> 
			<input type="text" class="form-control" name="numero" value="${telefono.numero}" placeholder="Numero" id="numero"
				aria-describedby="sizing-addon1" required>
		</div>

		<br>
		<c:choose>
		<c:when test="${telefono.operadora=='movistar'}">
		<div>
			<label for="operadora">Operadora: </label> <select id="operadora"
				name="operadora">
				<option value="cnt">CNT</option>
				<option value="claro">CLARO</option>
				<option value="tuenti">TUENTI</option>
				<option value="movistar" selected>MOVISTAR</option>
			</select>
		</div>
		</c:when>
		<c:when test="${telefono.operadora=='tuenti'}">
		<div>
			<label for="operadora">Operadora: </label> <select id="operadora"
				name="operadora">
				<option value="cnt">CNT</option>
				<option value="claro">CLARO</option>
				<option value="tuenti" selected>TUENTI</option>
				<option value="movistar">MOVISTAR</option>
			</select>
		</div>
		</c:when>
		<c:when test="${telefono.operadora=='claro'}">
		<div>
			<label for="operadora">Operadora: </label> <select id="operadora"
				name="operadora">
				<option value="cnt">CNT</option>
				<option value="claro" selected>CLARO</option>
				<option value="tuenti">TUENTI</option>
				<option value="movistar">MOVISTAR</option>
			</select>
		</div>
		</c:when>
		<c:when test="${telefono.operadora=='cnt'}">
		<div>
			<label for="operadora">Operadora: </label> <select id="operadora"
				name="operadora">
				<option value="cnt" selected>CNT</option>
				<option value="claro" >CLARO</option>
				<option value="tuenti">TUENTI</option>
				<option value="movistar">MOVISTAR</option>
			</select>
		</div>
		</c:when>
		</c:choose>
		<br>
		<c:choose>
			<c:when test="${telefono.tipo=='movil'}">
				<div>
					<label for="tipo">Tipo: </label> <select id="tipo" name="tipo">
						<option value="movil" selected>MOVIL</option>
						<option value="fijo">FIJO</option>
					</select>
				</div>
			</c:when>
			<c:when test="${telefono.tipo=='fijo'}">
				<div>
					<label for="tipo">Tipo: </label> <select id="tipo" name="tipo">
						<option value="movil">MOVIL</option>
						<option value="fijo" selected>FIJO</option>
					</select>
				</div>
			</c:when>


		</c:choose>

		<br> <br>
		<button class="btn btn-lg btn-primary btn-block btn-signin"
			id="actualizar" type="submit">Actualizar</button>

	</form>

	<!-- DESDE AQUI VA LAS TABLAS -->

	<%-- <div class="container" style="margin-top: 25px; padding: 10px">
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
						<td><a href="../BuscarTelefono?${telefonos.id_tel}" type="button">eliminar</a><a herf="" type="button">modificar</a></td>
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
        $(document).ready(function () {
            $('#tablax').DataTable({
                language: {
                    processing: "Tratamiento en curso...",
                    search: "Buscar&nbsp;:",
                    lengthMenu: "Agrupar de _MENU_ items",
                    info: "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
                    infoEmpty: "No existen datos.",
                    infoFiltered: "(filtrado de _MAX_ elementos en total)",
                    infoPostFix: "",
                    loadingRecords: "Cargando...",
                    zeroRecords: "No se encontraron datos con tu busqueda",
                    emptyTable: "No hay datos disponibles en la tabla.",
                    paginate: {
                        first: "Primero",
                        previous: "Anterior",
                        next: "Siguiente",
                        last: "Ultimo"
                    },
                    aria: {
                        sortAscending: ": active para ordenar la columna en orden ascendente",
                        sortDescending: ": active para ordenar la columna en orden descendente"
                    }
                },
                scrollY: 400,
                lengthMenu: [ [10, 25, -1], [10, 25, "All"] ],
            });
        });
    </script>
 --%>
</body>
</html>