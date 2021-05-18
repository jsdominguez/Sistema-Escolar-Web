<%@ include file="../includes/validarSesion.jsp" %>

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
            <div class="page-content-wrap">
               <div class="row">
                  <div class="panel-body">
                     
                     <div class="col-md-3">

                            <div class="widget widget-primary widget-item-icon">
                                <div class="widget-item-left">
                                    <span class="fa fa-user"></span>
                                </div>
                                <div class="widget-data" style="margin-top:16px;margin-left:3px;">
                                    <div class="widget-int num-count centerTextWidget">1024</div>
                                     <div class="widget-title centerTextWidget">Alumnos</div>
                                </div>                           
                            </div>

                        </div>
                        <div class="col-md-3">

                            <div class="widget widget-danger widget-item-icon">
                                <div class="widget-item-left">
                                    <span class="fa fa-briefcase"></span>
                                </div>
                                <div class="widget-data" style="margin-top:16px;margin-left:3px;">
                                    <div class="widget-int num-count centerTextWidget">1024</div>
                                     <div class="widget-title centerTextWidget">Docentes</div>
                                </div>                           
                            </div>

                        </div>
                        <div class="col-md-3">

                            <div class="widget widget-success widget-item-icon">
                                <div class="widget-item-left">
                                    <span class="fa fa-user"></span>
                                </div>
                                <div class="widget-data" style="margin-top:16px;margin-left:3px;">
                                    <div class="widget-int num-count centerTextWidget">1024</div>
                                     <div class="widget-title centerTextWidget">Padres</div>
                                </div>                           
                            </div>

                        </div>
                        <div class="col-md-3">

                            <div class="widget widget-default widget-item-icon">
                                <div class="widget-item-left">
                                    <span class="fa fa-book"></span>
                                </div>
                                <div class="widget-data" style="margin-top:16px;margin-left:3px;">
                                    <div class="widget-int num-count centerTextWidget">68</div>
                                    <div class="widget-title centerTextWidget">Cursos</div>
                                </div>                           
                            </div>

                        </div>
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

