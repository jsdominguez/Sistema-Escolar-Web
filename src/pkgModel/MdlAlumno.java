package pkgModel;

import java.io.InputStream;

public class MdlAlumno {
	
	private String codAlumno;
	private String nomAlumno;
	private String apeAlumno;
	private int edadAlumno;
	private int dniAlumno;
	private String fechaNac;
	private int idTipoUsuario;
	private String seccion;
	private int grado;
	private int estado_acceso;
	private InputStream archivo;
	private String nivel;

	public MdlAlumno() {}



	public String getCodAlumno() {
		return codAlumno;
	}



	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}



	public String getNomAlumno() {
		return nomAlumno;
	}



	public void setNomAlumno(String nomAlumno) {
		this.nomAlumno = nomAlumno;
	}



	public String getApeAlumno() {
		return apeAlumno;
	}



	public void setApeAlumno(String apeAlumno) {
		this.apeAlumno = apeAlumno;
	}



	public int getEdadAlumno() {
		return edadAlumno;
	}



	public void setEdadAlumno(int edadAlumno) {
		this.edadAlumno = edadAlumno;
	}



	public int getDniAlumno() {
		return dniAlumno;
	}



	public void setDniAlumno(int dniAlumno) {
		this.dniAlumno = dniAlumno;
	}



	public String getFechaNac() {
		return fechaNac;
	}



	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}



	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}



	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}



	public int getEstado_acceso() {
		return estado_acceso;
	}



	public void setEstado_acceso(int estado_acceso) {
		this.estado_acceso = estado_acceso;
	}



	public InputStream getArchivo() {
		return archivo;
	}



	public void setArchivo(InputStream archivo) {
		this.archivo = archivo;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}



	public String getNivel() {
		return nivel;
	}



	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
}
