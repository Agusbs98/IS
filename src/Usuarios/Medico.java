package Usuarios;



import java.util.ArrayList;

import Gestion.Receta;

public class Medico extends Usuario{
	private String _Especialidad;
	private int _Consulta;
	private ArrayList<Receta> recetas;
	
	public Medico( String nombre, String email,String password, String DNI, String telefono,String especialidad,int consulta) {
		super(nombre,email,password,DNI,telefono);
		set_Especialidad(especialidad);
		set_Consulta(consulta);
	}
	
	public String get_Especialidad() {
		return _Especialidad;
	}
	
	public void set_Especialidad(String especialidad) {
		this._Especialidad = especialidad;
	}
	
	public int get_Consulta() {
		return _Consulta;
	}
	
	public void set_Consulta(int consulta) {
		this._Consulta = consulta;
	}
	@Override
	public void addReceta(Receta receta) {
		recetas.add(receta);
		
	}
    
	@Override
	public String serialize() {
		return super.serialize()+";"+get_Especialidad()+";"+get_Consulta();
	}

	@Override
	public String get_tipo() {
		// TODO Auto-generated method stub
		return "m";
	}
	
}
