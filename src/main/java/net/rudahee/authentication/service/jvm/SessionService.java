package net.rudahee.authentication.service.jvm;

import net.rudahee.authentication.model.api.TokenDTO;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Random;

@Service
public class SessionService {

    private String token;
    private Instant expire;

    private void generateToken() throws NoSuchAlgorithmException {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder clearToken = new StringBuilder();

        Random rng = new Random();
        for (int i=0; i<128; i++) {
            clearToken.append(chars.charAt(rng.nextInt(chars.length())));
        }

        this.token = clearToken.toString();

        this.expire = Instant.now().plusSeconds(1800);
    }

    public String getToken() throws NoSuchAlgorithmException {
        if (token == null) {
            generateToken();
            return token;
        }

        if (expire.isBefore(Instant.now())) {
            generateToken();
            return token;
        }

        return token;
    }


    public boolean validateToken(String token) throws NoSuchAlgorithmException {
        if (token == null) {
            return false;
        } else {
            if (token == null) {
                return false;
            }
        }

        if (!getToken().equals(token)) {
            return false;
        }
        return true;
    }


}
