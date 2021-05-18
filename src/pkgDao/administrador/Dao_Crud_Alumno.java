package pkgDao.administrador;

import pkgConexion.Conexion;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pkgModel.MdlAlumno;

public class Dao_Crud_Alumno {
	
	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	public String daoGetGenerarCodigoAlumno() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		String codGenerado= "";
		
		try {
			
			this.sql = "select concat('E00',(select count(*)+1 from alumnos)) as codigo_Generado";
			consultaPreparada = this.cn.prepareCall(this.sql);
			this.resultadoDatos = consultaPreparada.executeQuery();
			this.resultadoDatos.last();
			codGenerado = resultadoDatos.getString("codigo_Generado");
			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Alumno.daoGetGenerarCodigoAlumno' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return codGenerado;
	}
	
	
	public int daoGetCountListAlumno() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		int cantidadRegistros=0;
		
		try {
			
			this.sql = "select count(*) from alumnos";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			resultadoDatos.last();
			cantidadRegistros = resultadoDatos.getInt(1);
			
			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Alumno.countListAlumno' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return cantidadRegistros;
	}
	
	public ArrayList<MdlAlumno> daoGetListaAlumno() {
	
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		ArrayList<MdlAlumno> arrAlumno = new ArrayList<MdlAlumno>();
		MdlAlumno objAlumno;
		
		try {
			
			this.sql = "select idAlumno,nomAlumno,apeAlumno,edadAlumno,dniAlumno,estado_acceso,archivo,grado,seccion,nivel from alumnos";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				objAlumno = new MdlAlumno();
				objAlumno.setCodAlumno(resultadoDatos.getString("idAlumno"));
				objAlumno.setNomAlumno(resultadoDatos.getString("nomAlumno"));
				objAlumno.setApeAlumno(resultadoDatos.getString("apeAlumno"));
				objAlumno.setEdadAlumno(resultadoDatos.getInt("edadAlumno"));
				objAlumno.setDniAlumno(resultadoDatos.getInt("dniAlumno"));
				objAlumno.setGrado(resultadoDatos.getInt("grado"));
				objAlumno.setSeccion(resultadoDatos.getString("seccion"));
				objAlumno.setEstado_acceso(resultadoDatos.getInt("estado_acceso"));
				objAlumno.setNivel(resultadoDatos.getString("nivel"));
				arrAlumno.add(objAlumno);
			}
			
			this.cn.close();
			
		}catch(SQLException e){
			
			System.out.println("[X] DAO 'Alumno.getListaAlumnos' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
		}
		
		return arrAlumno;
	}
	
	public int daoRegistrarAlumno(MdlAlumno objAlumno,InputStream[] archivo) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		try {
			            
			this.sql = "insert into usuario(nomUsuario,passUsuario,idInfoUsuario,idTipoUsuario,estado_acceso,archivo) values(?,?,?,?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getCodAlumno());
			consultaPreparada.setString(2,"");
			consultaPreparada.setString(3,objAlumno.getCodAlumno());
			consultaPreparada.setInt(4,3);
			consultaPreparada.setInt(5,2);
			consultaPreparada.setBlob(6,archivo[0]);
			consultaPreparada.execute();
			
			this.sql = "insert into alumnos(idAlumno,nomAlumno,apeAlumno,edadAlumno,dniAlumno,idTipoUsuario,estado_acceso,archivo,grado,seccion,nivel)" +
					   " values(?,?,?,?,?,?,?,?,?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getCodAlumno());
			consultaPreparada.setString(2,objAlumno.getNomAlumno());
			consultaPreparada.setString(3,objAlumno.getApeAlumno());
			consultaPreparada.setInt(4,objAlumno.getEdadAlumno());
			consultaPreparada.setInt(5,objAlumno.getDniAlumno());
			consultaPreparada.setInt(6,3);
			consultaPreparada.setInt(7,2);
			consultaPreparada.setBlob(8,archivo[1]);
			consultaPreparada.setInt(9,objAlumno.getGrado());
			consultaPreparada.setString(10,objAlumno.getSeccion());
			consultaPreparada.setString(11,objAlumno.getNivel());
			
			consultaPreparada.execute();
			
			this.sql = "insert into infousuario(idInfUsuario,nombreUsuario,apellidoUsuario) values(?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getCodAlumno());
			consultaPreparada.setString(2,objAlumno.getNomAlumno());
			consultaPreparada.setString(3,objAlumno.getApeAlumno());
			consultaPreparada.execute();
			
			this.cn.close();
		}catch(Exception e) {
			System.out.println("[X] DAO 'Alumno.registrarAlumno' FALLIDA [X]");
			e.printStackTrace();
			return 500;
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		
		return 200;
	}
	
	
public int daoUpdateAlumno(MdlAlumno objAlumno) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		
		try {
			
			this.sql = "update alumnos set nomAlumno = ? ,apeAlumno = ?,edadAlumno = ?,dniAlumno = ? , grado = ? , seccion = ?"+
					   " where idAlumno = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getNomAlumno());
			consultaPreparada.setString(2,objAlumno.getApeAlumno());
			consultaPreparada.setInt(3,objAlumno.getEdadAlumno());
			consultaPreparada.setInt(4,objAlumno.getDniAlumno());
			consultaPreparada.setInt(5,objAlumno.getGrado());
			consultaPreparada.setString(6,objAlumno.getSeccion());
			consultaPreparada.setString(7,objAlumno.getCodAlumno());
			
			consultaPreparada.execute();
			
			this.sql = "update infousuario set nombreUsuario = ?,apellidoUsuario = ?"+
					   " where idInfUsuario = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getNomAlumno());
			consultaPreparada.setString(2,objAlumno.getApeAlumno());
			consultaPreparada.setString(3,objAlumno.getCodAlumno());
			consultaPreparada.execute();
			
			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Alumno.daoUpdateAlumno' FALLIDA [X]");
			e.printStackTrace();
			return 500;
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return 200;
	}

public int daoSetCredentialAlumno(String codigo,String pass) {
	
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
		
		this.sql = "update alumnos set estado_acceso = ? where idAlumno = ?";
		consultaPreparada = this.cn.prepareStatement(this.sql);
		consultaPreparada.setInt(1,0);
		consultaPreparada.setString(2,codigo);
		consultaPreparada.execute();
		
		finalizadoOk = 1;
		
	}catch(Exception e) {
		
		System.out.println("[X] DAO 'Alumno.daoSetCredentialAlumno' FALLIDA [X]");
		e.printStackTrace();
		
	}finally {
		
		objConectar.desconectar();
		this.cn = null;
		
	}
	
	return finalizadoOk;
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
		
		
		this.sql = "update alumnos set estado_acceso = ? where idAlumno = ?";
		consultaPreparada = this.cn.prepareStatement(this.sql);
		consultaPreparada.setInt(1,valorEstado);
		consultaPreparada.setString(2,codigo);
		consultaPreparada.execute();
		
		cambioOk = 200;
		
	}catch(Exception e) {
		
		System.out.println("[X] DAO 'Alumnos.daoSetAccesoAlumnos' FALLIDA [X]");
		e.printStackTrace();
		
	}finally {
		
		objConectar.desconectar();
		this.cn = null;
		
	}
	
	return cambioOk;
}

}
