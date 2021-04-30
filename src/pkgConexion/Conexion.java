package pkgConexion;

import java.util.Properties;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;

public class Conexion {
		
	Connection conn;
	
	public Conexion() {
		
		Properties prop;
		String dbgestor,server,port,database,user,password;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//prop = new Properties();
			//fileReader = lee caracter por caracter un archivo que contiene texto
			//BufferedReader = le ayuda a obtener toda una linea de caracteres y potencia para ser mas rapida la lectura  
			//FileReader fileConfigurcion =  new FileReader("../../config.properties");
			//BufferedReader leerPropiedades = new BufferedReader(fileConfigurcion);
			//prop.load(leerPropiedades);
			
			//obtenemos las propiedades del rchivo de configuracion
			dbgestor = "mysql";//prop.getProperty("dbgestor");
			server = "localhost";//prop.getProperty("server");
			port = "3306";//prop.getProperty("port");
			database = "demodb";//prop.getProperty("database");
			user = "root";//prop.getProperty("user");
			password = "";//prop.getProperty("password");
			
			//unimos la ruta y obtenemos de conexion
			this.conn = DriverManager.getConnection("jdbc:"+dbgestor+"://"+server+":"+port+"/"+database,user,password);
		
		}catch(Exception e){
			System.out.println("[X] CONEXION FALLIDA[X]");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		System.out.println("conectado");
		return this.conn;
	}
	
	public void desconectar() {
		
		if(this.conn != null) {
			try {
				this.conn.close();
			}catch(Exception e) {
				System.out.println("[X] DESCONEXION FALLIDA[X]");
				e.printStackTrace();
			}
		}	
	}
	
	
}
