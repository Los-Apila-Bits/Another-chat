package Ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Cliente;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class JChatCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cliente cliente;
	private String usuario;
	private JTextField textField;
	private JButton btnEnviar;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenuItem mntmConectar;
	private JMenu mnOpciones;
	private JScrollPane scrollPane;
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
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMsj();
				textField.setText("");
			}
		});
		textField.setEditable(false);
		textField.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setEnabled(false);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMsj();
				textField.setText("");
			}
		});
		btnEnviar.setBounds(335, 219, 89, 31);
		contentPane.add(btnEnviar);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 22);
		contentPane.add(menuBar);
		
		mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		mntmConectar = new JMenuItem("Conectar");
		mnOpciones.add(mntmConectar);
		mntmConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Ingrese nombre de usuario");
				cliente = new Cliente(20,"localhost");
				cliente.inicializarHiloCliente(iniciar());
				usuario = nombre;
				btnEnviar.setEnabled(true);
				textField.setEditable(true);
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 33, 414, 175);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
	}
	public JChatCliente iniciar() {
		return this;
	}
	
	public void escribirMensajeEnTextArea(String mensaje) {
		textArea.append(mensaje);
		try {
			sonidoMsj();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarMsj() {
		String message = textField.getText();
		if(!message.isEmpty())
			cliente.enviarMensaje(usuario+": "+message+"\n");
		return;
	}
	
	public void sonidoMsj() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		String soundName = "sonidos/sonido_msn.wav";
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	}
}
