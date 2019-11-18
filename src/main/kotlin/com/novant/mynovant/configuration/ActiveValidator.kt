package com.novant.mynovant.configuration

import com.novant.mynovant.UAA
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class ActiveValidator : OAuth2TokenValidator<Jwt> {
    private val error = OAuth2Error("invalid_token",
            "The token is inactive", null)

    @Autowired
    lateinit var uaa: UAA;

    private val logger = KotlinLogging.logger {}

    override fun validate(jwt: Jwt): OAuth2TokenValidatorResult {
        logger.trace { "ActiveValidator#validate: uaa is $uaa" }
        if (uaa != null) {
            return if (uaa.isTokenActive(jwt)) {
                OAuth2TokenValidatorResult.success()
            } else {
                OAuth2TokenValidatorResult.failure(error)
            }
        } else {
            logger.error {"UAA did not get injected?!"}
            throw Exception("UAA did not get injected?")
        }
    }
}