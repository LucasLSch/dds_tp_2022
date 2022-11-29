package ddsutn.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

  @Bean
  public AuthenticationManager authManager(HttpSecurity http,
                                           BCryptPasswordEncoder bCryptPasswordEncoder,
                                           UserDetailsService userDetailsService) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder)
            .and()
            .build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/calculadoraHC")
            .hasAnyAuthority("ADMINISTRATOR_USER", "STANDARD_USER")
            .antMatchers("/iniciarSesion", "/registrarse", "/registrarseMiembro")
            .anonymous()
            .antMatchers("/iniciarSesion", "/utils/**", "/footer/**", "/registrarse/**", "/menu/**", "/iniciarSesion.css", "/")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/iniciarSesion")
            .loginProcessingUrl("/iniciarSesion")
            .failureUrl("/iniciarSesion?authError=true");

    return http.build();
  }

}
