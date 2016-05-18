package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

import juego.Jugador;

public class JugadorBD {

	// Atributos necesarios de la conexion.
	private static Connection conexion;
	private static Statement orden = null;

	// Constructor, que permite realizar las consultas a la base de datos
	// mediante una conexion establecida anteriormente, la cual le mandamos por
	// parametros
	public JugadorBD(Connection c) {
		this.conexion = c;
	}

	// ----------------------------------------
	// ---- REGISTRAR UN JUGADOR
	// ----------------------------------------

	// Metodo que permite insertar un usuario en la base de datos
	public static void insertarJugador(Jugador j) {
		Integer aux = 0;
		try {
			// Conectarse a la conexion
			orden = conexion.createStatement();
			// Aqui metemos la sentecia SQL. En este caso es una sencilla
			// sentencia de tipo INSERT.
			// En sql, los VARCHAR van asi 'comilla simple'. "String normal de
			// java" = en sql = '"STRING para SQL"'
			String sql = "INSERT INTO mathdice (nombre,apellido1,apellido2,edad,puntos) " + "VALUES ('" + j.getNombre()
					+ "', '" + j.getPrimerApellido() + "', '" + j.getSegundoApellido() + "', " + j.getEdad() + ", "
					+ aux + ")";
			// Ejecutar la sentencia SQL que hemos escrito en la linea de arriba
			orden.executeUpdate(sql);
			System.out.println("Usuario registrado con exito");

			idNuevosJugadores(j);

		} catch (SQLException se) {
			// ERRORES DE SQL
			se.printStackTrace();
		} catch (Exception e) {
			// ERRORES DE JAVA
			e.printStackTrace();
		} finally {
			// ESTE APARTADO SIRVE PARA CERRAR LA CONEXION
			/*
			 * try{ if(orden!=null) conexion.close(); }catch(SQLException se){ }
			 * try{ //Comprueba que la conexion SIGUE ABIERTA para cerrarla
			 * if(conexion!=null) conexion.close(); }catch(SQLException se){
			 * se.printStackTrace(); }
			 */
		}

	}

	// ----------------------------------------
	// ---- OBTENER LA ID DE LOS JUGADORES QUE SE REGISTRAN
	// ----------------------------------------

	public static void idNuevosJugadores(Jugador j) {
		ResultSet rs;
		try {
			orden = conexion.createStatement();
			String sql = "SELECT ID FROM mathdice ORDER BY `ID` DESC LIMIT 1";
			rs = orden.executeQuery(sql);
			// Cogemos los usuarios
			while (rs.next()) {
				j.setId(rs.getInt("ID"));
			}
			// Debemos cerrar la conexion
			rs.close();
		} catch (SQLException se) {
			// Se produce un error con la consulta
			se.printStackTrace();
		} catch (Exception e) {
			// Se produce cualquier otro error
			e.printStackTrace();
		} finally {
		}
	}

	// ----------------------------------------
	// ---- RELLENAR EL COMBOBOX CON LOS JUGADORES DE LA BD
	// ----------------------------------------

	// Metodo para rellenar el JComboBox a partir de los datos de la base de
	// datos
	public static void extraerJugadoresBD(JComboBox jc) {
		ResultSet rs;
		try {
			orden = conexion.createStatement();
			String sql = "SELECT ID,nombre, apellido1, apellido2,puntos, edad FROM mathdice";
			rs = orden.executeQuery(sql);
			// Cogemos los usuarios
			while (rs.next()) {
				Integer idJugadorBD = rs.getInt("ID");
				String nombreJugadorBD = rs.getString("nombre");
				String apellido1JugadorBD = rs.getString("apellido1");
				String apellido2JugadorBD = rs.getString("apellido2");
				Integer edadJugadorBD = rs.getInt("edad");
				Integer puntosJugadorBD = rs.getInt("puntos");

				Jugador u = new Jugador(nombreJugadorBD, apellido1JugadorBD, apellido2JugadorBD, edadJugadorBD);
				u.setId(idJugadorBD);
				u.setPuntos(puntosJugadorBD);
				jc.addItem(u);

			}
			// Debemos cerrar la conexion
			rs.close();
		} catch (SQLException se) {
			// Se produce un error con la consulta
			se.printStackTrace();
		} catch (Exception e) {
			// Se produce cualquier otro error
			e.printStackTrace();
		} finally {

			/*
			 * //Cerramos los recursos try{ if(orden!=null) conexion.close();
			 * }catch(SQLException se){ } try{ if(conexion!=null)
			 * conexion.close(); }catch(SQLException se){ se.printStackTrace();
			 * }//end finally try
			 */
		}
	}

	// ----------------------------------------
	// ---- ACTUALIZAR PUNTUACION SI GANAS
	// ----------------------------------------

	public static void jugadorGana(Jugador u) {
		Integer puntosantiguos = u.getPuntos();
		Integer puntosnuevos = puntosantiguos + 5;
		u.setPuntos(puntosnuevos);
		try {
			orden = conexion.createStatement();

			String sql = "UPDATE mathdice " + "SET nombre = '" + u.getNombre() + "'" + ",apellido1 = '"
					+ u.getPrimerApellido() + "'" + ",apellido2 = '" + u.getSegundoApellido() + "'" + ",edad = "
					+ u.getEdad() + " " + ",puntos = " + puntosnuevos + " " + "WHERE id = " + u.getId();

			orden.executeUpdate(sql);
		} catch (SQLException se) {
			// Se produce un error con la consulta
			se.printStackTrace();
		} catch (Exception e) {
			// Se produce cualquier otro error
			e.printStackTrace();
		} finally {
			// Cerramos los recursos

			/*
			 * try{ if(orden!=null) conexion.close(); }catch(SQLException se){ }
			 * try{ if(conexion!=null) conexion.close(); }catch(SQLException
			 * se){ se.printStackTrace(); }//end finally try
			 */

		}
	}

	// ----------------------------------------
	// ---- ACTUALIZAR JUGADOR SI GANAS
	// ----------------------------------------

	public static void actualizarJugador(Jugador u) {

		try {
			orden = conexion.createStatement();

			String sql = "UPDATE mathdice " + "SET nombre = '" + u.getNombre() + "'" + ",apellido1 = '"
					+ u.getPrimerApellido() + "'" + ",apellido2 = '" + u.getSegundoApellido() + "'" + ",edad = "
					+ u.getEdad() + " " + ",puntos = " + u.getPuntos() + " " + "WHERE id = " + u.getId();

			orden.executeUpdate(sql);
		} catch (SQLException se) {
			// Se produce un error con la consulta
			se.printStackTrace();
		} catch (Exception e) {
			// Se produce cualquier otro error
			e.printStackTrace();
		} finally {
			// Cerramos los recursos

			/*
			 * try{ if(orden!=null) conexion.close(); }catch(SQLException se){ }
			 * try{ if(conexion!=null) conexion.close(); }catch(SQLException
			 * se){ se.printStackTrace(); }//end finally try
			 */

		}
	}
}
