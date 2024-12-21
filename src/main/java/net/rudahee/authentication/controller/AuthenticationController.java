package net.rudahee.authentication.controller;

import net.rudahee.authentication.model.api.LoginDTO;
import net.rudahee.authentication.model.api.TokenDTO;
import net.rudahee.authentication.service.jvm.LoginService;
import net.rudahee.authentication.service.jvm.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final LoginService loginService;
    private final SessionService sessionService;

    public AuthenticationController(LoginService loginService, SessionService sessionService) {
        this.loginService = loginService;
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException {
        System.out.println(loginDTO);
        if (loginService.login(loginDTO.getPassword())) {
            String token = sessionService.getToken();
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            return ResponseEntity.ok(tokenDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenDTO());
        }
    }
}
