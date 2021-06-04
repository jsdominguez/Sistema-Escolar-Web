<%@ include file="../includes/validarSesion.jsp" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<html lang="en">
   <head>

 	  <%@ include file="../includes/dashboard/header.jsp" %>
      
      <!-- END META SECTION -->
      
       <%@ include file="../includes/dashboard/LibreriasCss.jsp" %>
       
   </head>
   
   <style type="text/css">
      .centerTextWidget{
         text-align: center !important;
      }
   </style>

   <body>
      
      <div id="loader" class="center"></div> 
      
      <div class="page-container">
         
         <!-- Barra lateral -->
         <div class="page-sidebar">
            
            <ul class="x-navigation">
            
            	<%@ include file="../includes/dashboard/logo.jsp" %>
            	<%@ include file="../includes/dashboard/profile.jsp" %>
            	<%@ include file="modulos.jsp" %>
            	
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
                    
                     <%@ include file="infoInicio.jsp" %>
                     
                  </div>
                
               </div>
            </div>
            <!-- fin area de contenido -->                                
         </div>
          <!-- fin contenedor -->
      </div>
      

   	  <%@ include file="../includes/dashboard/logOut.jsp" %>
   	  <%@ include file="../includes/dashboard/audioLogOut.jsp" %>
   	  
   </body>
	
	<%@ include file="../includes/dashboard/LibreriasJS.jsp" %>
</html>

