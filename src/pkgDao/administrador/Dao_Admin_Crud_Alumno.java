package pkgDao.administrador;

import pkgConexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pkgModel.MdlAlumno;
import pkgModel.MdlDocente;

public class Dao_Admin_Crud_Alumno {
	
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
			
			this.sql = "select idAlumno,nomAlumno,apeAlumno,edadAlumno,dniAlumno from alumnos";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			
			while(resultadoDatos.next()) {
				objAlumno = new MdlAlumno();
				objAlumno.setCodAlumno(resultadoDatos.getString("idAlumno"));
				objAlumno.setNomAlumno(resultadoDatos.getString("nomAlumno"));
				objAlumno.setApeAlumno(resultadoDatos.getString("apeAlumno"));
				objAlumno.setEdadAlumno(resultadoDatos.getInt("edadAlumno"));
				objAlumno.setDniAlumno(resultadoDatos.getInt("dniAlumno"));
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
	
	
	public int daoRegistrarAlumno(MdlAlumno objAlumno) {
		
		//int idDocente = this.daoGetCountListDocente();
		//idDocente +=1;
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		
		try {
			
			this.sql = "insert into alumnos(idAlumno,nomAlumno,apeAlumno,edadAlumno,dniAlumno,idTipoUsuario,estado_acceso)" +
					   " values(?,?,?,?,?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getCodAlumno());
			consultaPreparada.setString(2,objAlumno.getNomAlumno());
			consultaPreparada.setString(3,objAlumno.getApeAlumno());
			consultaPreparada.setInt(4,objAlumno.getEdadAlumno());
			consultaPreparada.setInt(5,objAlumno.getDniAlumno());
			consultaPreparada.setInt(6,3);
			consultaPreparada.setInt(7,2);
			
			consultaPreparada.execute();
			
			this.sql = "insert into infousuario(idInfUsuario,nombreUsuario,apellidoUsuario) values(?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getCodAlumno());
			consultaPreparada.setString(2,objAlumno.getNomAlumno());
			consultaPreparada.setString(3,objAlumno.getApeAlumno());
			consultaPreparada.execute();
			
		
			this.sql = "insert into usuario(nomUsuario,passUsuario,idInfoUsuario,idTipoUsuario,estado_acceso) values(?,?,?,?,?)";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objAlumno.getCodAlumno());
			consultaPreparada.setString(2,"");
			consultaPreparada.setString(3,objAlumno.getCodAlumno());
			consultaPreparada.setInt(4,3);
			consultaPreparada.setInt(5,2);
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
}
