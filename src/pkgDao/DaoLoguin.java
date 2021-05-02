package pkgDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pkgConexion.Conexion;
import pkgModel.MdlAlumno;

public class DaoLoguin {
	
	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	public boolean DaoValidarLoguin(String usuario,String pass) {
		
		this.cn = new Conexion().conectar();
		boolean loguinSucces = false;
		
		try {
			
			this.sql = "select * from alumno where nombre = ? and apellido = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,usuario);
			consultaPreparada.setString(2,pass);
			resultadoDatos = consultaPreparada.executeQuery();
			
			if(resultadoDatos.last()) {
				loguinSucces = true;
			}else {
				System.out.println("No se encontro ningun resultado");
			}
			
		}catch(Exception e) {
			System.out.println("[X] DAO 'DaoLoguin' FALLIDA [X]");
			e.printStackTrace();
		}finally {
			this.cn = null;
		}
		return loguinSucces;
	}
	
	public MdlAlumno DaoObtenerDatosLoguin(String usuario,String pass) {
		
		this.cn = new Conexion().conectar();
		MdlAlumno objAlumno = null;
		
		try {
			this.sql = "select nombre,apellido,tipo_usuario_string from alumno where nombre = ? and apellido = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,usuario);
			consultaPreparada.setString(2,pass);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				objAlumno = new MdlAlumno();
				objAlumno.setNombre(resultadoDatos.getString("nombre"));
				objAlumno.setApellido(resultadoDatos.getString("apellido"));
				objAlumno.setTipo_usuario_string(resultadoDatos.getString("tipo_usuario_string"));
			}
		}catch(Exception e) {
			System.out.println("[X] DAO 'DaoObtenerDatosLoguin' FALLIDA [X]");
			e.printStackTrace();
		}finally {
			this.cn = null;
		}
		return objAlumno;
	}
}
