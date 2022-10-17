package logic;

import java.util.ArrayList;
import java.util.Date;

public class Partido 
{
	private String fecha;
	private String hora;
	private ArrayList<Jugador> jugadores;
	private Equipo Equipo1;
	private Equipo Equipo2;
	private String resultado;
	private String localVisitante;
	
	public Partido (Equipo pEquipo1,String pLocalVisitante , String pFecha, String pHora, ArrayList<Jugador> pJugadores,String pResultado)
	{
		Equipo1=pEquipo1;
		fecha=pFecha;
		hora=pHora;
		resultado=pResultado;
		localVisitante=pLocalVisitante;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Equipo getEquipo1() {
		return Equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		Equipo1 = equipo1;
	}
	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String pResultado) {
		this.resultado=pResultado;
	}

	public String getLocalVisitante() {
		return localVisitante;
	}

	public void setLocalVisitante(String plocalVisitante) {
		this.localVisitante=plocalVisitante;
	}
	public void agregarConvocado(Jugador pJugador)
	{
		jugadores=new ArrayList<Jugador>(18);
		jugadores.add(pJugador);
	}
	public void quitarConvocado(Jugador pJugador)
	{
		jugadores.remove(pJugador);
	}

}
