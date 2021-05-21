package pkgController.administrador;

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import pkgDao.administrador.Dao_Grado_Seccion;
import pkgModel.MdlGrado_Seccion;

public class Ctrl_Grado_Seccion {
	
	public static JsonObject ctrlListarGradoSeccion() {

		Dao_Grado_Seccion objDaoGradoSeccion = new Dao_Grado_Seccion();
		ArrayList<MdlGrado_Seccion> arrObjGradoSeccion = new ArrayList<MdlGrado_Seccion>();
		JsonArray data = new JsonArray();
		JsonObject item;
		
		//String paramLength = request.getParameter("length");
		//String paramStart = request.getParameter("start");
		
		int cantidadRegistros = objDaoGradoSeccion.daoGetCountListGradoSeccion();
		
		arrObjGradoSeccion  = objDaoGradoSeccion.daoGetListaGradoSeccion();

		//String btnEditar = "<button type='button' id='btnEditDatosModalAlumno' class='btn btn-info btn-square' data-toggle='modal' data-target='#modalRegisterOrUpdate'><span data-toggle='tooltip' data-placement='bottom' title='Editar' class='fa fa-edit'></span></button>";
		//String columnAcciones = btnEditar;
		
		for(MdlGrado_Seccion o : arrObjGradoSeccion) {
			
			String cantidad = "";
			String nivel = "";
			String estado_ingreso= "";
			
			if(o.getCantidad()<35) {
				estado_ingreso = "<span class='label label-success label-form'>Disponible</span>";
			}else {
				estado_ingreso = "<span class='label label-danger label-form'>Full</span>";
			}
			item = new JsonObject();
			item.addProperty("codigo",o.getCodigo());
			nivel = (o.getNivel().equals("P")) ? "Primaria": "Secundaria";
			item.addProperty("nivel",nivel);
			item.addProperty("grado",o.getGrado());
			item.addProperty("seccion",o.getSeccion());
			cantidad = (o.getCantidad() != 0) ? o.getCantidad()+"" : "<span class='label label-default label-form'>Vacio</span>";
			item.addProperty("cantidad_alumnos",cantidad);
			item.addProperty("estado_ingreso",estado_ingreso);
			data.add(item);
		}
		
		JsonObject gson = new JsonObject();
		gson.addProperty("recordsTotal", cantidadRegistros);
		gson.addProperty("recordsFiltered", cantidadRegistros);
		gson.addProperty("draw", 1);
        gson.add("datos", data);
        
        return gson;
	}


/*
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
			Dao_Crud_Alumno objDaoAlumno = new Dao_Crud_Alumno();
			
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
		Dao_Crud_Alumno objDaoAlumno = new Dao_Crud_Alumno();
		
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
*/
}