package pkgController.administrador;


import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

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

		String btnEditar = "<button type='button' id='btnEditDatosModalAlumno' class='btn btn-info btn-square' data-toggle='modal' data-target='#modalRegisterOrUpdate' data-tooltip='tooltip' data-placement='bottom' title='Editar'><span class='fa fa-edit'></span></button>";
		String btnSetCredentials = "<button type='button' id='btnShowModalCredentials' class='btn btn-default btn-square' data-toggle='modal' data-target='#mdlSetCredentialAlumno' data-tooltip='tooltip' data-placement='bottom' title='Credenciales'><span class='fa fa-user'></span></button>";
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
		
		String foto = guadarArchivo(request,codigoGenerado);
		codigoExitoOperacion = objDaoAlumno.daoRegistrarAlumno(objAlumno,foto);
		return codigoExitoOperacion;
	}

public static int ctrlUpdateAlumno(HttpServletRequest request) {
	
	String codAlumno = request.getParameter("txtCodigo");
	String nombre = request.getParameter("txtNombre");
	String apellido = request.getParameter("txtApellido");
	String edad = request.getParameter("txtEdad");
	String dni = request.getParameter("txtDni");
	
	int codigoExitoOperacion = 0;
	
	MdlAlumno objAlumno = new MdlAlumno();
	Dao_Admin_Crud_Alumno objDaoAlumno = new Dao_Admin_Crud_Alumno();
	
	objAlumno.setCodAlumno(codAlumno);
	objAlumno.setNomAlumno(nombre);
	objAlumno.setApeAlumno(apellido);
	objAlumno.setEdadAlumno(Integer.parseInt(edad));
	objAlumno.setDniAlumno(Integer.parseInt(dni));

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
	int cambioOk = objDaoAlumno.daoSetAccesoUsuario(codigo, Integer.parseInt(valorEstado));
	return cambioOk;
}

private static String guadarArchivo(HttpServletRequest request,String codigoGenerado) {
	
	String UPLOAD_DIR = "C:/Users/Jhosep/Desktop/Eclipse/ProyectoIntegrador2/WebContent/Vistas/Administrador/Alumno/imgAlumnos/";
	String foto="";
	
    try {
    	
    	File fileSaveDir = new File(UPLOAD_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
        //System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        String extension = null;
        
        for (Part part : request.getParts()) {
        	extension = obtenerArchivo(part);
            if(extension!=""){
            	//System.out.println(UPLOAD_DIR+ codigoGenerado+"."+extension);
            	foto = codigoGenerado+"."+extension;
            	part.write(UPLOAD_DIR+ codigoGenerado+"."+extension);
            }
        }
        
    }catch(Exception e) {
    	e.printStackTrace();
    	System.out.println("[*] ERROR : Ctrl_Admin_Crud_Alumno : guadarArchivo [*]");
    }
    
    return foto;
}

private static String obtenerArchivo(Part part) {
    
	String contentDisp = part.getHeader("content-disposition");
    //System.out.println("content-disposition header= "+contentDisp);
    String[] tokens = contentDisp.split(";");
    
    for (String token : tokens) {
        if (token.trim().startsWith("filename")) {
        	
            String fileName_extension = token.substring(token.indexOf("=") + 2, token.length()-1);
            String extension = fileName_extension.split("\\.")[1];
            return extension;
        }
    }
    
    return "";
}
	
}
