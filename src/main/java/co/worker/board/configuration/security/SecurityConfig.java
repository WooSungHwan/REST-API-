package co.worker.board.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity //@AuthenticationPrincipal  사용가능
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        System.out.println("----------------security config-----------------------");
        http
                .authorizeRequests().antMatchers("/api/**").authenticated()
                .antMatchers("/upload/**").authenticated()
                .antMatchers("/todo").permitAll();

        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/", "/hello").permitAll().antMatchers("/my").authenticated();

        http.formLogin().successHandler(signInSuccessHandler());

        http.logout()
                .logoutUrl("/logout")
                .addLogoutHandler(logOutHandler())
                .logoutSuccessUrl("/hello")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "auth_key")
                .permitAll();

        System.out.println("----------------security config end -----------------------");
    }

    @Bean
    public LogoutHandler logOutHandler(){
        return new MyLogoutHandler();
    }

    @Bean
    public AuthenticationSuccessHandler signInSuccessHandler() {
        return new SignInSuccessHandler();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        System.out.println("----------------cors config-----------------------");

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        System.out.println("----------------cors config end-----------------------");
        return source;
    }
}
