package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import logic.Equipo;
import logic.Persona;

public class Ingreso 
{
	private static Registro registro= new Registro();
	private static ArrayList<EquipoFantasia> equipos = new ArrayList<>();
	private EquipoFantasia equipo;
	
	public Persona personaNueva(String pNombre, String pLogin, String pPassword, boolean pEsAdmin)
	{
		Persona persona = new Persona(pNombre, pLogin, pPassword, pEsAdmin);
		return persona;
	}
	
	public static void esAdministrador()
	{
		System.out.println("-----------------------");
		String respuesta=input("¿Desea cargar o actualizar datos? 0 = cargar / 1 = acualizar");
		if(respuesta.equals("0")) 
		{
			registroCompleto();
		}
		else if(respuesta.equals("1")) 
		{
			registroParcial();
		}
		else System.out.println("Ingrese un valor correcto");
	}
	
	public static void esUsuario() throws FileNotFoundException
	{
		//Rooney
		cargarUsuarios();
		System.out.println("-------EQUIPO FANTASIA--------");
		String respuesta=input("¿Ya tiene equipo fantasia? (si/no)");
		if (respuesta.equals("si"))
		{
			String NombreEquipo=input("Ingrese el nombre del equipo: ");
			EquipoFantasia equipoJugador = buscarEquipoPorNombre(NombreEquipo);
			boolean terminar=false;
			while(terminar==false) {
	            	if (equipoJugador!=null) 
	            		{
	            			System.out.println("-------EQUIPO FANTASIA--------");
	            			DecimalFormat formato1 = new DecimalFormat("#.00");
	            			System.out.println("Presupuesto: "+formato1.format(equipoJugador.getPresupuesto()));
	            			System.out.println("Jugadores: ");
	            			if(equipoJugador.getJugadores()==null)
	            			{
		            			System.out.println("No hay jugadores");
	            			}
	            			else 
	            			{
	            				for(int j=0;j<=equipoJugador.getJugadores().size()-1;j++)
		            			{
		            				System.out.println(j+1+". "+equipoJugador.getJugadores().get(j).getNombre());
		            			}
	            			}
	            			System.out.println("----Opciones----");
	            			System.out.println("1.Fichar Jugador");
	            			System.out.println("2.Cambiar Jugador");
	            			System.out.println("3.Consultar puntos");
	            			String respuesta2=input("Ingrese el numero de la opcion: ");
	            			if (respuesta2.equals("1"))
	            			{
	            				String nombreClub=input("Ingrese el club donde juega el jugador: ");
	            				String nombreJugador=input("Ingrese el nombre jugador: ");
	            				Equipo equipo1 = registro.buscarEquipoPorNombre(nombreClub);
	            				if(equipo1.buscarJugadorPorNombre(nombreJugador)!=null)
	            				{
	            					String respuesta3 = equipoJugador.agregarJugador(nombreClub, nombreJugador, registro);
	            					if(respuesta3.equals(" "))System.out.println("Jugador no encontrado o presupuesto insuficiente");
	            				}
	            				else System.out.println("El dato del jugador o del equipo es erroneo");
	            				
	            			}
	            			else if(respuesta2.equals("2"))
	            			{
	            				String nombreJugador1=input("Ingrese el nombre jugador que desea cambiar: ");
	            				String nombreClub=input("Ingrese el club donde juega el jugador nuevo: ");
	            				String nombreJugador2=input("Ingrese el nombre jugador que desea fichar: ");
	            				equipoJugador.cambiarJugador(nombreJugador1, nombreClub, nombreJugador2, registro);
	            			}
	            			else if(respuesta2.equals("3"))
	            			{
	            				System.out.println(equipoJugador.getPuntosTotales());
	            			}
	            		}
	            	else System.out.println("No se encuentra el equipo");
	       }
			String continuar=input("Desea continuar? (si/no) ");
			if(continuar.equals("no")) terminar=true;
		}
			if(respuesta.equals("no"))
			{
				System.out.println("----CREAR EQUIPO FANTASIA ----");
				String nombreClub=input("Ingrese el nombre del club: ");
				if(equipoExiste(nombreClub)==false)
				{
					EquipoFantasia equipo = new EquipoFantasia(nombreClub, 100, 0);
					equipos.add(equipo);
					agregarEquipo(equipo);
//					System.out.println("Equipo creado, vuelva a lanzar la app para jugar");
				}
				else System.out.println("Ese nombre de equipo ya existe");
				
			}
	}
	
