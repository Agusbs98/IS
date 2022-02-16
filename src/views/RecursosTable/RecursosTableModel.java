package views.RecursosTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Controller.Controller;
import Controller.Observer;
import Gestion.Cita;
import Gestion.Receta;
import Gestion.Recursos;
import Gestion.Recursos;
import Usuarios.Usuario;

public class RecursosTableModel extends AbstractTableModel implements Observer{
	private Controller _ctrl;
	private List<Recursos> _Lcompleta;
	private List<Recursos> _Lrecursos;
	private String[] columnNames = {"Recurso","Cantidad"};
	private Usuario usu;
	private boolean editEnable = false;
	
	public RecursosTableModel(Controller ctrl) {
		_Lcompleta = new ArrayList<>();
		_Lrecursos = new ArrayList<>();
		_ctrl = ctrl;
		ctrl.addObs(this);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
			if(_Lrecursos == null) return 0;
			return _Lrecursos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object salida = null;
		switch(columnIndex){
		case 0:
			salida = _Lrecursos.get(rowIndex).get_Recurso();
			break;
		case 1:
			salida = _Lrecursos.get(rowIndex).get_Cantidad();
			break;
		}
		return salida;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public String[] orden() {
		return columnNames;
	}
	
	public void filtro(String text, int tipo) {
		_Lrecursos.clear();
		for(Recursos data: _Lcompleta) {
			switch(tipo){
			case 0:
				if(text.toLowerCase().equals(data.get_Recurso().toLowerCase()) || data.get_Recurso().toLowerCase().indexOf(text.toLowerCase())!= -1)_Lrecursos.add(data);
				break;
			case 1:
				try {
					if(Integer.parseInt(text) >=data.get_Cantidad())_Lrecursos.add(data);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR cantidad introducida incorrecta.", "ERROR", JOptionPane.ERROR_MESSAGE );
					_Lrecursos.add(data);
				}
				break;
			default:
					_Lrecursos.add(data);
			}
		}
		fireTableStructureChanged();
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		//columnIndex == 2 || 
		
		return columnIndex== 1 && editEnable;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		try {
			if(_Lrecursos.get(rowIndex).get_Cantidad()> Integer.parseInt((String)aValue)) throw new Exception("ERROR cantidad introducida menor");
			_ctrl.addRecurso(_Lrecursos.get(rowIndex).get_Recurso(), Integer.parseInt((String)aValue));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERROR cantidad introducida incorrecta.", "ERROR", JOptionPane.ERROR_MESSAGE );
		}
	}
	
	@Override
	public void init(Usuario Us) {
		// TODO Auto-generated method stub
		usu = Us;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void charge(int n,Usuario Us, ArrayList<Cita> cita, ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void opciones(int n, ArrayList<Cita> cita,ArrayList<Receta> receta, ArrayList<Recursos> recurso) {
		// TODO Auto-generated method stub
		_Lcompleta.clear();
		_Lcompleta.addAll(recurso);
		filtro("",-1);
		fireTableStructureChanged();
	}

	public void edit() {
		// TODO Auto-generated method stub
		editEnable = !editEnable;
	}

	public void aumenta(int cant) {
		// TODO Auto-generated method stub
		_ctrl.minRecursos(cant);
		
	}

	

}

