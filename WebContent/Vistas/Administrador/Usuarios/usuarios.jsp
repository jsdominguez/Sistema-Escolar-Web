<%@ include file="../../includes/validarSesion.jsp" %>

<!DOCTYPE html>

<html lang="en">
    
    <head>
        
        <%@ include file="../../includes/dashboard/header.jsp" %>

        <!-- END META SECTION -->

        <%@ include file="../../includes/dashboard/LibreriasCss.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/bootstrap-select/dist/css/bootstrap-select.css">
        
    </head>

    <body>

    	<div id="loader" class="center"></div> 

        <div class="page-container">
            
            <!-- Barra lateral -->
            <div class="page-sidebar">
                
                <ul class="x-navigation">
                    <%@ include file="../../includes/dashboard/logo.jsp" %> 
                    <%@ include file="../../includes/dashboard/profile.jsp" %> 
                    <%@ include file="modulos.jsp" %>
                </ul>

            </div>
            <!-- fin barra lateral -->

            <!-- contenedor -->
            <div class="page-content">
                
                <!-- Barra de busqueda -->
                <%@ include file="../../includes/dashboard/barraHorizontal.jsp" %>

                <!-- Barra de ruta -->
                <%@ include file="../../includes/dashboard/ruta.jsp" %>

                <!-- area de contenido -->
                <div class="page-content-wrap" style="background-color: #f1f1f0 !important;">
                    
                    <div class="row">
                        
                        <div class="panel-body">
                     
		                    <div class="col-md-12">
		                        
		                        <div id="alertMessageResponse"></div>

		                        <table id="tblUsuarios" class="table responsive dt-responsive text-center" width="100%">
		                           <thead>
		                                 <th class="text-center">Codigo</th>
		                                 <th class="text-center">Nombre</th>
		                                 <th class="text-center">Apellido</th>
		                                 <th class="text-center">Tipo</th>
		                                 <th class="text-center">Acceso Sistema</th>
                                         <th class="text-center">Acciones</th>
		                           </thead>
		                        </table>

		                    </div>
		                <%@ include file="../Modals/modal_Credenciales_Usuario.jsp" %>
                        </div>
                    </div>
                </div>
                <!-- fin area de contenido -->
            </div>
            <!-- fin contenedor -->
        </div>

        <%@ include file="../../includes/dashboard/logOut.jsp" %> 
        <%@ include file="../../includes/dashboard/audioLogOut.jsp" %>

    </body>

    <%@ include file="../../includes/dashboard/LibreriasJS.jsp" %>
	<script type="text/javascript" src="usuarios.js"></script>
</html>

