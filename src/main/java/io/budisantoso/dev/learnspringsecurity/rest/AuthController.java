package io.budisantoso.dev.learnspringsecurity.rest;

import io.budisantoso.dev.learnspringsecurity.dto.SigninDTO;
import io.budisantoso.dev.learnspringsecurity.dto.SignupDTO;
import io.budisantoso.dev.learnspringsecurity.utils.UniversalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/v1/auth", "v1/auth"})
public class AuthController {

    @PostMapping({"signin", "/signin"})
    public ResponseEntity<UniversalResponse<?>> signin(@RequestBody SigninDTO signinDTO) {
        return null;
    }

    @PostMapping({"signup", "/signup"})
    public ResponseEntity<UniversalResponse<?>> signup(@RequestBody SignupDTO signupDTO) {
        System.out.println(signupDTO);
        return null;
    }

    @PostMapping({"signout", "/signout"})
    public ResponseEntity<UniversalResponse<?>> signout() {
        return null;
    }

}
