package com.novant.mynovant.login;
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.FuelJson
import com.github.kittinunf.fuel.json.responseJson
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController;

// todo: api response class (rest response, with errors/meta/etc)?

@RestController
public class LoginController {
    @Value("\${uaaUrl}")
    var uaa: String? = null

    @Value("\${ssoServiceClientId}")
    var clientId: String? = null

    @Value("\${ssoServiceClientSecret}")
    var clientSecret: String? = null

    @PostMapping("/login")
    fun login(
            @RequestParam email: String,
            @RequestParam password: String
    ): Map<String, *> {
        var oauthTokenEndpoint = "$uaa/oauth/token?client_id=$clientId&client_secret=$clientSecret&grant_type=password&username=$email&password=$password&token_format=opaque"
        var successful: Boolean = false;
        var apiToken: String? = null;
        oauthTokenEndpoint.httpPost().responseJson { _, _, result ->
            val (d, e) = result
            apiToken = (d as FuelJson).obj().get("id_token") as String?
            successful = true
        }.join();
        return mapOf(
          "authenticated" to successful,
          "apiToken" to apiToken
        )
    }
}
