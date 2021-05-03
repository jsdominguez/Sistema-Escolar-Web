package pkgServlet;

import java.io.IOException;
import java.io.PrintWriter;
import pkgController.CtrlLoguin;
import pkgModel.MdlAlumno;
import pkgModel.MdlUsuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkgBeans.LoginUser;

/**
 * Servlet implementation class Srvlt_Login
 */
@WebServlet(name = "Srvlt_Login" , urlPatterns = {"/Srvlt_Login"})
public class Srvlt_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Srvlt_Login() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String txtUser = request.getParameter("txtUser");
		String txtPass = request.getParameter("txtPass");
		
		boolean loguinSucces = CtrlLoguin.CtrlValidarUsuario(txtUser,txtPass);
		
		if(loguinSucces) {
			//crea y obtiene la session actual
			HttpSession sesion = request.getSession();
			if(sesion.getAttribute("MdlUsuario") == null) {
				MdlUsuario objUsuario =  CtrlLoguin.CtrlObtenerDatosLoguin(txtUser,txtPass);
				sesion.setAttribute("MdlUsuario",objUsuario);
			}
			//loguin ok
			out.print(1);
		}else {
			//loguin bad
			out.print(0);
		}
	}

}
