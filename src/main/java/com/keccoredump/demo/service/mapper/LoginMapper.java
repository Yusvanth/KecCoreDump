package com.keccoredump.demo.service.mapper;

import javax.servlet.http.HttpServletResponse;

import com.keccoredump.demo.entity.RefreshToken;
import com.keccoredump.demo.entity.User;
import com.keccoredump.demo.handlers.ApiException;
import com.keccoredump.demo.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keccoredump.demo.dto.AuthenticationResponseDto;
import com.keccoredump.demo.dto.LoginDTO;
import com.keccoredump.demo.service.UserService;
import com.keccoredump.demo.util.JwtUtils;


@Service
public class LoginMapper {

	@Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserServiceMapper userDetailsService;
    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    RefreshTokenService refreshTokenService;
    public AuthenticationResponseDto createAuthenticationToken(LoginDTO authenticationRequest, HttpServletResponse response) {

        try
        {

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());
            User user = userService.findByEmail(authenticationRequest.getEmail());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            if(user.getUserDetails().isDeactivated()){
                return null;
            }
            String jwt = jwtTokenUtil.generateJwt(user.getEmail());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
            return new AuthenticationResponseDto(jwt,refreshToken.getToken());
        }
        catch(UsernameNotFoundException e)
        {
            throw new UsernameNotFoundException("Username not found");
        }
        catch (Exception e){
            throw new ApiException("Invalid password", HttpStatus.BAD_REQUEST);
        }
    }
}
