package com.oumana.security;



import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.oumana.exceptions.APIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	@Value("${app.jwt-expiration-milliseconds}")
	private Integer jwtExpirationInMs;
	
	//generate token
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime() + jwtExpirationInMs);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		return token;
	}
	
	//get username from token
	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	//validate jwt token
	public boolean validateToken(String token) {
		try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new APIException("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
        	throw new APIException("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
        	throw new APIException("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
        	throw new APIException("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
        	throw new APIException("JWT claims string is empty - {}", ex.getMessage());
        }
	}
}
