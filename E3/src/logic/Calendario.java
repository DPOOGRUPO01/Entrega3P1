package logic;

import java.util.ArrayList;

public class Calendario 
{
	ArrayList<Partido> partidos;
	
	public Calendario()
	{
		partidos= new ArrayList<>();
	}
	
	public void agregarPartido(Partido pPartido)
	{
		partidos.add(pPartido);
	}
	public ArrayList<Partido> getpartidos()
	{
		return partidos;
	}
	public Partido consultarPartido(int i)
	{
		return partidos.get(i);
	}

}
