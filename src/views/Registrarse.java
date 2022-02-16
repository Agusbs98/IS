package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Controller;
import Usuarios.*;

public class Registrarse extends JPanel{
	private Controller _ctrl;
	
	public Registrarse(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
	}
	private void initGUI() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel tabla = new JPanel();
		
		tabla.setVisible(false);
		add(tabla);
		
		JButton Pac = new JButton("Paciente");
		JButton Med = new JButton("Medico");
		Pac.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabla.add(RegUsuario(1));
				tabla.setVisible(true);
				Med.setVisible(false);
				Pac.setVisible(false);
				
			}
			
		});
		Med.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabla.add(RegUsuario(0));
				tabla.setVisible(true);
				Med.setVisible(false);
				Pac.setVisible(false);
			}
			
		});
		add(Pac);
		add(Med);
		
	}
	
	
	private JPanel RegUsuario(int n) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JPanel tabla = new JPanel(new GridLayout(0,2,5,5));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		JTextField correo = new JTextField(10);
		tabla.add(new JLabel("Correo: "),BorderLayout.CENTER);
		tabla.add(correo,BorderLayout.CENTER);
		
		JPasswordField contra = new JPasswordField(10);
		contra.setEchoChar('*');
		tabla.add(new JLabel("Contraseña: "),BorderLayout.CENTER);
		tabla.add(contra, BorderLayout.CENTER);
		
		JTextField nombre = new JTextField(10);
		tabla.add(new JLabel("Nombre: "));
		tabla.add(nombre);
		
		JTextField dni = new JTextField(10);
		tabla.add(new JLabel("DNI: "));
		tabla.add(dni);
		
		JTextField tel = new JTextField(10);
		tabla.add(new JLabel("Telefono: "));
		tabla.add(tel);
		JTextField especialidad = new JTextField(10);
		JTextField consulta = new JTextField(10);
		if(n == 0) {
			tabla.add(new JLabel("Especialidad: "),BorderLayout.CENTER);
			tabla.add(especialidad,BorderLayout.CENTER);
			tabla.add(new JLabel("Consulta: "),BorderLayout.CENTER);
			tabla.add(consulta, BorderLayout.CENTER);
		}
		
		panel.add(tabla);
		
		
		JButton OK = new JButton("OK");
		JButton Cancel = new JButton("Cancel");
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String cont = String.copyValueOf(contra.getPassword());
					int cons = consulta.getText().length() > 0? Integer.parseInt(consulta.getText()):0;
					Usuario us = null;
					if(!nombre.getText().equals("") && !correo.getText().equals("") && !cont.equals("") && !dni.getText().equals("") && !tel.getText().equals("")) {
						if(n == 0)
							us = new Medico(nombre.getText(),correo.getText(), cont,  dni.getText(),tel.getText(),especialidad.getText(),cons);
						else {
							us = new Paciente(nombre.getText(),correo.getText(), cont,  dni.getText(),tel.getText());
						}
					}
					_ctrl.registrarse(us);
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(OK.getParent(), ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
						_ctrl.cerrarSesion();
					}
				setVisible(false);
			}
		});
		Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				_ctrl.cerrarSesion();
			}
		});
		buttonPanel.add(Cancel);
		buttonPanel.add(Box.createRigidArea(new Dimension(20,20)));
		buttonPanel.add(OK);
		panel.add(buttonPanel);
		return panel;
	}
	

}
