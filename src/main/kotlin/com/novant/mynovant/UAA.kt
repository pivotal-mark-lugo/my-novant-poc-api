package com.novant.mynovant

import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.FuelJson
import com.github.kittinunf.fuel.json.responseJson
import com.nimbusds.jose.util.Base64
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service

/** Note the version of UAA docs we are coding against is:
 * http://docs.cloudfoundry.org/api/uaa/version/4.28.0/#introspect-token
 */
@Service
class UAA {
    @Value("\${uaaUrl}")
    var uaaUrl: String? = null

    @Value("\${ssoServiceClientId}")
    var clientId: String? = null

    @Value("\${ssoServiceClientSecret}")
    var clientSecret: String? = null

    private val logger = KotlinLogging.logger {}

    fun getOauthToken(email: String, password: String): Map<String, *> {
        logger.trace { "UAA#getOauthToken for $email against $uaaUrl" }
        var oauthTokenEndpoint = "$uaaUrl/oauth/token?client_id=$clientId&client_secret=$clientSecret&grant_type=password&username=$email&password=$password&token_format=opaque"
        var successful: Boolean = false;
        var apiToken: String? = null;
        oauthTokenEndpoint.httpPost().responseJson { _, _, result ->
            val (d, _) = result
            apiToken = (d as FuelJson).obj().get("id_token") as String?
            successful = true
        }.join()
        logger.trace { "UAA#getOauthToken -- authenticated is $successful" }
        return mapOf(
                "authenticated" to successful,
                "apiToken" to apiToken
        )
    }

    fun revokeOauthToken(jwt: Jwt): Boolean {
        logger.trace { "UAA#revokeOauthToken | ${jwt.id}"}
        // call the /oauth/token/revoke endpoint...
        var revokedSuccessfully = false
        var oauthTokenRevoke = "$uaaUrl/oauth/token/revoke/${jwt.id}"
        var headerMap = mapOf(
                "Accept" to "application/json",
                "Authorization" to "Bearer ${jwt.tokenValue}",
                "Content-Type" to "application/x-www-form-urlencoded"
        )

        oauthTokenRevoke.httpDelete().header(headerMap).response { request, response, _ ->
            logger.trace { request }
            logger.trace { response }

            logger.debug { "Status Code: ${response.statusCode}"}

            revokedSuccessfully = response.statusCode == 200
        }.join()

        return revokedSuccessfully
    }

    fun isTokenActive(jwt: Jwt): Boolean { // jwt or jti??
        logger.trace { "UAA#isTokenActive -- jwt token is ${jwt.id}" }
        var active = false
        var oauthIntrospectEndpoint = "$uaaUrl/introspect"
        var credentials = base64("$clientId:$clientSecret")
        var headerMap = mapOf(
                "Authorization" to "Basic $credentials",
                "Content-Type" to "application/x-www-form-urlencoded"
        )
        var body = "token=${jwt.id}"
        oauthIntrospectEndpoint.httpPost().header(headerMap).body(body).responseJson { req, rsp, result ->
            logger.debug{"req $req"}
            logger.debug{"rsp: $rsp"}
            var (d, _) = result
            logger.trace{"INTROSPECT RESPONSE: $d"}
            active = (d as FuelJson).obj().get("active") as Boolean
        }.join()
        return active
    }

    fun base64(inputString: String): String {
        // implement a base 64 function that mirrors what cUrl does
        return Base64.encode(inputString).toString();
    }
}