package pl.bak.auction_shop.domain.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bak.auction_shop.domain.services.AuthenticationService;
import pl.bak.auction_shop.dto.UserDto;
import pl.bak.auction_shop.rest.entity.AuthenticationRequest;
import pl.bak.auction_shop.rest.entity.AuthenticationResponse;
import pl.bak.auction_shop.rest.entity.RegisterRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
