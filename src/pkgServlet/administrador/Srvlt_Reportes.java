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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.sf.jasperreports.engine.JRException;
import pkgController.administrador.Ctrl_Reportes;
import pkgDao.administrador.Dao_Reportes;
import pkgModel.MdlAlumno;

@MultipartConfig(maxFileSize = 16177215)  
@WebServlet(name = "Srvlt_Reportes" , urlPatterns = {"/Srvlt_Reportes"})
public class Srvlt_Reportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Srvlt_Reportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fechaInicio = request.getParameter("fechaMin"); 
		String fechaFin = request.getParameter("fechaMax"); 
		String reporte = request.getParameter("report"); 
		String chart = request.getParameter("chart"); 
		if(chart.equals("1") || chart.equals(1)) {
			if(reporte.equals("xlsx")) {
				Ctrl_Reportes demo = new Ctrl_Reportes();
				response.setContentType("application/xlsx");
				response.setHeader("Content-Disposition", "attachment; filename=Reporte01.xlsx");
				demo.ctrlGenerarReporteExcel(response,fechaInicio,fechaFin);
			}else {
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=Reporte01.pdf");
			}
		}
		
		if(chart.equals("2") || chart.equals(2)) {
			if(reporte.equals("xlsx")) {
				Ctrl_Reportes demo = new Ctrl_Reportes();
				response.setContentType("application/xlsx");
				response.setHeader("Content-Disposition", "attachment; filename=Reporte02.xlsx");
				demo.ctrlGenerarReporteExcel02(response,fechaInicio,fechaFin);
			}else {
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition",  "inline; filename=reporte.pdf");
				Ctrl_Reportes controller = new Ctrl_Reportes();
				try {
					controller.ctrlGenerarReportePdf(response);
				} catch (JRException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(chart.equals("3") || chart.equals(3)) {
			if(reporte.equals("xlsx")) {
				Ctrl_Reportes demo = new Ctrl_Reportes();
				response.setContentType("application/xlsx");
				response.setHeader("Content-Disposition", "attachment; filename=Reporte03.xlsx");
				demo.ctrlGenerarReporteExcel03(response,fechaInicio,fechaFin);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Dao_Reportes doa = new Dao_Reportes();
		
		String chart = request.getParameter("chart"); 
		String fechaInicio = request.getParameter("fechaMin"); 
		String fechaFin = request.getParameter("fechaMax"); 
		JsonObject gson = new JsonObject();
		int parseChart = Integer.parseInt(chart);
		
		if(parseChart == 1) {
			
			ArrayList<MdlAlumno> demoenteros = doa.demo(fechaInicio,fechaFin);
			JsonArray data = new JsonArray();
			JsonArray data2 = new JsonArray();
			
			for(MdlAlumno dato : demoenteros) {
				data.add(Integer.parseInt(dato.getCodAlumno()));
				data2.add(dato.getNivel());
			}

			gson.add("datos", data);
			gson.add("info", data2);
			
		}
		
		if(parseChart == 2) {
			
			ArrayList<MdlAlumno> demoenteros = doa.demo2(fechaInicio,fechaFin);
			JsonArray data = new JsonArray();
			JsonArray data2 = new JsonArray();
			
			for(MdlAlumno dato : demoenteros) {
				data.add(Integer.parseInt(dato.getCodAlumno()));
				data2.add(dato.getNivel());
			}

			gson.add("datos", data);
			gson.add("info", data2);
			
		}
		
		if(parseChart == 3) {
			ArrayList<MdlAlumno> arrlabels = doa.labels(fechaInicio,fechaFin);
			ArrayList<MdlAlumno> data01 = doa.dataset1(fechaInicio,fechaFin);
			ArrayList<MdlAlumno> data02 = doa.dataset2(fechaInicio,fechaFin);
			JsonArray labels = new JsonArray();
			JsonArray data2 = new JsonArray();
			JsonArray data3 = new JsonArray();
			
			for(MdlAlumno dato1 : arrlabels) {		
				labels.add(dato1.getNivel()); // labels
			}
			
			for(MdlAlumno dato2 : data01) {		
				data2.add(dato2.getCodAlumno()); // labels
			}
			
			for(MdlAlumno dato3 : data02) {		
				data3.add(dato3.getSeccion()); // labels
			}

			gson.add("infox", labels); //label
			gson.add("datosx1", data2);
			gson.add("datosx2", data3);
		}
		response.setContentType("application/json");
		out.print(gson);
		
	}
}
