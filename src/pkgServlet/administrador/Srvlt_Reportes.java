package pkgServlet.administrador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import pkgController.administrador.Ctrl_Reportes;


@WebServlet(name = "Srvlt_Reportes" , urlPatterns = {"/Srvlt_Reportes"})
public class Srvlt_Reportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Srvlt_Reportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String download = request.getParameter("download");
		
		if(download.equalsIgnoreCase("pdf")) {
			
			try {
				
				Ctrl_Reportes objCtrlReportes= new Ctrl_Reportes();
				response.setContentType("application/x-download");
				response.addHeader("Content-disposition", "attachment; filename=demo.pdf");
				objCtrlReportes.ctrlGenerarReportePdf(response);
			} catch (JRException e) { e.printStackTrace(); }
			  catch (IOException e) { e.printStackTrace(); }
			
		}
		
		if(download.equalsIgnoreCase("excel")){
			
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		    response.setHeader("Content-Disposition", "attachment; filename= demo.xlsx");
			Ctrl_Reportes objCtrlReportes= new Ctrl_Reportes();
			objCtrlReportes.ctrlGenerarReporteExcel(response);
			
		}

		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
