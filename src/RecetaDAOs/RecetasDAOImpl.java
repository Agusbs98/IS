package RecetaDAOs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import Gestion.Receta;

public class RecetasDAOImpl implements IRecetasDAO{
	private String dataBaseName="datos_recetas.txt";
	private int indexEncontrado;

	@Override
	public boolean existeReceta(int rct) {
		ArrayList<Receta>listaRecetas=leerArchivo();
		int cont=0;
		boolean encontrado=false;
		while(cont<listaRecetas.size() && !encontrado) {
			if(listaRecetas.get(cont).get_ID()== rct) {
				encontrado=true;
				indexEncontrado=cont;
			}
			++cont;
		}
		return encontrado;
	}
	@Override
	public void addReceta(Receta rect) {
		ArrayList<Receta>listaRecetas=leerArchivo();
		listaRecetas.add(rect);
		guardarArchivo(listaRecetas);
	}
	@Override
	public void deleteReceta() {
		ArrayList<Receta>listaRecetas=leerArchivo();
		listaRecetas.remove(indexEncontrado);
		guardarArchivo(listaRecetas);
	}
	@Override
	public void modificaReceta(Receta rect) {
		ArrayList<Receta>listaRecetas=leerArchivo();
		listaRecetas.set(indexEncontrado, rect);
		guardarArchivo(listaRecetas);
		
	}
	@Override
	public Receta consulta1Receta() {
		ArrayList<Receta>listaRecetas=leerArchivo();
		return listaRecetas.get(indexEncontrado);
	}
	@Override
	public ArrayList<Receta> consultaTodos(String us) {
		ArrayList<Receta>listaRecetas=leerArchivo();
		ArrayList<Receta>lista=new ArrayList<Receta>();
		int cont=0;
		while(cont<listaRecetas.size()) {
			if(listaRecetas.get(cont).get_Medico().equals(us)||listaRecetas.get(cont).get_Paciente().equals(us)) {
				lista.add(listaRecetas.get(cont));
			}
			++cont;
		}
		return lista;
	}
	
	private ArrayList<Receta> leerArchivo() {
		ArrayList<Receta>listaRecetas = new ArrayList<Receta>();
		BufferedReader br = null;
		try {
	         br = new BufferedReader(new FileReader(dataBaseName));
	         String linea;
	         Receta.cont = 0;
	         while((linea=br.readLine())!=null) {
	        	 String[]parameters=linea.trim().split(";");
	        	 Receta rec = new Receta(parameters[0],parameters[1], parameters[2], parameters[3], parameters[4]);
	        	 int comp =rec.get_fechaFin().compareTo(Calendar.getInstance()); 
	        	 if(comp>=0)
	        	 listaRecetas.add(rec);
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
		return listaRecetas;
		
	}
	
	private void guardarArchivo(ArrayList<Receta> listaRecetas) {
		BufferedWriter outChars=null;
		int cont=0;
		try {
			outChars=new BufferedWriter(new FileWriter(dataBaseName));
			while(cont<listaRecetas.size()) {
				outChars.write(listaRecetas.get(cont).serialize()+'\n');
				++cont;
			}
			outChars.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}
