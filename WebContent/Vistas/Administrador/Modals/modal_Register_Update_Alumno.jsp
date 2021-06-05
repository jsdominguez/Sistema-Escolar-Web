<div class="modal fade" id="modalRegisterOrUpdate" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="defModalHead">Informacion Alumno</h4>
         </div>
         <div class="modal-body">
            
            <form class="form-horizontal" enctype="multipart/form-data" name="frmModalRegistroAlumno" id="frmModalRegistroAlumno">
               
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
                  <label class="col-md-3 col-xs-12 control-label">Nombre</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="txtNombre" id="txtNombre" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Apellido</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="txtApellido" id="txtApellido" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Edad</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="txtEdad" id="txtEdad" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Dni</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="text" required="true" name="txtDni" id="txtDni" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">F.Nacimiento</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="date" required="true" name="txtFechaNac" id="txtFechaNac" class="form-control"/>
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
                                    

               <div class="form-group">
                  <label class="col-md-3 col-xs-6 control-label">Grado / Seccion</label>
                  <div class="col-md-3 col-xs-6"> 
                     <select name="selectGrado" id="selectGrado" class="form-control combos selectpicker" title="Seleccione">
                           <option value="1">1</option>
                           <option value="2">2</option>
                           <option value="3">3</option>
                           <option value="4">4</option>
                           <option value="5">5</option>
                       </select>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
                  <div class="col-md-3 col-xs-6">
                     <select name="selecSeccion" id="selecSeccion" class="form-control combos selectpicker" title="Seleccione">
                           <option value="A">A</option>
                           <option value="B">B</option>
                           <option value="C">C</option>
                           <option value="D">D</option>
                           <option value="E">E</option>
                       </select>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

               <div class="form-group">
                  <label class="col-md-3 col-xs-12 control-label">Foto</label>
                  <div class="col-md-6 col-xs-12">
                     <div class="input-group">
                        <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                        <input type="file" required="true"  name="fileImagen" id="fileImagen" class="form-control"/>
                     </div>
                     <span class="help-block hidden">*Campo Requerido</span>
                  </div>
               </div>

            </form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            <button type="button" id="btnSubmitFrmRegistro" class="btn btn-primary pull-right">Confirmar</button>
         </div>
      </div>
   </div>
</div>
