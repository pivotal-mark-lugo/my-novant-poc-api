package com.novant.mynovant.configuration

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*

@Configuration
class ServerConfiguration {
    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuerUri: String? = null

    @Autowired
    lateinit var activeValidator: ActiveValidator;
//    private val activeValidator: ActiveValidator? = null;

    private val logger = KotlinLogging.logger {}

    @Bean
    internal fun jwtDecoder(): JwtDecoder {
        logger.trace {"jwt logging: $issuerUri, and $activeValidator :)"}
        logger.debug {"JWT DECODER -- using ISSUER URI: $issuerUri"}
        logger.debug {"JWT DECODER -- active validator: $activeValidator"}

        val jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUri) as NimbusJwtDecoderJwkSupport

        val issuerValidator = JwtIssuerValidator(issuerUri)
        val timestampValidator = JwtTimestampValidator()
        val audienceValidator = AudienceValidator()
        val jwtValidators = DelegatingOAuth2TokenValidator(
                issuerValidator,
                timestampValidator,
                audienceValidator,
                activeValidator
        )

        jwtDecoder.setJwtValidator(jwtValidators)

        return jwtDecoder
    }
}