	public static void cargarUsuarios() throws FileNotFoundException
	{
		registro.cargarInformacionEquipos("Equipos1");
		registro.cargarInformacionCalendario("CalendarioPremier1");
		File archivo = new File("./data/EquiposFantasia.txt");
		FileReader pArchivo = new FileReader("./data/EquiposFantasia.txt");
		BufferedReader bf = new BufferedReader(pArchivo);
        try{
            Scanner scanner = new Scanner(archivo);
            String linea = scanner.nextLine();
            for(int i = 0; i<=bf.lines().count(); i++)
            {
//            	System.out.println(linea);
            	String nombreEquipo = linea.split(";")[0];
            	Double presupuestoEquipo  = Double.parseDouble(linea.split(";")[1]);
//            	System.out.println(presupuestoEquipo);
            	int puntosEquipo  = Integer.parseInt(linea.split(";")[2]);
            	EquipoFantasia equipo = new EquipoFantasia(nombreEquipo, presupuestoEquipo, puntosEquipo);
            	equipos.add(equipo);
                if(i<bf.lines().count()) linea=scanner.nextLine();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("El archivo no existe");
        }
        
	}
	
	public static void agregarEquipo(EquipoFantasia equipo) throws FileNotFoundException
	{
		FileReader pArchivo = new FileReader("./data/EquiposFantasia.txt");
		BufferedReader bf = new BufferedReader(pArchivo);
		FileWriter fichero=null;
		PrintWriter pw = null;
        try
        {
        	fichero=new FileWriter("./data/EquiposFantasia.txt", true);
        	pw=new PrintWriter(fichero);
            
        	pw.println('\n'+equipo.getNombre()+";"+equipo.getPresupuesto()+";"+equipo.getPuntosTotales()+";");
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	        
	
	public static void registroCompleto()
	{
		System.out.println("--------Cargar----------");
		String archivoEquipos=input("Ingrese nombre del archivo con los equipos: ");
		registro.cargarInformacionEquipos(archivoEquipos);
		String archivoCalendario=input("Ingrese nombre del archivo con el calendario: ");
		registro.cargarInformacionCalendario(archivoCalendario);
	}
	
	public static void registroParcial()
	{
		System.out.println("--------Actualizar----------");
		String nombreEquipo=input("Ingrese el nombre del equipo para actualizar sus datos: ");
		if (registro.existeElEquipo(nombreEquipo)==true)
		{
			System.out.println("--------Actualizar----------");
			System.out.println("¿Que datos desea actualizar?:");
			System.out.println("1.Datos del club (nombre/ciudad/entrenador)");
			System.out.println("2.Datos de los partidos (rivales/localidad/fecha/hora/jugadores convocados/resultado)");
			System.out.println("3.Datos de los jugadores (nombre/posicion/precio/puntos/lesion) ");
			int opcion=Integer.parseInt(input("Ingrese el numero de la opcion: "));
			if(opcion==1)
			{
				System.out.println("--------Actualizar----------");
				System.out.println("¿Que datos desea actualizar?:");
				System.out.println("1.Nombre club");
				System.out.println("2.Ciudad club");
				System.out.println("3.Nombre entrenador");
				int opcion2=Integer.parseInt(input("Ingrese el numero de la opcion: "));
				if (opcion2==1)
				{
					String NombreEquipoNuevo=input("Ingrese nombre nuevo del equipo: ");
					registro.buscarEquipoPorNombre(nombreEquipo).setNombre(NombreEquipoNuevo);
					System.out.println("Nombre modificado");
				}
				else if (opcion2==2)
				{
					String NombreCiudadNuevo=input("Ingrese el nombre nuevo de la ciudad: ");
					registro.buscarEquipoPorNombre(nombreEquipo).setNombre(NombreCiudadNuevo);
					System.out.println("Ciudad modificada");
				}
				else if (opcion2==3)
				{
					String NombreEntrenador=input("Ingrese el nombre nuevo del entrenador: ");
					registro.buscarEquipoPorNombre(nombreEquipo).setNombre(NombreEntrenador);
					System.out.println("Entrenador modificado");
				}
				else System.out.println("Opcion incorrecta");
			}
			if(opcion==2) 
			{
				System.out.println("--------Actualizar----------");
				int numeroPartido=Integer.parseInt(input("Ingrese el numero del partido que quiere actualizar: (ej. para actualizar el 5to partido que el equipo disputa ingrese: 5 "));
				if(registro.existePartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido)==true)
				{
					System.out.println("--------Actualizar----------");
					System.out.println("Equipo Rival: "+registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).getEquipo1().getNombre()+".");
					System.out.println("Local/Visitante: "+registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).getLocalVisitante()+".");
					System.out.println("Fecha: "+registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).getFecha()+".");
					System.out.println("Hora: "+registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).getHora()+".");
					System.out.println("Jugadores convocados:");
					if(registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).getJugadores()==null)
					{
						System.out.println("No hay jugadores convocados");
					}
					else
					{
						for(int i=0;i<=registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).getJugadores().size()-1;i++)
						{
						System.out.println("Jugador "+i+1+": "+registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).getJugadores().get(i).getNombre()+".");
						}
					}
					System.out.println("Resultado: "+registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).getResultado()+".");
					System.out.println("¿Que datos desea actualizar?:");
					System.out.println("1.Rival");
					System.out.println("2.Local/Visitante");
					System.out.println("3.Fecha");
					System.out.println("4.Hora");
					System.out.println("5.Jugadores convocados");
					System.out.println("6.Resultado");
					int opcion2=Integer.parseInt(input("Ingrese el numero de la opcion: "));
					if (opcion2==1)
					{
						String NombreEquipoRivalNuevo=input("Ingrese nombre del nuevo equipo rival: ");
						registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).setEquipo1(registro.buscarEquipoPorNombre(NombreEquipoRivalNuevo));
						System.out.println("Rival actualizado");
					}
					else if (opcion2==2)
					{
						String LocaliaNuevo=input("Ingrese la nueva localia (Local/Visitante): ");
						registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).setLocalVisitante(LocaliaNuevo);
						System.out.println("Localia actualizada");
					}
					else if (opcion2==3)
					{
						String FechaNueva=input("Ingrese la nueva fecha (dia/mes/año): ");
						registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).setFecha(FechaNueva);
						System.out.println("Fecha actualizada");
					}
					else if (opcion2==4)
					{
						String HoraaNueva=input("Ingrese la nueva hora (hora:minutos): ");
						registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).setHora(HoraaNueva);
						System.out.println("Hora actualizada");
					}
					else if (opcion2==5)
					{
						System.out.println("1. Convocar");
						System.out.println("2. Desconvocar");
						int opcion3=Integer.parseInt(input("Ingrese el numero de la opcion: "));
						if(opcion3==1)
						{
							String NombreJugador=input("Ingrese el nombre del jugador: ");
							if(registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), NombreJugador)!=null)
							{
								registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).agregarConvocado(registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), NombreJugador));
								System.out.println("Jugador convocado");
							}
							else System.out.println("Jugador no encontrado");
						}
						else if(opcion3==2)
						{
							String NombreJugador=input("Ingrese el nombre del jugador: ");
							if(registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), NombreJugador)!=null)
							{
								registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).quitarConvocado(registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), NombreJugador));
								System.out.println("Jugador desconvocado");
							}
							else System.out.println("Jugador no encontrado");
						}
						else System.out.println("Opcion incorrecta");
					}
					else if(opcion2==6)
					{
						String ResultadoNuevo=input("Ingrese el nuevo resultado: ");
						registro.consultarPartido(registro.buscarEquipoPorNombre(nombreEquipo), numeroPartido).setResultado(ResultadoNuevo);
						System.out.println("resultado actualizado");
					}
					else System.out.println("Opcion incorrecta");
					
				}
			}
			if(opcion==3) 
			{
				System.out.println("--------Actualizar----------");
				String nombreJugador=input("Ingrese el nombre del jugador para actualizar sus datos: ");
				if(registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador)!=null)
				{
					System.out.println("--------Actualizar----------");
					System.out.println("1.Nombre: "+ registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).getNombre()+".");
					System.out.println("2.Posicion: "+ registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).getPosicion()+".");
					System.out.println("3.Precio: "+ registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).getPrecio()+".");
					System.out.println("4.Puntos totales: "+ registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).getPuntosTotales()+".");
					System.out.println("5.Puntos ultimo partido: "+ registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).getPuntosPartido()+".");
					System.out.println("6.Esta lesionado: "+ registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).getLesionado()+".");
					int opcion2=Integer.parseInt(input("Ingrese el numero de la opcion: "));
					if (opcion2==1)
					{
						String NombreNuevoJugador=input("Ingrese nombre nuevo del jugador: ");
						registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).setNombre(NombreNuevoJugador);
						System.out.println("Nombre actualizado");
					}
					else if (opcion2==2)
					{
						String nombrePosicion=input("Ingrese la nueva posicion del jugador: ");
						registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).setPosicion(nombrePosicion);
						System.out.println("Posicion actualizada");
					}
					else if (opcion2==3)
					{
						Double PrecioNuevo=Double.parseDouble(input("Ingrese la nueva posicion del jugador: "));
						registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).setPrecio(PrecioNuevo);
						System.out.println("Precio actualizado");
					}
					else if (opcion2==4)
					{
						int PuntosTotalesNuevos=Integer.parseInt(input("Ingrese los puntos totales del jugador: "));
						registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).setPuntosTotales(PuntosTotalesNuevos);
						System.out.println("Puntos totales actualizados");
					}
					else if (opcion2==5)
					{
						int PuntosNuevos=Integer.parseInt(input("Ingrese los puntos del jugador: "));
						registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).setPuntosTotales(PuntosNuevos);
						System.out.println("Puntos actualizados");
					}
					else if (opcion2==6)
					{
						boolean Lesion=Boolean.parseBoolean(input("Ingrese true si el jugador esta lesionado, o false si no lo esta: "));
						registro.consultarJugador(registro.buscarEquipoPorNombre(nombreEquipo), nombreJugador).setLesionado(Lesion);
						System.out.println("Lesion actualizada");
					}
					else System.out.println("Opcion incorrecta");
				}
				else System.out.println("Jugador no encontrado");
				
			}
		}
		else System.out.println("Equipo no encontrado");
		
		
	}
