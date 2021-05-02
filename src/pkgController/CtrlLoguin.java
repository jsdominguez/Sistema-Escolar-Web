package pkgController;

import pkgDao.DaoLoguin;
import pkgModel.MdlAlumno;

public class CtrlLoguin {
	
	public static boolean CtrlValidarUsuario(String txtUser,String txtPass) {
		return new DaoLoguin().DaoValidarLoguin(txtUser,txtPass);
	}
	 
	public static MdlAlumno CtrlObtenerDatosLoguin(String txtUser,String txtPass) {
		MdlAlumno objAlumno = new MdlAlumno();
		objAlumno = new DaoLoguin().DaoObtenerDatosLoguin(txtUser,txtPass);
		return objAlumno;
	}
}
