<%@ include file="../includes/validarSesion.jsp" %>

<!DOCTYPE html>

<html lang="en">
   <head>
   
 	  <%@ include file="../includes/dashboard/header.jsp" %>
      
      <!-- END META SECTION -->
      
       <%@ include file="../includes/dashboard/LibreriasCss.jsp" %>
       
   </head>
   <body>

      <div class="page-container">
         
         <!-- Barra lateral -->
         <div class="page-sidebar" >
            
            <ul class="x-navigation" >
            
            	<%@ include file="../includes/dashboard/logo.jsp" %>
            	<%@ include file="../includes/dashboard/profile.jsp" %>
            	<jsp:include page="modulos.jsp" flush="true" />
            	
            </ul>
         </div>
         <!-- fin barra lateral -->        

         <!-- contenedor -->
         <div class="page-content">
            
            <!-- Barra de busqueda -->
            <%@ include file="../includes/dashboard/barraHorizontal.jsp" %>
            
            <!-- Barra de ruta -->
            <%@ include file="../includes/dashboard/ruta.jsp" %>
            
            <!-- area de contenido -->
            <div class="page-content-wrap" style="background-color: #f1f1f0 !important;">
               <div class="row">
                  <div class="panel-body">
                     
                    <div class="col-md-12">
                        <div id="alertMessageResponse"></div>
                        <button type="button" class="btn btn-primary" id="btnRegistrar" data-toggle='modal' data-target='#mimodal'>Registrar</button>
                        
                        <table id="tblDemo" class="table table-bordered dt-responsive text-center">
                           <thead>
                                 <th class="text-center">Codigo</th>
                                 <th class="text-center" data-priority="1">Nombre</th>
                                 <th class="text-center" data-priority="2">Apellido</th>
                                 <th class="text-center">Edad</th>
                                 <th class="text-center">Dni</th>
                                 <th class="text-center">FechaNac</th>
                                 <th class="text-center">Acceso</th>
                                 <th class="text-center">Acciones</th>
                           </thead>
                        </table>
                     </div>
                  </div>
               </div>
            </div>
            <!-- fin area de contenido -->                                
         </div>
          <!-- fin contenedor -->
      </div>
      
      <!-- modals -->
       <%@ include file="Modals/modalRegister_Update_Docente.jsp" %>
        <%@ include file="Modals/modalSetCredencialDocente.jsp" %>
       <!-- Fin modal -->

   	  <%@ include file="../includes/dashboard/logOut.jsp" %>
   	  <%@ include file="../includes/dashboard/audioLogOut.jsp" %>
   	  
   </body>
	
	<%@ include file="../includes/dashboard/LibreriasJS.jsp" %>
	<script type="text/javascript" src="CrudDocente.js"></script>
</html>

