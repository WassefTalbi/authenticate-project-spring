package yaz.market.ecommerce.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yaz.market.ecommerce.Iservice.IAuthService;
import yaz.market.ecommerce.dao.RoleRepository;
import yaz.market.ecommerce.dao.UserRepository;
import yaz.market.ecommerce.dto.request.AuthenticationRequest;
import yaz.market.ecommerce.dto.request.RegisterRequest;
import yaz.market.ecommerce.dto.response.AuthenticationResponse;
import yaz.market.ecommerce.exception.EmailExistsExecption;
import yaz.market.ecommerce.model.Role;
import yaz.market.ecommerce.model.User;

@Service
@Transactional
@RequiredArgsConstructor

public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public User register(RegisterRequest registerRequestDTO) {
        if(userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()){
            throw new EmailExistsExecption("Email already exists");
        }
        User user=new User();
        user.setFirstName(registerRequestDTO.getFirstName());
        user.setLastName(registerRequestDTO.getLastName());
        user.setMobileNumber(registerRequestDTO.getMobileNumber());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequestDTO.getPassword()));
        user.setEmail(registerRequestDTO.getEmail());
        user.setEnabled(false);
        user.setLocked(false);
        user.setGender(registerRequestDTO.getGender());
        user.setBirthday(registerRequestDTO.getBirthday());
        Role role =new Role("ROLE_USER");
        user.getRoles().add(role);
        roleRepository.save(role);
        return userRepository.save(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authenticationRequest.getUsername(),authenticationRequest.getPassword()
        ));

        var user=userRepository.findByEmail(authenticationRequest.getUsername()).orElseThrow();
        return new AuthenticationResponse( jwtService.generateToken(user));
    }


}
