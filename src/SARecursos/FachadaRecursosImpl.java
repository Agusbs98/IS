package SARecursos;

import java.util.ArrayList;

import Gestion.Recursos;

public class FachadaRecursosImpl  implements IFachadaRecursos {
	private ISARecursos iRecursos=new SARecursosImpl();
	@Override
	public boolean addRecurso(Recursos rec) {
		return iRecursos.addRecurso(rec);
		
	}

	@Override
	public boolean deleteRecurso(String rec) {
		return iRecursos.deleteRecurso(rec);
	}

	@Override
	public Recursos consulta1Recurso(String recurso) {
		return iRecursos.consulta1Recurso(recurso);
	}

	@Override
	public ArrayList<Recursos> consultaTodos(int cantidad) {
		// TODO Auto-generated method stub
		return iRecursos.consultaTodos(cantidad);
	}

	@Override
	public boolean modificaRecurso(Recursos rec) {
		return iRecursos.modificaRecurso(rec);
	}

}
