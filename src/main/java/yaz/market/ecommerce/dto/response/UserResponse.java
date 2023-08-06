package yaz.market.ecommerce.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import yaz.market.ecommerce.model.Role;
import yaz.market.ecommerce.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Data @NoArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
    public UserResponse(User user){
        this.firstName= user.getFirstName();
        this.lastName= user.getLastName();
        this.email=user.getEmail();
        this.roles=user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }
}
