package views.CitasTable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controller.Controller;

public class CitasTable extends JPanel{
	private CitasTableModel _CTM;
	private JTable _T;
	
	public CitasTable(Controller ctrl) {
		_CTM = new CitasTableModel(ctrl);
		_T = new JTable(_CTM);
		add(new JScrollPane(_T));
	}
	
	public String[] orden() {
		return _CTM.orden();
	}
	
	public void filtro(String text, int tipo) {
		_CTM.filtro(text, tipo);
	}

	public void edit() {
		// TODO Auto-generated method stub
		_CTM.edit();
	}

	public void elimina(int fila) {
		// TODO Auto-generated method stub
		_CTM.elimina(fila);
	}

}
