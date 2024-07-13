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
        System.out.println("authorizationHeader==>"+     authorizationHeader);
        System.out.println("Before==>"+ SecurityContextHolder.getContext().getAuthentication() );
        System.out.println("Before==>"+ SecurityContextHolder.getContext());
        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            System.out.println(jwtToken);
           // Optional<Long> authId = jwtTokenManager.getAuthIdFromToken(jwtToken);
            Optional<String> role = jwtTokenManager.getRoleFromToken(jwtToken);
            if (role.isPresent()) {
                UserDetails userDetails = jwtUserDetail.loadUserByRole(role);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    System.out.println("After==>"+ SecurityContextHolder.getContext().getAuthentication() );
                    System.out.println("After==>"+ SecurityContextHolder.getContext());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
