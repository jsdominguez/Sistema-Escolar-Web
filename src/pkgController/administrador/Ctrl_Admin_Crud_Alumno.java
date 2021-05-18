package pkgController.administrador;



import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pkgDao.administrador.Dao_Admin_Crud_Alumno;
import pkgModel.MdlAlumno;

public class Ctrl_Admin_Crud_Alumno {
	
	public static JsonObject ctrlListarAlumno() {

		Dao_Admin_Crud_Alumno objDaoAlumno = new Dao_Admin_Crud_Alumno();
		ArrayList<MdlAlumno> arrObjAlumno = new ArrayList<MdlAlumno>();
		JsonArray data = new JsonArray();
		JsonObject item;
		
		//String paramLength = request.getParameter("length");
		//String paramStart = request.getParameter("start");
		
		int cantidadRegistros = objDaoAlumno.daoGetCountListAlumno();
		
		arrObjAlumno  = objDaoAlumno.daoGetListaAlumno();

		String btnEditar = "<button type='button' id='btnEditDatosModalAlumno' class='btn btn-info btn-square' data-toggle='modal' data-target='#modalRegisterOrUpdate'><span data-toggle='tooltip' data-placement='bottom' title='Editar' class='fa fa-edit'></span></button>";
		String btnSetCredentials = "<button type='button' id='btnShowModalCredentials' class='btn btn-default btn-square' data-toggle='modal' data-target='#mdlSetCredentialAlumno'><span data-toggle='tooltip' data-placement='bottom' title='Credenciales' class='fa fa-user'></span></button>";
		String btnSwitchAccess = "";
		String columnAcciones = btnEditar+btnSetCredentials;
		
		for(MdlAlumno o : arrObjAlumno) {
			String estado_acceso = "";
			item = new JsonObject();
			item.addProperty("Codigo",o.getCodAlumno());
			item.addProperty("Nombre",o.getNomAlumno());
			item.addProperty("Apellido",o.getApeAlumno());
			item.addProperty("Edad",o.getEdadAlumno());
			item.addProperty("Dni",o.getDniAlumno());
			item.addProperty("grado",o.getGrado());
			item.addProperty("seccion",o.getSeccion());
			item.addProperty("nivel",o.getNivel());
			if(o.getEstado_acceso() == 1) {  estado_acceso = "checked"; }
			if(o.getEstado_acceso() == 2 ) { estado_acceso = "disabled";  }
			btnSwitchAccess = "<label class='switch switch-small'>" + 
								 "<input type='checkbox' id='btnSwitchAccess' "+estado_acceso+" value= '"+ o.getEstado_acceso() +"'>" + 
								 "<span></span>" + 
							   "</label>";
			item.addProperty("Acceso",btnSwitchAccess);
			item.addProperty("acciones",columnAcciones);
			data.add(item);
		}
		
		JsonObject gson = new JsonObject();
		gson.addProperty("recordsTotal", cantidadRegistros);
		gson.addProperty("recordsFiltered", cantidadRegistros);
		gson.addProperty("draw", 1);
        gson.add("datos", data);
        
        return gson;
	}



	public static int ctrlRegisterAlumno(HttpServletRequest request) {
	
		int codigoExitoOperacion = 0;
	
		try {
			String nombre = request.getParameter("txtNombre");
			String apellido = request.getParameter("txtApellido");
			String edad = request.getParameter("txtEdad");
			String dni = request.getParameter("txtDni");
			String grado = request.getParameter("selectGrado");
			String seccion = request.getParameter("selecSeccion");
			String nivel = request.getParameter("selectNivel");
			
	
			MdlAlumno objAlumno = new MdlAlumno();
			Dao_Admin_Crud_Alumno objDaoAlumno = new Dao_Admin_Crud_Alumno();
			
			String codigoGenerado = objDaoAlumno.daoGetGenerarCodigoAlumno();
			objAlumno.setCodAlumno(codigoGenerado);
			objAlumno.setNomAlumno(nombre);
			objAlumno.setApeAlumno(apellido);
			objAlumno.setEdadAlumno(Integer.parseInt(edad));
			objAlumno.setDniAlumno(Integer.parseInt(dni));
			objAlumno.setGrado(Integer.parseInt(grado));
			objAlumno.setSeccion(seccion);
			objAlumno.setNivel(nivel);
			
			Part fileForm = request.getPart("fileImagen");
			InputStream imageBinary[] = new InputStream[2];
			
			if(fileForm.getSize()>0) {
				imageBinary[0] = fileForm.getInputStream();
				imageBinary[1] = fileForm.getInputStream();
			}
			
			codigoExitoOperacion = objDaoAlumno.daoRegistrarAlumno(objAlumno,imageBinary);
		
		}catch(Exception e) { e.printStackTrace();	}
			
			return codigoExitoOperacion;
	}

	public static int ctrlUpdateAlumno(HttpServletRequest request) {
		
		String codAlumno = request.getParameter("txtCodigo");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String edad = request.getParameter("txtEdad");
		String dni = request.getParameter("txtDni");
		String grado = request.getParameter("selectGrado");
		String seccion = request.getParameter("selecSeccion");
		
		int codigoExitoOperacion = 0;
		
		MdlAlumno objAlumno = new MdlAlumno();
		Dao_Admin_Crud_Alumno objDaoAlumno = new Dao_Admin_Crud_Alumno();
		
		objAlumno.setCodAlumno(codAlumno);
		objAlumno.setNomAlumno(nombre);
		objAlumno.setApeAlumno(apellido);
		objAlumno.setEdadAlumno(Integer.parseInt(edad));
		objAlumno.setDniAlumno(Integer.parseInt(dni));
		objAlumno.setGrado(Integer.parseInt(grado));
		objAlumno.setSeccion(seccion);
	
		codigoExitoOperacion = objDaoAlumno.daoUpdateAlumno(objAlumno);
		return codigoExitoOperacion;
	}
	
	
	public static int ctrlSetCredentialAlumno(HttpServletRequest request) {
		Dao_Admin_Crud_Alumno objDaoAlumno = new Dao_Admin_Crud_Alumno();
		String codigo = request.getParameter("txtCod");
		String pass = request.getParameter("txtPass");
		int codigoExitoOperacion = 0;
		codigoExitoOperacion = objDaoAlumno.daoSetCredentialAlumno(codigo,pass);
		return codigoExitoOperacion;
	}
	
	public static int ctrlSetAccesoUsuario(HttpServletRequest request) {
		Dao_Admin_Crud_Alumno objDaoAlumno = new Dao_Admin_Crud_Alumno();
		String codigo = request.getParameter("codAlumno");
		String valorEstado = request.getParameter("valorEstado");
		int parseValorEstado = Integer.parseInt(valorEstado);
		int cambioOk = objDaoAlumno.daoSetAccesoUsuario(codigo,parseValorEstado);
		return cambioOk;
	}

}