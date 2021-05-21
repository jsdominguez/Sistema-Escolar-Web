<%@ include file="../includes/validarSesion.jsp" %>

<!DOCTYPE html>

<html lang="en">
   <head>
   
 	  <%@ include file="../includes/dashboard/header.jsp" %>
      
      <!-- END META SECTION -->
      
       <%@ include file="../includes/dashboard/LibreriasCss.jsp" %>

       <link rel="stylesheet" href="${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/scheluderJquery/dist/jquery.schedule.css">
       
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
                                    <div class="widget-int num-count centerTextWidget">15</div>
                                     <div class="widget-title centerTextWidget">Tereas</div>
                                </div>                           
                            </div>

                        </div>
                        <div class="col-md-3">

                            <div class="widget widget-danger widget-item-icon">
                                <div class="widget-item-left">
                                    <span class="fa fa-briefcase"></span>
                                </div>
                                <div class="widget-data" style="margin-top:16px;margin-left:3px;">
                                    <div class="widget-int num-count centerTextWidget">4</div>
                                     <div class="widget-title centerTextWidget">Practicas</div>
                                </div>                           
                            </div>

                        </div>
                        <div class="col-md-3">

                            <div class="widget widget-success widget-item-icon">
                                <div class="widget-item-left">
                                    <span class="fa fa-user"></span>
                                </div>
                                <div class="widget-data" style="margin-top:16px;margin-left:3px;">
                                    <div class="widget-int num-count centerTextWidget">10</div>
                                     <div class="widget-title centerTextWidget">Foros</div>
                                </div>                           
                            </div>

                        </div>
                        <div class="col-md-3">

                            <div class="widget widget-default widget-item-icon">
                                <div class="widget-item-left">
                                    <span class="fa fa-book"></span>
                                </div>
                                <div class="widget-data" style="margin-top:16px;margin-left:3px;">
                                    <div class="widget-int num-count centerTextWidget">10</div>
                                    <div class="widget-title centerTextWidget">Cursos</div>
                                </div>                           
                            </div>

                        </div>
                  </div>
               </div>
               <div class="row">
                 <div class="panel-body">
                   <div id="schedule-demo" class="jqs-demo mb-3">
                     
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
  <script type="text/javascript" src="${pageContext.request.contextPath}/Vistas/Resources/dashboard/js/plugins/scheluderJquery/dist/jquery.schedule.min.js"></script>
	<script type="text/javascript">
   $("#schedule-demo").jqs({

   
 mode: "read",

      days: [

      "Lunes",

        "Martes",

        "Miercoles",

        "Jueves",

        "Viernes",

        "Sabado",

        "Domingo"

    ],
    data: [

      {

        "day": 0,
        "periods": [
      {
        "start": "08:00",
        "end": "09:30",
        "title": "Matematica",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "09:30",
        "end": "11:30",
        "title": "Comunicacion",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "11:30",
        "end": "13:00",
        "title": "Religion",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      }
    ],


      },
      {

        day: 1,

        periods: [
      {
        "start": "08:00",
        "end": "09:30",
        "title": "Literatura",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "09:30",
        "end": "11:30",
        "title": "Ingles",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "11:30",
        "end": "13:00",
        "title": "Religion",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      }
    ]

      },
      {

        day: 2,

        periods: [
      {
        "start": "08:00",
        "end": "09:30",
        "title": "Matematica",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "09:30",
        "end": "11:30",
        "title": "Razonamiento Matematico",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "11:30",
        "end": "13:00",
        "title": "Razonamiento Verbal",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      }
    ]

      },
      {

        day: 3,

        periods: [
      {
        "start": "08:00",
        "end": "09:30",
        "title": "Linguistica",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "09:30",
        "end": "11:30",
        "title": "Comunicacion",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "11:30",
        "end": "13:00",
        "title": "Religion",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      }
    ]
      },
      {

        day: 4,

        periods: [
      {
        "start": "08:00",
        "end": "09:30",
        "title": "Matematica",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "09:30",
        "end": "11:30",
        "title": "Geopolitica",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      },{
        "start": "11:30",
        "end": "13:00",
        "title": "Literatura",
        "backgroundColor": "rgba(0, 0, 0, 0.5)",
        "borderColor":"#000",
        "textColor": "#fff"
      }
    ]

      }
    ]
  }); 

  </script>
</html>

