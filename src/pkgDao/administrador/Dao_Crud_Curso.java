package pkgDao.administrador;

import pkgConexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pkgModel.MdlCurso;

public class Dao_Crud_Curso {
	
	Connection cn;
	PreparedStatement consultaPreparada;
	ResultSet resultadoDatos;
	String sql;
	
	public String daoGetGenerarCodigoCurso() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		String codGenerado= "";
		
		try {
			
			this.sql = "select concat('CSC',(select count(*)+1 from cursos)) as codigo_Generado";
			consultaPreparada = this.cn.prepareCall(this.sql);
			this.resultadoDatos = consultaPreparada.executeQuery();
			this.resultadoDatos.last();
			codGenerado = resultadoDatos.getString("codigo_Generado");
			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Curso.daoGetGenerarCodigo' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return codGenerado;
	}
	
	
	public int daoGetCountListCurso() {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		int cantidadRegistros=0;
		
		try {
			
			this.sql = "select count(*) from cursos";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			resultadoDatos.last();
			cantidadRegistros = resultadoDatos.getInt(1);
			
			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Curso.countListCurso' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return cantidadRegistros;
	}
	
	public ArrayList<MdlCurso> daoGetListaCursos() {
	
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		ArrayList<MdlCurso> arrCursos = new ArrayList<MdlCurso>();
		MdlCurso objCursos;
		
		try {
			
			this.sql = "select nomCurso,cur.idCurso_grado_seccion,gsec.nivel from cursos cur inner join grado_seccion gsec where cur.idCurso_grado_seccion = gsec.idCurso_grado_seccion group by cur.nomCurso order by idCurso_grado_seccion";
			consultaPreparada = this.cn.prepareCall(this.sql);
			resultadoDatos = consultaPreparada.executeQuery();
			while(resultadoDatos.next()) {
				objCursos = new MdlCurso();
				objCursos.setCodCurso(resultadoDatos.getString("idCurso_grado_seccion"));
				objCursos.setNomCurso(resultadoDatos.getString("nomCurso"));
				objCursos.setNivel(resultadoDatos.getString("nivel"));
				arrCursos.add(objCursos);
			}
			
			this.cn.close();
			
		}catch(SQLException e){
			
			System.out.println("[X] DAO 'Curso.getListaCurso' FALLIDA [X]");
			e.printStackTrace();
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
		}
		
		return arrCursos;
	}
	
	public int daoRegistrarCursos(MdlCurso objCurso) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		String codigo = "";
		try {
			
			if(objCurso.getNivel().equals("S")) {
				codigo = "CSC";
			}else {
				codigo = "CPR";
			}
			
			if(objCurso.getCodCurso().equals("ALL")) {
				int contador = 100;
				for(int i=0;i<5;i++) {
					this.sql = "insert into cursos (idCurso_grado_seccion,nomCurso) values(?,?)";
					consultaPreparada = this.cn.prepareStatement(this.sql);
					consultaPreparada.setString(1,(codigo+contador));
					consultaPreparada.setString(2,objCurso.getNomCurso());
					consultaPreparada.execute();
					contador += 100;
				}
			}else {
				this.sql = "insert into cursos (idCurso_grado_seccion,nomCurso) values(?,?)";
				consultaPreparada = this.cn.prepareStatement(this.sql);
				consultaPreparada.setString(1,objCurso.getCodCurso());
				consultaPreparada.setString(2,objCurso.getNomCurso());
				consultaPreparada.execute();
			}
			
			this.cn.close();
						
		}catch(Exception e) {
			System.out.println("[X] DAO 'Cursos.registrarCurso' FALLIDA [X]");
			e.printStackTrace();
			return 500;
		}finally {
			objConectar.desconectar();
			this.cn = null;
		}
		
		return 200;
	}
	
	
	public int daoUpdateCurso(MdlCurso objCurso) {
		
		Conexion objConectar = new Conexion();
		this.cn = objConectar.conectar();
		
		try {
			
			this.sql = "update cursos set nomCurso = ?  where idCurso_grado_seccion = ?";
			consultaPreparada = this.cn.prepareStatement(this.sql);
			consultaPreparada.setString(1,objCurso.getNomCurso());
			consultaPreparada.setString(2,objCurso.getNivel());
			consultaPreparada.setInt(3,objCurso.getGrado());
			consultaPreparada.setString(4,objCurso.getCodCurso());
			
			consultaPreparada.execute();

			this.cn.close();
			
		}catch(Exception e) {
			
			System.out.println("[X] DAO 'Curso.daoUpdateCurso' FALLIDA [X]");
			e.printStackTrace();
			return 500;
			
		}finally {
			
			objConectar.desconectar();
			this.cn = null;
			
		}
		
		return 200;
	}

}
