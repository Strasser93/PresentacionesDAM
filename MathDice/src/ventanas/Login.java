package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import Modelo.ConexionBD;
import Modelo.JugadorBD;
import juego.Jugador;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	private Login ref;
	
	private JButton botonJugar;
	private JButton botonRegistrarse;
	private JComboBox comboBoxJugadores;
	private Jugador jugadorSeleccionado;

	
	private ConexionBD conexion1 = new ConexionBD();		
	private Connection conexion = (Connection) conexion1.getConexion();
	private JugadorBD jugadorBD = new JugadorBD(conexion);
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("MathDice");
		ref=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//ComboBox de los Jugadores
		comboBoxJugadores = new JComboBox();
		comboBoxJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugadorSeleccionado=(Jugador) comboBoxJugadores.getSelectedItem();
				
			}
		});
		comboBoxJugadores.setBounds(108, 52, 244, 22);
		contentPane.add(comboBoxJugadores);
		
		jugadorBD.extraerJugadoresBD(comboBoxJugadores);
		
		
		
		//Boton Jugar
		botonJugar = new JButton("Jugar");
		botonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContenedorVentanas ven2 = new ContenedorVentanas();	
				ven2.inicializacion(jugadorSeleccionado);
				ref.dispose();
				ven2.setVisible(true);
				
			}
		});
		botonJugar.setBounds(108, 101, 108, 23);
		contentPane.add(botonJugar);
		
		
		
		//Boton de registro
		botonRegistrarse = new JButton("Registrarse");
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro ven1 = new Registro();				
				ref.dispose();
				ven1.setVisible(true);
			}
		});
		botonRegistrarse.setBounds(235, 101, 117, 23);
		contentPane.add(botonRegistrarse);
		
	
	}

}