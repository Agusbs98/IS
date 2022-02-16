package views.DialogEditable;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Controller;
import Controller.Observer;
import Gestion.Cita;
import Gestion.Receta;
import Gestion.Recursos;
import Usuarios.Usuario;
import views.CitasTable.CitasTable;
import views.RecetasTable.RecetasTable;
import views.RecursosTable.RecursosTable;

public class DialogTable extends JDialog{
	private int _status;
	private RecetasTable _Trecetas;
	private RecursosTable _Trecursos;
	private CitasTable _Tcitas;
	private Controller _ctrl;
	
	public DialogTable(Controller ctrl, RecetasTable Trecetas, RecursosTable Trecursos, CitasTable Tcitas) {
		_ctrl = ctrl;
		_Trecetas = Trecetas;
		_Trecursos = Trecursos;
		_Tcitas = Tcitas;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		mainPanel.add(_Tcitas);
		mainPanel.add(_Trecetas);
		mainPanel.add(_Trecursos);
		
		JPanel barra = new JPanel();
		barra.setLayout(new BoxLayout(barra, BoxLayout.X_AXIS));
		
		JButton OK = new JButton("OK");
		JButton Cancel = new JButton("Cancel");

		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//añadir las acciones de confirmar
				setVisible(false);
				try {
					_ctrl.actualiza(1);
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
			}
		});
		barra.add(Cancel);
		barra.add(OK);
		mainPanel.add(barra);
		this.setVisible(true);
		this.setSize(new Dimension(500,560));
	}

	
}
