package pkgServlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import pkgController.administrador.Ctrl_Admin_Crud_Docente;

/**
 * Servlet implementation class CtrlAlumno
 */
@MultipartConfig
@WebServlet(name = "Srvlt_Admin_Crud_Docente" , urlPatterns = {"/Srvlt_Admin_Crud_Docente"})
public class Srvlt_Admin_Crud_Docente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Srvlt_Admin_Crud_Docente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
				res = Ctrl_Admin_Crud_Docente.ctrlListarAlumno().toString();
			break;
			case "ctrlRegisterAlumno":
				tipoRespuesta = "text/html";
				res = Ctrl_Admin_Crud_Docente.ctrlRegisterAlumno(request);
				break;
			case "ctrlUpdateAlumno":
				tipoRespuesta = "text/html";
				String paramIdAlumno = request.getParameter("hiddenIdAlumno");
				res = Ctrl_Admin_Crud_Docente.ctrlUpdateAlumno(request,paramIdAlumno);
			break;
			case "ctrlEliminarAlumno":
				tipoRespuesta = "text/html";
				String idAlumno = request.getParameter("idAlumno");
				res = Ctrl_Admin_Crud_Docente.ctrlEliminarAlumno(idAlumno);
			break;
			
		}
		
		response.setContentType(tipoRespuesta);
		out.print(res);
	}

}
