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
                "metodo": "ctrlListarAlumno"
            }
        },
		columns: [
            {"data": "id"},
			{"data": "nombre"},
			{"data": "apellido"},
			{"data": "edad"},
            {"data": "campo1"},
            {"data": "campo2"}
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
        $("#opcRegisterorUpdate").val(1);
        $("#hiddenIdAlumno").val("")
        $("#inputNombre").val('');
        $("#inputApellido").val('');
        $("#inputEdad").val('');
    });

    $('#mimodal').modal({
        keyboard: false,
        backdrop : 'static',
        show : false
     });

   $('#tblDemo tbody').on( 'click', '#btnShowModalEdit', function () {
        var datosFila = tblDemo.row($(this).parents("tr")).data();
        var hiddenIdAlumno = datosFila.id;
        var inputNombre = datosFila.nombre;
        var inputApellido = datosFila.apellido;
        var inputEdad = datosFila.edad;

        $("#opcRegisterorUpdate").val(2);
        $("#hiddenIdAlumno").val(hiddenIdAlumno);
        $("#inputNombre").val(inputNombre);
        $("#inputApellido").val(inputApellido);
        $("#inputEdad").val(inputEdad);

    } );

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
            tblDemo.ajax.url( '/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente?metodo=ctrlListarAlumno').load();
             console.log(response);
         });
        
    });

    $("#btnSubmitFrmRegistro").click(function(){
        var formData = new FormData(document.getElementById("frmModalRegistro"));
        var hiddenIdAlumno = $("#hiddenIdAlumno").val();
        var opcRegisterorUpdate = $("#opcRegisterorUpdate").val();

        if(opcRegisterorUpdate == 1){
             formData.append("metodo","ctrlRegisterAlumno");
        }

        if(opcRegisterorUpdate == 2){
            formData.append("metodo","ctrlUpdateAlumno");
            formData.append("hiddenIdAlumno", hiddenIdAlumno);
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
            tblDemo.ajax.url( '/ProyectoIntegrador2/Srvlt_Admin_Crud_Docente?metodo=ctrlListarAlumno').load();
            if(response == 200){
            	var alert = "<div class='alert alert-success' role='alert'><button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times</span><span class='sr-only'>Close</span></button>"+
            				"<strong>Well done!</strong> You successfully read this important alert message.</div>";
            	$("#alertMessageResponse").html(alert);
            }
         });
    })


});