package views.RecursosTable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controller.Controller;

public class RecursosTable extends JPanel{
	private RecursosTableModel _RTM;
	private JTable _T;
	
	public RecursosTable(Controller ctrl) {
		_RTM = new RecursosTableModel(ctrl);
		_T = new JTable(_RTM);
		add(new JScrollPane(_T));
	}

	public void filtro(String text, int i) {
		// TODO Auto-generated method stub
		_RTM.filtro(text, i);
	}

	public String[] orden() {
		// TODO Auto-generated method stub
		return _RTM.orden();
	}

	public void edit() {
		// TODO Auto-generated method stub
		_RTM.edit();
	}

	public void aumenta(int cant) {
		// TODO Auto-generated method stub
		_RTM.aumenta(cant);
		
	}
	

}
