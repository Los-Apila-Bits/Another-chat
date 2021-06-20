package Ventanas;

import javax.swing.JFrame;
import java.awt.EventQueue;
import datos.Sala;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class JLobby extends JFrame {

	private static final long serialVersionUID = 1L;
	private Sala sala;
	private JChatCliente cliente;
	JList list;
	JScrollPane listScroller;
	DefaultListModel model = new DefaultListModel();
	private JButton crearSalaButton;
	private JButton unirseSalaButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLobby frame = new JLobby();
					frame.setTitle("Chat");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JLobby() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(50, 50, 358, 288);
		sala = new Sala();

		crearSalaButton = new JButton("Crear Sala");
		iniButtonCrearSala();
		list = new JList();
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listScroller.setBounds(10, 33, 319, 175);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setBounds(29, 46, 300, 300);
		getContentPane().add(listScroller);
		unirseSalaButton = new JButton("Unirse");
		iniButtonUnirse();
		iniTextField();
	}

	public void iniTextField() {
	}

	public void iniButtonCrearSala() {
		crearSalaButton.setBounds(229, 216, 100, 23);
		getContentPane().add(crearSalaButton);
		crearSalaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sala.crearSala();
				String nombreSala = JOptionPane.showInputDialog("Ingrese el nombre de la sala");
				model.addElement(nombreSala);
				list.setModel(model);
			}
		});
	}

	public void iniButtonUnirse() {
		unirseSalaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				run();
			}
		});
		unirseSalaButton.setBounds(130, 216, 89, 23);
		getContentPane().add(unirseSalaButton);
		JLabel lblSalas = new JLabel("Salas");
		lblSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSalas.setBounds(10, 13, 46, 17);
		getContentPane().add(lblSalas);
	}

	public void run() {
		try {
			cliente = new JChatCliente();
			if (list.getSelectedIndex() == -1)
				JOptionPane.showMessageDialog(null, "Seleccione una sala");
			else {
				cliente.unirseSala(9000 + list.getSelectedIndex());
				cliente.setTitle("Chat");
				cliente.setVisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