//	public static void main (String[] args) throws IOException
//	{
//		String nombre= input("Nombre: ");
//		String login= input("Nombre: ");
//		String password= input("Nombre: ");
//		boolean esAdmin= Boolean.parseBoolean(input("Es admin: 0=si 1=no"));
//		Persona persona = new Persona(nombre, login, password, esAdmin);
//		guardarUsuariosNuevos(persona);
//		
//		String nombre2= input("Nombre: ");
//		String login2= input("Nombre: ");
//		ingresoUsuarioRegistrado(nombre2, login2);
//	}
	
	public static void ingresoUsuarioRegistrado(String pLogin, String pPassword) throws IOException
	{
		File archivo = new File("./data/usuarios.txt");
		FileReader pArchivo = new FileReader("./data/usuarios.txt");
		BufferedReader bf = new BufferedReader(pArchivo);
        try{
            Scanner scanner = new Scanner(archivo);
            String linea = scanner.nextLine();
            int longitud=(int)bf.lines().count();
            for(int i = 0; i<=longitud; i++)
            {
            	String login = linea.split("-")[1];
            	String password = linea.split("-")[2];
            	String esAdmin = linea.split("-")[3];
            	if (login.equals(pLogin) && password.equals(pPassword)) 
            		{
            		System.out.println("Usuario encontrado");   
            		if (esAdmin.equals("0")) 
            		{
            			boolean terminar=false;
            			while(terminar==false) 
            			{
                		esAdministrador();
            			String respuesta=input("¿Desea seguir? (si/no)");
            			if (respuesta.equals("no"))
            				{
            					terminar=true;
            				}		
            			}
                	}
                	if(esAdmin.equals("1")){ 
                		
                		boolean terminar=false;
                		while(terminar==false) 
            			{
                		esUsuario();
            			String respuesta=input("¿Desea seguir? (si/no)");
            			if (respuesta.equals("no"))
            				{
            					terminar=true;
            				}		
            			}
                	}
            		}
            	if(i<longitud) linea=scanner.nextLine();
            }
        }catch(FileNotFoundException e){
        }
        bf.close();
	}
	
	public static void guardarUsuariosNuevos(Persona persona) throws FileNotFoundException
    {
		FileReader pArchivo = new FileReader("./data/usuarios.txt");
		BufferedReader bf = new BufferedReader(pArchivo);
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("./data/usuarios.txt", true);
            pw = new PrintWriter(fichero);
            for(int i = 0; i<=bf.lines().count(); i++)
            {
            	
            }
            
            pw.println('\n'+persona.getNombre()+"-"+persona.getLogin()+"-"+persona.getContraseña()+"-"+persona.getEsAdmin());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + " ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	public static EquipoFantasia buscarEquipoPorNombre(String pNombre)
	{
		EquipoFantasia equipo=null;
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
	public static boolean equipoExiste(String pNombre)
	{
		boolean existe=false;
		for(int i=0; i<=equipos.size()-1;i++)
		{
			if (equipos.get(i).getNombre().equals(pNombre)) existe=true;
		}
		return existe;
	}
}
