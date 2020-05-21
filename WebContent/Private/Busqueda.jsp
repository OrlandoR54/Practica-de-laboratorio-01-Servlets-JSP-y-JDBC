<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!--  BOOSTRAP  -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
      
     
<!-- FONTAWESOME -->
<script src="https://kit.fontawesome.com/3f81fb8d3b.js" crossorigin="anonymous"></script>  
<style type="text/css">
	body{
	background: #ABC4AB;
}
	
</style>      

</head>
<body>

<c:set var = "users" value = "${requestScope['users']}" />

	<!-- TABLA DE TELEFONOS DE USUARIOS -->
<!-- 
	<div class="container" style="margin-top: 25px; padding: 10px">
		<form name="formulario_tabla_usuarios" action="../BuscarUsuarios" method="post">
			<div class="box">
				<div class="container-3">
					<span class="icon"><i class="far fa-address-book"></i></span> 
					<input type="search" id="phone" name="busquedaUser" placeholder="Buscar usuario..."  required />
				</div>
			</div>
			<button type="submit">Buscar</button>
		</form>
		 -->

		
		<div class="ui container">
        	<c:choose>
            	<c:when test="${users.size() > 0}">
                    <table class="table table-hover">
                    	<thead>
                            <tr>
                                <th scope="col">Contactos</th>
                                <th scope="col">Correo</th>
                                <th scope="col">Numero de telefono</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:set var="i" value = "${num}"/>
                            <c:forEach var="user" items="${users}">
                            	<c:set var="i" value = "${i+1}"/>
                                <tr data-toggle="modal" data-target="#exampleModal${i}" data-whatever="@mdo">
                                	<td>
                                        <div class="ui middle aligned selection list">
                                            <div class="item">
                                               	<i class="fas fa-user"></i>
                                                <div class="content">
                                                    <div class="header">${user.nombre} ${user.apellido}</div>
                                                </div>
                                            </div>
										</div>
                                    </td>
                                    <td>${user.correo}</td>
                                    <td>${user.telefonos[num].numero}</td>
                                </tr>

                            <div class="modal fade" id="exampleModal${i}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content " style="width: 293px;">
                                        <div class="ui card aling-self-center">
                                            <div class="image">
                                                <img src="https://semantic-ui.com/images/avatar2/small/patrick.png">
                                            </div>
                                            <div class="content">
                                                <a class="header">${user.nombre} ${user.apellido}</a>
                                                <div class="meta">
                                                   <i class="envelope icon"></i> <a href="mailto:${user.correo}"> ${user.correo}</a>
                                                </div>
                                                <div class="description">
                                                    <c:choose>
                                                        <c:when test="${user.telefonos.size() > 0}">
                                                            <c:forEach var="telefono" items="${user.telefonos}">
                                                                <p><i class="phone icon"></i> <a href="tel:${telefono.numero}">${telefono.numero}</a>  &#9679; ${telefono.tipo} &#9679; ${telefono.operadora}</p>
                                                            </c:forEach>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <p>No tiene numeros.</p>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                            <div class="extra content">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="myFunction()">Cerrar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>    
               	<c:otherwise>
                  <!--   <h1>No hay contactos para esta busqueda</h1> -->
                   <img class="img-fluid" alt="" src="../IMG/talk1.jpg">
                </c:otherwise>
            </c:choose>
        </div>
		
	</div>

<script>
function myFunction() {
  location.replace("http://localhost:8080/Practica-de-laboratorio-01-Servlets-JSP-y-JDBC/index.html")
}
</script>


<!-- BOOSTRAP -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
	
</body>
</html>