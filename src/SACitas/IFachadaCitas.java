package SACitas;

import java.util.ArrayList;

import Gestion.Cita;


public interface IFachadaCitas {
	
	public boolean addCita(Cita cita);
	public boolean deleteCita(int cita);
	public boolean modificaCita(int cita,String fecha, int consulta);
	public Cita consulta1Cita(int cita);
	public ArrayList<Cita> consultaTodosCita(String usuario);

}
