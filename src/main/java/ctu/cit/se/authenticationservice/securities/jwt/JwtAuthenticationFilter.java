package ctu.cit.se.authenticationservice.securities.jwt;

import ctu.cit.se.authenticationservice.repositories.ICustomUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUntil jwtTokenUntil;
    @Autowired
    private ICustomUserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /* Get the JWT Token from a header */
        String receivedToken = jwtTokenUntil.resolveToken(request);
        if (Objects.isNull(receivedToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        /* Get user details entity from database */
        UserDetails loggedUser = userRepository.findByUsername(jwtTokenUntil.getUsernameFromToken(receivedToken)).orElse(null);

        /* Create an UsernamePasswordAuthenticationToken */
        UsernamePasswordAuthenticationToken authentication = null;
        if (Objects.nonNull(loggedUser)) {
            authentication = new UsernamePasswordAuthenticationToken(loggedUser, null, loggedUser.getAuthorities());
        }

        /* Setup detail of the Authentication Object like IP address or something else --> May not give attention for this problem */
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        /* Set the security context holder in order to transfer to DaoAuthenticationProvider the Authentication Object with principal (User Details) and credentials (Password) */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /* Do next the filters */
        filterChain.doFilter(request, response);
    }
}
