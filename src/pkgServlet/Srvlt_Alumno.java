package pkgServlet;

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

import pkgController.CtrlAlumno;

/**
 * Servlet implementation class CtrlAlumno
 */
@MultipartConfig
@WebServlet(name = "Srvlt_Alumno" , urlPatterns = {"/Srvlt_Alumno"})
public class Srvlt_Alumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Srvlt_Alumno() {
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
				res = CtrlAlumno.ctrlListarAlumno().toString();
			break;
			case "ctrlRegisterAlumno":
				tipoRespuesta = "text/html";
				res = CtrlAlumno.ctrlRegisterAlumno(request);
				break;
			case "ctrlUpdateAlumno":
				tipoRespuesta = "text/html";
				String paramIdAlumno = request.getParameter("hiddenIdAlumno");
				res = CtrlAlumno.ctrlUpdateAlumno(request,paramIdAlumno);
			break;
			case "ctrlEliminarAlumno":
				tipoRespuesta = "text/html";
				String idAlumno = request.getParameter("idAlumno");
				res = CtrlAlumno.ctrlEliminarAlumno(idAlumno);
			break;
			
		}
		
		response.setContentType(tipoRespuesta);
		out.print(res);
	}

}
