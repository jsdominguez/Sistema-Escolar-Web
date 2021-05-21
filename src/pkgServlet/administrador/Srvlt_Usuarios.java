package pkgServlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkgController.administrador.Ctrl_Usuario;

/**
 * Servlet implementation class Srvlt_Usuarios
 */
@MultipartConfig(maxFileSize = 16177215)  
@WebServlet(name = "Srvlt_Usuarios" , urlPatterns = {"/Srvlt_Usuarios"})
public class Srvlt_Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Srvlt_Usuarios() {
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
			case "ctrlListarUsuarios":
				tipoRespuesta = "application/json";
				res = Ctrl_Usuario.ctrlListarUsuarios().toString();
			break;
			case "ctrlSetCredentialUsuario":
				tipoRespuesta = "text/html";
				res = Ctrl_Usuario.ctrlSetCredentialUsuario(request);
			break;
			case "ctrlSetAccesoUsuario":
				tipoRespuesta = "text/html";
				res = Ctrl_Usuario.ctrlSetAccesoUsuario(request);
			break;
		}
		
		response.setContentType(tipoRespuesta);
		out.print(res);
	}

}
