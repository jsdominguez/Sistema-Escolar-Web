$(function() {

  $('.combos').selectpicker();

  $('body').tooltip({
      selector: "[data-tooltip=tooltip]",
      container: "body"
  });


	var tblAlumno = $('#tblAlumno').DataTable({
	    "filter": true,
   		"searching": true,
        "processing": true,
        /*"serverSide": true,*/
        "ajax": {
            url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Alumno",
            type: "POST",
			      dataSrc: "datos",
            data: {
                "metodo": "ctrlListarAlumno",
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
            {"data": "Acceso"},
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
            loadingRecords: "<img src='ajax-loader.gif'/>",
            processing: "<img src='ajax-loader.gif'/>",
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

      if(opcRegisterOrUpdate == 1){
           formData.append("metodo","ctrlRegisterAlumno");
      }

      if(opcRegisterOrUpdate == 2){
          formData.append("metodo","ctrlUpdateAlumno");
      }

      fnEnviarPeticion(formData);

      $('#modalRegisterOrUpdate').modal('hide');
  })


  $("#tblAlumno tbody").on( 'click', '#btnShowModalCredentials', function () {
        
        document.getElementById("frmCredentialAlumno").reset();
        var datosFila = tblAlumno.row($(this).parents("tr")).data();
        var idAlumno = datosFila.Codigo;
        $("#txtCod").val(idAlumno);       
    })

    $("#btnSubmitSetPassword").click(function(){
        
        var formData = new FormData(document.getElementById("frmCredentialAlumno"));
        formData.append("metodo","ctrlSetCredentialAlumno");
        fnEnviarPeticion(formData);
        $('#mdlSetCredentialAlumno').modal('hide');
        
    })


    $("#tblAlumno tbody").on( 'click', '#btnSwitchAccess', function () {
        
        var datosFila = tblAlumno.row($(this).parents("tr")).data();
        var codigoAlumno = datosFila.Codigo;
        var valorEstado = $(this).attr("value");
        (valorEstado == 1) ? valorEstado = 0 : valorEstado = 1;
        $(this).val(valorEstado);
        valorEstado = $(this).attr("value");

        var formData = new FormData();
        formData.append("valorEstado",  valorEstado );
        formData.append("codAlumno", codigoAlumno );
        formData.append("metodo", "ctrlSetAccesoUsuario");

       fnEnviarPeticion(formData);

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
             url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Alumno",
             type: "POST",
             data: formData,
             cache: false,
            contentType: false,
             processData: false
    }).done(function(response) {
            fnShowResponse(response);
    });

  }


  function fnShowResponse(response){
        
      $('#modalRegisterOrUpdate').modal('hide')    
      
      tblAlumno.ajax.url( '/ProyectoIntegrador2/Srvlt_Admin_Crud_Alumno?metodo=ctrlListarAlumno').load();
      
      var tipoAlert = ""
      var msg = "";
      var alert = " id='alertMsg' role='alert'><button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times</span><span class='sr-only'>Close</span></button>"+msg;

      if(response == 200 || response == 1){
        
        msg = "<strong>Exito!</strong> El evento se realizo satisfactoriamente .</div>";
        tipoAlert = "<div class='alert alert-success' ";  

      }else{

        msg = "<strong>Error!</strong> Ha ocurrido un evento inesperado internamente .</div>";
        tipoAlert = "<div class='alert alert-danger' ";  

      }

      alert = tipoAlert+alert+msg;

      $("#alertMessageResponse").html(alert);

      window.setTimeout(function() {
          $("#alertMsg").fadeTo(1000, 0).slideUp(1000, function(){
            $("#alertMsg").alert('close');
          });
      }, 3000);

  }

  function fnConfigModals(){
    $('#modalRegisterOrUpdate').modal( fnOptions() );
    $('#mdlSetCredentialAlumno').modal( fnOptions() );
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

});