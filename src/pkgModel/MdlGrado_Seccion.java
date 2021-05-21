package pkgModel;

public class MdlGrado_Seccion {
	
	private String codigo;
	private int grado;
	private String seccion;
	private String nivel;
	private int estado_ingreso;
	private int cantidad;
	
	public MdlGrado_Seccion() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getEstado_ingreso() {
		return estado_ingreso;
	}

	public void setEstado_ingreso(int estado_ingreso) {
		this.estado_ingreso = estado_ingreso;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	};
	
	
	
}
