package org.eduardomaravill.customers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomAccessFilter customAccessFilter;

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        /*httpSecurity.authorizeHttpRequests(r -> {
            r.requestMatchers("/api/register","/api/login").permitAll();
            r.requestMatchers("/api/**").authenticated();
        });*/

        httpSecurity.addFilterBefore(customAccessFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
