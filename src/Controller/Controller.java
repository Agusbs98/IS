package Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Gestion.*;
import SACitas.*;
import SARecetas.*;
import SARecursos.*;
import SAUsuarios.*;
import Usuarios.*;

public class Controller {
	private Usuario _Us;
	private boolean _iniSesion = false;
	private List<Observer> _obs;
	private IFachadaRecetas _IFRecetas;
	private IFachadaRecursos _IFRecursos;
	private IFachadaUsuarios _IFUsuarios;
	private IFachadaCitas _IFCitas;
	private int _cantidadRecursos;
	
	public Controller() {
		_obs = new ArrayList<Observer>();
		_IFRecetas = new FachadaRecetasImpl();
		_IFRecursos = new FachadaRecursosImpl();
		_IFUsuarios = new FachadaUsuariosImpl();
		_IFCitas = new FachadaCitasImpl();
		_cantidadRecursos = 1;
	}
	
	public void iniciarSesion(String DNI, String Contra) {
		Usuario aux=_IFUsuarios.consulta1Usuario(DNI);
		if(aux.equalPass(Contra)) {
			_Us = aux;
			_iniSesion = true;
		}
		for(int i = 0;i<_obs.size();i++) {
			_obs.get(i).init(_Us);
		}
	}
	
	public void registrarse(Usuario us) throws Exception {
		if(us !=null) {
			if(_IFUsuarios.addUsuario(us)) {
				_iniSesion = true;
				_Us = us;
				for(int i = 0;i<_obs.size();i++) {
					_obs.get(i).init(_Us);
				}
			}
			else throw new Exception("ERROR usuario existente");
		}
		else throw new Exception("ERROR datos vacios");
			
	}
	
	public boolean hayUsuario() {
		return _iniSesion;
	}
	
	public void eliminaUsuario() {
		_IFUsuarios.deleteUsuario(_Us.get_DNI());
		cerrarSesion();
	}
	
	public void addObs(Observer obs) {
		if(!_obs.contains(obs)) {
			_obs.add(obs);
		}
	}
	public void editaUsuario(String contra, String email, String tlf) throws Exception{
		if(contra.equals("")&&email.equals("")&&tlf.equals("")) throw new Exception("ERROR datos vacios");
		Usuario us = _Us;
		if(!email.equals(""))
			us.set_Email(email);
		if(!contra.equals(""))
			us.set_password(contra);
		if(!tlf.equals(""))
			us.set_Telefono(tlf);
		
		if(!_IFUsuarios.modificaUsuario(_Us)) throw new Exception("ERROR Usuario no encontrado");
	}
	
	public void eliminaCita(int id) {
        _IFCitas.deleteCita(id);
        opciones(1);
    }
    
    public void editaCita(int id, String fecha, int consulta) throws Exception {
        if(!_IFCitas.modificaCita(id, fecha, consulta)) throw new Exception("ERROR al editar los datos");
        opciones(1);
    }
    
    public void eliminaReceta(int rect) {
        _IFRecetas.deleteReceta(rect);
        opciones(2);
    }
    
    public void editaReceta(int rect, String fechaFin, int cantidad) throws Exception{
    	String [] fechaCalendar= fechaFin.trim().split(":");
		int anio=Integer.parseInt(fechaCalendar[0]);
		int mes=Integer.parseInt(fechaCalendar[1]);
		int dia=Integer.parseInt(fechaCalendar[2]);
		Calendar cal = Calendar.getInstance();
		cal.set(anio, mes,dia);
		Receta re = _IFRecetas.consulta1Receta(rect);
		if(re == null) throw new Exception("ERROR ID de receta inexistente");
		if(cal.compareTo(re.get_fechaFin()) >= 0) {
			re.set_FechaFin(Integer.parseInt(fechaCalendar[0]), Integer.parseInt(fechaCalendar[1])-1,Integer.parseInt(fechaCalendar[2]));
		}
		else throw new Exception("ERROR fecha introducida ");
		if(cantidad>0) {
			re.set_Cantidad(cantidad);
		}
		else throw new Exception("ERROR cantidad erronea");
        _IFRecetas.modificaReceta(re);
        opciones(2);
    }
    
    public void minRecursos(int cantidad) {
    	_cantidadRecursos = cantidad;
    	opciones(3);
    }

