package com.novant.mynovant

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*

@Configuration
class ServerConfiguration {
    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuerUri: String? = null

    @Bean
    internal fun jwtDecoder(): JwtDecoder {
        println();

        val jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUri) as NimbusJwtDecoderJwkSupport

        val issuerValidator = JwtIssuerValidator(issuerUri)
        val timestampValidator = JwtTimestampValidator()
        val audienceValidator = AudienceValidator()
        val jwtValidators = DelegatingOAuth2TokenValidator(issuerValidator, timestampValidator, audienceValidator)

        jwtDecoder.setJwtValidator(jwtValidators)

        return jwtDecoder
    }
}