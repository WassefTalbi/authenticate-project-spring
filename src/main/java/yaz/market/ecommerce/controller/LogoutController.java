package yaz.market.ecommerce.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yaz.market.ecommerce.service.LogoutService;

@RestController
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutService logoutService;

    @GetMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication){
        logoutService.logout(request,response,authentication);
    }
}
