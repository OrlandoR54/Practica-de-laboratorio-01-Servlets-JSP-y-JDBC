<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sesion</title>


<!-- BOOTSTRAP -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">


<script src="https://kit.fontawesome.com/3f81fb8d3b.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="CSS/styleUser.css" type="text/css">

</head>
<body>
	<nav id="mySidenav" class="sidenav">
		<ul>
			<li>
				<a href="">Nuevo Telefono</a>
			</li>
		</ul>
		<ul>
			<li>
				<a href="/Practica-de-laboratorio-01-Servlets-JSP-y-JDBC/CerrarSesion"><i class="fas fa-sign-out-alt"></i>Cerrar Sesion</a>
			</li>
		</ul>
	</nav>
	<c:set var="usuario" value="${requestScope['usuario']}" />
	<h1>Bienvenid@ ${usuario.nombre} ${usuario.apellido}</h1>

	<section>
		<form action="AgregarTelefono" method="post" name="formularioTelefonos">
	
			<input type="hidden" value="${usuario.cedula}" id="cedula" name="num_ced"> 
			<label for="numero">Numero:</label>
			<div class="box">
				<div class="container-3">
					<span class="icon"><i class="fas fa-phone fa-lg"></i></span> 
					<input type="text" id="phone" name="numero" placeholder="0955572141" maxlength="10" required />
				</div>
			</div>
			
			<br>
	
			<div class="boxOpr" id="selectorOperadora">
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
					<option value="fijo">CONVENCIONAL</option>
				</select>
			</div>
			<br> <br>
			<button class="" id="Registrarce" type="submit">Registrar nuevo telefono</button>
	
		</form>
	</section>
	<!--BUSCA EL TELEFONO DEL USUARIO-->
	<h1>-----------------------------------------------------------------</h1>
	<form action="BuscarTelefono" method="post" name="buscarUsuariosCedCorr">
		<h2>Buscar mis números de telefono</h2>
		<label for="criterio">Buscar mi número de telefono:</label>
		<div class="box">
			<div class="container-3">
				<span class="icon"><i class="fas fa-tty"></i></span> 
				<input type="text" id="phone" name="numTelf" placeholder="Buscar numero..." maxlength="10" required />
			</div>
		</div>
	
		<br>
		<button type="submit">Buscar</button>

	</form>
	
	<table class="table ">
		<thead class="thead-dark">
			<tr>
				<th>Numero</th>
				<th>tipo</th>
				<th>operadora</th>
			</tr>
		</thead>
		<tbody>
		<c:set var="bTelefono" value="${requestScope['telefono']}" />
			<tr>
				<td><c:out value="${bTelefono.numero}" /></td>
				<td><c:out value="${bTelefono.tipo}" /></td>
				<td><c:out value="${bTelefono.operadora}" /></td>
			</tr>

		</tbody>
	</table>
	<h1>------------------------------------------------------</h1>
	<!---------------------------------------------------------------->

	<!-- TABLA DE TELEFONOS DEL USUARIO -->

	<div class="container" style="margin-top: 25px; padding: 10px">
		<form name="formulario_tabla" method="post">
			<table id="tablax" class="table " style="width: 100%">
				<thead class="thead-dark">
					<th>Numero</th>
					<th>tipo</th>
					<th>operadora</th>
					<th>opciones</th>
				</thead>
				<tbody>
					<c:forEach var="telefonos" items="${usuario.telefonos}">
						<tr>
							<td><input type="text" placeholder="${telefonos.numero}" value="" class="tel_dato" name="tel_numero" maxlength="10"></td>
							
							<td><input type="text" placeholder="${telefonos.tipo}" value="" class="tel_dato" name="tel_tipo" maxlength="10"></td>
							
							<td><input type="text" placeholder="${telefonos.operadora}" value="" class="tel_dato" name="tel_operadora" maxlength="10"></td>
							
							<td>
								<input type="hidden" value="${telefonos.id}" id="tel_id" name="tel_id">
								<input type="submit" onclick = "this.form.action = 'ModificarTelefono'" value="Modificar" />
								<input type="submit" onclick = "this.form.action = 'EliminarTelefono'" value="Eliminar" />
							</td>
						</tr>
						<!--  <tr>
						<td class="td_hide">${telefonos.numero}</td>
						<td class="td_hide">${telefonos.tipo}</td>
						<td class="td_hide">${telefonos.operadora}</td>
						<td class="td_hide">opcion</td>
						</tr>-->
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	
<!----------------------------------------------------------------->
<!-- TABLA DE TELEFONOS DE USUARIOS -->

<div class="container" style="margin-top: 25px; padding: 10px">
		<h2>Agenda Telefonica</h2>
		<form name="formulario_tabla_usuarios" action="BuscarUsuarios" method="get">
			<div class="box">
				<div class="container-3">
					<span class="icon"><i class="far fa-address-book"></i></span> 
					<input type="search" id="phone" name="busquedaUser" placeholder="Buscar usuario..."  required />
				</div>
			</div>
			<button type="submit">Buscar</button>
			<table id="tabla_Usuarios" class="table " style="width: 100%">
				<thead class="thead-dark">
					<tr>
						<th>Numero</th>
						<th>tipo</th>
						<th>operadora</th>
						<th>opciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="telefonos" items="${usuario.telefonos}">
						<tr>
							<td><input type="text" placeholder="${telefonos.numero}" value="" class="tel_dato" name="tel_numero" maxlength="10"></td>
							
							<td><input type="text" placeholder="${telefonos.tipo}" value="" class="tel_dato" name="tel_tipo" maxlength="10"></td>
							
							<td><input type="text" placeholder="${telefonos.operadora}" value="" class="tel_dato" name="tel_operadora" maxlength="10"></td>
							
							<td>
								<input type="hidden" value="${telefonos.id}" id="tel_id" name="tel_id">
								<!--  <input type="submit" onclick = "this.form.action = ''" value="Modificar" />
								<input type="submit" onclick = "this.form.action = ''" value="Eliminar" />-->
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>


<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>



	<!-- JQUERY -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous">
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
                    search: "",
                    lengthMenu: "",
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

<script>
        $(document).ready(function () {
            $('#tabla_Usuarios').DataTable({
                language: {
                    processing: "Tratamiento en curso...",
                    search: "",
                    lengthMenu: "",
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
	
</body>
</html>