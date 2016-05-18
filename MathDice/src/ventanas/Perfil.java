package ventanas;

import javax.swing.JPanel;

import juego.Jugador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Modelo.JugadorBD;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Perfil extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton actualizarDatos;
	private static JTextField textField_4;
	private JTextField textField_5;
	// Create the panel

	public Perfil(Jugador j) {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(34, 41, 85, 14);
		add(lblNewLabel);

		JLabel lblPrimerApellido = new JLabel("Primer apellido");
		lblPrimerApellido.setBounds(34, 84, 100, 14);
		add(lblPrimerApellido);

		JLabel lblSegundoApellido = new JLabel("Segundo apellido");
		lblSegundoApellido.setBounds(34, 129, 100, 14);
		add(lblSegundoApellido);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(34, 185, 85, 14);
		add(lblEdad);

		textField = new JTextField();
		textField.setBounds(171, 38, 422, 20);
		textField.setText(j.getNombre());
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(171, 81, 422, 20);
		textField_1.setText(j.getPrimerApellido());
		add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(171, 126, 422, 20);
		textField_2.setText(j.getSegundoApellido());
		add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(171, 182, 422, 20);
		textField_3.setText(String.valueOf(j.getEdad()));
		add(textField_3);

		// Boton para actualizar los datos.
		actualizarDatos = new JButton("Actualizar ");
		actualizarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
							
				
				try{				
					if (textField.getText().compareTo("")==0|| textField_1.getText().compareTo("")==0 || textField_2.getText().compareTo("")==0 || textField_3.getText().compareTo("")==0) {
						textField_5.setText("Rellene todos los campos");
					} else {

						j.setNombre(textField.getText());
						j.setPrimerApellido(textField_1.getText());
						j.setSegundoApellido(textField_2.getText());
						j.setEdad(Integer.valueOf(textField_3.getText()));

						JugadorBD.actualizarJugador(j);
						textField_5.setText("Perfil actualizado");
						Juego.cambiarLabelNombre(j.getNombre());
						
						
					}				
				} catch(Exception e) {
					textField_5.setText("Has introducido un valor de edad invalido");
					System.out.println(e);
					
				}
			}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			
		});
		actualizarDatos.setBounds(79, 241, 127, 36);
		add(actualizarDatos);

		JLabel lblPuntos = new JLabel("Puntos");
		lblPuntos.setBounds(34, 160, 46, 14);
		add(lblPuntos);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(171, 157, 422, 20);
		add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(String.valueOf(j.getPuntos()));
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setBounds(257, 228, 353, 49);
		add(textField_5);
		textField_5.setColumns(10);
		
		

	}
	
	public static void cambiarPuntosPantalla(String pnuevos){
		textField_4.setText(pnuevos);
	}
}
