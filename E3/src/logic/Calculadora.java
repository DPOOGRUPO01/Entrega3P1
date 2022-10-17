package logic;

public class Calculadora 
{
	private static final double TAX=0.03;
	
	public double calcularPresupuesto(double precioJugador)
	{
		double preciofinal=precioJugador-(precioJugador*TAX);
		return preciofinal;
	}

}
