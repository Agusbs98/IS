package views.CitasTable;

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

public class CitasTableModel extends AbstractTableModel implements Observer{
	private Controller _ctrl;
	private List<Cita> _Lcompleta;
	private List<Cita> _Lcitas;
	private String[] columnNames = {"Medico","Paciente","Fecha","Especialidad","Consulta"};
	private Usuario usu;
	private boolean editEnable = false;
	
	public CitasTableModel(Controller ctrl) {
		_Lcompleta = new ArrayList<>();
		_Lcitas = new ArrayList<>();
		_ctrl = ctrl;
		ctrl.addObs(this);
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(_Lcitas == null) return 0;
		return _Lcitas.size();
		
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
		//_Lcitas.clear();
		switch(columnIndex){
		case 0:
			salida = rowIndex+1;
			break;
		case 1:
			salida = _Lcitas.get(rowIndex).getMedico();
			break;
		case 2:
			salida = _Lcitas.get(rowIndex).getPaciente();
			break;
		case 3:
			salida = _Lcitas.get(rowIndex).getFechaM();
			break;
		case 4:
			salida = _Lcitas.get(rowIndex).get_Especialidad();
			break;
		case 5:
			salida = _Lcitas.get(rowIndex).get_Consulta();
			break;
		}
		return salida;
	}
	
	@Override
	public String getColumnName(int col) {
		if(col==0)return "Cita";
		return columnNames[col-1];
	}

	public String[] orden() {
		return columnNames;
	}
	
	public void filtro(String text, int tipo) {
		_Lcitas.clear();
		for(Cita data: _Lcompleta) {
			switch(tipo){
			case 0:
				if(text.toLowerCase().equals(data.getMedico().toLowerCase())|| data.getMedico().toLowerCase().indexOf(text.toLowerCase()) != -1)_Lcitas.add(data);
				break;
			case 1:
				if(text.toLowerCase().equals(data.getPaciente().toLowerCase())|| data.getPaciente().toLowerCase().indexOf(text.toLowerCase()) != -1)_Lcitas.add(data);
				break;
			case 2:
				if(data.getFechaM().indexOf(text) != -1)_Lcitas.add(data);
				break;
			case 3:
				if(text.toLowerCase().equals(data.get_Especialidad().toLowerCase())|| data.get_Especialidad().toLowerCase().indexOf(text.toLowerCase()) != -1)_Lcitas.add(data);
				break;
			case 4:
				if(text.equals(String.valueOf(data.get_Consulta()))) _Lcitas.add(data);
				break;
			default:
					_Lcitas.add(data);
			}
		}
		fireTableStructureChanged();
	}
	
	@Override
	public void init(Usuario Us) {
		// TODO Auto-generated method stub
		//usuario = Us.get_Nombre();
		this.usu=Us;
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		//columnIndex == 2 || 
		if(editEnable) {
			if(usu.get_tipo()=="m") {
				if(columnIndex==3 || columnIndex==5) {
					return true;
				}else {
					return false;
				}
			}else if(usu.get_tipo()=="p") {
				if(columnIndex==3) {
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
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
		_Lcompleta.addAll(cita);
		filtro("",-1);
		fireTableStructureChanged();
	}

	public void edit() {
		// TODO Auto-generated method stub
		editEnable = !editEnable;
		fireTableStructureChanged();
	}
	@Override
	//2:3  1/2/3
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		try {
			if(columnIndex == 3) {
				String[] fechaCalendar=o.toString().trim().split("  ");
				String[] fecha = fechaCalendar[1].toString().trim().split("/");
				String[] hora = fechaCalendar[0].toString().trim().split(":");
				String FechaNueva = fecha[2].toString() + ":" + fecha[1].toString() + ":" +fecha[0].toString() + ":" + hora[0].toString() + ":" + hora[1].toString();
				_ctrl.editaCita(_Lcitas.get(rowIndex).getId(), FechaNueva, _Lcitas.get(rowIndex).get_Consulta());
			}
			else {
				_ctrl.editaCita(_Lcitas.get(rowIndex).getId(), _Lcitas.get(rowIndex).getFechaS(),Integer.parseInt(o.toString()));
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
		}
	}

	public void elimina(int fila) {
		// TODO Auto-generated method stub
		_ctrl.eliminaCita(_Lcitas.get(fila).getId());
	}

}
