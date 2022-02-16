package SAUsuarios;

import java.util.ArrayList;

import UsuarioDAOs.*;
import Usuarios.Usuario;

public class SAUsuariosImpl implements ISAUsuarios {
      private IFachadaUsuariosDAO ifUsuarioDAO = new FachadaUsuariosDAO();

	@Override
	public boolean addUsuario(Usuario us) {
		if(ifUsuarioDAO.existeUsuario(us.get_DNI()))return false;
		ifUsuarioDAO.addUsuario(us);
		return true;
	}

	@Override
	public boolean deleteUsuario(String us) {
                
		if(!ifUsuarioDAO.existeUsuario(us))return false;
		ifUsuarioDAO.deleteUsuario();
		return true;		
	}

	@Override
	public Usuario consulta1Usuario(String id) {
		 
		 Usuario aux=null;
		if(ifUsuarioDAO.existeUsuario(id))
			aux= ifUsuarioDAO.consulta1Usuario(id);
		else
			System.out.println("[ERROR] Usuario inexistente");
			
		return aux;
		
		
	}

	@Override
	public ArrayList<Usuario> consultaTodos(String tipo) {

		return ifUsuarioDAO.consultaTodos(tipo);
	}

	@Override
	public boolean modificaUsuario(Usuario us) {
	
		if(!ifUsuarioDAO.existeUsuario(us.get_DNI()))return false;
		ifUsuarioDAO.modificaUsuario(us);
		return true;

		 
		
	}

}
