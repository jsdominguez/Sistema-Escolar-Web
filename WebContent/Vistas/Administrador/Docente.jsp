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
                                 <th class="text-center">id</th>
                                 <th class="text-center" data-priority="1">nombre</th>
                                 <th class="text-center" data-priority="2">apellido</th>
                                 <th class="text-center">edad</th>
                                 <th class="text-center">campo1</th>
                                 <th class="text-center">campo2</th>
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
       <%@ include file="../includes/page1/modal.jsp" %>
       <!-- Fin modal -->

   	  <%@ include file="../includes/dashboard/logOut.jsp" %>
   	  <%@ include file="../includes/dashboard/audioLogOut.jsp" %>
   	  
   </body>
	
	<%@ include file="../includes/dashboard/LibreriasJS.jsp" %>
	
</html>

