package org.eduardomaravill.customers.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.eduardomaravill.customers.entities.User;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "prtn982@dfdf";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public static String generateToken(User user){
        return  JWT.create().withIssuer("ATLAcademy")
                .withClaim("userId",user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(getExpiresAt())
                .sign(algorithm);
    }

    private static Date getExpiresAt() {
        return new Date(System.currentTimeMillis() +
                (1000L * 60 * 60 * 24*15)); //15 days
    }

   public static String getUserIdByToken(String token){
       JWTVerifier verifier = JWT.require(algorithm)
               .withIssuer("ATLAcademy")
               .build();
       DecodedJWT decoded = verifier.verify(token);
       return decoded.getClaim("userId").toString();
    }
}
