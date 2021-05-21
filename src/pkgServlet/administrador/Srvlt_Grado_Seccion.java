package pkgServlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkgController.administrador.Ctrl_Grado_Seccion;

/**
 * Servlet implementation class Srvlt_Crud_Curso
 */
@MultipartConfig(maxFileSize = 16177215)  
@WebServlet(name = "Srvlt_Grado_Seccion" , urlPatterns = {"/Srvlt_Grado_Seccion"})
public class Srvlt_Grado_Seccion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Srvlt_Grado_Seccion() {
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
			case "ctrlListarGradoSeccion":
				tipoRespuesta = "application/json";
				res = Ctrl_Grado_Seccion.ctrlListarGradoSeccion().toString();
			break;
			case "ctrlRegisterCurso":
				tipoRespuesta = "text/html";
				//res = Ctrl_Crud_Curso.ctrlRegisterCurso(request);
			break;
			case "ctrlUpdateCurso":
				tipoRespuesta = "text/html";
				//res = Ctrl_Crud_Curso.ctrlUpdateCurso(request);
			break;
		}
		
		response.setContentType(tipoRespuesta);
		out.print(res);
	}
}
