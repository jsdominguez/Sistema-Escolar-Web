$(function() {

  $('.combos').selectpicker();

  $('[data-toggle="tooltip"]').tooltip(); 

	var tblCursos = $('#tblCursos').DataTable({
	    "filter": true,
   		"searching": true,
      "responsive" : true,
      "ordering": false,
      //"processing": true,
        /*"serverSide": true,*/
      "ajax": {      
          url: 		  "/ProyectoIntegrador2/Srvlt_Crud_Curso",
          type: 		"POST",
		      dataSrc: 	"datos",
          data: {
              		  "metodo": "ctrlListarCursos"
          }
      },
		  columns: [
            {"data": "codigo"},
		      	{"data": "nomCurso"},
			      {"data": "nivel"},
            {"data": "nivelCodigo"},
            {"data": "acciones"}
		  ],
      columnDefs: [{
          "targets": [0,3],
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
        
      document.getElementById("frmModalRegistroCurso").reset();
      fnRrefreshSelect();
      $("#frmModalRegistroId").css("display","none");
      $("#opcRegisterorUpdateCurso").val(1);

  });

  $('#tblCursos tbody').on( 'click', '#btnEditDatosModalCurso', function () {
       
        document.getElementById("frmModalRegistroCurso").reset();
        var datosFila   = tblCursos.row($(this).parents("tr")).data();
        var idCodigo    = datosFila.codigo;
        var txtNombre   = datosFila.nomCurso;
        var selectGrado = datosFila.grado;
        var selectNivel = datosFila.nivelCodigo;

        $("#frmModalRegistroId").css("display","block");
        $("#opcRegisterorUpdateCurso").val(2);
        $("#txtCodigo").val(idCodigo);
        $("#txtCurso").val(txtNombre);
        $('#selectGrado').selectpicker('val',selectGrado);
        $('#selectNivel').selectpicker('val',selectNivel);
        $('#selectGrado').prop('disabled', false);
    	$('#selectGrado').selectpicker('refresh');

    } );

  $("#btnSubmitFrmRegistro").click(function(){

      var formData = new FormData(document.getElementById("frmModalRegistroCurso"));
      
      var opcRegisterorUpdateCurso = $("#opcRegisterorUpdateCurso").val();

      if(opcRegisterorUpdateCurso == 1){   formData.append("metodo","ctrlRegisterCurso"); }

      if(opcRegisterorUpdateCurso == 2){   formData.append("metodo","ctrlUpdateCurso");   }

      fnEnviarPeticion(formData);

      $('#modalRegisterOrUpdateCursos').modal('hide');

  })


  $("#selectNivel").on("change",function(){
    
      $('#selectGrado').prop('disabled', false);
      $('#selectGrado').selectpicker('refresh');

  })


  /*******  FUNCIONES ******/

  function fnEnviarPeticion(formData){

    $.ajax({

            url: "/ProyectoIntegrador2/Srvlt_Crud_Curso",
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
        $('#tblCursos').waitMe('hide');

    });

  }

  function fnWaitResponse(){
    
    //https://www.jqueryscript.net/loading/jQuery-Plugin-For-Creating-Loading-Overlay-with-CSS3-Animations-waitMe.html
      $('#tblCursos').waitMe({

        effect : 'roundBounce',
        text   : '',
        bg     : 'rgba(255,255,255,0.7)',
        color  : '#000'

      });

  }

  function fnShowResponse(response){
        
      $('#modalRegisterOrUpdateCursos').modal('hide')    
      
      tblCursos.ajax.url( '/ProyectoIntegrador2/Srvlt_Crud_Curso?metodo=ctrlListarCursos').load();
      
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

    $('#modalRegisterOrUpdateCursos').modal( fnOptions() );
  }

  function fnOptions(){

    return {
        keyboard: false,
        backdrop : 'static',
        show : false
    }

  }


  function fnRrefreshSelect(){
  	 $('#selectGrado').prop('disabled', true);
     $('#selectGrado').selectpicker('refresh');
     $('#selectNivel').selectpicker('refresh');
  }


}); //fin jquery