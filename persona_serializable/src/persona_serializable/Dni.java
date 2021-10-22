/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona_serializable;

/**
 *
 * @author dama
 */
public class Dni {
    
    String dni;
    public Dni(String dniInput) {
        dni = comprobarDni(dniInput);
    }
    
    private String comprobarDni(String dniInput) {
        String llego = Integer.toString(dniInput.length());
        if (dniInput.length() == 8) {
            int dniNum = Integer.parseInt(dniInput.substring(0, 7));
            String dniLetra = dniInput.substring(8, 8);
            llego += dniNum + dniLetra;
            if (dniLetra.matches("[a-zA-Z]+")) {
                llego += "letra";
               if (comprobarLetra(dniNum) == dniLetra) {
                   llego += dniLetra;
                   return dniInput;
               }
            }
        }
        return "Fallo";
    }
    
    private String comprobarLetra(int dni) {
        String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letrasDni.substring(dni%23, dni%23);
    }
}
