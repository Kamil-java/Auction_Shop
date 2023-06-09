package pl.bak.auction_shop.domain.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bak.auction_shop.config.security.JwtService;
import pl.bak.auction_shop.dto.UserDto;
import pl.bak.auction_shop.model.Role;
import pl.bak.auction_shop.model.User;
import pl.bak.auction_shop.rest.entity.AuthenticationRequest;
import pl.bak.auction_shop.rest.entity.AuthenticationResponse;
import pl.bak.auction_shop.rest.entity.RegisterRequest;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    public UserDto register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.firstname())
                .lastName(request.lastname())
                .email(request.email())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        jwtService.generateToken(user);
        return modelMapper.map( userService.save(user), UserDto.class);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authentication);
        var user = userService.getUserByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
