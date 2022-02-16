package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.Controller;
import Controller.Observer;
import Gestion.Cita;
import Gestion.Receta;
import Gestion.Recursos;
import Usuarios.Usuario;

public class ControlPanel extends JPanel implements Observer{
	private Controller _ctrl;
	private iniSesion _ini;
	private Registrarse _reg;
	private JPanel _op = new JPanel();
	private TablaDeContenidos _entrada; 
	private JPanel _mainPanel;
	
	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObs(this);
	}
	private void initGUI() {
		_mainPanel = new JPanel(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		_op.setLayout(new BoxLayout(_op,BoxLayout.Y_AXIS));
		_op.setAlignmentX(CENTER_ALIGNMENT);
		JButton ini = new JButton("Iniciar Sesion");
		JButton reg = new JButton("Crear una cuenta");
		_op.add(reg);
		_op.add(Box.createRigidArea(new Dimension(20,20)));
		_op.add(ini);
		
		ini.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//añadir las acciones de confirmar
				_op.setVisible(false);
				_ini= new iniSesion(_ctrl);
				_ini.setVisible(true);
				
				_mainPanel.add(_ini,BorderLayout.CENTER);
				
				//_op.setVisible(true);
			}
		});
		reg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_op.setVisible(false);
				_reg = new Registrarse(_ctrl);
				_reg.setVisible(true);
				_mainPanel.add(_reg,BorderLayout.CENTER);
				//op.setVisible(!_ctrl.hayUsuario());

			}
		});
		//_op.setVisible(true);
		_mainPanel.add(_op);
		
		//mainPanel.add(contentPanel);
		_mainPanel.setVisible(true);
		this.add(_mainPanel);
		//this.setSize(550, 700);
		//this.pack();
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.setVisible(true);
	}/*
	@Override
	protected void paintComponent(Graphics g) {
		//imagen = new ImageIcon(getClass().getResource("/Icons/hospitalFondo")).getImage();
		super.paintComponent(g);
		g.drawImage(new ImageIcon(getClass().getResource("resources/icons/fondo.png")).getImage(),0,0, getWidth(), getHeight(), null);
		setOpaque(false);
	}
	*/
	@Override
	public void init(Usuario Us) {
		// TODO Auto-generated method stub
		if(_ctrl.hayUsuario()){
			_entrada = new TablaDeContenidos(_ctrl);
			_mainPanel.add(_entrada);
		}
		else {
			_op.setVisible(true);
			_mainPanel.add(_op);
		}
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		_op.setVisible(true);
		_mainPanel.add(_op);
		
	}
	@Override
	public void charge(int n,Usuario Us, ArrayList<Cita> cita, ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		if(_ctrl.hayUsuario()){
			_entrada = new TablaDeContenidos(_ctrl);
			_mainPanel.add(_entrada);
		}
		else {
			_op.setVisible(true);
			_mainPanel.add(_op);
		}
	}
	@Override
	public void opciones(int n, ArrayList<Cita> cita,ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		
	}

}
