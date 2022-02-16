package Usuarios;

import Gestion.Receta;

public abstract class Usuario {
	private String _password; // cambio por que no es necesario saber el tipo
	private String _Nombre;
	private String _Email;
	private String _DNI;
	private String _Telefono;
	
	

	public Usuario(String nombre, String email, String password, String dni, String telefono) {
		set_password(password);
		set_Nombre (nombre);
		set_Email(email);
		set_DNI(dni);
		set_Telefono(telefono);
		
	}
	
	public String get_Nombre() {
		return _Nombre;
	}

	public void set_Nombre(String _Nombre) {
		this._Nombre = _Nombre;
	}

	public String get_Email() {
		return _Email;
	}

	public void set_Email(String _Email) {
		this._Email = _Email;
	}

	public String get_DNI() {
		return _DNI;
	}

	public void set_DNI(String _DNI) { //el unico set privado por que sumonemos que una vez creado el ID no puede ser cambiado asi evitamos problemas
		this._DNI = _DNI;
	}

	public String get_Telefono() {
		return _Telefono;
	}

	public void set_Telefono(String _Telefono) {
		this._Telefono = _Telefono;
	}
	
	abstract public void addReceta(Receta receta);

	private String get_password() {
		return _password;
	}
	public boolean equalPass(String pass) {
		return _password.equals(pass);
	}

	public void set_password(String ocupacion) {
		this._password = ocupacion;
	}

	public String serialize() {
		return get_Nombre()+";"+get_Email()+";"+get_password()+";"+get_DNI()+";"+get_Telefono();
	}
	
	abstract public String get_tipo();


}
