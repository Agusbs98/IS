package RecursosDAOs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Gestion.Recursos;



public class RecursosDAOImpl implements IRecursosDAO{
	private String dataBaseName="datos_recursos.txt";
	private int indexEncontrado;
	
	@Override
	public boolean existeRecurso(String nombre) {
		ArrayList<Recursos>listaRecursos=leerArchivo();
		boolean encontrado=false;
		int cont=0;
		while(cont<listaRecursos.size() && !encontrado) {
			if(listaRecursos.get(cont).get_Recurso().toLowerCase().equals(nombre.toLowerCase())) {
				encontrado=true;
				indexEncontrado=cont;
			}
			++cont;
		}
		return encontrado;
	}

	@Override
	public void addRecurso(Recursos rec) {
		ArrayList<Recursos>listaRecursos=leerArchivo();
		if(existeRecurso(rec.get_Recurso())) {
			listaRecursos.get(indexEncontrado).set_Cantidad(+rec.get_Cantidad());
		}else {
			listaRecursos.add(rec);
		}
		guardarArchivo(listaRecursos);
	}

	@Override
	public void deleteRecurso() {
		ArrayList<Recursos>listaRecursos=leerArchivo();
		listaRecursos.remove(indexEncontrado);
		guardarArchivo(listaRecursos);
	}

	@Override
	public Recursos consulta1Recurso() {
		ArrayList<Recursos>listaRecursos=leerArchivo();
		Recursos rec= listaRecursos.get(indexEncontrado);
		return rec;
	}

	@Override
	public ArrayList<Recursos> consultaTodos(int cantidad) {
		ArrayList<Recursos>listaRecursos=leerArchivo();
		ArrayList<Recursos>lista=new ArrayList<Recursos>();
		int cont=0;
		while(cont<listaRecursos.size()) {
			if(listaRecursos.get(cont).get_Cantidad()>=cantidad) {
				lista.add(listaRecursos.get(cont));
			}
			++cont;
		}
		return lista;
	}

	@Override
	public void modificaRecurso(int cantidad) {
		ArrayList<Recursos>listaRecursos=leerArchivo();
		listaRecursos.get(indexEncontrado).set_Cantidad(cantidad);
		guardarArchivo(listaRecursos);

	}
	private ArrayList<Recursos> leerArchivo() {
		ArrayList<Recursos>listaRecursos=new ArrayList<Recursos>();
		BufferedReader br = null;
		try {
	         br = new BufferedReader(new FileReader(dataBaseName));
	         String linea;
	         while((linea=br.readLine())!=null) {
	        	 String[]parameters=linea.trim().split(";");
	        	 listaRecursos.add(new Recursos(parameters[0], Integer.parseInt(parameters[1])));
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
		return listaRecursos;
		
	}
	
	private void guardarArchivo(ArrayList<Recursos>listaRecursos) {
		BufferedWriter outChars=null;
		int cont=0;
		try {
			outChars=new BufferedWriter(new FileWriter(dataBaseName));
			while(cont<listaRecursos.size()) {
				outChars.write(listaRecursos.get(cont).serialize()+'\n');
				++cont;
			}
			outChars.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}


}
