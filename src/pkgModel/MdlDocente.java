package pkgModel;

public class MdlDocente {
	
	private String codDocente;
	private String nomDocente;
	private String apeDocente;
	private int edadDocente;
	private int dniDocente;
	private String fechaNac;
	private int idTipoUsuario;
	private int estado_acceso;
	
	

	public MdlDocente() {}

	public String getCodDocente() {
		return codDocente;
	}

	public void setCodDocente(String codDocente) {
		this.codDocente = codDocente;
	}

	public String getNomDocente() {
		return nomDocente;
	}

	public void setNomDocente(String nomDocente) {
		this.nomDocente = nomDocente;
	}

	public String getApeDocente() {
		return apeDocente;
	}

	public void setApeDocente(String apeDocente) {
		this.apeDocente = apeDocente;
	}

	public int getEdadDocente() {
		return edadDocente;
	}

	public void setEdadDocente(int edadDocente) {
		this.edadDocente = edadDocente;
	}

	public int getDniDocente() {
		return dniDocente;
	}

	public void setDniDocente(int dniDocente) {
		this.dniDocente = dniDocente;
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
}
