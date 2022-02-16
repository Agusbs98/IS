package RecursosDAOs;

import java.util.ArrayList;

import Gestion.Recursos;

public class FachadaRecursosDAO implements IFachadaRecursosDAO{
	private IRecursosDAO iRecursosDAO=new RecursosDAOImpl();

	@Override
	public boolean existeRecurso(String nombre) {
		return iRecursosDAO.existeRecurso(nombre);
	}

	@Override
	public void addRecurso(Recursos rec) {
		iRecursosDAO.addRecurso(rec);
		
	}

	@Override
	public void deleteRecurso() {
		iRecursosDAO.deleteRecurso();
	}

	@Override
	public Recursos consulta1Recurso() {
		return iRecursosDAO.consulta1Recurso();
	}

	@Override
	public ArrayList<Recursos> consultaTodos(int cantidad) {
	
		return iRecursosDAO.consultaTodos(cantidad);
	}

	@Override
	public void modificaRecurso(int cantidad) {
		iRecursosDAO.modificaRecurso(cantidad);
	}


}
