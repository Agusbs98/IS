package views;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Controller;

public class MainWindow extends JFrame{
	private Controller _ctrl;
	
	public MainWindow(Controller ctrl) {
		super("Hospital");
		_ctrl = ctrl;
		
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel (new BorderLayout());
		//Graphics g = new Graphics();
		//g.drawImage(new ImageIcon(getClass().getResource("resources/icons/fondo.png")).getImage(),0,0, getWidth(), getHeight(), null);
		//mainPanel.paintComponent(g);
		setContentPane(mainPanel);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
		
		mainPanel.add(contentPanel);
		
		ControlPanel  ctrlPanel = new ControlPanel(_ctrl);
		BarraDeTareas brt = new BarraDeTareas(_ctrl);
		BarraDeInfo bri = new BarraDeInfo(_ctrl);
		mainPanel.add(brt,BorderLayout.PAGE_START);
		mainPanel.add(ctrlPanel);
		mainPanel.add(bri,BorderLayout.PAGE_END);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width- getSize().width)/2- 250, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height)/2 - 330);
		setSize(500, 660);
		//pack();

		this.setVisible(true);
		
		
	}
	
	

}
