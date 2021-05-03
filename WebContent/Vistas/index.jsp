<%@ page import="pkgModel.MdlUsuario" %>
<%@ include file="includes/validarSesion.jsp" %>

<%
MdlUsuario objUsuario = (MdlUsuario) mySesion.getAttribute("MdlUsuario");
if(objUsuario.getTipoUsuarioString().equals("Administrador")){
  response.sendRedirect("/ProyectoIntegrador2/Vistas/Administrador/index.jsp");
}else{
  response.sendRedirect("/ProyectoIntegrador2/Vistas/Estudiante/index.jsp");
}

%> 