	public void cerrarSesion() {
		// TODO Auto-generated method stub
		_iniSesion = false;
		_Us = null;
		for(int i = 0;i<_obs.size();i++) {
			_obs.get(i).close();
		}
		
	}
	public void actualiza(int n) {
		ArrayList<Cita> citas= new ArrayList<Cita>(_IFCitas.consultaTodosCita(_Us.get_Nombre()));
		ArrayList<Receta> recetas = new ArrayList<Receta>(_IFRecetas.consultaTodos(_Us.get_Nombre()));
		ArrayList<Recursos> recursos = new ArrayList<Recursos>(/*_IFRecursos.consultaTodos(5)*/);
		for(int i = 0;i<_obs.size();i++) {
			_obs.get(i).charge(n,_Us,citas,recetas,recursos);
		}
	}
	public void opciones(int n) {
		ArrayList<Cita> citas= new ArrayList<Cita>(_IFCitas.consultaTodosCita(_Us.get_Nombre()));
		ArrayList<Receta> recetas = new ArrayList<Receta>(_IFRecetas.consultaTodos(_Us.get_Nombre()));
		ArrayList<Recursos> recursos = new ArrayList<Recursos>(_IFRecursos.consultaTodos(_cantidadRecursos));
		for(int i = 0;i<_obs.size();i++) {
			_obs.get(i).opciones(n,citas, recetas,recursos);
		}
	}
	
	public void addReceta(String m,String medicamento, String cantidad, Date fechaFin) throws Exception {
        DateFormat fecha=new SimpleDateFormat("yyyy:MM:dd:hh:mm");
    	String convertido = fecha.format(fechaFin);
    	Usuario aux=_IFUsuarios.consulta1Usuario(m);
    	Receta rct = null;
    	Recursos rec = _IFRecursos.consulta1Recurso(medicamento);
    	if( rec == null) throw new Exception("ERROR recurso no encontrado");
    	else if (rec.get_Cantidad()<Integer.parseInt(cantidad)) throw new Exception("ERROR recursos insuficientes");
    	else {
    		rec.set_Cantidad(rec.get_Cantidad()-Integer.parseInt(cantidad));
    		_IFRecursos.modificaRecurso(rec);
	    	if(aux.get_tipo()== "m") {
	    		rct=new Receta(aux.get_Nombre(),_Us.get_Nombre(), medicamento, cantidad , convertido);
	    	}
	    	else {
	    		rct=new Receta(_Us.get_Nombre(),m, medicamento, cantidad , convertido);
	    	}
	        
	        if(!_IFRecetas.addReceta(rct)) { // Arreglar por que se duplican...
	        	throw new Exception("ERROR receta ya existente");
	        }
	        if(rec.get_Cantidad()-Integer.parseInt(cantidad) == 0){_IFRecursos.deleteRecurso(rec.get_Recurso());}
	        opciones(2);
    	}

    }
    public void addCita(String m,Date f)throws Exception {
    	DateFormat fecha=new SimpleDateFormat("yyyy:MM:dd:HH:mm");
    	String convertido = fecha.format(f);
    	Usuario aux=_IFUsuarios.consulta1Usuario(m);
    	Cita cita = null;
    	if(aux.get_tipo()== "m") {
    		String[] data = aux.serialize().trim().split(";");
    		cita=new Cita(aux.get_Nombre(),_Us.get_Nombre(), data[5], Integer.parseInt(data[6]) , convertido);
    	}
    	else {
    		String[] data = _Us.serialize().trim().split(";");
    		cita=new Cita(_Us.get_Nombre(),m, data[5], Integer.parseInt(data[6]) , convertido);
    	}
    	if(!cita.getFecha().after(Calendar.getInstance())) throw new Exception("ERROR no se pueden introducir fechas pasadas");
    	else if(!_IFCitas.addCita(cita)) {
        	throw new Exception("ERROR cita ya existente");
        }
        opciones(1);
    }

    public void addRecurso(String recurso, int cantidad) throws Exception {
    	Recursos rec=new Recursos(recurso, cantidad);
        
        if(!_IFRecursos.addRecurso(rec)) {
        	throw new Exception("ERROR al aniadir recurso");
        }
        opciones(3);
        
    }
    
    public List<Usuario> listaUsuarios(){
    	if(_Us.get_tipo().equals("p")) return _IFUsuarios.consultaTodos("m");
    	return _IFUsuarios.consultaTodos("p");
    }
    
    public List<Recursos> listaRecursos(){
    	return _IFRecursos.consultaTodos(1);
    }

}