package pkgDao.administrador;

import pkgConexion.Conexion;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pkgModel.MdlDocente;

public class Dao_Crud_Docente {
	
	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	
	public String daoGetGenerarCodigoDocente() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		String codGenerado= "";
		try {
			this.sql = "select concat('D000',(select count(*)+1 from docente)) as codigo_Generado";
			consultaPreparada = this.cn.prepareCall(this.sql);
			this.resultadoDatos = consultaPreparada.executeQuery();
			this.resultadoDatos.last();
			codGenerado = resultadoDatos.getString("codigo_Generado");
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Docente.daoGetGenerarCodigoDocente' FALLIDA [X]");
			e.printStackTrace();
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		return codGenerado;
	}
	
	public ArrayList<MdlDocente> daoGetListaDocente() {
	
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		ArrayList<MdlDocente> arrDocente = new ArrayList<MdlDocente>();
		MdlDocente objDocente;
		
		try {
			
			this.sql = "select codDocente,nomDocente,apeDocente,edadDocente,dniDocente,fechaNac" + 
					   " from docente";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				objDocente = new MdlDocente();
				objDocente.setCodDocente(resultadoDatos.getString("codDocente"));
				objDocente.setNomDocente(resultadoDatos.getString("nomDocente"));
				objDocente.setApeDocente(resultadoDatos.getString("apeDocente"));
				objDocente.setEdadDocente(resultadoDatos.getInt("edadDocente"));
				objDocente.setDniDocente(resultadoDatos.getInt("dniDocente"));
				objDocente.setFechaNac(resultadoDatos.getString("fechaNac"));
				arrDocente.add(objDocente);
			}
			this.cn.close();
		}catch(SQLException e){
			System.out.println("[X] DAO 'Docente.getListaDocente' FALLIDA [X]");
			e.printStackTrace();
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		
		return arrDocente;
	}
	
	public int daoGetCountListDocente() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		int cantidadRegistros=0;
		
		try {
			this.sql = "select count(*) from docente";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			resultadoDatos.last();
			cantidadRegistros = resultadoDatos.getInt(1);
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Docente.countListDocente' FALLIDA [X]");
			e.printStackTrace();
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		
		return cantidadRegistros;
	}
	
	
	public int daoRegistrarDocente(MdlDocente objDocente,InputStream archivo[]) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		
		try {
			
			this.sql = "insert into docente(codDocente,nomDocente,apeDocente,edadDocente,dniDocente,fechaNac,idTipoUsuario,archivo)" +
					   " values(?,?,?,?,?,?,2,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objDocente.getCodDocente());
			consultaPreparada.setString(2,objDocente.getNomDocente());
			consultaPreparada.setString(3,objDocente.getApeDocente());
			consultaPreparada.setInt(4,objDocente.getEdadDocente());
			consultaPreparada.setInt(5,objDocente.getDniDocente());
			consultaPreparada.setString(6,objDocente.getFechaNac());
			consultaPreparada.setBlob(7,archivo[0]);
			
			consultaPreparada.execute();
			
			this.sql = "insert into infousuario() values(?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objDocente.getCodDocente());
			consultaPreparada.setString(2,objDocente.getNomDocente());
			consultaPreparada.setString(3,objDocente.getApeDocente());
			consultaPreparada.execute();
			
		
			this.sql = "insert into usuario(nomUsuario,passUsuario,idInfoUsuario,idTipoUsuario,estado_acceso,archivo) values(?,?,?,?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objDocente.getCodDocente());
			consultaPreparada.setString(2,"");
			consultaPreparada.setString(3,objDocente.getCodDocente());
			consultaPreparada.setInt(4,2);
			consultaPreparada.setInt(5,2);
			consultaPreparada.setBlob(6,archivo[1]);
			consultaPreparada.execute();
			
			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Docente.registrarDocente' FALLIDA [X]");
			e.printStackTrace();
			return 500;
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return 200;
	}
	
	public int daoUpdateDocente(MdlDocente objDocente) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		
		try {
			
			this.sql = "update docente set nomDocente = ?,apeDocente = ?,edadDocente = ?,dniDocente = ?,fechaNac = ?"+
					   " where codDocente = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objDocente.getNomDocente());
			consultaPreparada.setString(2,objDocente.getApeDocente());
			consultaPreparada.setInt(3,objDocente.getEdadDocente());
			consultaPreparada.setInt(4,objDocente.getDniDocente());
			consultaPreparada.setString(5,objDocente.getFechaNac());
			consultaPreparada.setString(6,objDocente.getCodDocente());
			
			consultaPreparada.execute();
			
			this.sql = "update infousuario set nombreUsuario = ?,apellidoUsuario = ?"+
					   " where idInfUsuario = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objDocente.getNomDocente());
			consultaPreparada.setString(2,objDocente.getApeDocente());
			consultaPreparada.setString(3,objDocente.getCodDocente());
			consultaPreparada.execute();
			
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Docente.daoUpdateDocente' FALLIDA [X]");
			e.printStackTrace();
			return 500;
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		
		return 200;
	}
	
	/*public int daoEliminarDocente(int idDocente) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		
		try {
			
			this.sql = "delete from alumno where id = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setInt(1,idDocente);
			consultaPreparada.execute();
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Docente.daoEliminarAlumno' FALLIDA [X]");
			e.printStackTrace();
			return 500;
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		return 200;
	}*/
	
}
