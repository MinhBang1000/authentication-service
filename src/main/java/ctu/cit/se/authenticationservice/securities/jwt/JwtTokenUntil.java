package ctu.cit.se.authenticationservice.securities.jwt;

import ctu.cit.se.authenticationservice.exceptions.messages.CustomExceptionMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtTokenUntil {
    private Environment environment;
    private String secretKey;
    private long accessTokenValidityInSeconds = 3600;
    private long accessTokenValidityInSecondsForRefresh = 86400;
    private JwtParser jwtParser;
    private String TOKEN_HEADER = "Authorization";
    private String TOKEN_PREFIX = "Bearer ";

    public JwtTokenUntil(Environment environment) {
        secretKey = environment.getProperty("auth.secretkey");
        jwtParser = Jwts.parser().setSigningKey(secretKey);
    }

    public String createToken(UserDetails userDetails, boolean isRefresh) {
        Date tokenValidity = Date.from(Instant.now().plusSeconds(accessTokenValidityInSeconds));
        Claims claims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(tokenValidity)
                .setIssuer(userDetails.getUsername());
        if (isRefresh) {
            Date refreshTokenValidity = Date.from(Instant.now().plusSeconds(accessTokenValidityInSecondsForRefresh));
            claims.setExpiration(refreshTokenValidity);
            claims.put("isRefresh", true);
        }else {
            claims.put("isRefresh", false);
        }
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            String token = bearerToken.substring(TOKEN_PREFIX.length());
            if (!validateClaims(parseJwtClaims(token))) {
                throw new IllegalArgumentException(CustomExceptionMessage.TOKEN_EXPIRED);
            }
            return token;
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        }catch (Exception e) {
            throw e;
        }
    }

    public String getUsernameFromToken(String token) {
        return parseJwtClaims(token).getSubject();
    }

    public String getIssuerFromToken(String token) {
        return parseJwtClaims(token).getIssuer();
    }

    public  Date getIssuedAtFromToken(String token) {
        return parseJwtClaims(token).getIssuedAt();
    }

    public Date getExpirationFromToken(String token) {
        return parseJwtClaims(token).getExpiration();
    }

    public boolean getIsRefreshedFromToken(String token) { return (boolean) parseJwtClaims(token).get("isRefresh"); }
}
