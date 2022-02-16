package SACitas;

import java.util.ArrayList;

import CitaDAOs.FachadaCitasDAO;
import CitaDAOs.IFachadaCitaDAO;
import Gestion.Cita;


public class SACitasImpl implements ISACitas {
	
	
	private IFachadaCitaDAO ifCitasDAO = new FachadaCitasDAO();

	@Override
	public boolean addCita(Cita cita) {
		
		if(ifCitasDAO.existeCita(cita.getId()) || !ifCitasDAO.cambioCorrecto(cita.getFechaS(),cita.get_Consulta() ))
			return false;
		ifCitasDAO.addCita(cita);
		return true;
		
	}

	@Override
	public boolean deleteCita(int cita) {
	
		if(!ifCitasDAO.existeCita(cita))
			return false;
			ifCitasDAO.deleteCita();
		return true;
		
	}

	@Override
	public boolean modificaCita(int cita,String fecha, int consulta) {
		
		if(!ifCitasDAO.existeCita(cita) || !ifCitasDAO.cambioCorrecto(fecha, consulta))
			return false;
		
		ifCitasDAO.modificaCita(fecha, consulta);
		return true;
	}

	@Override
	public Cita consulta1Cita(int cita) {
		Cita aux=null;
		if(ifCitasDAO.existeCita(cita)) {
			aux=ifCitasDAO.consulta1Cita();
		}
		return aux;
	}

	@Override
	public ArrayList<Cita> consultaTodosCita(String usuario) {
		// TODO Auto-generated method stub
		return ifCitasDAO.consultaTodosCita(usuario);
	}

}
