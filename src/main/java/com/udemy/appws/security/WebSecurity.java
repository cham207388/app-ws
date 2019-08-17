package com.udemy.appws.security;

import com.udemy.appws.service.UserService;
import com.udemy.appws.util.URL;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public WebSecurity(boolean disableDefaults, UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(disableDefaults);
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private UserService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, URL.POST_URL).permitAll()
                .antMatchers(HttpMethod.POST, URL.LOGIN).permitAll()
                .antMatchers(HttpMethod.GET, URL.USERS).permitAll()
                .antMatchers(HttpMethod.GET,   URL.ID_URL).permitAll()
                .antMatchers(HttpMethod.GET, URL.H2_CONSOLE).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").hasRole("ADMIN")//allow h2 console access to admins only
                .anyRequest().authenticated()//all other urls can be access by any authenticated role
                .and().formLogin()//enable form login instead of basic login
                .and().csrf().ignoringAntMatchers("/h2-console/**")//don't apply CSRF protection to /h2-console
                .and().headers().frameOptions().sameOrigin();//allow use of frame to same origin urls
    }*/

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * sets the login endpoint to /user/login
     * @return
     * @throws Exception
     */
    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/user/login");
        return filter;
    }
}