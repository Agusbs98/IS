package SAUsuarios;

import java.util.ArrayList;


import Usuarios.Usuario;

public class FachadaUsuariosImpl  implements IFachadaUsuarios{
	
	private ISAUsuarios iUsuarios = new SAUsuariosImpl();

	@Override
	public boolean addUsuario(Usuario us) {
		
		return iUsuarios.addUsuario(us);
		
	}

	@Override
	public boolean deleteUsuario(String us) {
		
		return iUsuarios.deleteUsuario(us);
		
	}

	@Override
	public Usuario consulta1Usuario(String id) {
		return iUsuarios.consulta1Usuario(id);
	}

	@Override
	public ArrayList<Usuario> consultaTodos(String tipo) {
		
		return  iUsuarios.consultaTodos(tipo);
	}

	@Override
	public boolean modificaUsuario(Usuario us) {
		return iUsuarios.modificaUsuario(us);	
	}

}
