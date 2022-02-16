package RecetaDAOs;

import java.util.ArrayList;

import Gestion.Receta;

public class FachadaRecetasDAO implements IFachadaRecetasDAO {
	private IRecetasDAO iRecetasDAO= new RecetasDAOImpl();
	
	@Override
	public boolean existeReceta(int rct) {
		return iRecetasDAO.existeReceta(rct);
	}

	@Override
	public void addReceta(Receta rect) {
		iRecetasDAO.addReceta(rect);
	}

	@Override
	public void deleteReceta() {
		iRecetasDAO.deleteReceta();
	}

	@Override
	public void modificaReceta(Receta rect) {
		iRecetasDAO.modificaReceta(rect);
	}

	@Override
	public Receta consulta1Receta() {
		return iRecetasDAO.consulta1Receta();
	}

	@Override
	public ArrayList<Receta> consultaTodos(String us) {
		return iRecetasDAO.consultaTodos(us);
	}


}
