package yaz.market.ecommerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yaz.market.ecommerce.service.JwtService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     final String authHeader= request.getHeader("Authorization");
     final String token;
     final String username;
     if (authHeader==null || !authHeader.startsWith("Bearer ")){
         filterChain.doFilter(request,response);
         return;
     }
        token=authHeader.substring(7);
         username=jwtService.extractUsername(token);
     if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
         UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                 userDetails.getAuthorities()
                         .forEach(grantedAuthority ->System.out.println(grantedAuthority));
            log.info(userDetails.getAuthorities().toString());
         if (jwtService.isTokenValid(token, userDetails) ) {
             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                     userDetails,
                     null,
                     userDetails.getAuthorities()
             );
             authToken.setDetails(
                     new WebAuthenticationDetailsSource().buildDetails(request)
             );
             SecurityContextHolder.getContext().setAuthentication(authToken);
         }
     }
        filterChain.doFilter(request, response);

    }




}