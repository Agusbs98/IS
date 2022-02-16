package RecursosDAOs;

import java.util.ArrayList;

import Gestion.Recursos;

public interface IRecursosDAO {
	public boolean existeRecurso(String nombre);
	public void addRecurso(Recursos rec);
	public void deleteRecurso();
	public Recursos consulta1Recurso();
	public ArrayList<Recursos> consultaTodos(int cantidad);
	public void modificaRecurso(int cantidad);
}
