package CitaDAOs;

import java.util.ArrayList;


import Gestion.Cita;

public interface IFachadaCitaDAO {
	public boolean existeCita(int cita);
	public void addCita(Cita cita);
	public void deleteCita();
	public void modificaCita(String fecha, int consulta);
	public Cita consulta1Cita();
	public ArrayList<Cita> consultaTodosCita(String us);
	public boolean cambioCorrecto(String fecha, int consulta);
}
