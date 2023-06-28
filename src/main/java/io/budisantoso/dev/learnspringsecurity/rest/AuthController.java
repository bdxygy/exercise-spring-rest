package io.budisantoso.dev.learnspringsecurity.rest;

import io.budisantoso.dev.learnspringsecurity.dto.SigninDTO;
import io.budisantoso.dev.learnspringsecurity.dto.SigninResponseDTO;
import io.budisantoso.dev.learnspringsecurity.dto.SignupDTO;
import io.budisantoso.dev.learnspringsecurity.dto.UserDTO;
import io.budisantoso.dev.learnspringsecurity.entities.UserEntity;
import io.budisantoso.dev.learnspringsecurity.repositories.UserRepository;
import io.budisantoso.dev.learnspringsecurity.services.AuthService;
import io.budisantoso.dev.learnspringsecurity.services.JwtService;
import io.budisantoso.dev.learnspringsecurity.services.UserService;
import io.budisantoso.dev.learnspringsecurity.utils.UniversalResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/v1/auth", "v1/auth"})
public class AuthController {

    private UserService userService;
    private AuthService authService;
    private JwtService jwtService;

    @Autowired
    public AuthController(UserService userService, AuthService authService, JwtService jwtService) {
        this.userService = userService;
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping({"signin", "/signin"})
    public ResponseEntity<UniversalResponse<SigninResponseDTO>> signin(@RequestBody SigninDTO signinDTO, HttpServletResponse response) {
        final UserEntity userExist = userService.findByEmail(signinDTO.getEmail());

        final boolean isPasswordMatch = authService.matching(signinDTO.getPassword(), userExist);

        if (!isPasswordMatch) {
            throw new RuntimeException("User not match!");
        }

        final Map<String, Object> extraClaims = new HashMap<>();

        final String token = jwtService.generateToken(extraClaims, userExist);

        SigninResponseDTO signinResponseDTO = new SigninResponseDTO(userExist.getEmail(), token);

        UniversalResponse<SigninResponseDTO> responses = new UniversalResponse<>(HttpStatus.ACCEPTED.value(), "Login user success", signinResponseDTO);

//        Cookie cookie = new Cookie("tokenId", token);
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge((int) new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7).getTime());
//        cookie.setSecure(true);
//        cookie.setPath("*");
//
//        response.addCookie(cookie);

        return new ResponseEntity<>(responses, HttpStatus.ACCEPTED);
    }

    @PostMapping({"signup", "/signup"})
    public ResponseEntity<UniversalResponse<UserDTO>> signup(@RequestBody SignupDTO signupDTO) {
        final UserEntity userEntity = userService.create(new UserDTO(signupDTO.getName(), signupDTO.getEmail(), signupDTO.getPassword()));
        final UserDTO newUser = new UserDTO(userEntity.getName(), userEntity.getEmail(), userEntity.getRoles());
        final UniversalResponse<UserDTO> response = new UniversalResponse<>(HttpStatus.CREATED.value(), "Create user success", newUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping({"/me"})
    public ResponseEntity<UniversalResponse<UserDTO>> whoIsMe(HttpServletRequest request) {
        final String currentUser = (String) request.getAttribute("currentUser");
        final UserEntity user = userService.findByEmail(currentUser);

        UserDTO userDTO = new UserDTO(user.getName(), user.getEmail(), user.getRoles());

        UniversalResponse<UserDTO> response = new UniversalResponse<>(HttpStatus.OK.value(), "Success to get your information", userDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping({"signout", "/signout"})
    public ResponseEntity<UniversalResponse<?>> signout() {
        return null;
    }

}
