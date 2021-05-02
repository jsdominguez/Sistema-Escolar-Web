
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
               (result == 0) ? showErrorLogin() : location.href="Vistas/index.jsp";
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
    
    function showErrorLogin(){
        var msgLoginIncorrecto = "<div class='alert alert-danger' role='alert'>"+
                                    "<button type='button' class='close' data-dismiss='alert'>"+
                                        "<span aria-hidden='true'>&times</span><span class='sr-only'>Close</span>"+
                                    "</button><strong>Error!</strong> Datos incorrectos"+
                                 "</div>";

        $("#divMsgLoginIncorrecto").html(msgLoginIncorrecto);
    }

})(jQuery);