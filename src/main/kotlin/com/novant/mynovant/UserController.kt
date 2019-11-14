package com.novant.mynovant

import com.fasterxml.jackson.core.type.TypeReference
import org.springframework.web.bind.annotation.RestController
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import java.io.IOException
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.jwt.Jwt

@RestController
class UserController(private var objectMapper: ObjectMapper) {
    @GetMapping("/me")
    fun userInfo(authentication: Authentication): Map<String, *> {
        val jwt = authentication.principal as Jwt
        return parseToken(jwt.tokenValue)
    }

    @Throws(IOException::class)
    private fun parseToken(base64Token: String): Map<String, *> {
        val token = base64Token.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        return objectMapper.readValue(Base64.decodeBase64(token), object : TypeReference<Map<String, *>>() {

        })
    }

    @Throws(Exception::class)
    private fun toPrettyJsonString(`object`: Any): String {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(`object`)
    }
}