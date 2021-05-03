<%
	HttpSession sesion = request.getSession();
    sesion.invalidate();
   	response.sendRedirect("/ProyectoIntegrador2/index.html");
%>