package com.keccoredump.demo.util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static String key = "My_Secret_Key";
	private Long jwtExpirationInMillis;
	public long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60;
	private static long expiryDuration = 60*60*2;
	
	private List<String> blackedJwt = new ArrayList<>();
	
	public void logout(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		//System.out.println("in logout");
		String jwt=header.substring(7);
		blackedJwt.add(jwt);
	}

	public String generateJwt(String userName)
	{
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, key).compact();
	}
	public boolean legitJwt(String jwt) {
		return blackedJwt.contains(jwt);
	}
	
//	public String generateJwt(UserDetails user) {
//
//		long millitime = System.currentTimeMillis();
//
//		long expiryTime = millitime + expiryDuration *1000;
//
//		Date issuedAt = new Date(millitime);
//
//		Date expiryAt = new Date(expiryTime);
//
//		Claims claims = Jwts.claims()
//						.setSubject(user.getUsername()+"")
//						.setIssuedAt(issuedAt)
//						.setExpiration(expiryAt);
//
//		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key).compact();
//	}
	
	public String extractUserName(String jwt) {
		return extractClaims(jwt,Claims::getSubject);
	}
	
	public Date extractExpirationDate(String jwt) {
		return extractClaims(jwt, Claims::getExpiration);
	}
	
    private <T>T extractClaims(String jwt, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(jwt);
		
		return claimsResolver.apply(claims);
	}

    private Claims extractAllClaims(String jwt) {
    	Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
		return claims;
	}
    
    public boolean isExpired(String jwt) {
    	return extractExpirationDate(jwt).before(new Date());
    }
    
    public boolean validateToken(String jwt,UserDetails userDetails) {
    	String username=extractUserName(jwt);
    	return userDetails.getUsername().equals(username)&&!isExpired(jwt);
    }
    
    public void cleanExpired() {
        for(String jwt:blackedJwt)
        {
            if(isExpired(jwt))
            {
                blackedJwt.remove(jwt);
                if(blackedJwt.size()==0)
                {
                    return;
                }
            }
        }
    }

//	public Claims verify(String authorization) {
//		try {
//			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(authorization).getBody();
//			return claims;
//		}
//		catch (Exception e) {
//			System.out.println(e);
//			return null;
//		}
//		
//	}
	

	public String decodeJwt(String token) throws UnsupportedEncodingException {
		String payload = token.split("\\.")[1];
		return new String(Base64.decodeBase64(payload),"UTF-8");
	}
}
