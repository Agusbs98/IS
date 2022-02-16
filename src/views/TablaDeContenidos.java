package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Controller.Observer;
import Gestion.*;
import Usuarios.Usuario;
import views.CitasTable.CitasTable;
import views.RecetasTable.RecetasTable;
import views.RecursosTable.RecursosTable;

public class TablaDeContenidos extends JPanel implements Observer{
	private Controller _ctrl;
	private CitasTable _Tcitas;
	private RecetasTable _Trecetas;
	private RecursosTable _Trecursos;
	private JComboBox<String> _ordenTablas;
	private boolean isEdit = false; 
	private int estadoFiltrar;
	
	
	public TablaDeContenidos(Controller ctrl) {
		_ctrl = ctrl;
		_ctrl.addObs(this);
		_Tcitas = new CitasTable(ctrl);
		_Trecetas = new RecetasTable(ctrl);
		_Trecursos = new RecursosTable(ctrl);
		
		initGUI();
		
	}
	private void initGUI() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		_Tcitas.setVisible(false);
		_Trecetas.setVisible(false);
		_Trecursos.setVisible(false);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		JButton filtrar = new JButton("Buscar");
		JTextField filtro = new JTextField();
		JButton editar = new JButton("Editar");
		JPanel eliminar = new JPanel();
		eliminar.setLayout(new BoxLayout(eliminar, BoxLayout.X_AXIS));
		eliminar.add(Box.createHorizontalGlue());
		eliminar.add(new JLabel("Numero de fila a eliminar: "));
		JTextField fil = new JTextField();
		eliminar.add(fil);
		JButton elimina = new JButton("Eliminar");
		elimina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int fila = Integer.parseInt(fil.getText())-1;
					if(estadoFiltrar == 1)_Tcitas.elimina(fila);
					else if(estadoFiltrar == 2)_Trecetas.elimina(fila);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(elimina.getParent(), "ERROR al eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		eliminar.add(elimina);
		eliminar.add(Box.createHorizontalGlue());
		eliminar.setVisible(false);
		
		JPanel aniadir = new JPanel();
		aniadir.setLayout(new BoxLayout(aniadir, BoxLayout.X_AXIS));
		aniadir.add(Box.createHorizontalGlue());
		
		aniadir.add(new JLabel("Numero de unidades minimas: "));
		JTextField ca = new JTextField();
		aniadir.add(ca);
		JButton aniade = new JButton("Ver");
		aniade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int cant = Integer.parseInt(ca.getText());
					_Trecursos.aumenta(cant);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(aniade.getParent(), "ERROR al cambiar.", "ERROR", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		aniadir.add(aniade);
		aniadir.add(Box.createHorizontalGlue());
		aniadir.setVisible(false);
		
		editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isEdit) editar.setBackground(new JButton().getBackground());
				else editar.setBackground(Color.GREEN);
				_Tcitas.edit();
				_Trecursos.edit();
				_Trecetas.edit();
				isEdit = !isEdit;
				if(estadoFiltrar !=3) { 
					eliminar.setVisible(isEdit);
					aniadir.setVisible(false);
				}
				else {
					eliminar.setVisible(false);
					aniadir.setVisible(isEdit);
				}
				//opciones(estadoFiltrar, null,null,null);
				//edita();
				
			}
		});
		JLabel txt = new JLabel("Introduzca una opcion de filtro:");
		_ordenTablas = new JComboBox<>();
		filtrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(estadoFiltrar == 1)_Tcitas.filtro(filtro.getText(), _ordenTablas.getSelectedIndex()-1);
				else if(estadoFiltrar == 2)_Trecetas.filtro(filtro.getText(), _ordenTablas.getSelectedIndex()-1);
				else if(estadoFiltrar == 3)_Trecursos.filtro(filtro.getText(),_ordenTablas.getSelectedIndex()-1);
			}
		});
		panel.add(filtro);
		panel.add(filtrar);
		panel.add(editar);
		add(panel);
		add(txt);
		add(_ordenTablas);
		add(eliminar);
		add(aniadir);
		add(_Tcitas);
		add(_Trecetas);
		add(_Trecursos);
		setVisible(false);
	}


	@Override
	public void init(Usuario Us) {
		// TODO Auto-generated method stub
		setVisible(false);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		setVisible(false);
	}

	@Override
	public void charge(int n,Usuario Us, ArrayList<Cita> cita, ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		if(_ctrl.hayUsuario()) {
			/*setVisible(false);
			add(_Tcitas);
			add(_Trecursos);
			add(_Trecetas);
			_Tcitas.setVisible(false);
			_Trecetas.setVisible(false);
			_Trecursos.setVisible(false); */
			opciones(estadoFiltrar, cita,receta,recurso);
		}
	}

	@Override
	public void opciones(int n, ArrayList<Cita> cita,ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		setVisible(true);
		//_ordenTablas = new JComboBox<>();
		_ordenTablas.removeAllItems();
		_ordenTablas.addItem("Sin filtro");
		if(n == 1) {
			_Tcitas.setVisible(true);
			_Trecetas.setVisible(false);
			_Trecursos.setVisible(false);
			
			for(String data: _Tcitas.orden()) {
				_ordenTablas.addItem(data);
			}
			
		}
		else if (n == 2) {
			_Tcitas.setVisible(false);
			_Trecetas.setVisible(true);
			_Trecursos.setVisible(false);
			for(String data: _Trecetas.orden()) {
				_ordenTablas.addItem(data);
			}
		}
		else if(n == 3) {
			_Tcitas.setVisible(false);
			_Trecetas.setVisible(false);
			_Trecursos.setVisible(true);
			for(String data: _Trecursos.orden()) {
				_ordenTablas.addItem(data);
			}
		}
		_ordenTablas.setSelectedIndex(0);
		estadoFiltrar = n;
		
	}

}