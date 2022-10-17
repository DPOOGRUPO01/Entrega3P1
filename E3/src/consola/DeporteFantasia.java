package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.Ingreso;
import controller.Registro;

public class DeporteFantasia 
{
	private static Ingreso ingreso= new Ingreso();
	private static Registro registro= new Registro();
	
	public static void main(String [] args) throws IOException
	{
		System.out.println("---BINEVENIDO A DEPORTE FANTASIA---");
		String respuesta=input("¿Ya esta registrado?: (si/no)");
		if(respuesta.equals("si"))
		{
			String login=input("Login:");
			String password=input("Password:");
			ingreso.ingresoUsuarioRegistrado(login,password);
		}
		else if (respuesta.equals("no"))
		{
			System.out.println("INGRESE LOS SIGUIENTES DATOS PARA CREAR UN USUARIO");
			String nombre=input("Nombre:");
			String login=input("Login:");
			String password=input("Password:");
			boolean esAdmin=Boolean.parseBoolean(input("Es usuario tipo administrador (si=0/no=1):"));
			
			ingreso.guardarUsuariosNuevos(ingreso.personaNueva(nombre, login, password, esAdmin));
			System.out.println("Usuario Creado - vuelva a lanzar la aplicacion para ingresar");
		}
		
	}

	public static void cargarEquipos(String pNombreArchivo)
	{
		registro.cargarInformacionEquipos(pNombreArchivo);
	}
	
	public static void cargarCalendario(String pNombreArchivo)
	{
		registro.cargarInformacionCalendario(pNombreArchivo);
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
}
