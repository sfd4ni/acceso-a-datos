package es.iespuertodelacruz.daniel.bibliotecarest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Operadore;
import es.iespuertodelacruz.daniel.bibliotecarest.security.GestorDeJWT;
import es.iespuertodelacruz.daniel.bibliotecarest.service.OperadorService;



@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/*  funciona el form urlencode */
	@PostMapping(path = "/api/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<?> login(@RequestParam("name") String username, @RequestParam("password") String pwd) {
		
		
		String token = getJWTToken(username,pwd);
		
		//token nulo si operador/pass no es válido
		if( token != null) {
			return ResponseEntity.ok(token);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("operador/pass erróneos");
		
	}
	
	
	static class OperadorJsonLogin{
		String nick;
		String password;
		public String getNick() { return nick;};
		public String getPassword() {return password;};
		public void setNick(String nick ) {this.nick = nick;};
		public void setPassword(String password ) {this.password = password;};
			
	}
	
	/* json post */
	@PostMapping(path = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody OperadorJsonLogin operadorJson) {
		
		
		String token = getJWTToken(operadorJson.nick, operadorJson.password);
		
		//token nulo si operador/pass no es válido
		if( token != null) {
			return ResponseEntity.ok(token);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("operador/pass erróneos");
		
	}	

	@Autowired
	OperadorService operadorService;
	
	
	private String getJWTToken(String username, String passTextoPlanoRecibida) {
		
		String respuesta = null;
		
		GestorDeJWT gestorDeJWT = GestorDeJWT.getInstance();
		
		Operadore operador = operadorService.findByNick(username);
		logger.info("Password operador: " + operador.getPassword());
		logger.info("Password recibida: " + passTextoPlanoRecibida);
        
        String passwordOperadorEnHash = "";
        boolean autenticado = false;
        
        if(operador != null) { 
        	passwordOperadorEnHash = operador.getPassword();
        	
        	//autenticado = BCrypt.checkpw(passTextoPlanoRecibida, passwordOperadorEnHash);
        	if (passTextoPlanoRecibida.equals(passwordOperadorEnHash)) {
        		autenticado = true;
        	}
        	
        }
        
   
      
		if(autenticado) {

				
			String rol = "";
			List<String> roles = new ArrayList<String>(); 
			roles.add(rol);
			logger.info("los roles obtenidos: " + roles);
			

			int duracionMinutos = 600;
			
			String token = gestorDeJWT.generarToken(username, roles, duracionMinutos);
			
			respuesta = gestorDeJWT.BEARERPREFIX + token;			
		}
		
		return respuesta;

	}	

}
