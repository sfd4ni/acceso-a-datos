package es.iespuertodelacruz.daniel.bibliotecarest.dto;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Ejemplare;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Libro;

public class EjemplarDTO {
	private String localizacion;

	public EjemplarDTO() {
	}

	public EjemplarDTO(Ejemplare ejemplar) {
		this.localizacion = ejemplar.getLocalizacion();
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

}
