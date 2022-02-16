package SACitas;

import java.util.ArrayList;

import Gestion.Cita;


public class FachadaCitasImpl implements IFachadaCitas {
     
	
	private ISACitas iCitas = new SACitasImpl();
	
	
	@Override
	public boolean addCita(Cita cita) {
		
		return iCitas.addCita(cita);
		
	}

	@Override
	public boolean deleteCita(int id) {
		
		return iCitas.deleteCita(id);
		
	}

	@Override
	public boolean modificaCita(int cita,String fecha, int consulta) {
		
		return iCitas.modificaCita(cita,fecha, consulta);
		
	}

	@Override
	public Cita consulta1Cita(int cita) {
		
		return iCitas.consulta1Cita(cita);
	}

	@Override
	public ArrayList<Cita> consultaTodosCita(String usuario) {
		
		return iCitas.consultaTodosCita(usuario);
	}

	
	
	

}
