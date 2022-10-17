package logic;

public class Persona {
	
	private String nombre;
	private String login;
	private String contraseña;
	private boolean esAdmin;
	public Persona(String nombre, String login, String contraseña, boolean esAdmin) {
		super();
		this.nombre = nombre;
		this.login = login;
		this.contraseña = contraseña;
		this.esAdmin = esAdmin;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public boolean getEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	

}
