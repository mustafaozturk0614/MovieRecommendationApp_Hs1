package com.bilgeadam.config.security;

import com.bilgeadam.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;

    private final JwtUserDetail jwtUserDetail;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        filterChain.doFilter(request, response);
        if(Objects.nonNull(authorizationHeader)&& authorizationHeader.startsWith("Bearer ")){
            String jwtToken = authorizationHeader.substring(7);
            UserDetails userDetails=null;
            Optional<Long> authId=jwtTokenManager.getAuthIdFromToken(jwtToken);
            if (authId.isPresent()){
                userDetails= jwtUserDetail.loadUserByAuthId(authId.get());
                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                System.out.println("UserDetails: "+userDetails);
                System.out.println("getContext: "+SecurityContextHolder.getContext());

            }
        }
        filterChain.doFilter(request, response);
    }
}
