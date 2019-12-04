package com.cognizant.smartShopservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cognizant.smartShopservices.security.AppUserDetailsService;
import com.cognizant.smartShopservices.security.JwtAuthorizationFilter;





@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Autowired
       AppUserDetailsService appUserDetailsService;

       @Override
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
              auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
       }

       @Bean
       public PasswordEncoder passwordEncoder() {
              return new BCryptPasswordEncoder();
       }

       @Override
       protected void configure(HttpSecurity httpSecurity) throws Exception {
              httpSecurity.cors();
//              httpSecurity.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/users")
//              .permitAll();
//             
//              httpSecurity.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/authenticate")
//                           .hasAnyAuthority("S","U","A")
//                           .antMatchers("/pendings")
//                          .hasAnyRole("S").antMatchers("/purchase").permitAll().anyRequest().authenticated().and()
//                           .addFilter(new JwtAuthorizationFilter(authenticationManager()));
          	httpSecurity.csrf().disable().httpBasic().and().authorizeRequests()
            .antMatchers("/pendings","/pendings","/purchase","/product","/users").permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
           .anyRequest().authenticated().and().addFilter(new JwtAuthorizationFilter(authenticationManager()));
       }

}
