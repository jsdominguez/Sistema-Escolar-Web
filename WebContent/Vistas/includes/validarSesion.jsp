<%
	//argumento false es para evitar crear una session si en caso no existe una
	HttpSession mySesion = request.getSession(false);

	if(mySesion.getAttribute("MdlUsuario") == null) {
		response.sendRedirect("/ProyectoIntegrador2/index.html");
	}
%>