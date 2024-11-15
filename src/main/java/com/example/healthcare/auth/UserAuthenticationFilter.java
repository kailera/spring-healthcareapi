package com.example.healthcare.auth;

import com.example.healthcare.configuration.SecurityConfig;
import com.example.healthcare.model.User;
import com.example.healthcare.repository.UserResponsitory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final  UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserAuthenticationFilter(JwtTokenService jwtTokenService, UserDetailsServiceImpl userDetailsService){
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException{
        if(checkIfEndpointIsNotPublic(request)){
            String token = recoveryToken(request);
            if(token!=null){
                String subject = jwtTokenService.getSubjectFromToken(token);
                User user = (User) userDetailsService.loadUserByUsername(subject);
                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                //cria um objeto de autenticaçao do Spring Security
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(),
                                null,
                                userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                throw  new RuntimeException("O token está Ausente");
            }
        }
        filterChain.doFilter(request, response);
    }

    //recupera o token do cabecalho authorization da requisição
    private String recoveryToken (HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader!=null){

            return authorizationHeader.replace("Bearer", "");
        }
        return null;
    }

    // verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }
}
