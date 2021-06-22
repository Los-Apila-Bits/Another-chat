package Ventanas;

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
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class JChatCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Cliente cliente;
	private String usuario;
	private JTextField textField;
	private JButton btnEnviar;
	private JTextArea textArea;
	private JScrollPane scrollPane;
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JChatCliente frame = new JChatCliente();
//					frame.setTitle("Chat");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public JChatCliente() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//cliente.cerrarSocket();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		// textField.setEditable(false);
		textField.setColumns(10);

		btnEnviar = new JButton("Enviar");
		// btnEnviar.setEnabled(false);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMsj();
				textField.setText("");
			}
		});
		btnEnviar.setBounds(335, 219, 89, 31);
		contentPane.add(btnEnviar);

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
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = now.format(format);
		if (!message.isEmpty())
			cliente.enviarMensaje("[" + formatDateTime + "] " + usuario + ": " + message + "\n");
		return;
	}

	public void sonidoMsj() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		String soundName = "sonidos/sonido_msn.wav";
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	}

	public void unirseSala(int puerto) {
		String nombre = JOptionPane.showInputDialog("Ingrese nombre de usuario");
		cliente = new Cliente(puerto, "localhost");
		cliente.inicializarHiloCliente(iniciar());
		usuario = nombre;
		btnEnviar.setEnabled(true);
		textField.setEditable(true);
	}
}
