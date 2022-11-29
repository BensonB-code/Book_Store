package bbsapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import bbsapi.domain.service.user.LoginUserDetails;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	final private Algorithm algorithm;

    public SimpleAuthenticationSuccessHandler(String secretKey) {
        Objects.requireNonNull(secretKey, "secret key must be not null");
        try {
            this.algorithm = Algorithm.HMAC256(secretKey);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        if (response.isCommitted()) {
            log.info("Response has already been committed.");
            return;
        }

        setToken(response, generateToken(auth));
        response.setStatus(HttpStatus.OK.value());
        //clearAuthenticationAttributes(request);
    }

    
    private static final Long EXPIRATION_TIME = 10000L * 60L * 60L;

    private String generateToken(Authentication auth) {
        LoginUserDetails loginUser = (LoginUserDetails) auth.getPrincipal();
        Date issuedAt = new Date();
        Date notBefore = new Date(issuedAt.getTime());
        Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
        String token = JWT.create()
                .withIssuedAt(issuedAt)      //"iat" : Issued At
                .withNotBefore(notBefore)    //"nbf" : Not Before
                .withExpiresAt(expiresAt)    //"exp" : Expiration Time
                .withSubject(loginUser.getUser().getUserId().toString()) //"exp" : Expiration Time
                .sign(this.algorithm);
        log.debug("generate token : {}", token);
        return token;
    }
    
    private void setToken(HttpServletResponse response, String token) {
        response.setHeader("Authorization", String.format("Bearer %s", token));
    }


    /**
     * Removes temporary authentication-related data which may have been stored in the
     * session during the authentication process.
     */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}