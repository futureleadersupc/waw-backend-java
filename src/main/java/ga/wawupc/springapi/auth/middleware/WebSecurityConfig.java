package ga.wawupc.springapi.auth.middleware;

import ga.wawupc.springapi.auth.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserService userService;

  JwtAuthenticationEntryPoint unauthorizedHandler;

  public WebSecurityConfig(UserService userService) {
    this.userService = userService;
  }

  @Bean
  public JwtAuthorizationFilter authorizationFilter() {
    return new JwtAuthorizationFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  protected void configure(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(userService);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
      .exceptionHandling()
      .authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeHttpRequests().antMatchers(
        "/api/v1/companies/*",
        "/api/v1/offers/*"
      ).authenticated()
      .anyRequest().permitAll();
    http.addFilterBefore(authorizationFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
