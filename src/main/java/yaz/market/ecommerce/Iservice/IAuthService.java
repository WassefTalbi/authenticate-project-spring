package yaz.market.ecommerce.Iservice;

import yaz.market.ecommerce.dto.request.AuthenticationRequest;
import yaz.market.ecommerce.dto.request.RegisterRequest;
import yaz.market.ecommerce.dto.response.AuthenticationResponse;
import yaz.market.ecommerce.model.User;

public interface IAuthService {
    public User register(RegisterRequest registerRequestDTO);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
