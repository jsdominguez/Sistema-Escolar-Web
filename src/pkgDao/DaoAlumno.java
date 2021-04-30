package pkgDao;

import pkgConexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pkgModel.MdlAlumno;

public class DaoAlumno {
	
	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	public ArrayList<MdlAlumno> daoGetListaAlumnos() {
	
		this.cn = new Conexion().conectar();
		ArrayList<MdlAlumno> arrAlumno = new ArrayList<MdlAlumno>();
		MdlAlumno objAlumno;
		
		try {
			
			this.sql = "select id,nombre,apellido,edad from alumno order by id";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				objAlumno = new MdlAlumno();
				objAlumno.setIdAlumno(resultadoDatos.getInt("id"));
				objAlumno.setNombre(resultadoDatos.getString("nombre"));
				objAlumno.setApellido(resultadoDatos.getString("apellido"));
				objAlumno.setEdad(resultadoDatos.getInt("edad"));
				arrAlumno.add(objAlumno);
			}
			this.cn.close();
		}catch(SQLException e){
			System.out.println("[X] DAO 'Alumno.getListaAlumnos' FALLIDA [X]");
			e.printStackTrace();
		}finally {
			this.cn = null;
		}
		
		return arrAlumno;
	}
	
	public int daoGetCountListAlumno() {
		
		this.cn = new Conexion().conectar();
		int cantidadRegistros=0;
		
		try {
			this.sql = "select id from alumno order by id";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			resultadoDatos.last();
			cantidadRegistros = resultadoDatos.getInt(1);
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Alumno.countListAlumno' FALLIDA [X]");
			e.printStackTrace();
		}finally {
			this.cn = null;
		}
		
		return cantidadRegistros;
	}
	
	
	public int daoRegistrarAlumno(MdlAlumno objAlumno) {
		
		int idAlumno = this.daoGetCountListAlumno();
		idAlumno +=1;
		this.cn = new Conexion().conectar();
		
		try {
			
			this.sql = "insert into alumno(id,nombre,apellido,edad) values(?,?,?,?);";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setInt(1,idAlumno);
			consultaPreparada.setString(2,objAlumno.getNombre());
			consultaPreparada.setString(3,objAlumno.getApellido());
			consultaPreparada.setInt(4,objAlumno.getEdad());
			
			consultaPreparada.execute();
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Alumno.registrarAlumno' FALLIDA [X]");
			e.printStackTrace();
			return 500;
		}finally {
			this.cn = null;
		}
		
		return 200;
	}
	
	public int daoUpdateAlumno(MdlAlumno objAlumno,int paramIdAlumno) {
		
		this.cn = new Conexion().conectar();
		
		try {
			
			this.sql = "update alumno set nombre = ?,apellido = ?,edad = ? where id = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getNombre());
			consultaPreparada.setString(2,objAlumno.getApellido());
			consultaPreparada.setInt(3,objAlumno.getEdad());
			consultaPreparada.setInt(4,objAlumno.getIdAlumno());
			
			consultaPreparada.execute();
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Alumno.daoUpdateAlumno' FALLIDA [X]");
			e.printStackTrace();
			return 500;
		}finally {
			this.cn = null;
		}
		
		return 200;
	}
	
	public int daoEliminarAlumno(int idAlumno) {
		
		this.cn = new Conexion().conectar();
		
		try {
			
			this.sql = "delete from alumno where id = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setInt(1,idAlumno);
			consultaPreparada.execute();
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Alumno.daoEliminarAlumno' FALLIDA [X]");
			e.printStackTrace();
			return 500;
		}finally {
			this.cn = null;
		}
		return 200;
	}
}
