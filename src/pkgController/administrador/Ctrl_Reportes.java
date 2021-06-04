package pkgController.administrador;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import pkgDao.administrador.Dao_Reportes;

public class Ctrl_Reportes {

	public void ctrlGenerarReporteExcel(HttpServletResponse response,String fechaInicio,String fechaFin) {
		Dao_Reportes objDaoReportes = new Dao_Reportes();
		objDaoReportes.opGenerarReporteExcel(objDaoReportes.daoGetListaAlumno(fechaInicio,fechaFin),response);
	}
	
	public void ctrlGenerarReporteExcel02(HttpServletResponse response,String fechaInicio,String fechaFin) {
		Dao_Reportes objDaoReportes = new Dao_Reportes();
		objDaoReportes.opGenerarReporteExcel02(objDaoReportes.daoGetListaAlumno02(fechaInicio,fechaFin),response);
	}
	
	public void ctrlGenerarReporteExcel03(HttpServletResponse response,String fechaInicio,String fechaFin) {
		Dao_Reportes objDaoReportes = new Dao_Reportes();
		objDaoReportes.opGenerarReporteExcel03(objDaoReportes.daoGetListaAlumno03(fechaInicio,fechaFin),response);
	}
	
	public void ctrlGenerarReportePdf(HttpServletResponse response) throws JRException, IOException {
		Dao_Reportes objDaoReportes = new Dao_Reportes();
		objDaoReportes.opGenerarReportePDF(response);
	}
}
