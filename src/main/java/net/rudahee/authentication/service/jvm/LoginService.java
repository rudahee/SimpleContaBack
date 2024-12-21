package net.rudahee.authentication.service.jvm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {

    @Value("${token.login.user}")
    private String clearPassphrase;

    public boolean login(String hashedToken) throws NoSuchAlgorithmException {

        return clearPassphrase.equals(hashedToken);
    }

}
