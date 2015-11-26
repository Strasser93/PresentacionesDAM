package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jugador.Jugador;

public class Principal extends JFrame{
	// Variables
	private JPanel contentPane;
	private JTextField caja1;
	private JTextField caja2;
	private JTextField caja3;
	private JTextField caja4;

	// Punto de entrada de nuestra aplicaci�n
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// Constructor

	public Principal() {

		// Propiedades de ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// texto1
		JLabel texto1 = new JLabel("DATOS DEL JUGADOR");
		texto1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		texto1.setBounds(10, 11, 242, 25);
		contentPane.add(texto1);

		// texto2
		JLabel texto2 = new JLabel("Nombre");
		texto2.setBounds(10, 69, 46, 14);
		contentPane.add(texto2);

		// texto3
		JLabel texto3 = new JLabel("Apellidos");
		texto3.setBounds(10, 94, 76, 14);
		contentPane.add(texto3);

		// texto4
		JLabel texto4 = new JLabel("Edad");
		texto4.setBounds(10, 119, 46, 14);
		contentPane.add(texto4);

		// caja de texto 1
		caja1 = new JTextField();
		caja1.setBounds(107, 66, 286, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);

		// caja de texto 2
		caja2 = new JTextField();
		caja2.setColumns(10);
		caja2.setBounds(107, 91, 286, 20);
		contentPane.add(caja2);

		// caja de texto 3
		caja3 = new JTextField();
		caja3.setColumns(10);
		caja3.setBounds(107, 116, 286, 20);
		contentPane.add(caja3);

		// caja de texto 4
		caja4 = new JTextField();
		caja4.setBounds(10, 188, 414, 62);
		contentPane.add(caja4);
		caja4.setColumns(10);

		// Boton
		JButton boton1 = new JButton("A JUGAR");
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (caja1.getText().equals("") || caja2.getText().equals("") || caja3.getText().equals("")) {
					caja4.setText("Rellene todos los campos");
				} else {
					Jugador j = new Jugador(caja1.getText(), caja2.getText(), Integer.valueOf(caja3.getText()));
					caja4.setText(
							"Player " + j.getNombre() + " " + j.getApellidos() + " de edad " + j.getEdad() + " creado");
				}

			}
		});
		boton1.setBounds(10, 154, 383, 23);
		contentPane.add(boton1);

	}
}
