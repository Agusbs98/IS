package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Controller.Controller;
import Controller.Observer;
import Gestion.Cita;
import Gestion.Receta;
import Gestion.Recursos;
import Usuarios.Usuario;
import views.Dialogs.*;

public class BarraDeTareas extends JPanel implements Observer{
	private JButton _citas;
	private JButton _recetas;
	private JButton _nuevaReceta;
	private JButton _nuevaCita;
	private JButton _recursos;
	private JMenuBar _opciones;
	private JToolBar _tb; 
	private Controller _ctrl;
	
	public BarraDeTareas(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObs(this);
	}
	
	private void initGUI() {
		_tb = new JToolBar(); 
		setLayout(new BorderLayout());
		_citas = new JButton(new ImageIcon("resources/icons/citas.png"));
		_citas.setToolTipText("Citas");
		_citas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_ctrl.opciones(1);
			}
			
		});
		
		_nuevaCita = new JButton(new ImageIcon("resources/icons/nuevaCita.png"));
		_nuevaCita.setToolTipText("Nueva Cita");
		_nuevaCita.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_ctrl.opciones(1);
				new CitaDialog(_ctrl);
			}
		});
		
		_recetas = new JButton(new ImageIcon("resources/icons/recetas.png"));
		_recetas.setToolTipText("Recetas");
		_recetas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_ctrl.opciones(2);
			}
			
		});
		
		_nuevaReceta = new JButton(new ImageIcon("resources/icons/nuevaReceta.png"));
		_nuevaReceta.setToolTipText("Nueva receta");
		_nuevaReceta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_ctrl.opciones(2);
				new RecetaDialog(_ctrl);
			}		
		});
		
		_recursos = new JButton(new ImageIcon("resources/icons/recursos.png"));
		_recursos.setToolTipText("Recursos");
		_recursos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_ctrl.opciones(3);
			}
		});
		
		_opciones = new JMenuBar();
		JMenu menu = new JMenu();
		menu.setIcon(new ImageIcon("resources/icons/hospital.png"));
		JMenuItem m1 =new JMenuItem("Editar Usuario"); 
		menu.add(m1);
		JMenuItem m2 = new JMenuItem("Darse De Baja");
		menu.add(m2);
		JMenuItem m3 = new JMenuItem("Cerrar Sesion");
		menu.add(m3);
		_opciones.add(menu);
		//_opciones.setIcon(new ImageIcon("resources/icons/hospital.png"));
		_opciones.setToolTipText("Opciones");
		m1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UsuarioDialog(_ctrl);
			}
		});
		m2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_ctrl.eliminaUsuario();
			}
		});
		m3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_ctrl.cerrarSesion();
			}
		});
		_tb.add(_citas);
		_tb.addSeparator();
		_tb.add(_nuevaCita);
		_tb.addSeparator();
		_tb.add(_recetas);
		_tb.addSeparator();
		_tb.add(_nuevaReceta);
		_tb.addSeparator();
		_tb.add(_recursos);
		_tb.add(Box.createHorizontalGlue());
		_tb.add(_opciones);
		_tb.setVisible(false);
		add(_tb);
	}

	@Override
	public void init(Usuario Us) {
		// TODO Auto-generated method stub
		
		_tb.setVisible(_ctrl.hayUsuario());
		_recursos.setVisible(Us.get_tipo().equals("m") );
		_nuevaReceta.setVisible(Us.get_tipo().equals("m"));
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		_tb.setVisible(false);
	}

	@Override
	public void charge(int n,Usuario Us, ArrayList<Cita> cita, ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		if(_ctrl.hayUsuario()) {
			_tb.setVisible(true);
		}
		else {
			_tb.setVisible(false);
		}
		
	}
	
	@Override
	public void opciones(int n, ArrayList<Cita> cita,ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		_tb.setVisible(true);
	}

}
