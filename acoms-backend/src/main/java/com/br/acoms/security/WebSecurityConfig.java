package com.br.acoms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.br.acoms.models.Roles;
import com.br.acoms.security.jwt.AuthEntryPointJwt;
import com.br.acoms.security.jwt.JwtFilter;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
    }

//     @Override
//     protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//         super.configureMessageConverters(converters);
//         converters.add(mappingJackson2HttpMessageConverter());
//     }

//     //null object to solve the problem of sequence 
//     private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//         ObjectMapper mapper = new ObjectMapper();
//         //critical code
//         mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//         MappingJackson2HttpMessageConverter converter =
//                 new MappingJackson2HttpMessageConverter(mapper);
//         return converter;
//     }

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    MultipleSecurityController multipleSecurityController;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole(Roles.ADMIN.toString());

        http.authorizeRequests()
                .antMatchers("/school/**")
                .hasRole(Roles.MANAGEMENT.toString());

        http.authorizeRequests()
                .antMatchers("/coordinator/**")
                .hasRole(Roles.COORDINATOR.toString());

        http.authorizeRequests()
                .antMatchers("/guardian/**")
                .hasRole(Roles.GUARDIAN.toString());

        http.authorizeRequests()
                .antMatchers("/teacher/**")
                .hasRole(Roles.TEACHER.toString());

        http.authorizeRequests()
                .antMatchers("/student/**")
                .hasRole(Roles.STUDENT.toString());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}