package Gestion;

import java.util.Calendar;

public class Receta {
	private Calendar _FechaFin;
	private String _Medicamento;
	private int _Cantidad;
	private String _Medico;
	private String _Paciente;
	public static int cont=0;
	private int _ID;
	
	public Receta(String Medico, String Paciente, String medicamento, String cantidad,String fechaFin) {
		String [] fechaCalendar= fechaFin.trim().split(":");
		set_FechaFin(Integer.parseInt(fechaCalendar[0]),Integer.parseInt(fechaCalendar[1])-1,Integer.parseInt(fechaCalendar[2]));
		set_Medicamento(medicamento);
		set_Cantidad(Integer.parseInt(cantidad));
		set_Medico(Medico);
		set_Paciente(Paciente);
		set_ID(cont++);
		
	}
	

	public String get_FechaFinS() {
		String s= _FechaFin.get(Calendar.DATE)+"/"+(_FechaFin.get(Calendar.MONTH)+1)+"/"+_FechaFin.get(Calendar.YEAR);
		return s;
	}
	public String get_FechaFinSerialize() {
		String s= _FechaFin.get(Calendar.YEAR)+":"+(_FechaFin.get(Calendar.MONTH)+1)+":"+_FechaFin.get(Calendar.DATE);
		return s;
	}

	public void set_FechaFin(int anio, int mes, int dia) {
		this._FechaFin = Calendar.getInstance();
		this._FechaFin.set(anio, mes, dia);
	}
	
	public Calendar get_fechaFin() {
		return this._FechaFin;
	}

	public String get_Medicamento() {
		return this._Medicamento;
	}

	public void set_Medicamento(String Medicamento) {
		this._Medicamento = Medicamento;
	}

	public int get_Cantidad() {
		return _Cantidad;
	}

	public void set_Cantidad(int Catidad) {
		this._Cantidad = Catidad;
	}


	public String serialize() {
		String x = get_Medico()+";"+get_Paciente()+";"+get_Medicamento()+";"+get_Cantidad()+";"+get_FechaFinSerialize();
		return x;
	}

	public String get_Medico() {
		return _Medico;
	}

	public void set_Medico(String _Medico) {
		this._Medico = _Medico;
	}

	public String get_Paciente() {
		return _Paciente;
	}

	public void set_Paciente(String _Paciente) {
		this._Paciente = _Paciente;
	}

	public int get_ID() {
		return _ID;
	}

	private void set_ID(int _ID) {
		this._ID = _ID;
	}


	
}
