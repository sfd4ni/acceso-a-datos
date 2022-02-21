package es.iespuertodelacruz.daniel.bibliotecarest.dto;



import es.iespuertodelacruz.daniel.bibliotecarest.entity.Ejemplare;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Libro;

public class EjemplarDTO {
	private String localizacion;

	//private Libro libro;
	
	public EjemplarDTO() {}
	
	public EjemplarDTO(Ejemplare ejemplar) {
		this.localizacion = ejemplar.getLocalizacion();
		//this.libro = ejemplar.getLibro();
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	/*public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}*/
	
}
