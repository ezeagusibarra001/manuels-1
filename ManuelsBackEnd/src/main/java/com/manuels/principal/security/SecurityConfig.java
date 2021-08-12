package com.manuels.principal.security;

import com.manuels.principal.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/* LIMITAR INICIOS DE SESION!!! !!!  ! !*/
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/private/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/api/lessons").permitAll()
                .antMatchers(HttpMethod.POST, "/api/lessons").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/lessons").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/lessons").hasRole("ADMIN")
                
                .antMatchers(HttpMethod.GET, "/api/dates").permitAll()
                .antMatchers(HttpMethod.POST, "/api/dates").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/dates").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/dates").hasRole("ADMIN")
      
                .antMatchers(HttpMethod.GET, "/api/images").permitAll()
                .antMatchers(HttpMethod.GET, "/api/images/{names}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/images").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/images").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/images").hasRole("ADMIN")
                
                .antMatchers(HttpMethod.GET, "/api/publications/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/publications/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/publications/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/publications/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/publications/").hasRole("ADMIN")
                
                .antMatchers(HttpMethod.GET, "/api/reviews").permitAll()
                .antMatchers(HttpMethod.POST, "/api/reviews").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/reviews").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/reviews").hasRole("ADMIN")
                
                .antMatchers(HttpMethod.GET, "/api/payments").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/payments").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/payments").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/payments").hasRole("ADMIN")
                
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
		.allowedMethods("HEAD", "GET", "PUT", "POST",
		"DELETE", "PATCH").allowedHeaders("*");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}