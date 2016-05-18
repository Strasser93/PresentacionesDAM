package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
	//Un objeto de tipo connection que se inicializa a null (relacionado con el try catch)
	private static Connection connect = null;
	//Instanciar=crear objeto
	//Preparar un huevo para que cuando se cree la conexion, se almacene y no se creen mas a pesar de que puede parecer que estamos creando mas
	private static ConexionBD INSTANCE = null;
	
	private ConexionBD() {
		try {
		     //Cargar la clase en ejecucion del driver que usemos 
		      Class.forName("com.mysql.jdbc.Driver");
		      
		      //En esta linea se realiza la conexion (login)
		      //jbdc = porque es driver que usamos
		      //mysql = porque es la base de datos que vamos a usar y es la que nos relaciona con el driver
		      //localhost=porque el servidor al que accedemos es un servidor local. Aqui se cambiaria por una IP en caso de conectarnos a un server remoto
		      //mathdice = Es el nombre de la base de datos 
		      connect = DriverManager.getConnection("jdbc:mysql://localhost/mathdice?user=strasser&password=");
		     System.out.println("Conexion realizada");
	    
		} catch (Exception e) {
			//Recoje el error que muestra Java (Se hace asi por el try catch)
	        System.out.println(e);
	        System.out.println("LA CONEXION FALLO");
	    } 
	}
	
	//El profesor explico que este metodo es util porque vamos a abrir la conexion 1 vez, y la mantendremos abierta hasta el final de ejecucion
	//Con este metodo recuperamos la conexion en curso.
	public static Connection getConexion(){
		if (INSTANCE==null){
			INSTANCE=new ConexionBD();
		}
		
			return connect;
	}

}