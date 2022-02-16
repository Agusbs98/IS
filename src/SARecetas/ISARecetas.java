package SARecetas;

import java.util.ArrayList;
import Gestion.Receta;

public interface ISARecetas {
	
	public boolean addReceta(Receta rect);
	public boolean deleteReceta(int rect);
	public boolean modificaReceta(Receta rect);
	public Receta consulta1Receta(int rect);
	public ArrayList<Receta> consultaTodos(String us);
}
