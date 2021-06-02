$(function() {


    var tblUsuarios = $('#tblUsuarios').DataTable({
    "filter": true,
    	"searching": true,
    "responsive" : true,
    //"processing": true,
    /*"serverSide": true,*/
    "ajax": {      
          url: 		  "/ProyectoIntegrador2/Srvlt_Usuarios",
          type: 		"POST",
    	  dataSrc: 	"datos",
          data: {
              		  "metodo": "ctrlListarUsuarios"
    }
    },
    columns: [
    {"data": "codigo"},
    {"data": "nombre"},
    {"data": "apellido"},
    {"data": "tipousuario"},
    {"data": "accesoSistema"},
    {"data": "acciones"}
    ],
    columnDefs: [{
        //"targets": [5,6,7], //oculta columna pero estara disponible para su seleccion
        //"visible": false,
        //"searchable": false
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

    $("#tblUsuarios tbody").on( 'click', '#btnShowModalCredentials', function () {

        document.getElementById("frmCredentialUsuario").reset();
        var datosFila = tblUsuarios.row($(this).parents("tr")).data();
        var idUsuario = datosFila.codigo;
        $("#txtCod").val(idUsuario);  
  
    })

    $("#btnSubmitSetPassword").click(function(){

        var formData = new FormData(document.getElementById("frmCredentialUsuario"));
        formData.append("metodo","ctrlSetCredentialUsuario");
        fnEnviarPeticion(formData);
        $('#mdlSetCredentialUsuario').modal('hide');

    })

    $("#tblUsuarios tbody").on( 'click', '#btnSwitchAccess', function () {

        var datosFila = tblUsuarios.row($(this).parents("tr")).data();
        var codigoDocente = datosFila.codigo;
        var valorEstado = $(this).attr("value");
        var formData = new FormData();        

        (valorEstado == 1) ? valorEstado = 0 : valorEstado = 1;

        $(this).val(valorEstado);

        valorEstado = $(this).attr("value");


        formData.append("valorEstado",  valorEstado );
        formData.append("codigo", codigoDocente );
        formData.append("metodo", "ctrlSetAccesoUsuario");

        fnEnviarPeticion(formData);

    })

    function fnEnviarPeticion(formData){

        $.ajax({

                url: "/ProyectoIntegrador2/Srvlt_Usuarios",
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
            $('#tblUsuarios').waitMe('hide');

        });

    }

    function fnWaitResponse(){

    //https://www.jqueryscript.net/loading/jQuery-Plugin-For-Creating-Loading-Overlay-with-CSS3-Animations-waitMe.html
      $('#tblUsuarios').waitMe({

          effect : 'roundBounce',
          text   : '',
          bg       : 'rgba(255,255,255,0.7)',
          color  : '#000'

      });

    }

    function fnShowResponse(response){
        
      $('#mdlSetCredentialUsuario').modal('hide')    
      
      tblUsuarios.ajax.url( '/ProyectoIntegrador2/Srvlt_Usuarios?metodo=ctrlListarUsuarios').load();
      
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

    $('#mdlSetCredentialUsuario').modal( fnOptions() );

    }

    function fnOptions(){

    return {
        keyboard: false,
        backdrop : 'static',
        show : false
        }

    }
    
}); //fin jquery