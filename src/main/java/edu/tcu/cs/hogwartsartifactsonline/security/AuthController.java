package edu.tcu.cs.hogwartsartifactsonline.security;

import edu.tcu.cs.hogwartsartifactsonline.system.Result;
import edu.tcu.cs.hogwartsartifactsonline.system.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("${spring.api.endpoint.base-url}/users")
public class AuthController {
    private final AuthService authService;
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Result getLoginInfo(Authentication auth) {
        logger.debug("Authenticated user: '{}'", auth.getName());

        return new Result(true, StatusCode.OK,"User info and JWT authenticated", this.authService.createLoginInfo(auth));
    }
}