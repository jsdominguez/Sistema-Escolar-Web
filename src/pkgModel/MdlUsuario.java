package pkgModel;


public class MdlUsuario {
	
	private String nombreUsuario;
	private String apellidoUsuario;
	private int idTipoUsuario;
	private String tipoUsuarioString;
	private String codigo;
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	public String getTipoUsuarioString() {
		return tipoUsuarioString;
	}
	public void setTipoUsuarioString(String tipoUsuarioString) {
		this.tipoUsuarioString = tipoUsuarioString;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
