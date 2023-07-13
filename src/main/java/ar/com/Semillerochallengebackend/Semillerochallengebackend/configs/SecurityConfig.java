package ar.com.Semillerochallengebackend.Semillerochallengebackend.configs;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.UserService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    // INSTANCES
    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).
                passwordEncoder(new BCryptPasswordEncoder());
    }

    // METHODS
    @Override
    protected void configure(HttpSecurity httpSec) throws Exception {

        //httpSec.httpBasic().disable();
        //COMANDO CREADO PARA DESHABILITAR MENSAJE DE LOGIN AUTOMATICO DE SPRING AL TIPEAR LA URL EN EL EXPLORADOR
        //mas info >>> https://stackoverflow.com/questions/23636368/how-to-disable-spring-security-login-screen        
        
        httpSec.headers().frameOptions().sameOrigin()
                .and().authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/img/**")
                .permitAll()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=email_o_password_no_validos")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll()
                .and().csrf().disable();
    }
    
    /*
    // PARA ANULAR SEGURIDAD, AGREGAR ESTE CÓDIGO Y COMENTAR TODO EXCEPTO LA ANOTACIÓN "@Configuration"
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
    */
}