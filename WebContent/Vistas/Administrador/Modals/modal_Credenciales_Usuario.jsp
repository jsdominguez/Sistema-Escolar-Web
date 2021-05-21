<div class="modal fade" id="mdlSetCredentialUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="defModalHead">Credenciales Usuario</h4>
         </div>
         <div class="modal-body">
            
            <form role="form" class="form-horizontal" enctype="multipart/form-data" name="frmCredentialUsuario" id="frmCredentialUsuario">
               
               <input type="hidden" id="tipousuario" name="tipousuario">

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Codigo</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" readonly name="txtCod" id="txtCod" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Password</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="txtPass" id="txtPass" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

            </form>
         </div>
         <div id="msgInfoCredential">
            
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            <button type="button" id="btnSubmitSetPassword" class="btn btn-primary pull-right">Confirmar</button>
         </div>
      </div>
   </div>
</div>

