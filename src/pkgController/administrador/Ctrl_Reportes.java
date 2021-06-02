package pkgController.administrador;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import pkgDao.administrador.Dao_Reportes;

public class Ctrl_Reportes {

	public void ctrlGenerarReporteExcel(HttpServletResponse response) {
		Dao_Reportes objDaoReportes = new Dao_Reportes();
		objDaoReportes.opGenerarReporteExcel(objDaoReportes.daoGetListaAlumno(),response);
	}
	
	public void ctrlGenerarReportePdf(HttpServletResponse response) throws JRException, IOException {
		Dao_Reportes objDaoReportes = new Dao_Reportes();
		objDaoReportes.opGenerarReportePDF(response);
	}
}
