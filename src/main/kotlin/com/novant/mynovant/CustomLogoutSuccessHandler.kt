package com.novant.mynovant

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.jwt.Jwt

@Component
class CustomLogoutSuccessHandler(private var objectMapper: ObjectMapper) : LogoutSuccessHandler {

    @Value("\${ssoServiceUrl}")
    var ssoServiceUrl: String? = null

    @Value("\${spring.security.oauth2.client.registration.sso.client-id}")
    var clientId: String? = null

    @Throws(IOException::class)
    override fun onLogoutSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val jwt = authentication.principal as Jwt;
        var jti = parseToken(jwt.tokenValue)["jti"]
        val redirectUrl = UriComponentsBuilder.fromHttpUrl(ssoServiceUrl!!)
                .path("/oauth/token/revoke/$jti")
                .build()
        response.sendRedirect(redirectUrl.toString())
    }

    @Throws(IOException::class)
    private fun parseToken(base64Token: String): Map<String, *> {
        val token = base64Token.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        return objectMapper.readValue(Base64.decodeBase64(token), object : TypeReference<Map<String, *>>() {
        })
    }

}