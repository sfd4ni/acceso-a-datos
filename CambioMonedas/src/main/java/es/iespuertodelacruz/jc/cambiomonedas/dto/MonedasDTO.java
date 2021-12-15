package es.iespuertodelacruz.jc.cambiomonedas.dto;

import org.springframework.stereotype.Component;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;




@Component
public class MonedasDTO {

    private static final long serialVersionUID = 1L;

    private Integer idmoneda;

    private String nombre;
    
    private String pais;


	//private Collection<HistoricocambioeuroDTO> historicocambioeuroCollection;

    public MonedasDTO() {
    }	
    
    public MonedasDTO(Monedas moneda) {
    	idmoneda = moneda.getIdmoneda();
    	nombre = moneda.getNombre();
    	pais = moneda.getPais();
 
    	 
    }
    
    
    
    public Integer getIdmoneda() {
		return idmoneda;
	}

	public void setIdmoneda(Integer idmoneda) {
		this.idmoneda = idmoneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Monedas getMoneda() {
		Monedas moneda = new Monedas(this.idmoneda, this.nombre);
		moneda.setPais(pais);
		return moneda;
	}
	
}
