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
import java.util.HashMap;
import java.util.Map;

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
import pkgModel.MdlAlumno;
import pkgModel.MdlUsuario;

public class Dao_Reportes {

	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	
	public ArrayList<MdlAlumno> demo(String fechaInicio,String fechaFin) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		ArrayList<MdlAlumno> r = new ArrayList<>();
		MdlAlumno o = null;
		
		try {
			this.sql = "select year(userCreate) as anios,count(*) as cantidad from usuario u inner join alumnos a on u.nomUsuario=a.idAlumno where userCreate  between ? and ? group by year(userCreate) asc";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,fechaInicio);
			consultaPreparada.setString(2,fechaFin);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				o = new MdlAlumno();
				o.setCodAlumno(resultadoDatos.getString("cantidad"));
				o.setNivel(resultadoDatos.getString("anios"));
				r.add(o);
			}
		}catch(Exception e) {
			System.out.println("[X] DAO 'ESTADISTICA' FALLIDA [X]");
			e.printStackTrace();
			System.out.println(this.sql);
			
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		return r;
	}
	
public ArrayList<MdlAlumno> demo2(String fechaInicio,String fechaFin) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		ArrayList<MdlAlumno> r = new ArrayList<>();
		MdlAlumno o = null;
		
		try {
			this.sql = "select year(userCreate) as anios,count(*) as cantidad from usuario u inner join docente d on u.nomUsuario=d.codDocente where userCreate  between ? and ? group by year(userCreate) asc;";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,fechaInicio);
			consultaPreparada.setString(2,fechaFin);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				o = new MdlAlumno();
				o.setCodAlumno(resultadoDatos.getString("cantidad"));
				o.setNivel(resultadoDatos.getString("anios"));
				r.add(o);
			}
		}catch(Exception e) {
			System.out.println("[X] DAO 'ESTADISTICA' FALLIDA [X]");
			e.printStackTrace();
			System.out.println(this.sql);
			
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		return r;
	}

public ArrayList<MdlAlumno> labels(String fechaInicio,String fechaFin) {
	
	Conexion objConectar = new Conexion();
	this.cn = objConectar.conectar();
	ArrayList<MdlAlumno> r = new ArrayList<>();
	MdlAlumno o = null;
	
	try {
		this.sql = " select year(userCreate) as anios from usuario u inner join alumnos a on u.nomUsuario=a.idAlumno where userCreate  between ? and ? group by year(userCreate) order by year(userCreate);";
		consultaPreparada = this.cn.prepareStatement(this.sql);
		consultaPreparada.setString(1,fechaInicio);
		consultaPreparada.setString(2,fechaFin);
		resultadoDatos = consultaPreparada.executeQuery();
		
		while(resultadoDatos.next()) {
			o = new MdlAlumno();
			o.setNivel(resultadoDatos.getString("anios"));//labels
			r.add(o);
		}
	}catch(Exception e) {		
	}finally {
		objConectar.desconectar();
		this.cn = null;
	}
	return r;
}

public ArrayList<MdlAlumno> dataset1(String fechaInicio,String fechaFin) {
	
	Conexion objConectar = new Conexion();
	this.cn = objConectar.conectar();
	ArrayList<MdlAlumno> r = new ArrayList<>();
	MdlAlumno o = null;
	
	try {
		this.sql = "select count(*) as anios from usuario u inner join alumnos a on u.nomUsuario=a.idAlumno where userCreate  between ? and ? and nivel ='P' group by year(userCreate);";
		consultaPreparada = this.cn.prepareStatement(this.sql);
		consultaPreparada.setString(1,fechaInicio);
		consultaPreparada.setString(2,fechaFin);
		resultadoDatos = consultaPreparada.executeQuery();
		
		while(resultadoDatos.next()) {
			o = new MdlAlumno();
			o.setCodAlumno(resultadoDatos.getString("anios"));//datos01
			r.add(o);
		}
	}catch(Exception e) {		
	}finally {
		objConectar.desconectar();
		this.cn = null;
	}
	return r;
}

