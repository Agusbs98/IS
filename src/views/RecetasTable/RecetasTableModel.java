package views.RecetasTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Controller.Controller;
import Controller.Observer;
import Gestion.Cita;
import Gestion.Receta;
import Gestion.Recursos;
import Usuarios.Usuario;

public class RecetasTableModel extends AbstractTableModel implements Observer{
	private Controller _ctrl;
	private List<Receta> _Lcompleta;
	private List<Receta> _Lrecetas;
	private String[] columnNames = {"Medico","Paciente","Medicamento","Fecha fin","Cantidad"};
	private Usuario usuario;
	private boolean editEnable;
	
	public RecetasTableModel(Controller ctrl) {
		_Lcompleta = new ArrayList<>();
		_Lrecetas = new ArrayList<>();
		_ctrl = ctrl;
		ctrl.addObs(this);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
			if(_Lrecetas == null) return 0;
			return _Lrecetas.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length+1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object salida = null;
		switch(columnIndex){
		case 0:
			salida = rowIndex+1;
			break;
		case 1:
			salida = _Lrecetas.get(rowIndex).get_Medico();
			break;
		case 2: 
			salida = _Lrecetas.get(rowIndex).get_Paciente();
			break;
		case 3:
			salida = _Lrecetas.get(rowIndex).get_Medicamento();
			break;
		case 4:
			salida = _Lrecetas.get(rowIndex).get_FechaFinS();
			break;
		case 5:
			salida = _Lrecetas.get(rowIndex).get_Cantidad();
			break;
		}
		return salida;
	}
	
	@Override
	public String getColumnName(int col) {
		if(col==0)return "Receta";
		return columnNames[col-1];
	}

	public String[] orden() {
		return columnNames;
	}
	
	public void filtro(String text, int tipo) {
		_Lrecetas.clear();
		for(Receta data: _Lcompleta) {
			switch(tipo){
			case 0: 
				if(text.toLowerCase().equals(data.get_Medico().toLowerCase())|| data.get_Medico().toLowerCase().indexOf(text.toLowerCase()) != -1) _Lrecetas.add(data);
				break;
			case 1:
				if(text.toLowerCase().equals(data.get_Paciente().toLowerCase())|| data.get_Paciente().toLowerCase().indexOf(text.toLowerCase()) != -1) _Lrecetas.add(data);
				break;
			case 2:
				if(text.toLowerCase().equals(data.get_Medicamento().toLowerCase())|| data.get_Medicamento().toLowerCase().indexOf(text.toLowerCase()) != -1)_Lrecetas.add(data);
				break;
			case 3:
				if(data.get_FechaFinS().indexOf(text) != -1)_Lrecetas.add(data);
				break;
			case 4:
				if(text.equals(String.valueOf(data.get_Cantidad())))_Lrecetas.add(data);
				break;
			default:
					_Lrecetas.add(data);
			}
		}
		fireTableStructureChanged();
	}

	
	@Override
	public void init(Usuario Us) {
		// TODO Auto-generated method stub
		usuario = Us;
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
		_Lcompleta.addAll(receta);
		filtro("",-1);
		fireTableStructureChanged();
	}

	public void edit() {
		// TODO Auto-generated method stub
		editEnable = !editEnable;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		//columnIndex == 2 || 
		if(editEnable) {
			if(usuario.get_tipo()=="m") {
				if(columnIndex==4 || columnIndex==5) {
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	@Override
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		try {
			if(columnIndex == 4) {
				String[] fecha = o.toString().toString().trim().split("/");
				String FechaNueva = fecha[2].toString() + ":" + fecha[1].toString() + ":" +fecha[0].toString();
				_ctrl.editaReceta(_Lrecetas.get(rowIndex).get_ID(), FechaNueva,_Lrecetas.get(rowIndex).get_Cantidad());
			}
			else {
				_ctrl.editaReceta(_Lrecetas.get(rowIndex).get_ID(), _Lrecetas.get(rowIndex).get_FechaFinSerialize(),Integer.parseInt(o.toString()));
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
		}
	}

	public void elimina(int fila) {
		// TODO Auto-generated method stub
		_ctrl.eliminaReceta(_Lrecetas.get(fila).get_ID());
		
	}

}
