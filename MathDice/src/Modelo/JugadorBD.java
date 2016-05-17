package Modelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

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
		
		
		
		//Metodo para rellenar el JComboBox a partir de los datos de la base de datos
		public void extraerJugadoresBD(JComboBox jc){
			ResultSet rs;
			try{
			  orden = conexion.createStatement();
		      String sql = "SELECT nombre, apellido1, apellido2, edad FROM mathdice";
		      rs = orden.executeQuery(sql);
		      //Cogemos los usuarios
		      while(rs.next()){
		    	  String nombreJugadorBD=rs.getString("nombre");
		    	  String apellido1JugadorBD=rs.getString("apellido1");
		    	  String apellido2JugadorBD=rs.getString("apellido2");
		    	  Integer edadJugadorBD=rs.getInt("edad");
		    	  
		    	  Jugador u=new Jugador(nombreJugadorBD,apellido1JugadorBD,apellido2JugadorBD,edadJugadorBD);	      
			      jc.addItem(u);
			      
		      }
		      //Debemos cerrar la conexion
		      rs.close();
			}catch(SQLException se){
				      //Se produce un error con la consulta
				      se.printStackTrace();
			}catch(Exception e){
				      //Se produce cualquier otro error
				      e.printStackTrace();
			}finally{
			      //Cerramos los recursos
			      try{
			         if(orden!=null)
			        	 conexion.close();
			      }catch(SQLException se){
			      }
			      try{
			         if(conexion!=null)
			        	 conexion.close();
			      	 }catch(SQLException se){
			         se.printStackTrace();
			      	 }//end finally try
			}
		}
	}
