package views.Dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import Controller.Controller;
import Gestion.Receta;
import Gestion.Recursos;
import Usuarios.Usuario;

public class RecetaDialog extends JDialog{
	private Controller _ctrl;
	private JComboBox _comboU;
	private JComboBox _comboM;
	private String nombre;
	private String medicamento;
	
	public RecetaDialog(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		JPanel Pfecha = new JPanel();
		Pfecha.setLayout(new BoxLayout(Pfecha, BoxLayout.X_AXIS));
		Pfecha.add(Box.createHorizontalGlue());
		JSpinner fecha = new JSpinner(new SpinnerDateModel());
		fecha.setMaximumSize(new Dimension(450,40));
		Pfecha.add(fecha);
		Pfecha.add(Box.createHorizontalGlue());
		JPanel lista = new JPanel(new GridLayout(0,4,5,5));
		lista.setMaximumSize(new Dimension(450,40));
		
		lista.add(Box.createHorizontalGlue());
		lista.add(new JLabel("Nombre:"));
		_comboU = new JComboBox<String>();
		for(Usuario us:_ctrl.listaUsuarios()) {
			_comboU.addItem(us.get_Nombre());
		}
		_comboU.setSelectedIndex(-1);
		_comboU.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nombre = (String) _comboU.getSelectedItem();
			}
		});
		lista.add(_comboU);
		lista.add(Box.createHorizontalGlue());
		
		lista.add(Box.createHorizontalGlue());
		lista.add(new JLabel("Medicamento:"));
		_comboM = new JComboBox<String>();
		for(Recursos re:_ctrl.listaRecursos()) {
			_comboM.addItem(re.get_Recurso());
		}
		_comboM.setSelectedItem(-1);
		_comboM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				medicamento = (String) _comboM.getSelectedItem();
			}
		});
		lista.add(_comboM);
		lista.add(Box.createHorizontalGlue());
		
		lista.add(Box.createHorizontalGlue());
		lista.add(new JLabel("Cantidad:"));
		JTextField cantidad =new JTextField(10);
		lista.add(cantidad);
		lista.add(Box.createHorizontalGlue());
		
		JPanel barra = new JPanel();
		barra.setLayout(new BoxLayout(barra, BoxLayout.X_AXIS));
		JButton OK = new JButton("OK");
		JButton Cancel = new JButton("Cancel");
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					_ctrl.addReceta(nombre,medicamento, cantidad.getText(), (Date)fecha.getValue());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(OK.getParent(), ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
				}finally {
					setVisible(false);
				}
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
		mainPanel.add(new JLabel("Introducir Nueva Receta"));
		mainPanel.add(Box.createRigidArea(new Dimension(20,20)));
		mainPanel.add(lista);//, BorderLayout.CENTER);
		mainPanel.add(Box.createRigidArea(new Dimension(20,20)));
		
		mainPanel.add(Pfecha);//, BorderLayout.CENTER);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(barra);
		mainPanel.add(Box.createVerticalGlue());

		setLocationRelativeTo(null);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width- getSize().width)/2- 250, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height)/2 - 200);
		this.setVisible(true);
		this.setSize(new Dimension(500, 400));
	}
}
