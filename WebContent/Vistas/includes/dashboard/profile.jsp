<li class="xn-profile">
    <a href="#" class="profile-mini">
        <img src="${pageContext.request.contextPath}/Vistas/Resources/dashboard/assets/images/users/avatar.jpg" alt="John Doe"/>
    </a>
    <div class="profile">
        <div class="profile-image">
            <img src="${pageContext.request.contextPath}/Vistas/Resources/dashboard/assets/images/users/avatar.jpg" alt="John Doe"/>
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