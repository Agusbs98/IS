package CitaDAOs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import Gestion.Cita;


public class CitaDAOImpl implements ICitaDAO{
	private String dataBaseName="datos_citas.txt";
	private int indexEncontrado;
	
	@Override
	public boolean existeCita(int cita) {
		// TODO Auto-generated method stub
		ArrayList<Cita>listaCitas=leerArchivo();
		int cont=0;
		boolean encontrado=false;
		while(cont<listaCitas.size() && !encontrado) {
			if(listaCitas.get(cont).getId() == cita) {
				encontrado=true;
				indexEncontrado=cont;
			}
			++cont;
		}
		return encontrado;
	}

	@Override
	public void addCita(Cita cita) {
		ArrayList<Cita>listaCitas=leerArchivo();
		listaCitas.add(cita);
		guardarArchivo(listaCitas);
	}

	@Override
	public void deleteCita() {
		ArrayList<Cita>listaCitas=leerArchivo();
		listaCitas.remove(indexEncontrado);
		guardarArchivo(listaCitas);
	}

	@Override
	public void modificaCita(String fecha, int consulta) {
		String [] fechaCalendar= fecha.trim().split(":");
	  ArrayList<Cita>listaCitas=leerArchivo();
	  int anio=Integer.parseInt(fechaCalendar[0]);
	  int mes=Integer.parseInt(fechaCalendar[1]);
	  int dia=Integer.parseInt(fechaCalendar[2]);
	  if(consulta>0) {
		  listaCitas.get(indexEncontrado).setConsulta(consulta);		  
	  }
	  if(anio>=listaCitas.get(indexEncontrado).getFecha().YEAR && mes>=listaCitas.get(indexEncontrado).getFecha().MONTH 
			  && dia>=listaCitas.get(indexEncontrado).getFecha().DATE) {
		  listaCitas.get(indexEncontrado).set_Fecha(Integer.parseInt(fechaCalendar[0]),Integer.parseInt(fechaCalendar[1])-1,Integer.parseInt(fechaCalendar[2]),		  
				  Integer.parseInt(fechaCalendar[3]), Integer.parseInt(fechaCalendar[4]));
	  }
	  guardarArchivo(listaCitas);
	}

	@Override
	public Cita consulta1Cita() {
		ArrayList<Cita>listaCitas=leerArchivo();
		return listaCitas.get(indexEncontrado);
	}

	@Override
	public ArrayList<Cita> consultaTodosCita(String us) {
		// TODO Auto-generated method stub
		ArrayList<Cita> salida = new ArrayList<>();
		for (Cita data: leerArchivo()) {
			if(data.getMedico().equals(us)||data.getPaciente().equals(us)) { 
				//posible cambio si se hacen cambios en Cita
				salida.add(data);
			}
		}
		return salida;
	}
	private ArrayList<Cita> leerArchivo() {
		ArrayList<Cita>listaCitas=new ArrayList<Cita>();
		BufferedReader br = null;
		try {
	         br = new BufferedReader(new FileReader(dataBaseName));
	         String linea;
	         Cita.cont = 0;
	         while((linea=br.readLine())!=null) {
	        	 String[]parameters=linea.trim().split(";");
	        	 Cita c = new Cita(parameters[0], parameters[1],parameters[2], Integer.parseInt(parameters[3]),parameters[4]);
	        	// int comp =c.getFecha().after(Calendar.getInstance()); 
	        	 if(c.getFecha().after(Calendar.getInstance()))
	        		 listaCitas.add(c);
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
		return listaCitas;
		
	}
	
	private void guardarArchivo(ArrayList<Cita> listaCitas) {
		BufferedWriter outChars=null;
		int cont=0;
		try {
			outChars=new BufferedWriter(new FileWriter(dataBaseName));
			while(cont<listaCitas.size()) {
				outChars.write(listaCitas.get(cont).serialize()+'\n');
				cont++;
			}
			outChars.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public boolean cambioCorrecto(String fecha, int consulta) {
		// TODO Auto-generated method stub
		ArrayList<Cita>listaCitas=leerArchivo();
		int cont=0;
		boolean encontrado=true;
		while(cont<listaCitas.size() && encontrado) {
			 if(listaCitas.get(cont).getFechaS().equals(fecha) && (listaCitas.get(cont).get_Consulta() == consulta)){
				encontrado = false;
				
			} 
			
			++cont;
		}
		return encontrado;
	}
	
}
