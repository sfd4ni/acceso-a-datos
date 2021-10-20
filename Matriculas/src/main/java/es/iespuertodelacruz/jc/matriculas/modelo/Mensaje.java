package es.iespuertodelacruz.jc.matriculas.modelo;

public class Mensaje {
	private String nombreUsuario;
	private String texto;
	
	public Mensaje(String nombreUsuarioInput, String textoInput) {
		this.nombreUsuario = nombreUsuarioInput;
		this.texto = textoInput;
	}
	
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}
	
	public String getTexto() {
		return this.texto;
	}
}
