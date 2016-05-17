package main;

import java.awt.EventQueue;
import javax.swing.JFrame;

import ventanas.Login;
import ventanas.Registro;

public class Principal extends JFrame{
	
	// Punto de entrada de nuestra aplicación
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Creamos un objeto de tipo VentanaInicio llamado frame
							
					Login frame = new Login();
					
					//Registro frame = new Registro();
					
					
					//Aquí hacemos visible el objeto frame
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
