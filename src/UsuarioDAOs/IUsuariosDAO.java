package UsuarioDAOs;

import java.util.ArrayList;

import Usuarios.Usuario;

public interface IUsuariosDAO {

	public boolean existeUsuario(String id);
	public void addUsuario(Usuario us);
	public void deleteUsuario();
	public void modificaUsuario(Usuario us);
	public Usuario consulta1Usuario(String id);
	public ArrayList<Usuario> consultaTodos(String tipo);

	

}
