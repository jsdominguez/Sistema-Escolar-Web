$("document").ready(function(){

  $('body').tooltip({
      selector: "[data-tooltip=tooltip]",
      container: "body"
  });

	var tblAlumno = $('#tblAlumno').DataTable({
	    "filter": true,
   		"searching": true,
        /*"processing": true,*/
        /*"serverSide": true,*/
        "ajax": {
            url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Alumno",
            type: "POST",
			      dataSrc: "datos",
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

  $("#btnRegistrar").click(function(){
        document.getElementById("frmModalRegistro").reset();
        $("#frmModalRegistroId").css("display","none");
        $("#opcRegisterorUpdate").val(1);
  });

  $('#mimodal').modal({
        keyboard: false,
        backdrop : 'static',
        show : false
  });

  $('#tblAlumno tbody').on( 'click', '#btnShowModalEdit', function () {
        var datosFila = tblAlumno.row($(this).parents("tr")).data();
        var idAlumno = datosFila.Codigo;
        var txtNombre = datosFila.Nombre;
        var txtApellido = datosFila.Apellido;
        var txtEdad = datosFila.Edad;
        var txtDni = datosFila.Dni;

        $("#frmModalRegistroId").css("display","block");
        $("#opcRegisterorUpdate").val(2);
        $("#txtCodigo").val(idAlumno);
        
        $("#txtNombre").val(txtNombre);
        $("#txtApellido").val(txtApellido);
        $("#txtEdad").val(txtEdad);
        $("#txtDni").val(txtDni);
    } );

  $("#btnSubmitFrmRegistro").click(function(){

        var formData = new FormData(document.getElementById("frmModalRegistro"));
        var opcRegisterorUpdate = $("#opcRegisterorUpdate").val();

        if(opcRegisterorUpdate == 1){
             formData.append("metodo","ctrlRegisterAlumno");
        }

        if(opcRegisterorUpdate == 2){
            formData.append("metodo","ctrlUpdateAlumno");
        }

         $.ajax({
             url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Alumno",
             type: "POST",
             data: formData,
             cache: false,
            contentType: false,
             processData: false
         })
         .done(function(response) {
            
            $('#mimodal').modal('hide')
            
            tblAlumno.ajax.url( '/ProyectoIntegrador2/Srvlt_Admin_Crud_Alumno?metodo=ctrlListarAlumno').load();
            
            var tipoAlert = ""
            var msg = "";
            var alert = " id='alertMsg' role='alert'><button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times</span><span class='sr-only'>Close</span></button>"+msg;

            if(response == 200){
              
              msg = "<strong>Exito!</strong> El evento se realizo satisfactoriamente .</div>";
              tipoAlert = "<div class='alert alert-success' ";  

            }else{

              msg = "<strong>Error!</strong> Ha ocurrido un evento inesperado internamente .</div>";
              tipoAlert = "<div class='alert alert-warning' ";  

            }

            alert = tipoAlert+alert+msg;

            $("#alertMessageResponse").html(alert);

            window.setTimeout(function() {
                $("#alertMsg").fadeTo(1000, 0).slideUp(1000, function(){
                  $("#alertMsg").alert('close');
                });
            }, 3000);

         });
    })

});



        
    