package pkgServlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pkgDao.DaoUsuario;

/**
 * Servlet implementation class Srvlt_Login
 */
@WebServlet(name = "Srvlt_Obtener_Archivo" , urlPatterns = {"/Srvlt_Obtener_Archivo"})
public class Srvlt_Obtener_Archivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Srvlt_Obtener_Archivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		byte[] datosIMAGEN = null;
		try {
			response.setContentType("image/*");
			DaoUsuario objDaoUsuario = new DaoUsuario();
			byte[] b = objDaoUsuario.obtenerImagenPerfil(request.getParameter("codigo"));
			InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            datosIMAGEN = new byte[tamanoInput];
            bos.read(datosIMAGEN, 0, tamanoInput);

            response.getOutputStream().write(datosIMAGEN);
            bos.close();
		}catch(Exception e) {
			response = null;
			//e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
