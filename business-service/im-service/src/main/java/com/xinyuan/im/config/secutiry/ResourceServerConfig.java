//package com.xinyuan.im.config.secutiry;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.controller.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.controller.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.controller.configuration.ResourceServerConfigurerAdapter;
//
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.cors();
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/websocket-rabbitmq/**").permitAll()
//                .anyRequest().authenticated();
//    }
//
//}
