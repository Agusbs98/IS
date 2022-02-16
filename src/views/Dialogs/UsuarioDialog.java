package views.Dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Controller;

public class UsuarioDialog extends JDialog{
	private Controller _ctrl;
	
	public UsuarioDialog(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel lista = new JPanel(new GridLayout(0,2,5,5));
		lista.add(new JLabel("Correo: "));
		JTextField correo = new JTextField();
		correo.setSize(new Dimension(20,50));
		lista.add(correo);
		JPasswordField contra = new JPasswordField(10);
		contra.setSize(new Dimension(20,50));
		contra.setEchoChar('*');
		lista.add(new JLabel("Contraseña: "));
		lista.add(contra, BorderLayout.CENTER);
		JTextField tel = new JTextField(10);
		tel.setSize(new Dimension(20,50));
		lista.add(new JLabel("Telefono: "));
		lista.add(tel);
		JPanel barra = new JPanel();
		barra.setLayout(new BoxLayout(barra, BoxLayout.X_AXIS));
		JButton OK = new JButton("OK");
		JButton Cancel = new JButton("Cancel");
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					_ctrl.editaUsuario(String.copyValueOf(contra.getPassword()), correo.getText(), tel.getText());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
				}
				setVisible(false);
			}
		});
		Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		barra.add(Cancel);
		barra.add(OK);
		mainPanel.add(new JLabel("Editar Datos Del Usuario"));
		mainPanel.add(Box.createRigidArea(new Dimension(20,20)));
		mainPanel.add(lista, BorderLayout.CENTER);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(barra, BorderLayout.CENTER);
		mainPanel.add(Box.createVerticalGlue());

		setLocationRelativeTo(null);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width- getSize().width)/2- 250, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height)/2 - 200);
		this.setVisible(true);
		this.setSize(new Dimension(500, 400));
		
	}

}
