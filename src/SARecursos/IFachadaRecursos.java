package SARecursos;

import java.util.ArrayList;

import Gestion.Recursos;

public interface IFachadaRecursos {
	//public static int cont = 0; //para que cojones sirve esto??
	
	public boolean addRecurso(Recursos rec);
	public boolean deleteRecurso(String rec);
	public Recursos consulta1Recurso(String recurso);
	public ArrayList<Recursos> consultaTodos(int cantidad);
	public boolean modificaRecurso(Recursos rec);

}
