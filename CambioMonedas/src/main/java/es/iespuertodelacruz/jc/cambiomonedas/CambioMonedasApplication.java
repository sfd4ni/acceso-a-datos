package es.iespuertodelacruz.jc.cambiomonedas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class CambioMonedasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambioMonedasApplication.class, args);
	}
	
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	    @Override
	    public void configure(WebSecurity webSecurity) throws Exception
	    {
	        webSecurity
	            .ignoring()
	            //.antMatchers(HttpMethod.POST, "/api/login")
	            .antMatchers("/api/**")
	            ;
	    }		
		
		/*
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterBefore(new FiltroJWT(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				//.antMatchers("/api/products").hasRole("ADMIN")
				.anyRequest().authenticated();
		
			
		}
		*/
		
		
	}		
	

}
