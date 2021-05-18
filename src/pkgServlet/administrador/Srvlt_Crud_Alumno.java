package pkgServlet.administrador;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import pkgController.administrador.Ctrl_Crud_Alumno;

/**
 * Servlet implementation class CtrlAlumno
 */
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
@WebServlet(name = "Srvlt_Crud_Alumno" , urlPatterns = {"/Srvlt_Crud_Alumno"})
public class Srvlt_Crud_Alumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Srvlt_Crud_Alumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String paramDraw = request.getParameter("draw");
		PrintWriter out = response.getWriter();
		String metodo = request.getParameter("metodo");
		Object res = null;
		String tipoRespuesta = null;
		
		switch(metodo) {
			case "ctrlListarAlumno":
				tipoRespuesta = "application/json";
				res = Ctrl_Crud_Alumno.ctrlListarAlumno().toString();
			break;
			case "ctrlRegisterAlumno":
				tipoRespuesta = "text/html";
				res = Ctrl_Crud_Alumno.ctrlRegisterAlumno(request);
				break;
			case "ctrlUpdateAlumno":
				tipoRespuesta = "text/html";
				res = Ctrl_Crud_Alumno.ctrlUpdateAlumno(request);
			break;
			case "ctrlSetCredentialAlumno":
				tipoRespuesta = "text/html";
				res = Ctrl_Crud_Alumno.ctrlSetCredentialAlumno(request);
			break;
			case "ctrlSetAccesoUsuario":
				tipoRespuesta = "text/html";
				res = Ctrl_Crud_Alumno.ctrlSetAccesoUsuario(request);
			break;
		}
		
		response.setContentType(tipoRespuesta);
		out.print(res);
		
	}
}