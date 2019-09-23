package telran.security.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import telran.security.services.AuthService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;

@Component
public class CustomAuth {

    private final AuthService authService;

    @Autowired
    public CustomAuth(AuthService authService) {
        this.authService = authService;
    }


    public boolean requestIsValid(String authData) {
        String[] userDetails = decodeAuthHeader(authData);
        userDetails[1] = hashPassword(userDetails[1]);
        String passHash = authService.getPasswordHash(userDetails[0]);
        return passHash.equals(userDetails[1]);
    }

    public boolean superusersOnly(String userName) {
        HashSet<String> userRoles = authService.getRoles(userName);
        return userRoles.contains("SUPERUSER");
    }


    public static String hashPassword(String passwordString) {
        byte[] password = passwordString.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return Base64.getEncoder().encodeToString((md.digest(password)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String[] decodeAuthHeader(String authData) {
        String base64Credentials = authData.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        final String pair = new String(credDecoded);
        final String[] userDetails = pair.split(":", 2);
        return userDetails;
    }
}
