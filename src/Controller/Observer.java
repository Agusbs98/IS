package Controller;

import java.util.ArrayList;

import Gestion.*;
import Usuarios.Usuario;

public interface Observer {
	public void init(Usuario Us);
	public void close();
	public void charge(int n,Usuario Us, ArrayList<Cita> cita, ArrayList<Receta> receta, ArrayList<Recursos> recurso);
	public void opciones(int n, ArrayList<Cita> cita, ArrayList<Receta> receta, ArrayList<Recursos> recurso);
}
