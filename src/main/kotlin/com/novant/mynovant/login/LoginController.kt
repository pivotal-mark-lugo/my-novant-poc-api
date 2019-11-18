package com.novant.mynovant.login;
import com.novant.mynovant.UAA
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController;

// todo: api response class (rest response, with errors/meta/etc)?

@RestController
public class LoginController {
    @Autowired
    lateinit var uaa: UAA;

    private val logger = KotlinLogging.logger {}

    @PostMapping("/login")
    fun login(
            @RequestParam email: String,
            @RequestParam password: String
    ): Map<String, *> {
        logger.info {"Logging in $email into MyNovant"}
        return uaa.getOauthToken(email, password);
    }
}
