package com.novant.mynovant.configuration

import com.novant.mynovant.CustomLogoutSuccessHandler
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.HttpStatusEntryPoint

@EnableWebSecurity
open class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {


//    @Autowired
//    private val uaaLogoutSuccessHandler: CustomLogoutSuccessHandler? = null
    private val logger = KotlinLogging.logger {}

//    var stringArrayList: ArrayList<String> = arrayListOf<String>("Ajay","Vijay","Prakash")
    val foo: ArrayList<String> = arrayListOf<String> (
        "/",
        "/login",
        "/error"
    )

    override fun configure(http: HttpSecurity) {

        logger.info {"Configuring WebSecurity to permit everything for: $foo"}

        http
            .csrf().disable()
            .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
              .exceptionHandling()
              .authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            .and()
              .authorizeRequests()
//                .antMatchers(foo.toArrayOf(String)).permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/error").permitAll()
//                .antMatchers("/logout").permitAll()
                .anyRequest().authenticated()
//            .and()
                // got redirected to uaa/ping logon, but does not invalidate token...?
                // or maybe we aren't checking back with uaa?
//              .logout().logoutSuccessHandler(uaaLogoutSuccessHandler)
            .and()
              .oauth2ResourceServer()
              .jwt()
    }
}