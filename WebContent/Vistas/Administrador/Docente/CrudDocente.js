$("document").ready(function(){
  
  $('body').tooltip({
        selector: "[data-tooltip=tooltip]",
        container: "body"
    });

	var tblDemo = $('#tblDemo').DataTable({
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

   $('#tblDemo tbody').on( 'click', '#btnShowModalEdit', function () {
        var datosFila = tblDemo.row($(this).parents("tr")).data();
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

/*
    $('#tblDemo tbody').on( 'click', '#btnShowModalDelete', function () {
        var datosFila = tblDemo.row($(this).parents("tr")).data();
        var idAlumno = datosFila.id;

        $.ajax({
             url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente",
             type: "POST",
             data: {
                "metodo": "ctrlEliminarAlumno",
                "idAlumno":idAlumno
            }
         })
         .done(function(response) {
            tblDemo.ajax.url( '/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente?metodo=ctrlListarDocente').load();
             console.log(response);
         });
        
    });*/

    $("#btnSubmitFrmRegistro").click(function(){

        var formData = new FormData(document.getElementById("frmModalRegistro"));
        var opcRegisterorUpdate = $("#opcRegisterorUpdate").val();

        if(opcRegisterorUpdate == 1){
             formData.append("metodo","ctrlRegisterDocente");
        }

        if(opcRegisterorUpdate == 2){
            formData.append("metodo","ctrlUpdateDocente");
        }

         $.ajax({
             url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente",
             type: "POST",
             data: formData,
             cache: false,
            contentType: false,
             processData: false
         })
         .done(function(response) {
            $('#mimodal').modal('hide')
            tblDemo.ajax.url( '/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente?metodo=ctrlListarDocente').load();
            if(response == 200){
            	var alert = "<div class='alert alert-success' id='alertMsg' role='alert'><button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times</span><span class='sr-only'>Close</span></button>"+
            				"<strong>Exito!</strong> El evento se realizo satisfactoriamente .</div>";
            	
                $("#alertMessageResponse").html(alert);

                window.setTimeout(function() {
                    $("#alertMsg").fadeTo(1000, 0).slideUp(1000, function(){
                      $("#alertMsg").alert('close');
                    });
                }, 3000);
            }
         });
    })


    $("#tblDemo tbody").on( 'click', '#btnSwitchAccess', function () {
        var datosFila = tblDemo.row($(this).parents("tr")).data();
        var codigoDocente = datosFila.Codigo;
        var valorEstado = $(this).attr("value");
        (valorEstado == 1) ? valorEstado = 0 : valorEstado = 1;
        $(this).val(valorEstado);
        valorEstado = $(this).attr("value");

        $.ajax({
             url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente",
             type: "POST",
             data: {
                'valorEstado': valorEstado,
                'codDocente' : codigoDocente,
                'metodo': 'ctrlSetAccesoUsuario'
             }
         }).done(function() {
                var alert = "<div class='alert alert-success' id='alertMsg' role='alert'><button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times</span><span class='sr-only'>Close</span></button>"+
                              "<strong>Exito!</strong> El evento se realizo satisfactoriamente ."+
                            "</div>";
            
            $("#alertMessageResponse").html(alert);

            window.setTimeout(function() {
                    $("#alertMsg").fadeTo(1000, 0).slideUp(1000, function(){
                      $("#alertMsg").alert('close');
                    });
            }, 3000);

        }); 
    })


    $("#tblDemo tbody").on( 'click', '#btnSetCredentials', function () {
        
        document.getElementById("frmCredentialDocente").reset();
        var datosFila = tblDemo.row($(this).parents("tr")).data();
        var IdDocente = datosFila.Codigo;
        $("#txtCod").val(IdDocente);       
    })

    $("#btnSubmitSetPassword").click(function(){
        var formData = new FormData(document.getElementById("frmCredentialDocente"));
         formData.append("metodo","ctrlSetCredentialDocentes");

        $.ajax({
             url: "/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente",
             type: "POST",
             data: formData,
             cache: false,
            contentType: false,
             processData: false
         }).done(function(){
            
            $("#mdlSetCredentialDocente").modal('hide')
            tblDemo.ajax.url( '/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente?metodo=ctrlListarDocente').load();
            var alert = "<div class='alert alert-success' id='alertMsg' role='alert'><button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times</span><span class='sr-only'>Close</span></button>"+
                            "<strong>Exito!</strong> El evento se realizo satisfactoriamente ."+
                        "</div>";
            
            $("#alertMessageResponse").html(alert);

            window.setTimeout(function() {
                    $("#alertMsg").fadeTo(1000, 0).slideUp(1000, function(){
                      $("#alertMsg").alert('close');
                    });
            }, 3000);

        })
    })

});



        
    