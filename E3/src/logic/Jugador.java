package logic;

public class Jugador 
{
	private String nombre;
	private String posicion;
	private double precio;
	private int puntosTotales;
	private int puntosPartido;
	private boolean lesionado;
	
	public Jugador (String pNombre, String pPosicion, double pPrecio, int pPuntosTotales,int pPuntosPartido,  boolean pLesionado)
	{
		nombre=pNombre;
		posicion=pPosicion;
		precio=pPrecio;
		puntosTotales=pPuntosTotales;
		puntosPartido=pPuntosPartido;
		lesionado=pLesionado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getPuntosTotales() {
		return puntosTotales;
	}
	public void setPuntosTotales(int puntos) {
		this.puntosTotales = puntos;
	}
	public int getPuntosPartido() {
		return puntosPartido;
	}
	public void setPuntosPartido(int puntos) {
		this.puntosPartido = puntos;
	}
	public boolean getLesionado() {
		return lesionado;
	}
	public void setLesionado(boolean lesionado) {
		this.lesionado = lesionado;
	}
}
