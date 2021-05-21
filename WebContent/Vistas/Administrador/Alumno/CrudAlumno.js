$(function() {

  $('.combos').selectpicker();

  $('[data-toggle="tooltip"]').tooltip(); 

	var tblAlumno = $('#tblAlumno').DataTable({
	    "filter": true,
   		"searching": true,
      "responsive" : true,
      //"processing": true,
        /*"serverSide": true,*/
      "ajax": {      
          url: 		  "/ProyectoIntegrador2/Srvlt_Crud_Alumno",
          type: 		"POST",
		      dataSrc: 	"datos",
          data: {
              		  "metodo": "ctrlListarAlumno"
          }
      },
		  columns: [
            {"data": "Codigo"},
		      	{"data": "Nombre"},
			      {"data": "Apellido"},
			      {"data": "Edad"},
            {"data": "Dni"},
            {"data": "grado"},
            {"data": "seccion"},
            {"data": "nivel"},
            {"data": "acciones"}
		  ],
        columnDefs: [{
            "targets": [5,6,7], //oculta columna pero estara disponible para su seleccion
            "visible": false,
            "searchable": false
        }],
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

  fnConfigModals();

  $("#btnModalRegistrar").click(function(){
        
      document.getElementById("frmModalRegistroAlumno").reset();
      fnRrefreshSelect();
      $("#frmModalRegistroId").css("display","none");
      $("#opcRegisterorUpdate").val(1);

  });

  $('#tblAlumno tbody').on( 'click', '#btnEditDatosModalAlumno', function () {
       
        document.getElementById("frmModalRegistroAlumno").reset();
        fnRrefreshSelect();
        var datosFila = tblAlumno.row($(this).parents("tr")).data();
        var idAlumno = datosFila.Codigo;
        var txtNombre = datosFila.Nombre;
        var txtApellido = datosFila.Apellido;
        var txtEdad = datosFila.Edad;
        var txtDni = datosFila.Dni;
        var selectGrado = datosFila.grado;
        var selectSeccion = datosFila.seccion;
        var selectNivel = datosFila.nivel;

        $("#frmModalRegistroId").css("display","block");
        $("#opcRegisterorUpdate").val(2);
        $("#txtCodigo").val(idAlumno);
        $("#txtNombre").val(txtNombre);
        $("#txtApellido").val(txtApellido);
        $("#txtEdad").val(txtEdad);
        $("#txtDni").val(txtDni);
        $('#selectGrado').selectpicker('val',selectGrado);
        $('#selecSeccion').selectpicker('val',selectSeccion);
        $('#selectNivel').selectpicker('val',selectNivel);

    } );

  $("#btnSubmitFrmRegistro").click(function(){

      var formData = new FormData(document.getElementById("frmModalRegistroAlumno"));
      
      var opcRegisterOrUpdate = $("#opcRegisterorUpdate").val();

      if(opcRegisterOrUpdate == 1){   formData.append("metodo","ctrlRegisterAlumno");	}

      if(opcRegisterOrUpdate == 2){   formData.append("metodo","ctrlUpdateAlumno");		}

      fnEnviarPeticion(formData);

      $('#modalRegisterOrUpdate').modal('hide');

  })


  $("#selectNivel").on("change",function(){
    
      $('#selectGrado').prop('disabled', false);
      $('#selectGrado').selectpicker('refresh');

      $('#selecSeccion').prop('disabled', false);
      $('#selecSeccion').selectpicker('refresh');

  })


  /*******  FUNCIONES ******/

  function fnEnviarPeticion(formData){

    $.ajax({

            url: "/ProyectoIntegrador2/Srvlt_Crud_Alumno",
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend: function(){

            	fnWaitResponse();
            }
    }).done(function(response) {

        fnShowResponse(response);
        $('#tblAlumno').waitMe('hide');

    });

  }

  function fnWaitResponse(){
  	
  	//https://www.jqueryscript.net/loading/jQuery-Plugin-For-Creating-Loading-Overlay-with-CSS3-Animations-waitMe.html
      $('#tblAlumno').waitMe({

	      effect : 'roundBounce',
	      text 	 : '',
	      bg 	   : 'rgba(255,255,255,0.7)',
	      color  : '#000'

      });

  }

  function fnShowResponse(response){
        
      $('#modalRegisterOrUpdate').modal('hide')    
      
      tblAlumno.ajax.url( '/ProyectoIntegrador2/Srvlt_Crud_Alumno?metodo=ctrlListarAlumno').load();
      
       var tipoAlert = "<div class='alert alert-danger' "; 
      var msg = "<strong>Error!</strong> Ha ocurrido un evento inesperado internamente .</div>";
      var alertContent = "";

      if(response == 200 || response == 1){
        
        msg = "<strong>Exito!</strong> El evento se realizo satisfactoriamente .</div>";
        tipoAlert = "<div class='alert alert-success' ";  

      }

      var alert = " id='alertMsg' role='alert'><button type='button' class='close' data-dismiss='alert'>";
      alert +=  "<span aria-hidden='true'>&times</span><span class='sr-only'>Close</span></button>"+msg;

      alertContent = tipoAlert+alert;

      $("#alertMessageResponse").html(alertContent);
      
      fnTimeHide();


  }


  function fnTimeHide(){

	window.setTimeout(function() {

      $("#alertMsg").fadeTo(1000, 0).slideUp(1000, function(){

        $("#alertMsg").alert('close');

      });

    }, 3000);

  }


  function fnConfigModals(){

    $('#modalRegisterOrUpdate').modal( fnOptions() );
  }

  function fnOptions(){

    return {
        keyboard: false,
        backdrop : 'static',
        show : false
    }

  }


  function fnRrefreshSelect(){

     $('#selectNivel').selectpicker('refresh');
     $('#selectGrado').selectpicker('refresh');
     $('#selecSeccion').selectpicker('refresh');
  }

}); //fin jquery