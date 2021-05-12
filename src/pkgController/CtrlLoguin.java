package pkgController;

import pkgDao.DaoUsuario;
import pkgModel.MdlDocente;
import pkgModel.MdlUsuario;

public class CtrlLoguin {
	
	public static boolean CtrlValidarUsuario(String txtUser,String txtPass) {
		return new DaoUsuario().DaoValidarLoguin(txtUser,txtPass);
	}
	 
	public static boolean CtrlValidarAccesoLoguin(String txtUser,String txtPass) {
		return new DaoUsuario().daoValidarAccesoLoguin(txtUser,txtPass);
	}
	
	public static MdlUsuario CtrlObtenerDatosLoguin(String txtUser,String txtPass) {
		MdlUsuario objUsuario = new MdlUsuario();
		objUsuario = new DaoUsuario().DaoObtenerDatosLoguin(txtUser,txtPass);
		return objUsuario;
	}

}
