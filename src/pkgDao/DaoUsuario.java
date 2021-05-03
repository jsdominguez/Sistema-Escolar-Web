package pkgDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pkgConexion.Conexion;
import pkgModel.MdlUsuario;

public class DaoUsuario {
	
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	Connection cn;
	
	public boolean DaoValidarLoguin(String usuario,String pass) {
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		boolean loguinSucces = false;
		
		try {
			
			this.sql = "select * from usuario where nomUsuario = ? and passUsuario = ?";
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
			objConectar.desconectar();
			this.cn = null;
		}
		return loguinSucces;
	}
	
	public MdlUsuario DaoObtenerDatosLoguin(String usuario,String pass) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		MdlUsuario objUsuario = null;
		
		try {
			this.sql = "select inf.nombreUsuario,inf.apellidoUsuario,tuser.tipoUsuarioString,tuser.idTipoUsuario from infoUsuario inf" + 
					   " inner join usuario u" + 
					   " on inf.idInfUsuario=u.idInfoUsuario" + 
					   " inner join tipoUsuario tuser" + 
					   " on u.idTipoUsuario=tuser.idTipoUsuario" + 
					   " where u.nomUsuario = ? and u.passUsuario = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,usuario);
			consultaPreparada.setString(2,pass);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				objUsuario = new MdlUsuario();
				objUsuario.setNombreUsuario(resultadoDatos.getString("nombreUsuario"));
				objUsuario.setApellidoUsuario(resultadoDatos.getString("apellidoUsuario"));
				objUsuario.setTipoUsuarioString(resultadoDatos.getString("tipoUsuarioString"));
				objUsuario.setIdTipoUsuario(resultadoDatos.getInt("idTipoUsuario"));
			}
		}catch(Exception e) {
			System.out.println("[X] DAO 'DaoObtenerDatosLoguin' FALLIDA [X]");
			e.printStackTrace();
			System.out.println(this.sql);
			
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		return objUsuario;
	}
}
