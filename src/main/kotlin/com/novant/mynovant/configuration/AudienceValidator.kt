package com.novant.mynovant.configuration

import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.Jwt


class AudienceValidator : OAuth2TokenValidator<Jwt> {
    private val error = OAuth2Error("invalid_token", "The required audience is missing", null)

    override fun validate(jwt: Jwt): OAuth2TokenValidatorResult {
        //The audience string must be updated to represent the resource server's resource ID
        //When registering resources in SSO, resource ID is used as the first part of scopes, e.g. todo.read for the todo resource ID
//        return if (jwt.getAudience().contains("todo")) {
//            OAuth2TokenValidatorResult.success()
//        } else {
//            OAuth2TokenValidatorResult.failure(error)
//        }
        return OAuth2TokenValidatorResult.success()

    }
}