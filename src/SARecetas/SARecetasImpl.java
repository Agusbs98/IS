package SARecetas;

import java.util.ArrayList;
import Gestion.Receta;
import RecetaDAOs.FachadaRecetasDAO;
import RecetaDAOs.IFachadaRecetasDAO;

public class SARecetasImpl implements ISARecetas{
	
	private IFachadaRecetasDAO ifRecetaDAO = new FachadaRecetasDAO();

	public boolean addReceta(Receta rect) {
		if(ifRecetaDAO.existeReceta(rect.get_ID())) {
			return false;
		}
		ifRecetaDAO.addReceta(rect);
		return true;
	}

	public boolean deleteReceta(int rect) {

		if(ifRecetaDAO.existeReceta(rect)) {
			ifRecetaDAO.deleteReceta();
			return true;
		}
		return false;
		
	}

	public boolean modificaReceta(Receta rect) {

		if(ifRecetaDAO.existeReceta(rect.get_ID())) {
			ifRecetaDAO.modificaReceta(rect);
			return true;
		}
		return false;
		
	}

	public Receta consulta1Receta(int rect) {
		if(ifRecetaDAO.existeReceta(rect)) {
			return ifRecetaDAO.consulta1Receta();
		}
		return null;
		
	}
	
	public ArrayList<Receta> consultaTodos(String us) {
		return ifRecetaDAO.consultaTodos(us);
	}


	
}
