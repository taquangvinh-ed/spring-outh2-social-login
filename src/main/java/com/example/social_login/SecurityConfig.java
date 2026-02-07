package com.example.social_login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private String githubClientId;
    private String githubClientSecret;


    public SecurityConfig(@Value("${github.client.id}") String githubClientId,
                          @Value("${github.client.secret}")String githubClientSecret) {
        this.githubClientId = githubClientId;
        this.githubClientSecret = githubClientSecret;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity https) throws Exception{
        https.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/securedPage").authenticated()
                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());

        return https.build();
    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        return new InMemoryClientRegistrationRepository(regisWithGithub());
    }


    private ClientRegistration regisWithGithub(){
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId(githubClientId)
                .clientSecret(githubClientSecret)
                .build();
    }

}
