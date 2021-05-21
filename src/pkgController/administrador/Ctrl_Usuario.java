package pkgController.administrador;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import pkgDao.administrador.Dao_Crud_Docente;
import pkgDao.administrador.Dao_Usuario;
import pkgModel.MdlUsuario;

public class Ctrl_Usuario {
	
	public static JsonObject ctrlListarUsuarios() {

		Dao_Usuario objDaoUsuario = new Dao_Usuario();
		ArrayList<MdlUsuario> arrObjUsuario = new ArrayList<MdlUsuario>();
		JsonArray data = new JsonArray();
		JsonObject item;
		
		//String paramLength = request.getParameter("length");
		//String paramStart = request.getParameter("start");
		
		int cantidadRegistros = objDaoUsuario.daoGetCountListUsuario();
		
		arrObjUsuario  = objDaoUsuario.daoGetListaUsuario();

		String btnSetCredentials = "<button type='button' id='btnShowModalCredentials' class='btn btn-default btn-square' data-toggle='modal' data-target='#mdlSetCredentialUsuario'><span data-toggle='tooltip' data-placement='bottom' title='Credenciales' class='fa fa-user'></span></button>";
		
		for(MdlUsuario o : arrObjUsuario) {
			String estado_acceso = "";
			String btnSwitchAccess = "";
			item = new JsonObject();
			item.addProperty("codigo",o.getCodigo());
			item.addProperty("nombre",o.getNombreUsuario());
			item.addProperty("apellido",o.getApellidoUsuario());
			item.addProperty("tipousuario",o.getTipoUsuarioString());
			if(o.getEstado_acceso_sistema() == 1) {  estado_acceso = "checked"; }
			if(o.getEstado_acceso_sistema() == 2 ) { estado_acceso = "disabled";  }
			btnSwitchAccess = "<label class='switch switch-small'>" + 
								 "<input type='checkbox' id='btnSwitchAccess' "+estado_acceso+" value= '"+ o.getEstado_acceso_sistema() +"'>" + 
								 "<span></span>" + 
							   "</label>";
			item.addProperty("accesoSistema",btnSwitchAccess);
			item.addProperty("acciones",btnSetCredentials);
			data.add(item);
		}
		
		JsonObject gson = new JsonObject();
		gson.addProperty("recordsTotal", cantidadRegistros);
		gson.addProperty("recordsFiltered", cantidadRegistros);
		gson.addProperty("draw", 1);
        gson.add("datos", data);
        
        return gson;
	}
	
	public static int ctrlSetCredentialUsuario(HttpServletRequest request) {
		Dao_Usuario objDaoUsuario = new Dao_Usuario();
		String codigo = request.getParameter("txtCod");
		String pass = request.getParameter("txtPass");
		int codigoExitoOperacion = 0;
		codigoExitoOperacion = objDaoUsuario.daoSetCredentialUsuario(codigo,pass);
		return codigoExitoOperacion;
	}
	
	public static int ctrlSetAccesoUsuario(HttpServletRequest request) {
		Dao_Usuario objDaoUsuario = new Dao_Usuario();
		String codigo = request.getParameter("codigo");
		String valorEstado = request.getParameter("valorEstado");
		int cambioOk = objDaoUsuario.daoSetAccesoUsuario(codigo, Integer.parseInt(valorEstado));
		return cambioOk;
	}
}
