package views;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class iniSesion extends JPanel{
	private Controller _ctrl;
	
	public iniSesion(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();	
	}
	
	
	public void initGUI() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel supPanel = new JPanel(new FlowLayout());
		JPanel infPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		supPanel.setAlignmentX(CENTER_ALIGNMENT);
		infPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		JTextField correo = new JTextField(10);
		JPasswordField contra = new JPasswordField(10);
		contra.setEchoChar('*');

		supPanel.add(new JLabel("DNI: "));
		supPanel.add(correo);
		
		infPanel.add(new JLabel("Contraseña:  "));
		infPanel.add(contra);
		
		add(supPanel);
		add(infPanel);
		
		JButton OK = new JButton("OK");
		JButton Cancel = new JButton("Cancel");

		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//añadir las acciones de confirmar
				String cor = correo.getText();
				String cont = String.copyValueOf(contra.getPassword());
				setVisible(false);
				try {
					_ctrl.iniciarSesion(cor, cont);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(OK.getParent(), "ERROR al iniciar sesion.", "ERROR", JOptionPane.ERROR_MESSAGE );
					_ctrl.cerrarSesion();
				}
				
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
		add(buttonPanel);
		
		
	}

}
