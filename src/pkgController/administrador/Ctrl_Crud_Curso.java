package pkgController.administrador;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pkgDao.administrador.Dao_Crud_Curso;
import pkgModel.MdlCurso;



public class Ctrl_Crud_Curso {

	public static JsonObject ctrlListarCursos() {

		Dao_Crud_Curso objDaoCurso = new Dao_Crud_Curso();
		ArrayList<MdlCurso> arrObjCurso = new ArrayList<MdlCurso>();
		JsonArray data = new JsonArray();
		JsonObject item;
		
		//String paramLength = request.getParameter("length");
		//String paramStart = request.getParameter("start");
		
		int cantidadRegistros = objDaoCurso.daoGetCountListCurso();
		
		arrObjCurso  = objDaoCurso.daoGetListaCursos();

		String btnEditar = "<button type='button' id='btnEditDatosModalCurso' class='btn btn-info btn-square' data-toggle='modal' data-target='#modalRegisterOrUpdateCursos'><span data-toggle='tooltip' data-placement='bottom' title='Editar' class='fa fa-edit'></span></button>";
		
		for(MdlCurso o : arrObjCurso) {
			String nivel = "";
			item = new JsonObject();
			item.addProperty("codigo",o.getCodCurso());
			item.addProperty("nomCurso",o.getNomCurso());
			nivel = (o.getNivel().equals("P")) ? "Primaria" : "Secundaria";
			item.addProperty("nivel",nivel);
			item.addProperty("nivelCodigo",o.getNivel());
			item.addProperty("acciones",btnEditar);
			data.add(item);
		}
		
		JsonObject gson = new JsonObject();
		gson.addProperty("recordsTotal", cantidadRegistros);
		gson.addProperty("recordsFiltered", cantidadRegistros);
		gson.addProperty("draw", 1);
        gson.add("datos", data);
        
        return gson;
	}



	public static int ctrlRegisterCurso(HttpServletRequest request) {
	
		int codigoExitoOperacion = 0;
	
		try {
			String nombre = request.getParameter("txtNombre");
			String nivel = request.getParameter("selectNivel");
			String grado = request.getParameter("selectGrado");
			String codigo = "";
			
			if(nivel.equals("S")) {
				switch(grado) {
				 	case "1": codigo = "CSC100";break;
				 	case "2": codigo = "CSC200";break;
				 	case "3": codigo = "CSC300";break;
				 	case "4": codigo = "CSC400";break;
				 	case "5": codigo = "CSC500";break;
				 	case "6": codigo = "ALL";break;
				}
			}else{
				switch(grado) {
				 	case "1": codigo = "CPR100";break;
				 	case "2": codigo = "CPR200";break;
				 	case "3": codigo = "CPR300";break;
				 	case "4": codigo = "CPR400";break;
				 	case "5": codigo = "CPR500";break;
				 	case "6": codigo = "ALL";break;
			 	}
			}
			
			MdlCurso objCurso = new MdlCurso();
			Dao_Crud_Curso objDaoCurso = new Dao_Crud_Curso();
			
			//String codigoGenerado = objDaoCurso.daoGetGenerarCodigoCurso();
			objCurso.setCodCurso(codigo);
			objCurso.setNomCurso(nombre);
			objCurso.setNivel(nivel);
			objCurso.setGrado(Integer.parseInt(grado));
			
			codigoExitoOperacion = objDaoCurso.daoRegistrarCursos(objCurso);
		
			}catch(Exception e) { e.printStackTrace();	}
			
			return codigoExitoOperacion;
	}

	public static int ctrlUpdateCurso(HttpServletRequest request) {
		
		int codigoExitoOperacion = 0;
		
		String codigo = request.getParameter("txtCodigo");
		String nombre = request.getParameter("txtNombre");
		String nivel = request.getParameter("selectNivel");
		String grado = request.getParameter("selectGrado");
		int gradoParseado = Integer.parseInt(grado);
		
		MdlCurso objCurso = new MdlCurso();
		Dao_Crud_Curso objDaoCurso = new Dao_Crud_Curso();
		
		objCurso.setCodCurso(codigo);
		objCurso.setNomCurso(nombre);
		objCurso.setNivel(nivel);
		objCurso.setGrado(gradoParseado);
	
		codigoExitoOperacion = objDaoCurso.daoUpdateCurso(objCurso);
		return codigoExitoOperacion;
	}
	
}
