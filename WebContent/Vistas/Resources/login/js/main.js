
(function ($) {
    "use strict";

    /*==================================================================
    [ Focus Contact2 ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })

    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(event){
        event.preventDefault()
        var check = true;
        var formCampos = []; 

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
            formCampos[i] = input[i].value;
        }

        if(check){
            var formUsuario = formCampos[0];
            var formPass = formCampos[1];

            $.ajax({
                url: 'Srvlt_Login',
                type: 'POST',
                data: {
                    "txtUser" : formUsuario,
                    "txtPass" : formPass
                }
            })
            .done(function(result) {
                (result == 0 || result == 1) ? showInformation(result) : location.href="Vistas/index.jsp";
            });
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    function showInformation(result){

        var msgLoginIncorrecto = "";
        var tipoMsg=""; 
        var msg ="";

        if(result == 1){
            tipoMsg = "info";
            msg = "<strong>Info!</strong> Su usuario aun no esta habilitado para acceder al sistema";
        }else{
            tipoMsg = "danger";
             msg ="<strong>Error!</strong> Datos incorrectos";
        }
        
        msgLoginIncorrecto = "<div class='alert alert-"+tipoMsg+"' id='alertMsgLoguin' role='alert'>"+
                                    "<button type='button' class='close' data-dismiss='alert'>"+
                                        "<span aria-hidden='true'>&times</span><span class='sr-only'>Close</span>"+
                                    "</button>"+msg+
                                 "</div>";


        $("#divMsgLoginIncorrecto").html(msgLoginIncorrecto);

        window.setTimeout(function() {
            $("#alertMsgLoguin").fadeTo(1000, 0).slideUp(1000, function(){
                $("#alertMsgLoguin").alert('close');
            });
        }, 3000);
    }

})(jQuery);