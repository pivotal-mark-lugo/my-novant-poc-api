package com.novant.mynovant

import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.savedrequest.RequestCache
import kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext.DefaultImpls.getParameter
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.util.StringUtils


@EnableWebSecurity
open class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(
                        HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
    }

    // was helpful somehow for getting api flow working (rather than form login + redirect etc)
    private inner class MySavedRequestAwareAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

        private var requestCache: RequestCache = HttpSessionRequestCache()

        @Throws(ServletException::class, IOException::class)
        override fun onAuthenticationSuccess(
                request: HttpServletRequest,
                response: HttpServletResponse,
                authentication: Authentication) {

            val savedRequest = requestCache.getRequest(request, response)

            if (savedRequest == null) {
                clearAuthenticationAttributes(request)
                return
            }
            val targetUrlParam = targetUrlParameter
            if (isAlwaysUseDefaultTargetUrl || targetUrlParam != null &&
                    StringUtils.hasText(request.getParameter(targetUrlParam))) {
                requestCache.removeRequest(request, response)
                clearAuthenticationAttributes(request)
                return
            }

            clearAuthenticationAttributes(request)
        }

        fun setRequestCache(requestCache: RequestCache) {
            this.requestCache = requestCache
        }
    }
}