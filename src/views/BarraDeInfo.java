package views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controller;
import Controller.Observer;
import Gestion.Cita;
import Gestion.Receta;
import Gestion.Recursos;
import Usuarios.Usuario;

public class BarraDeInfo extends JPanel implements Observer{
	
	private Controller _ctrl;
	private JLabel _nombre;
	
	public BarraDeInfo(Controller ctrl) {
		_nombre = new JLabel();
		_ctrl = ctrl;
		_ctrl.addObs(this);
		initGUI();
	}
	
	public void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		add(_nombre);
		this.setVisible(false);
	}

	@Override
	public void init(Usuario Us) {
		// TODO Auto-generated method stub
		_nombre.setText(Us.get_Nombre());
		this.setVisible(true);
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		
	}

	@Override
	public void charge(int n,Usuario Us, ArrayList<Cita> cita, ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void opciones(int n, ArrayList<Cita> cita,ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		
	}

}
