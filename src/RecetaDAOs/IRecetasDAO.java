package RecetaDAOs;

import java.util.ArrayList;
import Gestion.Receta;

public interface IRecetasDAO {
	public boolean existeReceta(int rct);
	public void addReceta(Receta rect);
	public void deleteReceta();
	public void modificaReceta(Receta rect);
	public Receta consulta1Receta() ;
	public ArrayList<Receta> consultaTodos(String us);
}
