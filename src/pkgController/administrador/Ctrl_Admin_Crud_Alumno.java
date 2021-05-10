package pkgController.administrador;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pkgDao.administrador.Dao_Admin_Crud_Alumno;
import pkgDao.administrador.Dao_Admin_Crud_Docente;
import pkgModel.MdlAlumno;
import pkgModel.MdlDocente;

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

		String btnEditar = "<button type='button' id='btnShowModalEdit' class='btn btn-info btn-square' data-toggle='modal' data-target='#mimodal' data-tooltip='tooltip' data-placement='bottom' title='Editar'><span class='fa fa-edit'></span></button>";
		String btnSetCredentials = "<button type='button' id='btnSetCredentials' class='btn btn-default btn-square' data-toggle='modal' data-target='#mdlSetCredentialAlumno' data-tooltip='tooltip' data-placement='bottom' title='Credenciales'><span class='fa fa-user'></span></button>";
		String columnAcciones = btnEditar+btnSetCredentials;
		
		for(MdlAlumno o : arrObjAlumno) {
			item = new JsonObject();
			item.addProperty("Codigo",o.getCodAlumno());
			item.addProperty("Nombre",o.getNomAlumno());
			item.addProperty("Apellido",o.getApeAlumno());
			item.addProperty("Edad",o.getEdadAlumno());
			item.addProperty("Dni",o.getDniAlumno());
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
		
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String edad = request.getParameter("txtEdad");
		String dni = request.getParameter("txtDni");

		int codigoExitoOperacion = 0;
		
		MdlAlumno objAlumno = new MdlAlumno();
		Dao_Admin_Crud_Alumno objDaoAlumno = new Dao_Admin_Crud_Alumno();
		
		String codigoGenerado = objDaoAlumno.daoGetGenerarCodigoAlumno();
		objAlumno.setCodAlumno(codigoGenerado);
		objAlumno.setNomAlumno(nombre);
		objAlumno.setApeAlumno(apellido);
		objAlumno.setEdadAlumno(Integer.parseInt(edad));
		objAlumno.setDniAlumno(Integer.parseInt(dni));

		codigoExitoOperacion = objDaoAlumno.daoRegistrarAlumno(objAlumno);
		return codigoExitoOperacion;
	}
	
}
