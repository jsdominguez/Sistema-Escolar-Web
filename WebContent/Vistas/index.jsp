<%@ include file="includes/validarSesion.jsp" %>
<%@ page import="pkgModel.MdlUsuario" %>
<%

if(mySesion.getAttribute("MdlUsuario")!=null){
	
	MdlUsuario objUsuario = (MdlUsuario) mySesion.getAttribute("MdlUsuario");
	String tipoUsuario = objUsuario.getTipoUsuarioString();
	switch(tipoUsuario){
		case "Administrador":
		 response.sendRedirect("/ProyectoIntegrador2/Vistas/Administrador/index.jsp");
		break;
		case "Docente":
		 response.sendRedirect("/ProyectoIntegrador2/Vistas/Docente/index.jsp");
		break;
		case "Estudiante":
		 response.sendRedirect("/ProyectoIntegrador2/Vistas/Estudiante/index.jsp");
		break;
	}
}

%> 