package pkgController.administrador;


import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pkgDao.administrador.Dao_Admin_Crud_Docente;
import pkgModel.MdlDocente;

public class Ctrl_Admin_Crud_Docente {
	
	public static JsonObject ctrlListarDocente() {

		Dao_Admin_Crud_Docente objDaoDocente = new Dao_Admin_Crud_Docente();
		ArrayList<MdlDocente> arrObjDocente = new ArrayList<MdlDocente>();
		JsonArray data = new JsonArray();
		JsonObject item;
		
		//String paramLength = request.getParameter("length");
		//String paramStart = request.getParameter("start");
		
		int cantidadRegistros = objDaoDocente.daoGetCountListDocente();
		
		arrObjDocente  = objDaoDocente.daoGetListaDocente();

		String btnEditar = "<button type='button' id='btnShowModalEdit' class='btn btn-info btn-square' data-toggle='modal' data-target='#modalRegisterOrUpdate'><span data-toggle='tooltip' data-placement='bottom' title='Editar' class='fa fa-edit'></span></button>";
		//String btnEliminar = "<button type='button' id='btnShowModalDelete' class='btn btn-danger btn-square' data-tooltip='tooltip' data-placement='bottom' title='Eliminar'><span class='fa fa-trash-o'></span></button>";
		String btnSetCredentials = "<button type='button' id='btnShowModalCredentials' class='btn btn-default btn-square' data-toggle='modal' data-target='#mdlSetCredentialDocente'><span data-toggle='tooltip' data-placement='bottom' title='Credenciales' class='fa fa-user'></span></button>";
		String btnSwitchAccess = "";
		String columnAcciones = btnEditar+btnSetCredentials;
		
		for(MdlDocente o : arrObjDocente) {
			String estado_acceso = "";
			item = new JsonObject();
			item.addProperty("Codigo",o.getCodDocente());
			item.addProperty("Nombre",o.getNomDocente());
			item.addProperty("Apellido",o.getApeDocente());
			item.addProperty("Edad",o.getEdadDocente());
			item.addProperty("Dni",o.getDniDocente());
			item.addProperty("FechaNac",o.getFechaNac());
			if(o.getEstado_acceso() == 1) {  estado_acceso = "checked"; }
			if(o.getEstado_acceso() == 2 ) { estado_acceso = "disabled";  }
			btnSwitchAccess = "<label class='switch switch-small'>" + 
								 "<input type='checkbox' id='btnSwitchAccess' "+estado_acceso+" value= '"+ o.getEstado_acceso() +"'>" + 
								 "<span></span>" + 
							   "</label>";
			//onclick=\"fnSinAccesoDocente(this,'"+o.getCodDocente()+"')\"
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
	
	
	public static int ctrlRegisterDocente(HttpServletRequest request) {
		
		int codigoExitoOperacion = 0;
		
		try {
			
			String nombre = request.getParameter("txtNombre");
			String apellido = request.getParameter("txtApellido");
			String edad = request.getParameter("txtEdad");
			String dni = request.getParameter("txtDni");
			String fechaNac = request.getParameter("txtFechaNac");

			MdlDocente objDocente = new MdlDocente();
			Dao_Admin_Crud_Docente objDaoDocente = new Dao_Admin_Crud_Docente();
			
			String codigoGenerado = objDaoDocente.daoGetGenerarCodigoDocente();
			objDocente.setCodDocente(codigoGenerado);
			objDocente.setNomDocente(nombre);
			objDocente.setApeDocente(apellido);
			objDocente.setEdadDocente(Integer.parseInt(edad));
			objDocente.setDniDocente(Integer.parseInt(dni));
			objDocente.setFechaNac(fechaNac);
			
			Part fileForm = request.getPart("fileImagen");
			InputStream imageBinary[] = new InputStream[2];
			
			if(fileForm.getSize()>0) {
				imageBinary[0] = fileForm.getInputStream();
				imageBinary[1] = fileForm.getInputStream();
			}
			codigoExitoOperacion = objDaoDocente.daoRegistrarDocente(objDocente,imageBinary);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		return codigoExitoOperacion;

	}
	
	public static int ctrlUpdateDocente(HttpServletRequest request) {
		
		String codDocente = request.getParameter("txtCodigo");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String edad = request.getParameter("txtEdad");
		String dni = request.getParameter("txtDni");
		String fechaNac = request.getParameter("txtFechaNac");
		
		int codigoExitoOperacion = 0;
		
		MdlDocente objDocente = new MdlDocente();
		Dao_Admin_Crud_Docente objDaoDocente = new Dao_Admin_Crud_Docente();
		
		objDocente.setCodDocente(codDocente);
		objDocente.setNomDocente(nombre);
		objDocente.setApeDocente(apellido);
		objDocente.setEdadDocente(Integer.parseInt(edad));
		objDocente.setDniDocente(Integer.parseInt(dni));
		objDocente.setFechaNac(fechaNac);

		codigoExitoOperacion = objDaoDocente.daoUpdateDocente(objDocente);
		return codigoExitoOperacion;
	}
	
	public static int ctrlEliminarDocente(String idDocente) {
		int codigoExitoOperacion = 0;
		int parseIdDocente = Integer.parseInt(idDocente);
		Dao_Admin_Crud_Docente objDaoDocente = new Dao_Admin_Crud_Docente();
		codigoExitoOperacion = objDaoDocente.daoEliminarDocente(parseIdDocente);
		return codigoExitoOperacion;
	}
	
	public static int ctrlSetCredentialDocentes(HttpServletRequest request) {
		Dao_Admin_Crud_Docente objDaoDocente = new Dao_Admin_Crud_Docente();
		String codigo = request.getParameter("txtCod");
		String pass = request.getParameter("txtPass");
		int codigoExitoOperacion = 0;
		codigoExitoOperacion = objDaoDocente.daoSetCredentialDocentes(codigo,pass);
		return codigoExitoOperacion;
	}
	
	public static int ctrlSetAccesoUsuario(HttpServletRequest request) {
		Dao_Admin_Crud_Docente objDaoDocente = new Dao_Admin_Crud_Docente();
		String codigo = request.getParameter("codDocente");
		String valorEstado = request.getParameter("valorEstado");
		int cambioOk = objDaoDocente.daoSetAccesoUsuario(codigo, Integer.parseInt(valorEstado));
		return cambioOk;
	}
}
