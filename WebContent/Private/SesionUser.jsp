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

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="public/css/styles.css">        

<script src="https://kit.fontawesome.com/3f81fb8d3b.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="CSS/styleUser.css" type="text/css">

</head>
<body>
	<nav>
		<ul>
			<li>
				<a href="#primero">Nuevo Telefono</a>
			</li>
		
			<li>
				<a href="#segundo">Buscar mis numeros</a>
			</li>
			<li>
				<a href="tercero">Modificar o Eliminar mis Numeros</a>
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

	<section id="primero">
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
	
	<hr class="new4">
	
	<section id="#segundo">
		<!--BUSCA EL TELEFONO DEL USUARIO-->
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
	</section>
	
	<hr class="new4">
	<!---------------------------------------------------------------->

	<section id="tercero">
		<!-- TABLA DE TELEFONOS DEL USUARIO -->
	
		<div class="container" style="margin-top: 25px; padding: 10px">
				<table id="tablax" class="table " style="width: 100%">
					<thead class="thead-dark">
					<tr>
						<th>Numero</th>
						<th>tipo</th>
						<th>operadora</th>
						<th>opciones</th>
					</tr>
					</thead>
					<tbody>
						<c:set var="i" value = "${0}"/>
	                    <c:forEach var="telefono" items="${usuario.telefonos}">
	                    	<c:set var="i" value = "${i+1}"/>
	                    	
								<tr>
				
									<td>${telefono.numero}</td>
									
									<td>${telefono.tipo}</td>
									
									<td>${telefono.operadora}</td>
									
									<td>
								<form action="EliminarTelefono" method="post">	
									<input type="hidden" value="${telefono.id}" id="tel_id" readonly name="tel_id">
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter${i}">Modificar</button>
										
										<!--  <input type="submit" onclick = "this.form.action = 'ModificarTelefono'" value="Modificar" />-->
										<button type="submit" class="btn btn-danger" >Eliminar</button>
										</form>
									</td>
								</tr>
							
							
							
							<!-- Modal -->
							<div class="modal fade" id="exampleModalCenter${i}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
							  <div class="modal-dialog modal-dialog-centered" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLongTitle">Modificar:${telefono.numero}</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							     	<form action="ModificarTelefono" method="post">
		   						      <input type="hidden" value="${telefono.id}" id="tel_id" readonly name="tel_id">
								      <div class="modal-body">
								      	<div class="form-group">
		                                	<label for="numero">Numero</label>
		                                    <input type="text" class="form-control" id="numero" name="numero" value="${telefonos.numero}" required maxlength="10" pattern="[0-9]+">
		                                </div>
		                                <div class="form-row">
		                                	<div class="form-group col-md-6">
		                                    	<label for="tipo">Tipo</label>
		                                        <input type="text" class="form-control" id="tipo" name="tipo" required placeholder="Movil" value="${telefonos.tipo}">
		                                    </div>
		                                    <div class="form-group col-md-6">
		                                    	<label for="operadora">Operadora</label>
		                                        <input type="text" class="form-control" id="operadora" name="operadora" required placeholder="Movistar" value="${telefonos.operadora}">
		                                    </div>
		                              	</div>
								      </div>
								  	
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								        <button type="submit" class="btn btn-primary">Guardar</button>
								      </div>
							      </form>
							    </div>
							  </div>
							</div>
								
						</c:forEach>
					</tbody>
				</table>
		</div>
	</section>
<!----------------------------------------------------------------->


<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>
<!--  
 <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
      -->  
        
        

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
	
	 <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="public/js/script.js"></script>
	
</body>
</html>