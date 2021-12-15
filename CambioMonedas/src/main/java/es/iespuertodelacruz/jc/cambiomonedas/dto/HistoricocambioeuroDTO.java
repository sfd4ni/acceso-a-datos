package es.iespuertodelacruz.jc.cambiomonedas.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Historicocambioeuro;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;

@Component
public class HistoricocambioeuroDTO  {


    private static final long serialVersionUID = 1L;

    private Integer idhistoricocambioeuro;

    private Date fecha;

    private BigDecimal equivalenteeuro;
    
    

	private Integer idmoneda;

    public HistoricocambioeuroDTO() {
    }    
    
    

    public HistoricocambioeuroDTO(Historicocambioeuro hce) {
		super();
		this.idhistoricocambioeuro = hce.getIdhistoricocambioeuro();
		this.fecha = hce.getFecha();
		this.equivalenteeuro = hce.getEquivalenteeuro();
		this.idmoneda = hce.getFkidmoneda().getIdmoneda();
	}



	public Integer getIdhistoricocambioeuro() {
		return idhistoricocambioeuro;
	}

	public void setIdhistoricocambioeuro(Integer idhistoricocambioeuro) {
		this.idhistoricocambioeuro = idhistoricocambioeuro;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getEquivalenteeuro() {
		return equivalenteeuro;
	}

	public void setEquivalenteeuro(BigDecimal equivalenteeuro) {
		this.equivalenteeuro = equivalenteeuro;
	}

	public Integer getFkidmoneda() {
		return idmoneda;
	}

	public void setFkidmoneda(Integer fkidmoneda) {
		this.idmoneda = fkidmoneda;
	}




  

	
	
}
