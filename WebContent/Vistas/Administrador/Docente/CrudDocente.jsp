
<%@ include file="../../includes/validarSesion.jsp" %>

<!DOCTYPE html>

<html lang="en">
    
    <head>
        <%@ include file="../../includes/dashboard/header.jsp" %>

        <!-- END META SECTION -->

        <%@ include file="../../includes/dashboard/LibreriasCss.jsp" %>

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
                <div class="page-content-wrap" style="background-color: #f1f1f0 !important;" id="contenido">
                    
                    <div class="row">
                        
                        <div class="panel-body">
                            
                            
                            <div class="col-md-12">
                                
                                <div id="alertMessageResponse"></div>

                                <div class="col-md-12">
                                    <button type="button" class="btn btn-primary" id="btnModalRegistrar" data-toggle="modal" data-target="#modalRegisterOrUpdate">Registrar</button>
                                 </div>

                                <table id="tblDocente" class="table responsive dt-responsive text-center" width="100%">
                                    <thead>
                                        <th class="text-center" data-priority="1">Codigo</th>
                                        <th class="text-center" data-priority="2">Nombre</th>
                                        <th class="text-center" data-priority="3">Apellido</th>
                                        <th class="text-center">Edad</th>
                                        <th class="text-center">Dni</th>
                                        <th class="text-center">FechaNac</th>
                                        <th class="text-center" data-priority="4">Acciones</th>
                                    </thead>
                                </table>

                            </div>

                            <!-- modals -->
                            <%@ include file="../Modals/modal_Register_Update_Docente.jsp" %> 
                            <!-- Fin modal -->
                            
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
    <script type="text/javascript" src="CrudDocente.js"></script>
    
</html>
