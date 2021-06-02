package pkgDao.administrador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import pkgConexion.Conexion;

public class Dao_Reportes {

	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	public ResultSet daoGetListaAlumno() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		//ArrayList<MdlAlumno> arrAlumno = new ArrayList<MdlAlumno>();
		//MdlAlumno objAlumno;
		
		try {
			
			this.sql = "select idAlumno,nomAlumno,apeAlumno,edadAlumno from alumnos";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
					
			//this.cn.close();
			
		}catch(SQLException e){
			
			System.out.println("[X] DAO 'Alumno.getListaAlumnos' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			//objConectar.desconectar();
			//this.cn = null;
		}
		
		return resultadoDatos;
	}
	
public void opGenerarReporteExcel(ResultSet resultadoDatos,HttpServletResponse response) {
		
	
		try {
			
			//HSSFWorkbook = crea archivos excel antiguos (xls)
			//XSSFWorkbook = crea archivos excel 2007 en adelante (xlsx)
			XSSFWorkbook book = new XSSFWorkbook();
			XSSFSheet hoja1 = book.createSheet("Hoja1");
			XSSFSheet hoja2 = book.createSheet("Hoja2");
			
			//crea el archivo
			File excel = new File("C:/temp/Demo.xlsx");
			excel.createNewFile();
			//establece el vinculo para poder escribir
			FileOutputStream vinculo = new FileOutputStream(excel);
			
			ArrayList<String> headers = new ArrayList<String>();
			headers.add("ID");
			headers.add("NOMBRE");
			headers.add("ALUMNO");
			headers.add("APELLIDO");
			
			int posicionFila = 0;
			int posicionCelda = 0;
			
			XSSFRow fila,fila2;
			XSSFCell celda,celda2;
			
			fila = hoja1.createRow(posicionFila);//posicion de filas
			for(String cabezera : headers) {
				celda = fila.createCell(posicionCelda); //creacion de celdas
				celda.setCellValue(cabezera); //setea celda
				++posicionCelda;
			}
			
			if(resultadoDatos.next()) {
				resultadoDatos.beforeFirst();
				while(resultadoDatos.next()) {
					
					posicionCelda = 0;
					fila = hoja1.createRow(++posicionFila);//posicion de filas
					
					//crea celda 0
					celda = fila.createCell(posicionCelda); //setea celda
					celda.setCellValue(resultadoDatos.getString("idAlumno"));
					
					//crea celda 1
					celda = fila.createCell(++posicionCelda); 
					celda.setCellValue(resultadoDatos.getString("nomAlumno")); //setea celda
					
					//crea celda 2
					celda = fila.createCell(++posicionCelda);
					celda.setCellValue(resultadoDatos.getString("apeAlumno")); //setea celda
					
					//crea celda 3
					celda = fila.createCell(++posicionCelda);
					celda.setCellValue(resultadoDatos.getInt("edadAlumno")); //setea celda
				}
			}
				
			fila2 = hoja2.createRow(3);//posicion de filas
			celda2 = fila2.createCell(3); //setea celda
			celda2.setCellValue("Esto es un demo para la hoja 2");
			
			book.write(response.getOutputStream());
			book.close();
			vinculo.close();
			excel.delete();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void opGenerarReportePDF(HttpServletResponse response) throws JRException, IOException {
		Conexion objConectar = new Conexion();
		File jasperFile = new File("C:/Users/Jhosep/Desktop/Eclipse/ProyectoIntegrador2/src/Reportes/ReporteListaAlumnos.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile.getPath(),null,objConectar.conectar());
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);//export PDF directly
	}
}
