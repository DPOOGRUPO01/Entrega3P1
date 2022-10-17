package logic;

public class Persona {
	
	private String nombre;
	private String login;
	private String contrase�a;
	private boolean esAdmin;
	public Persona(String nombre, String login, String contrase�a, boolean esAdmin) {
		super();
		this.nombre = nombre;
		this.login = login;
		this.contrase�a = contrase�a;
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
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public boolean getEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	

}
