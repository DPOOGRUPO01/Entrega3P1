package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import logic.Calendario;
import logic.Equipo;
import logic.Jugador;
import logic.Partido;

public class Registro 
{
	public ArrayList<Equipo> equipos= new ArrayList<Equipo>();
	
	public void cargarInformacionEquipos(String NombreArchivo)
	{
		File archivo = new File("./data/"+NombreArchivo+".txt");
        try{
            Scanner scanner = new Scanner(archivo);
            scanner.nextLine();
            String linea = scanner.nextLine();
            for(int i=0 ; i<=19; i++)
            {
            	
            String nombreEquipo = linea.split(";")[0];
            String nombreCiudad = linea.split(";")[1];
            String nombreEntrenador = linea.split(";")[2];
            Equipo equipo=agregarEquipo(nombreEquipo, nombreCiudad, nombreEntrenador);
            for(int j=0; j<=30;j++)
            {
            	
            	String nombreJugador= linea.split(";")[3+j].split(",")[0];
	            String posicionJugador= linea.split(";")[3+j].split(",")[1];
	            double valorJugador= Double.parseDouble(linea.split(";")[3+j].split(",")[2]); 
	            int puntosTotalesJugador= Integer.parseInt(linea.split(";")[3+j].split(",")[3]);
	            int puntosPartidoJugador= Integer.parseInt(linea.split(";")[3+j].split(",")[4]);
	            boolean lesionJugador= Boolean.parseBoolean(linea.split(";")[3+j].split(",")[5]);
	            equipo.agregarJugador(nombreJugador, posicionJugador, valorJugador, puntosTotalesJugador, puntosPartidoJugador, lesionJugador);
	            
            }
            
            if (i<19) linea=scanner.nextLine();
            }
            
            System.out.println("Equipos cargados");
            //System.out.println(equipos.get(9).getNombre());

        }catch(FileNotFoundException e){
            System.out.println("El archivo no existe");
        }
        
	}
	
	public void cargarInformacionCalendario(String NombreArchivo)
	{
		File archivo = new File("./data/"+NombreArchivo+".txt");
        try{
            Scanner scanner = new Scanner(archivo);
            scanner.nextLine();
            String linea = scanner.nextLine();
            for(int i=0 ; i<=19; i++)
            {
            	String nombreEquipo = linea.split(";")[0];
            	Calendario calendario = new Calendario();
            	
            	for(int j=0; j<=37;j++)
                {
            		String EquipoLocal= linea.split(";")[1+j].split("-")[0];
    	            String EquipoVisitante= linea.split(";")[1+j].split("-")[1];
    	            String fecha= linea.split(";")[1+j].split("-")[2]; 
    	            String hora= linea.split(";")[1+j].split("-")[3];
    	            Equipo equipo;
    	            String localVisitante="";
    	            if(nombreEquipo.equals(EquipoLocal)) 
    	            	{
    	            		localVisitante="Local";
    	            		equipo=buscarEquipoPorNombre(EquipoVisitante);
    	            	}
    	            	else 
    	            	{
    	            		localVisitante="visitante";
    	            		equipo=buscarEquipoPorNombre(EquipoLocal);
    	            	}		
    	            Partido partido= new Partido(equipo,localVisitante , fecha, hora, null, null);
    	            calendario.agregarPartido(partido);
                }
            	
            	 asignarCalendarioAEquipo(nombreEquipo, calendario);
            	 if (i<19) linea=scanner.nextLine();
            }
            System.out.println("Calendario cargado");
        }catch(FileNotFoundException e){
            System.out.println("El archivo no existe");
        }
            
	}
	
	public Equipo agregarEquipo(String pNombre, String pCiudad, String pEntrenador)
    {
    	Equipo equipo = new Equipo(pNombre, pCiudad, pEntrenador);
    	equipos.add(equipo);
    	return equipo;
    }
	
	public void asignarCalendarioAEquipo(String pNombreEquipo, Calendario calendario)
	{
		Equipo equipo = buscarEquipoPorNombre(pNombreEquipo);
		equipo.setCalendario(calendario);
	}
	public Equipo buscarEquipoPorNombre(String pNombre)
	{
		Equipo equipo=null;
		for (int i=0; i<=equipos.size()-1;i++)
		{
//			System.out.println(equipos.get(i));
			if (equipos.get(i).getNombre().equals(pNombre))
			{
				equipo=equipos.get(i);
			}
		}
		return equipo;
	}
	
	public boolean existeElEquipo(String pNombreEquipo)
	{
		if (buscarEquipoPorNombre(pNombreEquipo)!=null)return true;
		else return false;
	}
	
	public Partido consultarPartido(Equipo equipo, int numeroPartido)
	{
		Partido partido=equipo.getCalendario().consultarPartido(numeroPartido);
		return partido;
	}
	public boolean existePartido(Equipo equipo, int numeroPartido)
	{
		Partido partido=equipo.getCalendario().consultarPartido(numeroPartido);
		if(partido!=null) return true;
		else return false;
	}
	
	public Jugador consultarJugador(Equipo equipo, String pNombreJugador)
	{
		Jugador jugador= equipo.buscarJugadorPorNombre(pNombreJugador);
		return jugador;
	}
	
}
