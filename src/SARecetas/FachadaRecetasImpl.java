package SARecetas;

import java.util.ArrayList;
import Gestion.Receta;

public class FachadaRecetasImpl implements IFachadaRecetas{
	
	private ISARecetas iRecetas = new SARecetasImpl();

	public boolean addReceta(Receta rect) {
		return iRecetas.addReceta(rect);
	}

	public boolean deleteReceta(int rect) {
		return iRecetas.deleteReceta(rect);
	}

	public boolean modificaReceta(Receta rect) {
		return iRecetas.modificaReceta(rect);
	}

	public Receta consulta1Receta(int rect) {
		return iRecetas.consulta1Receta(rect);
	}

	public ArrayList<Receta> consultaTodos(String us) {
		return iRecetas.consultaTodos(us);
	}

	
}
