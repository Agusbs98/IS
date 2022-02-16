package Usuarios;

import java.util.ArrayList;


import Gestion.Receta;

public class Paciente extends Usuario{
	private ArrayList<Receta> recetas;
	
	public Paciente(String nombre, String email,String password, String DNI, String telefono) {
		super(nombre,email,password,DNI,telefono);
		recetas = new ArrayList<>();
	}
	@Override
	public void addReceta(Receta receta) {
		recetas.add(receta);
		
	}

	@Override
	public String get_tipo() {
		// TODO Auto-generated method stub
		return "p";
	}
	
}
