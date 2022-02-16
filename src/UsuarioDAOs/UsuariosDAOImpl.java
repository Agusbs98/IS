package UsuarioDAOs;

import java.io.*;
import java.util.*;

import Usuarios.*;

public class UsuariosDAOImpl implements IUsuariosDAO{
	private String dataBaseName="datos_usuarios.txt";
	private int indexEncontrado;
	
	@Override
	public boolean existeUsuario(String dni) {
		ArrayList<Usuario>listaUsuarios=leerArchivo();
		int cont=0;
		boolean encontrado=false;
		while(cont<listaUsuarios.size() && !encontrado) {
			if(listaUsuarios.get(cont).get_DNI().contentEquals(dni.toLowerCase())|| listaUsuarios.get(cont).get_Nombre().toLowerCase().contentEquals(dni.toLowerCase())) {
				encontrado=true;
				indexEncontrado=cont;
			}
			++cont;
		}
		return encontrado;
	}
	@Override
	public void addUsuario(Usuario us) {
		ArrayList <Usuario>listaUsuarios= leerArchivo();
		listaUsuarios.add(us);
		guardarArchivo(listaUsuarios);
	}
	@Override
	public void deleteUsuario() {
		ArrayList <Usuario>listaUsuarios= leerArchivo();
		listaUsuarios.remove(indexEncontrado);
		guardarArchivo(listaUsuarios);
	}
	
	@Override
	public void modificaUsuario(Usuario us) {
		ArrayList <Usuario>listaUsuarios= leerArchivo();
		listaUsuarios.set(indexEncontrado,us);
		guardarArchivo(listaUsuarios);		
	}
	
	@Override
	public Usuario consulta1Usuario(String id) {
		ArrayList <Usuario>listaUsuarios= leerArchivo();
		Usuario us=listaUsuarios.get(indexEncontrado);
		return us;
	}
	@Override
	public ArrayList<Usuario> consultaTodos(String tipo) {
		ArrayList<Usuario> listaUsuarios=leerArchivo();
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		int cont=0;
		while(cont<listaUsuarios.size()) {
			if(listaUsuarios.get(cont).get_tipo().toLowerCase().equals(tipo.toLowerCase())) {
				lista.add(listaUsuarios.get(cont));
			}
			++cont;
		}
		return lista;
	}
	
	private ArrayList<Usuario> leerArchivo() {
		ArrayList<Usuario>listaUsuarios=new ArrayList<Usuario>();
		BufferedReader br = null;
		try {
	         br = new BufferedReader(new FileReader(dataBaseName));
	         String linea;
	         while((linea=br.readLine())!=null) {
	        	 String[]parameters=linea.trim().split(";");
	        	 if(parameters.length == 7) {
	        		 listaUsuarios.add(new Medico(parameters[0],parameters[1].toLowerCase(), parameters[2], parameters[3].toLowerCase(),parameters[4],parameters[5], Integer.parseInt(parameters[6])));
	        	 }else if(parameters.length == 5){
	        		 listaUsuarios.add(new Paciente(parameters[0],parameters[1].toLowerCase(), parameters[2], parameters[3].toLowerCase(), parameters[4]));
	        	 }
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != br ){   
	               br.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		return listaUsuarios;
		
	}
	
	private void guardarArchivo(ArrayList<Usuario> listaUsuarios) {
		BufferedWriter outChars=null;
		int cont=0;
		try {
			outChars=new BufferedWriter(new FileWriter(dataBaseName));
			while(cont<listaUsuarios.size()) {
				outChars.write(listaUsuarios.get(cont).serialize()+'\n');
				++cont;
			}
			outChars.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}


}
