<%
	//argumento false es para evitar crear una session si en caso no existe una
	HttpSession mySesion = request.getSession(false);

	if(mySesion.getAttribute("MdlAlumno") == null) {
		response.sendRedirect("../index.html");
	}
%>