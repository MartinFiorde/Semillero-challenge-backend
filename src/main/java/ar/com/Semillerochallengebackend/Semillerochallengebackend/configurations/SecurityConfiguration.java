package ar.com.Semillerochallengebackend.Semillerochallengebackend.configurations;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll(); // ESTE METODO PERMITE USAR TODA LA APLICACIÓN SIN CONTROLES DE SEGURIDAD - REF: https://stackoverflow.com/questions/36280181/disabling-spring-security-in-spring-boot-app
        httpSecurity.cors().and().csrf().disable(); // ESTE METODO Y EL BEAN DEBAJO PERMITEN USAR METODOS POST DESDE POSTMAN https://stackoverflow.com/questions/50486314/how-to-solve-403-error-in-spring-boot-post-request
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}