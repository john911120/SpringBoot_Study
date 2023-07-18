package org.zerock.club.security.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.zerock.club.security.dto.ClubAuthMemberDTO;
import org.zerock.club.util.JWTUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ApiLoginFillter extends AbstractAuthenticationProcessingFilter {

    private JWTUtil jwtUtil;

    public ApiLoginFillter(String defaultFilterProcessesUrl, JWTUtil jwtUtil){
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("---------ApiLoginFilter---------------");
        log.info("attemptAuthentication");

        String email = request.getParameter("email");
        String pw = "1111";

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, pw);

//        if(email == null) {
//            throw new BadCredentialsException("Email Cannot be null");
//        }

        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("-----------APILoginFilter-------------------------");
        log.info("SuccessfulAuthentication : " + authResult);

        log.info(authResult.getPrincipal());

        String email = ((ClubAuthMemberDTO)authResult.getPrincipal()).getUsername();
        String token = null;

        try {
            token = jwtUtil.generateToken(email);
            response.setContentType("text/plain");
            response.getOutputStream().write(token.getBytes());
            log.info(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}