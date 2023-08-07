package yaz.market.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yaz.market.ecommerce.dto.request.AuthenticationRequest;
import yaz.market.ecommerce.dto.request.RegisterRequest;
import yaz.market.ecommerce.dto.response.AuthenticationResponse;
import yaz.market.ecommerce.exception.EmailExistsExecption;
import yaz.market.ecommerce.service.AuthService;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequestDTO){
        try {
            return new ResponseEntity<>(authService.register(registerRequestDTO), HttpStatus.CREATED);
        }catch (EmailExistsExecption e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate( @Valid @RequestBody AuthenticationRequest authenticationRequest) {

        return new ResponseEntity<>(authService.authenticate(authenticationRequest),HttpStatus.OK) ;
    }


}
