<li class="xn-profile">
    <a href="#" class="profile-mini">
 		<img src="/ProyectoIntegrador2/Srvlt_Obtener_Archivo?codigo=${MdlUsuario.codigo}" 
 			onerror="this.src='/ProyectoIntegrador2/Vistas/Resources/dashboard/assets/images/users/no-image.jpg'" />
    </a>
    <div class="profile">
        <div class="profile-image">
             		<img src="/ProyectoIntegrador2/Srvlt_Obtener_Archivo?codigo=${MdlUsuario.codigo}" 
 			onerror="this.src='/ProyectoIntegrador2/Vistas/Resources/dashboard/assets/images/users/no-image.jpg'" />
        </div>
        <div class="profile-data">
            <div class="profile-data-name">${MdlUsuario.apellidoUsuario}</div>
            <div class="profile-data-title">${MdlUsuario.tipoUsuarioString}</div>
        </div>
        <div class="profile-controls">
            <a href="pages-profile.html" class="profile-control-left"><span class="fa fa-info"></span></a>
            <a href="pages-messages.html" class="profile-control-right"><span class="fa fa-envelope"></span></a>
        </div>
    </div>                                                                        
</li>