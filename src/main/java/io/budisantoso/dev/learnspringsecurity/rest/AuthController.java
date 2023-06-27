package io.budisantoso.dev.learnspringsecurity.rest;

import io.budisantoso.dev.learnspringsecurity.dto.SigninDTO;
import io.budisantoso.dev.learnspringsecurity.dto.SigninResponseDTO;
import io.budisantoso.dev.learnspringsecurity.dto.SignupDTO;
import io.budisantoso.dev.learnspringsecurity.dto.UserDTO;
import io.budisantoso.dev.learnspringsecurity.entities.UserEntity;
import io.budisantoso.dev.learnspringsecurity.repositories.UserRepository;
import io.budisantoso.dev.learnspringsecurity.services.UserService;
import io.budisantoso.dev.learnspringsecurity.utils.UniversalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/v1/auth", "v1/auth"})
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"signin", "/signin"})
    public ResponseEntity<UniversalResponse<SigninResponseDTO>> signin(@RequestBody SigninDTO signinDTO) {

        return null;
    }

    @PostMapping({"signup", "/signup"})
    public ResponseEntity<UniversalResponse<UserDTO>> signup(@RequestBody SignupDTO signupDTO) {
        final UserEntity userEntity = userService.create(new UserDTO(signupDTO.getName(), signupDTO.getEmail(), signupDTO.getPassword()));
        final UserDTO newUser = new UserDTO(userEntity.getName(), userEntity.getEmail());
        final UniversalResponse<UserDTO> response = new UniversalResponse<>(HttpStatus.CREATED.value(), "Create user success", newUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping({"signout", "/signout"})
    public ResponseEntity<UniversalResponse<?>> signout() {
        return null;
    }

}
