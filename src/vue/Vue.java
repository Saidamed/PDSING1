package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import socket.TestCreateSocket;



public class Vue extends JFrame{

	private static final long serialVersionUID = 3328699905787266648L;
	public JTextField nomv = new JTextField("nom");
	private JButton ajouter = new JButton("ajouter");
	private JPanel container = new JPanel();
	
	public Vue() {
		this.setLocationRelativeTo(null);
		this.setTitle("test");
		this.setSize(600, 600);
		this.setResizable(false);
		
	
		ajouter.addActionListener(new ajouterPersonne());
		JPanel panneau = new JPanel();
		container.setLayout(new BorderLayout());
		panneau.add(nomv);
		panneau.add(ajouter);
		panneau.setBackground(Color.white);
		container.add(panneau, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(container);
		this.setVisible(true);
	}
	

	//Lorsque l'on appui sur le bouton les données sont transmises du serveur à la BD.
	private class ajouterPersonne implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			String nom = nomv.getText();
			new TestCreateSocket(nom);
			}
		}
	
}
