<div class="modal fade" id="modalRegisterOrUpdateCursos" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="defModalHead">Informacion Curso</h4>
         </div>
         <div class="modal-body">
            
            <form class="form-horizontal" enctype="multipart/form-data" name="frmModalRegistroCurso" id="frmModalRegistroCurso">
               
               <input type="hidden" id="opcRegisterorUpdate" name="opcRegisterorUpdate">
               
               <div class="form-group" id="frmModalRegistroId">
                  <label class="col-md-3 col-xs-12 control-label">Codigo</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="txtCodigo" id="txtCodigo" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Curso</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="txtNombre" id="txtCurso" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                   <label class="col-md-3 control-label">Nivel</label>
                   <div class="col-md-3 col-xs-6">                
                        <select class="selectpicker combos form-control" name="selectNivel" id="selectNivel" title="Seleccione">
                           <option value="P">Primaria</option>
                           <option value="S">Secundaria</option>
                       </select>
                   </div>
               </div>

            </form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            <button type="button" id="btnSubmitFrmRegistro" class="btn btn-primary pull-right">Registrar</button>
         </div>
      </div>
   </div>
</div>

