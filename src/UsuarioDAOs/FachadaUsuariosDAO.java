package UsuarioDAOs;

import java.util.ArrayList;

import Usuarios.Usuario;

public class FachadaUsuariosDAO implements IFachadaUsuariosDAO{
	private IUsuariosDAO iUsuariosDAO=new UsuariosDAOImpl();

	@Override
	public boolean existeUsuario(String id) {
		return iUsuariosDAO.existeUsuario(id);
	}

	@Override
	public void addUsuario(Usuario us) {
		iUsuariosDAO.addUsuario(us);
	}

	@Override
	public void deleteUsuario() {
		iUsuariosDAO.deleteUsuario();
	}

	@Override
	public Usuario consulta1Usuario(String id) {
		return iUsuariosDAO.consulta1Usuario(id);
		
	}

	@Override
	public ArrayList<Usuario> consultaTodos(String tipo) {
		return iUsuariosDAO.consultaTodos(tipo);
	}

	@Override
	public void modificaUsuario(Usuario us) {
		iUsuariosDAO.modificaUsuario(us);
	}


	
}
