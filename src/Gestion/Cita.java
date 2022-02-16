package Gestion;

import java.util.Calendar;

public class Cita {
	private Calendar _Fecha;
	private String _Medico;
	private String _Paciente;
	private String _Especialidad;
	private int _Consulta;
	private int _Id;
	public static int cont=0;
	
	public Cita(String m, String p,String especialidad, int consulta,String fecha) {
		String [] fechaCalendar= fecha.trim().split(":");
		set_Fecha(Integer.parseInt(fechaCalendar[0]),Integer.parseInt(fechaCalendar[1])-1,Integer.parseInt(fechaCalendar[2]),
				Integer.parseInt(fechaCalendar[3]), Integer.parseInt(fechaCalendar[4]));
		setMedico(m);
		setPaciente(p);
		setId(cont);
		setEspecialidad(especialidad);
		setConsulta(consulta);
		++cont;
	}
	

	public Calendar getFecha() {
		return this._Fecha;
	}
	public String getFechaM() {
		String s=_Fecha.get(Calendar.HOUR_OF_DAY)+":"+_Fecha.get(Calendar.MINUTE)+"  "+_Fecha.get(Calendar.DATE)+"/"+(_Fecha.get(Calendar.MONTH)+1)
		+"/"+_Fecha.get(Calendar.YEAR);
		
		return s;
	}
	public String getFechaS() {
		String s=_Fecha.get(Calendar.YEAR)+":"+(_Fecha.get(Calendar.MONTH)+1)+":"+_Fecha.get(Calendar.DATE)+":"+_Fecha.get(Calendar.HOUR_OF_DAY)+":"+_Fecha.get(Calendar.MINUTE);
				
		return s;
	}
	
	public int getId() {
		return this._Id;
	}
	
	public void setMedico(String m){
		this._Medico = m;
	}
	
	public String getMedico() {
		return _Medico;
	}
	
	public void setPaciente(String p){
		this._Paciente = p;
	}
	public String getPaciente() {
		return _Paciente;
	}
	
	public void setId(int cont) {
		this._Id=cont;
	}
	
	public void set_Fecha(int anio, int mes, int dia, int hora, int minuto) {
		this._Fecha = Calendar.getInstance();
		this._Fecha.set(anio, mes, dia, hora, minuto);
	}
	public String serialize() {
		return _Medico+";"+_Paciente+";"+_Especialidad+";"+_Consulta+";"+ getFechaS();
	}
	
	public String get_Especialidad() {
		return _Especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this._Especialidad = especialidad;
	}

	public int get_Consulta() {
		return _Consulta;
	}

	public void setConsulta(int consulta) {
		this._Consulta = consulta;
	}
	
}
