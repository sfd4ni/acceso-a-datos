/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.xml;


import es.iespuertodelacruz.jc.monedasxml.entities.Almacen;
import es.iespuertodelacruz.jc.monedasxml.xml.JavaToXMLString;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author dama
 */
public class AlmacenXML implements JavaToXMLString<Almacen> {

    @Override
    public String objToStringXML(Almacen m) {
        JAXBContext contexto;
        Marshaller marshaller;
        OutputStream os=null;
        StringWriter sw = new StringWriter();
        try {
            contexto =  JAXBContext.newInstance(m.getClass());
            marshaller  = contexto.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(m, sw);
        } catch (JAXBException ex) {
            System.out.println(ex);
        }finally{
            return sw.toString();
        }
    }

    @Override
    public Almacen stringXMLToObj(String texto) {
        JAXBContext contexto;
        Marshaller marshaller;
        StringReader sr = new StringReader(texto);
        Almacen almacen = null;
        try {
            contexto =  JAXBContext.newInstance(Almacen.class);
            marshaller  = contexto.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
Boolean.TRUE);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();
            almacen = (Almacen) unmarshaller.unmarshal(sr);
        } catch (JAXBException ex) {
            System.out.println(ex);
        }finally{
            return almacen;
        }
    }
    
}
