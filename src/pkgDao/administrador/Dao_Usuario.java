package pkgDao.administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pkgConexion.Conexion;
import pkgModel.MdlUsuario;

public class Dao_Usuario {

	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	public int daoGetCountListUsuario() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		int cantidadRegistros=0;
		
		try {
			
			this.sql = "select count(*) from usuario user " + 
					   "inner join infousuario inf on user.idInfoUsuario=inf.idInfUsuario" + 
					   " inner join tipousuario tip on tip.idTipoUsuario=user.idTipoUsuario" + 
					   " where inf.idInfUsuario like 'D%' or inf.idInfUsuario like 'E%'";
			
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			resultadoDatos.last();
			cantidadRegistros = resultadoDatos.getInt(1);
			
			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Usuario.countListUsuario' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return cantidadRegistros;
	}
	
	public ArrayList<MdlUsuario> daoGetListaUsuario() {
	
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		ArrayList<MdlUsuario> arrUsuario = new ArrayList<MdlUsuario>();
		MdlUsuario objUsuario;
		
		try {
			
			this.sql = "select inf.idInfUsuario,inf.nombreUsuario,inf.apellidoUsuario,tip.tipoUsuarioString,user.estado_acceso from usuario user " + 
					   " inner join infousuario inf on user.idInfoUsuario=inf.idInfUsuario " + 
					   " inner join tipousuario tip on tip.idTipoUsuario=user.idTipoUsuario " + 
					   " where inf.idInfUsuario like 'D%' or inf.idInfUsuario like 'E%'";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				objUsuario = new MdlUsuario();
				objUsuario.setCodigo(resultadoDatos.getString("idInfUsuario"));
				objUsuario.setNombreUsuario(resultadoDatos.getString("nombreUsuario"));
				objUsuario.setApellidoUsuario(resultadoDatos.getString("apellidoUsuario"));
				objUsuario.setTipoUsuarioString(resultadoDatos.getString("tipoUsuarioString"));
				objUsuario.setEstado_acceso_sistema(resultadoDatos.getInt("estado_acceso"));
				arrUsuario.add(objUsuario);
			}
			
			this.cn.close();
			
		}catch(SQLException e){
			
			System.out.println("[X] DAO 'Usuario.getListaUsuario' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
		}
		
		return arrUsuario;
	}
	
	public int daoSetAccesoUsuario(String codigo, int valorEstado) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		int cambioOk = 500;
	
		try {
			
					
			this.sql = "update usuario set estado_acceso = ? where idInfoUsuario = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setInt(1,valorEstado);
			consultaPreparada.setString(2,codigo);
			consultaPreparada.execute();
						
			cambioOk = 200;
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Usuario.daoSetAccesoUsuario' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return cambioOk;
	}

	public int daoSetCredentialUsuario(String codigo,String pass) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		int finalizadoOk = 0;

		try {
			
			this.sql = "update usuario set passUsuario=?,estado_acceso = ? where idInfoUsuario = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,pass);
			consultaPreparada.setInt(2,0);
			consultaPreparada.setString(3,codigo);
			consultaPreparada.execute();

			finalizadoOk = 1;
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Usuario.daoSetCredentialUsuario' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return finalizadoOk;
	}

}
