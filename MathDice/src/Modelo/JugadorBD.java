package Modelo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import juego.Jugador;

public class JugadorBD {

	
		
		//Atributos necesarios de la conexion.
		private static Connection conexion;
		private static Statement orden = null;
		
		//Constructor, que permite realizar las consultas a la base de datos mediante una conexion establecida anteriormente, la cual le mandamos por parametros
		public JugadorBD(Connection c) {
			this.conexion=c;
		}
		
		//Metodo que permite insertar un usuario en la base de datos
		public static void insertarJugador(Jugador j){
			try{
				//Conectarse a la conexion
				orden = conexion.createStatement();
				//Aqui metemos la sentecia SQL. En este caso es una sencilla sentencia de tipo INSERT.
				//En sql, los VARCHAR van asi 'comilla simple'. "String normal de java" = en sql = '"STRING para SQL"'
			    String sql = "INSERT INTO mathdice (nombre,apellido1,apellido2,edad) "+"VALUES ('"+j.getNombre()+"', '"+j.getPrimerApellido()+"', '"+j.getSegundoApellido()+"', "+j.getEdad()+")";
			    //Ejecutar la sentencia SQL que hemos escrito en la linea de arriba
			    orden.executeUpdate(sql);
			    System.out.println("Usuario registrado con exito");
			   }catch(SQLException se){	
				   	  // ERRORES DE SQL
				      se.printStackTrace();
			   }catch(Exception e){	
				   	  // ERRORES DE JAVA
				      e.printStackTrace();
			   }finally{
				     //ESTE APARTADO SIRVE PARA CERRAR LA CONEXION
				      try{
				         if(orden!=null)
				        	 conexion.close();
				      }catch(SQLException se){
				      }
				      try{
				    	  //Comprueba que la conexion SIGUE ABIERTA para cerrarla
				         if(conexion!=null)
				        	 conexion.close();
				      	 }catch(SQLException se){
				         se.printStackTrace();
				      }
				}
		}
	}
