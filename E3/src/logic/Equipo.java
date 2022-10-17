package logic;

import java.util.ArrayList;

public class Equipo 
{
	private String nombre;
	private String ciudad;
	private String entrenador;
	private ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
	private Calendario calendario;
	private Partido partidoActual;
	
	public Equipo (String pNombre, String pCiudad, String pEntrenador)
	{
		nombre=pNombre;
		ciudad=pCiudad;
		entrenador=pEntrenador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}


	public Calendario getCalendario() {
		return calendario;
	}
	
	public void setCalendario(Calendario calendario)
	{
		this.calendario=calendario;
	}

	public Partido getPartidoActual() {
		return partidoActual;
	}

	public void setPartidoActual(Partido partidoActual) {
		this.partidoActual = partidoActual;
	}
	
	public void agregarJugador(String pNombre, String pPosicion, double pPrecio, int pPuntosTotales, int pPuntosPartido, boolean pLesionado)
	{
		Jugador jugador=new Jugador(pNombre,pPosicion,pPrecio,pPuntosTotales, pPuntosPartido,pLesionado);
		jugadores.add(jugador);
	}
	
	public Jugador buscarJugadorPorNombre(String pNombre)
	{
		Jugador jugador=null;
		for(int i=0; i<=jugadores.size()-1;i++)
		{
			if(jugadores.get(i).getNombre().equals(pNombre))
			{
				jugador=jugadores.get(i);
			}
		}
		return jugador;
	}

}
