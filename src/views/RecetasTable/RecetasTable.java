package views.RecetasTable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controller.Controller;

public class RecetasTable extends JPanel{
	private RecetasTableModel _RTM;
	private JTable _T;
	
	public RecetasTable(Controller ctrl) {
		_RTM = new RecetasTableModel(ctrl);
		_T = new JTable(_RTM);
		add(new JScrollPane(_T));
	}
	
	public String[] orden() {
		return _RTM.orden();
	}
	
	public void filtro(String text, int tipo) {
		_RTM.filtro(text, tipo);
	}

	public void edit() {
		// TODO Auto-generated method stub
		_RTM.edit();
	}
	
	public void elimina(int fila) {
		// TODO Auto-generated method stub
		_RTM.elimina(fila);
	}



}
