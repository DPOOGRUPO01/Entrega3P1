package controller;

import java.util.ArrayList;

import logic.Calculadora;
import logic.Equipo;
import logic.Jugador;

public class EquipoFantasia 
{
	private static Registro registro;
	private static String nombre;
	private int puntosPartido;
	private static ArrayList<Jugador> jugadores;
	private static double presupuesto;
	private int puntosTotales;
	private static Calculadora calculadora=new Calculadora();
	
	public EquipoFantasia(String pNombre, double pPresupuesto, int pPuntosTotales)
	{
		nombre=pNombre;
		puntosPartido=0;
		jugadores=new ArrayList<Jugador>(15);
		presupuesto=pPresupuesto;
		puntosTotales=pPuntosTotales;
	}

	
	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntosPartido() {
		return puntosPartido;
	}

	public void setPuntosPartido(int puntosPartido) {
		this.puntosPartido = puntosPartido;
	}
	public static double getPresupuesto() {
		return presupuesto;
	}

	public static void setPresupuesto(double presupuesto) {
		EquipoFantasia.presupuesto = presupuesto;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public static String agregarJugador(String nombreEquipo,String nombreJugador, Registro registro)
	{
		
		String respuesta=" ";
		for(int i = 0; i<registro.buscarEquipoPorNombre(nombreEquipo).getJugadores().size();i++)
		{
			if(registro.buscarEquipoPorNombre(nombreEquipo).getJugadores().get(i).getNombre().equals(nombreJugador) && registro.buscarEquipoPorNombre(nombreEquipo).getJugadores().get(i).getPrecio()<= presupuesto)
			{
				presupuesto=presupuesto-registro.buscarEquipoPorNombre(nombreEquipo).getJugadores().get(i).getPrecio();
				jugadores.add(registro.buscarEquipoPorNombre(nombreEquipo).getJugadores().get(i));
				respuesta="Jugador agregado";
				System.out.println(respuesta);
			}
		}
		return respuesta;
	}
	
	public static void cambiarJugador(String nombreJugador1,String equipoJugador2, String nombreJugador2, Registro registro)
	{
		for(int i=0; i<= jugadores.size()-1; i++)
		{
			if(jugadores.get(i).getNombre().equals(nombreJugador1))
			{
				double precioRembolso= calculadora.calcularPresupuesto(jugadores.get(i).getPrecio());
				presupuesto=presupuesto+precioRembolso;
				agregarJugador(equipoJugador2,nombreJugador2,registro);
				jugadores.remove(i);
				System.out.println("Jugador cambiado");
			}
			else System.out.println("Jugador no encontrado");
		}
	}
	
	public int getPuntosTotales()
	{
		return puntosTotales;
	}
	
	public void setPuntosTotales(int pPuntos)
	{
		puntosTotales=pPuntos;
	}
	
}
