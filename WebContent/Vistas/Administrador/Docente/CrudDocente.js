$("document").ready(function(){
  
  $('body').tooltip({
        selector: "[data-tooltip=tooltip]",
        container: "body"
    });

	var tblDocente = $('#tblDocente').DataTable({
	    "filter": true,
   		"searching": true,
        /*"processing": true,*/
        /*"serverSide": true,*/
        "ajax": {
            url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente",
            type: "POST",
			      dataSrc: "datos",
            data: {
                "metodo": "ctrlListarDocente"
            }
        },
		columns: [
            {"data": "Codigo"},
      			{"data": "Nombre"},
      			{"data": "Apellido"},
      			{"data": "Edad"},
            {"data": "Dni"},
            {"data": "FechaNac"},
            {"data": "Acceso"},
            {"data": "acciones"}
		],/*
        columnDefs: [{
            "orderable": true,
            "targets": 1
        }, {
            "targets": [0], //oculta columna pero estara disponible para su seleccion
            "visible": false,
            "searchable": false
        }],*/
        language: {
            search: "Buqueda:",
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "Ningún Dato Disponible",
            info: "_START_ al _END_ de _TOTAL_ registros",
            loadingRecords: "Cargando..",
            processing: "Procesando",
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
        document.getElementById("frmModalRegistroDocente").reset();
        $("#frmModalRegistroId").css("display","none");
        $("#opcRegisterorUpdate").val(1);
    });

   $('#tblDocente tbody').on( 'click', '#btnShowModalEdit', function () {
        document.getElementById("frmModalRegistroDocente").reset();
        var datosFila = tblDocente.row($(this).parents("tr")).data();
        var IdDocente = datosFila.Codigo;
        var txtNombre = datosFila.Nombre;
        var txtApellido = datosFila.Apellido;
        var txtEdad = datosFila.Edad;
        var txtDni = datosFila.Dni;
        var txtFechaNac = datosFila.FechaNac;

        $("#frmModalRegistroId").css("display","block");
        $("#opcRegisterorUpdate").val(2);
        $("#txtCodigo").val(IdDocente);
        
        $("#txtNombre").val(txtNombre);
        $("#txtApellido").val(txtApellido);
        $("#txtEdad").val(txtEdad);
        $("#txtDni").val(txtDni);
        $("#txtFechaNac").val(txtFechaNac);
    } );

    $("#btnSubmitFrmRegistro").click(function(){

        var formData = new FormData(document.getElementById("frmModalRegistroDocente"));
        var opcRegisterorUpdate = $("#opcRegisterorUpdate").val();

        if(opcRegisterorUpdate == 1){
             formData.append("metodo","ctrlRegisterDocente");
        }

        if(opcRegisterorUpdate == 2){
            formData.append("metodo","ctrlUpdateDocente");
        }

        $('#modalRegisterOrUpdate').modal('hide');
        fnEnviarPeticion(formData);
    })


    $("#tblDocente tbody").on( 'click', '#btnSwitchAccess', function () {
        
        var datosFila = tblDocente.row($(this).parents("tr")).data();
        var codigoDocente = datosFila.Codigo;
        var valorEstado = $(this).attr("value");
        (valorEstado == 1) ? valorEstado = 0 : valorEstado = 1;
        $(this).val(valorEstado);
        valorEstado = $(this).attr("value");

        var formData = new FormData();
        formData.append("valorEstado",  valorEstado );
        formData.append("codDocente", codigoDocente );
        formData.append("metodo", "ctrlSetAccesoUsuario");

       fnEnviarPeticion(formData);

    })


    $("#tblDocente tbody").on( 'click', '#btnShowModalCredentials', function () {
        
        document.getElementById("frmCredentialDocente").reset();
        var datosFila = tblDocente.row($(this).parents("tr")).data();
        var IdDocente = datosFila.Codigo;
        $("#txtCod").val(IdDocente);       
    })

    $("#btnSubmitSetPassword").click(function(){
        var formData = new FormData(document.getElementById("frmCredentialDocente"));
         formData.append("metodo","ctrlSetCredentialDocentes");

        $('#mdlSetCredentialDocente').modal('hide');
        fnEnviarPeticion(formData);
    })



  /*******  FUNCIONES ******/

  function fnEnviarPeticion(formData){

    $.ajax({
             url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente",
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
            
      tblDocente.ajax.url( '/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente?metodo=ctrlListarDocente').load();
      
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
    $('#mdlSetCredentialDocente').modal( fnOptions() );
  }

  function fnOptions(){
    return {
        keyboard: false,
        backdrop : 'static',
        show : false
    }
  }

});