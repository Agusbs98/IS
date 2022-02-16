package SARecursos;

import java.util.ArrayList;

import Gestion.Recursos;
import RecursosDAOs.FachadaRecursosDAO;
import RecursosDAOs.IFachadaRecursosDAO;

public class SARecursosImpl implements ISARecursos{
	
	
    private IFachadaRecursosDAO ifRecursosDAO = new FachadaRecursosDAO();


	@Override
	public boolean addRecurso(Recursos rec) {
		if(!ifRecursosDAO.existeRecurso(rec.get_Recurso())) 
			return false;
		ifRecursosDAO.addRecurso(rec);
		return true;
	}

	@Override
	public boolean deleteRecurso(String rec) {
		if(ifRecursosDAO.existeRecurso(rec)) {
			ifRecursosDAO.deleteRecurso();
			return true;
		}
		return false;
		
	}

	@Override
	public Recursos consulta1Recurso(String recurso) {
		 Recursos aux=null;
		if(ifRecursosDAO.existeRecurso(recurso))
			aux= ifRecursosDAO.consulta1Recurso();
			
		return aux;
	}

	@Override
	public ArrayList<Recursos> consultaTodos(int cantidad) {
		
		return ifRecursosDAO.consultaTodos(cantidad);
		
	}

	@Override
	public boolean modificaRecurso(Recursos rec) {
		if(!ifRecursosDAO.existeRecurso(rec.get_Recurso()))return false;
		ifRecursosDAO.modificaRecurso(rec.get_Cantidad());
		return true;
	}

}
