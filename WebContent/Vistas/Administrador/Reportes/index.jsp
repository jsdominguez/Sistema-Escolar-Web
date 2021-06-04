<%@ include file="../../includes/validarSesion.jsp" %>
<%@ page isELIgnored="false" %>

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
            <div class="page-content-wrap" style="background-color: #f1f1f0 !important;">
               <div class="row">
                  <div class="panel-body">
                  	
                  	<!-- ------------------------------------------------------------>
                  	<form id="reporte01" class="form-horizontal" enctype="multipart/form-data" name="reporte01">
                  		<input type="hidden" name="chart" value="1">
                  		<input type="date" name="fechaMin" min="2016-01-01" max="2020-12-31" value="2016-01-01">
                  		<input type="date" name="fechaMax" min="2016-01-01" max="2020-12-31" value="2020-12-31">
                  		<button class="btn btn-primary" id="btnSubmitFrmDate01" type="button">Buscar</button>
                  	</form><br><br>

                    <div class="chart-container">
                       <canvas id="myChart" style="position: relative; height:50vh; width:75vw; margin-bottom: 50px"></canvas>
                    </div>

                  <center>

                  	<button class="btn btn-danger">PDF</button>

                 	<button class="btn btn-success" id="btnDownloadReporteExcel01" type="button">Excel</button><br><br><br>


                  </center>
                  <!-- ------------------------------------------------------------>
                  <div style="width: 100%;height: 1px;background-color: grey"></div><br><br>

                  <form id="reporte02" class="form-horizontal" enctype="multipart/form-data" name="reporte02">
                  		<input type="hidden" name="chart" value="2">
                  		<input type="date" name="fechaMin" min="2016-01-01" max="2020-12-31"  value="2016-01-01">
                  		<input type="date" name="fechaMax" min="2016-01-01" max="2020-12-31" value="2020-12-31">
                  		<button class="btn btn-primary" id="btnSubmitFrmDate02" type="button">Buscar</button>
                  </form><br>


                     <div class="chart-container">
                       <canvas id="myChart2" style="position: relative;height:50vh; width:75vw; margin-bottom: 50px"></canvas>
                    </div>

                    <center>

                  	<button class="btn btn-danger" type="button" onclick="window.location='/ProyectoIntegrador2/Srvlt_Reportes?chart=2&report=pdf'">PDF</button>

                 	<button class="btn btn-success" type="button" id="btnDownloadReporteExcel02">Excel</button><br><br><br>
                 	
                  </center>
                  <!-- ------------------------------------------------------------>
                  <div style="width: 100%;height: 1px;background-color: grey"></div><br><br>
                  <form id="reporte03"  class="form-horizontal" enctype="multipart/form-data" name="reporte03">
                  		<input type="hidden" name="chart" value="3">
                  		<input type="date" name="fechaMin" min="2016-01-01" max="2020-12-31"  value="2016-01-01">
                  		<input type="date" name="fechaMax" min="2016-01-01" max="2020-12-31" value="2020-12-31">
                  		<button class="btn btn-primary" id="btnSubmitFrmDate03" type="button">Buscar</button>
                  </form><br><br>

                     <div class="chart-container">
                      
                      <canvas id="myChart3" style="position: relative;height:50vh; width:75vw; margin-bottom: 50px"></canvas>
                      
                    </div>
                 
                    <center>

                  	<button class="btn btn-danger" role="link" onclick="window.location='/ProyectoIntegrador2/Srvlt_Reportes?download=pdf'">PDF</button>

                 		<button class="btn btn-success" type="button" id="btnDownloadReporteExcel03">Excel</button><br><br>
                 	
                  </center>
                  <!-- ------------------------------------------------------------>
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
	<script type="text/javascript" src="../../Resources/dashboard/js/plugins/chartJS/dist/chart.min.js"></script>
	<script type="text/javascript" src="reporte.js"></script>
</html>

