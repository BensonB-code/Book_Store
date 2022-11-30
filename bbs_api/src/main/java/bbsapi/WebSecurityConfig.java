package bbsapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.GenericFilterBean;

import bbsapi.domain.repository.user.UserRepository;
import bbsapi.domain.service.user.LoginUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	LoginUserDetailsService userDetailsService;
	@Autowired
	UserRepository userRepository;
	
	@Value("${security.secret-key}")
    private String secretKey;

  
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		// AUTHORIZE
		.authorizeRequests()
		    .antMatchers("/api/user/auth", "/api/logout", "/api/payment/charge"
		    		, "api/reviews", "api/reviews*").authenticated()
		    .anyRequest().permitAll()
		  
		.and()
		// EXCEPTION
        .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler())

        .and()
         // LOGIN
		.formLogin()
		    .loginProcessingUrl("/api/login").permitAll()
		    .usernameParameter("username")
		    .passwordParameter("password")
		    .successHandler(authenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler())
		
		.and()
		// LOGOUT
		.logout()
		    .logoutUrl("/api/logout")
			//.invalidateHttpSession(true)
			//.deleteCookies("JSESSIONID")
			.logoutSuccessHandler(logoutSuccessHandler())
			
		.and()
		// CSRF
		.csrf()
		    .disable()
		    //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		       
		// AUTHORIZE
        .addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class)
        
        // SESSION
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()    
		// CORS
		.cors();
	
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth,
	    @Qualifier("borrowingUserDetailsService") UserDetailsService userDetailsService,
	    PasswordEncoder passwordEncoder) throws Exception {

	    auth.eraseCredentials(true)
	        .userDetailsService(userDetailsService)
	        .passwordEncoder(passwordEncoder);

	}
	
	 @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 GenericFilterBean tokenFilter() {
	        return new SimpleTokenFilter(userRepository, secretKey);
	    }

	 
	 AuthenticationEntryPoint authenticationEntryPoint() {
		    return new SimpleAuthenticationEntryPoint();
		} 
	 
	 AccessDeniedHandler accessDeniedHandler() {
		    return new SimpleAccessDeniedHandler();
		}
	 
	 AuthenticationSuccessHandler authenticationSuccessHandler() {
		    return new SimpleAuthenticationSuccessHandler(secretKey);
		}
	 
	 AuthenticationFailureHandler authenticationFailureHandler() {
		    return new SimpleAuthenticationFailureHandler();
		}
	 
	 LogoutSuccessHandler logoutSuccessHandler() {
		  return new HttpStatusReturningLogoutSuccessHandler();
		}
	   
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.addExposedHeader("Authorization"); //To get JWT Token
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	
}
