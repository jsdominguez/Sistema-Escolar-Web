package pkgDao.administrador;

import pkgConexion.Conexion;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pkgModel.MdlAlumno;
import pkgModel.MdlGrado_Seccion;

public class Dao_Grado_Seccion {
	
	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	public String daoGetCountGradoSeccion() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		String codGenerado= "";
		
		try {
			
			this.sql = "select concat('GRSEC0',(select count(*)+1 from alumnos)) as codigo_Generado";
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
	
	
	public int daoGetCountListGradoSeccion() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		int cantidadRegistros=0;
		
		try {
			
			this.sql = "select count(*) from grado_seccion";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			resultadoDatos.last();
			cantidadRegistros = resultadoDatos.getInt(1);
			
			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Grado_Secicon.countListGradoSeccion' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return cantidadRegistros;
	}
	
	public ArrayList<MdlGrado_Seccion> daoGetListaGradoSeccion() {
	
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		ArrayList<MdlGrado_Seccion> arrGradoSeccion = new ArrayList<MdlGrado_Seccion>();
		MdlGrado_Seccion objGradoSeccion;
		
		try {
			
			this.sql = "select codGrado_Seccion,grado,seccion,nivel,estado_ingreso,cantidad_alumnos from grado_seccion order by nivel,grado,seccion";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				objGradoSeccion = new MdlGrado_Seccion();
				objGradoSeccion.setCodigo(resultadoDatos.getString("codGrado_Seccion"));
				objGradoSeccion.setGrado(resultadoDatos.getInt("grado"));
				objGradoSeccion.setSeccion(resultadoDatos.getString("seccion"));
				objGradoSeccion.setNivel(resultadoDatos.getString("nivel"));
				objGradoSeccion.setEstado_ingreso(resultadoDatos.getInt("estado_ingreso"));
				objGradoSeccion.setCantidad(resultadoDatos.getInt("cantidad_alumnos"));
				arrGradoSeccion.add(objGradoSeccion);
			}
			
			this.cn.close();
			
		}catch(SQLException e){
			
			System.out.println("[X] DAO 'Grado_Seccion.getListaGradoSeccion' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
		}
		
		return arrGradoSeccion;
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
			
			this.sql = "insert into alumnos(idAlumno,nomAlumno,apeAlumno,edadAlumno,dniAlumno,idTipoUsuario,archivo,grado,seccion,nivel)" +
					   " values(?,?,?,?,?,?,?,?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getCodAlumno());
			consultaPreparada.setString(2,objAlumno.getNomAlumno());
			consultaPreparada.setString(3,objAlumno.getApeAlumno());
			consultaPreparada.setInt(4,objAlumno.getEdadAlumno());
			consultaPreparada.setInt(5,objAlumno.getDniAlumno());
			consultaPreparada.setInt(6,3);
			consultaPreparada.setBlob(7,archivo[1]);
			consultaPreparada.setInt(8,objAlumno.getGrado());
			consultaPreparada.setString(9,objAlumno.getSeccion());
			consultaPreparada.setString(10,objAlumno.getNivel());
			
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

}
