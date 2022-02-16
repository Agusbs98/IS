package CitaDAOs;

import java.util.ArrayList;


import Gestion.Cita;


public class FachadaCitasDAO implements IFachadaCitaDAO {
	private ICitaDAO iCitasDAO = new CitaDAOImpl();

	@Override
	public boolean existeCita(int cita) {
		return iCitasDAO.existeCita(cita);
	}

	@Override
	public void  addCita(Cita cita) {
		iCitasDAO.addCita(cita);
	}

	@Override
	public void deleteCita() {
		iCitasDAO.deleteCita();
	}

	@Override
	public void modificaCita(String fecha, int consulta) {
		iCitasDAO.modificaCita(fecha, consulta);
	}

	@Override
	public Cita consulta1Cita() {
		return iCitasDAO.consulta1Cita();
	}

	@Override
	public ArrayList<Cita> consultaTodosCita(String us) {
		// TODO Auto-generated method stub
		return iCitasDAO.consultaTodosCita(us);
	}

	@Override
	public boolean cambioCorrecto(String fecha, int consulta) {
		// TODO Auto-generated method stub
		return iCitasDAO.cambioCorrecto(fecha, consulta);
	}
	
	
	
	
}