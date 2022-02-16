package SAUsuarios;

import java.util.ArrayList;

import Usuarios.Usuario;

public interface IFachadaUsuarios {
	
	public boolean addUsuario(Usuario us);
	public boolean deleteUsuario(String us);
	public boolean modificaUsuario(Usuario us );
	public Usuario consulta1Usuario(String id);
	public ArrayList<Usuario> consultaTodos(String tipo);

}
