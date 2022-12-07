// package com.br.acoms.security;

// import java.util.List;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.converter.HttpMessageConverter;
// import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.SerializationFeature;

// @Configuration
// public class GlobalConfig extends WebMvcConfigurationSupport {
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
// }