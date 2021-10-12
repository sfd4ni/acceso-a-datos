/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.entities;


import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dama
 */

@XmlRootElement(name="almacen")
public class Almacen implements Serializable {
    
    @XmlElement(name="moneda")
    public ArrayList <Moneda> monedas = new ArrayList<>();
    
    public Almacen() {
        
    }
    
    public ArrayList <Moneda> getMonedas() {
        return monedas;
    }
}
