<div class="modal fade" id="mimodal" tabindex="-1" role="dialog" aria-labelledby="defModalHead" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="defModalHead">Editar Registro</h4>
         </div>
         <div class="modal-body">
            
            <form class="form-horizontal" enctype="multipart/form-data" name="frmModalRegistro" id="frmModalRegistro">
               
               <input type="hidden" id="opcRegisterorUpdate" name="opcRegisterorUpdate">
               <input type="hidden" id="hiddenIdAlumno" value="" name="hiddenIdAlumno">
               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Nombre</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="inputNombre" id="inputNombre" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Apellido</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true"="true"red name="inputApellido" id="inputApellido" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Edad</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="inputEdad" id="inputEdad" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>


            </form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" id="btnSubmitFrmRegistro" class="btn btn-primary pull-right">Confirmar</button>
         </div>
      </div>
   </div>
</div>

