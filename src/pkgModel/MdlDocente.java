package pkgModel;

public class MdlDocente {
	
	private int idAlumno;
	private String nombre;
	private String apellido;
	private int edad;
	private String tipo_usuario_string;
	
	public MdlDocente() {}
	
	public int getIdAlumno() {
		return idAlumno;
	}
	
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getTipo_usuario_string() {
		return tipo_usuario_string;
	}
	public void setTipo_usuario_string(String tipo_usuario) {
		this.tipo_usuario_string = tipo_usuario;
	}
	
	@Override
	 public String toString(){
		return this.idAlumno+" - " + this.nombre + " - " + this.apellido + " - " + this.edad;
	}
}