public ArrayList<MdlAlumno> dataset2(String fechaInicio,String fechaFin) {
	
	Conexion objConectar = new Conexion();
	this.cn = objConectar.conectar();
	ArrayList<MdlAlumno> r = new ArrayList<>();
	MdlAlumno o = null;
	
	try {
		this.sql = "select count(*) as anios from usuario u inner join alumnos a on u.nomUsuario=a.idAlumno where userCreate  between ? and ? and nivel ='S' group by year(userCreate);";
		consultaPreparada = this.cn.prepareStatement(this.sql);
		consultaPreparada.setString(1,fechaInicio);
		consultaPreparada.setString(2,fechaFin);
		resultadoDatos = consultaPreparada.executeQuery();
		
		while(resultadoDatos.next()) {
			o = new MdlAlumno();
			o.setSeccion(resultadoDatos.getString("anios"));//
			r.add(o);
		}
	}catch(Exception e) {		
	}finally {
		objConectar.desconectar();
		this.cn = null;
	}
	return r;
}


	public ResultSet daoGetListaAlumno(String fechaInicio,String fechaFin) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		//ArrayList<MdlAlumno> arrAlumno = new ArrayList<MdlAlumno>();
		//MdlAlumno objAlumno;
		
		try {
			
			this.sql = "select nomUsuario,nomAlumno,apeAlumno,year(userCreate) as anios from usuario u inner join alumnos a on u.nomUsuario=a.idAlumno where userCreate between ? and  ? order by year(userCreate) desc";
			consultaPreparada = this.cn.prepareCall(this.sql);
			consultaPreparada.setString(1,fechaInicio);
			consultaPreparada.setString(2,fechaFin);
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
	
public ResultSet daoGetListaAlumno02(String fechaInicio,String fechaFin) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		//ArrayList<MdlAlumno> arrAlumno = new ArrayList<MdlAlumno>();
		//MdlAlumno objAlumno;
		
		try {
			
			this.sql = "select nomUsuario,nomDocente,apeDocente, year(userCreate) as anios from usuario u inner join docente d on u.nomUsuario=d.codDocente where userCreate between ? and  ? order by year(userCreate) desc";
			consultaPreparada = this.cn.prepareCall(this.sql);
			consultaPreparada.setString(1,fechaInicio);
			consultaPreparada.setString(2,fechaFin);
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


public ResultSet daoGetListaAlumno03(String fechaInicio,String fechaFin) {
	
	Conexion objConectar = new Conexion();
	this.cn = objConectar.conectar();
	//ArrayList<MdlAlumno> arrAlumno = new ArrayList<MdlAlumno>();
	//MdlAlumno objAlumno;
	
	try {
		
		this.sql = "select idAlumno,nomAlumno,apeAlumno,nivel,year(userCreate) as anios from usuario u inner join alumnos a on u.nomUsuario=a.idAlumno where userCreate  between ? and ? order by year(userCreate) desc";
		consultaPreparada = this.cn.prepareCall(this.sql);
		consultaPreparada.setString(1,fechaInicio);
		consultaPreparada.setString(2,fechaFin);
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
			headers.add("Codigo");
			headers.add("Nombre");
			headers.add("Apellido");
			headers.add("Año");
			
			int posicionFila = 2;
			int posicionCelda = 2;
			
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
					
					posicionCelda = 2;
					fila = hoja1.createRow(++posicionFila);//posicion de filas
					
					//crea celda 0
					celda = fila.createCell(posicionCelda); //setea celda
					celda.setCellValue(resultadoDatos.getString("nomUsuario"));
					
					//crea celda 1
					celda = fila.createCell(++posicionCelda); 
					celda.setCellValue(resultadoDatos.getString("nomAlumno")); //setea celda
					
					//crea celda 2
					celda = fila.createCell(++posicionCelda);
					celda.setCellValue(resultadoDatos.getString("apeAlumno")); //setea celda
					
					//crea celda 3
					celda = fila.createCell(++posicionCelda);
					celda.setCellValue(resultadoDatos.getInt("anios")); //setea celda
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

public void opGenerarReporteExcel02(ResultSet resultadoDatos,HttpServletResponse response) {
	
	
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
		headers.add("Codigo");
		headers.add("Nombre");
		headers.add("Apellido");
		headers.add("Año");
		
		int posicionFila = 2;
		int posicionCelda = 2;
		
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
				
				posicionCelda = 2;
				fila = hoja1.createRow(++posicionFila);//posicion de filas
				
				//crea celda 0
				celda = fila.createCell(posicionCelda); //setea celda
				celda.setCellValue(resultadoDatos.getString("nomUsuario"));
				
				//crea celda 1
				celda = fila.createCell(++posicionCelda); 
				celda.setCellValue(resultadoDatos.getString("nomDocente")); //setea celda
				
				//crea celda 2
				celda = fila.createCell(++posicionCelda);
				celda.setCellValue(resultadoDatos.getString("apeDocente")); //setea celda
				
				//crea celda 3
				celda = fila.createCell(++posicionCelda);
				celda.setCellValue(resultadoDatos.getInt("anios")); //setea celda
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

public void opGenerarReporteExcel03(ResultSet resultadoDatos,HttpServletResponse response) {
	
	
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
		headers.add("Codigo");
		headers.add("Nombre");
		headers.add("Apellido");
		headers.add("Nivel");
		headers.add("Año");

		int posicionFila = 2;
		int posicionCelda = 2;
		
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
				
				posicionCelda = 2;
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
				celda.setCellValue(resultadoDatos.getString("nivel")); //setea celda
				
				//crea celda 3
				celda = fila.createCell(++posicionCelda);
				celda.setCellValue(resultadoDatos.getInt("anios")); //setea celda
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
	public void opGenerarReportePDF(HttpServletResponse response,int num,String fechaInicio,String fechaFin) throws JRException, IOException {
		
		Conexion objConectar = new Conexion();
		
		Map parametro = new HashMap();
		parametro.put("fechaInicio",fechaInicio);
		parametro.put("fechaFin",fechaFin);
		File jasperFile;
		
		if(num == 1){  jasperFile = new File("C:/Users/Jhosep/Desktop/Eclipse/ProyectoIntegrador2/src/Reportes/Reporte01.jasper"); }
		
		else if(num == 2){  jasperFile = new File("C:/Users/Jhosep/Desktop/Eclipse/ProyectoIntegrador2/src/Reportes/Reporte02.jasper"); }
		
		else{   jasperFile = new File("C:/Users/Jhosep/Desktop/Eclipse/ProyectoIntegrador2/src/Reportes/Reporte03.jasper"); }
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile.getPath(),parametro,objConectar.conectar());
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);//export PDF directly
	}
}
