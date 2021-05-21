$(function() {

 // $('.combos').selectpicker();

  //$('[data-toggle="tooltip"]').tooltip(); 

	var tblGradoSeccion = $('#tblGradoSeccion').DataTable({
	    "filter": true,
   		"searching": true,
    	"responsive" : true,
    	 "ordering": false,
      //"processing": true,
        /*"serverSide": true,*/
      "ajax": {      
          url: 		  "/ProyectoIntegrador2/Srvlt_Grado_Seccion",
          type: 		"POST",
		      dataSrc: 	"datos",
          data: {
              		  "metodo": "ctrlListarGradoSeccion"
          }
      },
		  columns: [
            	{"data": "codigo"},
		      	{"data": "nivel"},
			    {"data": "grado"},
			    {"data": "seccion"},
			    {"data": "cantidad_alumnos"},
			    {"data": "estado_ingreso"}
		  ],
        /*columnDefs: [{
            "targets": [5,6,7], //oculta columna pero estara disponible para su seleccion
            "visible": false,
            "searchable": false
        }],*/
        language: {
            search: "Buqueda:",
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "Ningun Dato Disponible",
            info: "_START_ al _END_ de _TOTAL_ registros",
            loadingRecords: "",
            processing: "",
            sInfoEmpty: " 0 al 0 de 0 registros",
            sInfoFiltered: "(filtrado de un total de _MAX_ registros)",
            oPaginate: {
                sFirst: "Primero",
                sLast: "Último",
                sNext: "Siguiente",
                sPrevious: "Anterior"
            }
        }
    });

}); //fin jquery