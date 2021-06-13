package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Cliente;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JChatCliente extends JFrame {

	private JPanel contentPane;
	private Cliente cliente;
	private String usuario;
	private JTextField textField;
	private JButton btnEnviar;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenuItem mntmConectar;
	private JMenu mnOpciones;
	public void escribirMensajeEnTextArea(String mensaje) {
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JChatCliente frame = new JChatCliente();
					frame.setTitle("Chat");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JChatCliente() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 219, 312, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cliente.enviarMensaje(textField.getText());
				textArea.setText("Hola");
			}
		});
		btnEnviar.setBounds(335, 219, 89, 31);
		contentPane.add(btnEnviar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 42, 387, 166);
		contentPane.add(textArea);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 22);
		contentPane.add(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmConectar = new JMenuItem("Conectar");
		mnOpciones.add(mntmConectar);
		
		
	}
}
