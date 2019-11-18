package com.novant.mynovant

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class LogoutController {
    @Autowired
    lateinit var uaa: UAA;

    private val logger = KotlinLogging.logger {}

    @PostMapping("/bye")
    fun logout(
        principal: Principal
    ): Boolean {
        val jwt: JwtAuthenticationToken = principal as JwtAuthenticationToken;
        logger.info { "Logging out of Novant on behalf of ${jwt.principal}..." }
        logger.debug { "Token is: ${jwt.token}" }
        return uaa.revokeOauthToken(jwt.token)
    }

}