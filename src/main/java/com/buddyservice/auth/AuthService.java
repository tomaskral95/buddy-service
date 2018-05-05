package com.buddyservice.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service("authService")
@Transactional
public class AuthService implements IAuthService {

    public String createMD5Hash(String toHash) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Zvoleno nesprávné kódování");
        }
        if (md != null) {
            md.update(toHash.getBytes());
        }
        byte byteData[] = md != null ? md.digest() : new byte[0];

        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
