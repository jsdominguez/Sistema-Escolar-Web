package pkgController;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import pkgDao.DaoAlumno;
import pkgModel.MdlAlumno;

public class CtrlAlumno {
	
	public static JsonObject ctrlListarAlumno() {

		DaoAlumno objDaoAlumno = new DaoAlumno();
		ArrayList<MdlAlumno> arrObjAlumno = new ArrayList<MdlAlumno>();
		JsonArray data = new JsonArray();
		JsonObject item;
		
		//String paramLength = request.getParameter("length");
		//String paramStart = request.getParameter("start");
		
		int cantidadRegistros = objDaoAlumno.daoGetCountListAlumno();
		
		arrObjAlumno  = objDaoAlumno.daoGetListaAlumnos();

		String btnEditar = "<button type='button' id='btnShowModalEdit' class='btn btn-info btn-square' data-toggle='modal' data-target='#mimodal' data-tooltip='tooltip' data-placement='bottom' title='Editar'><span class='fa fa-edit'></span></button>";
		String btnEliminar = "<button type='button' id='btnShowModalDelete' class='btn btn-danger btn-square' data-tooltip='tooltip' data-placement='bottom' title='Eliminar'><span class='fa fa-trash-o'></span></button>";
		String columnAcciones = btnEditar+btnEliminar;
		
		for(MdlAlumno o : arrObjAlumno) {
			item = new JsonObject();
			item.addProperty("id",o.getIdAlumno());
			item.addProperty("nombre",o.getNombre());
			item.addProperty("apellido",o.getApellido());
			item.addProperty("edad",o.getEdad());
			item.addProperty("campo1",1);
			item.addProperty("campo2",columnAcciones);
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
				
		String nombre = request.getParameter("inputNombre");
		String apellido = request.getParameter("inputApellido");
		String edad = request.getParameter("inputEdad");
		int codigoExitoOperacion = 0;
		
		MdlAlumno objAlumno = new MdlAlumno();
		DaoAlumno objDaoAlumno = new DaoAlumno();
		
		objAlumno.setNombre(nombre);
		objAlumno.setApellido(apellido);
		objAlumno.setEdad(Integer.parseInt(edad));

		codigoExitoOperacion = objDaoAlumno.daoRegistrarAlumno(objAlumno);
		return codigoExitoOperacion;
	}
	
	public static int ctrlUpdateAlumno(HttpServletRequest request, String paramIidAlumno) {
		
		int idalumno = Integer.parseInt(paramIidAlumno);
		String nombre = request.getParameter("inputNombre");
		String apellido = request.getParameter("inputApellido");
		String edad = request.getParameter("inputEdad");
		int codigoExitoOperacion = 0;
		
		MdlAlumno objAlumno = new MdlAlumno();
		DaoAlumno objDaoAlumno = new DaoAlumno();
		
		objAlumno.setIdAlumno(idalumno);
		objAlumno.setNombre(nombre);
		objAlumno.setApellido(apellido);
		objAlumno.setEdad(Integer.parseInt(edad));

		codigoExitoOperacion = objDaoAlumno.daoUpdateAlumno(objAlumno,idalumno);
		return codigoExitoOperacion;
	}
	
	public static int ctrlEliminarAlumno(String idAlumno) {
		int codigoExitoOperacion = 0;
		int parseIdAlumno = Integer.parseInt(idAlumno);
		DaoAlumno objDaoAlumno = new DaoAlumno();
		codigoExitoOperacion = objDaoAlumno.daoEliminarAlumno(parseIdAlumno);
		return codigoExitoOperacion;
	}
}
