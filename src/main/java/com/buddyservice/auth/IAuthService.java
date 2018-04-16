package com.buddyservice.auth;

import java.security.NoSuchAlgorithmException;

public interface IAuthService {

    String createMD5Hash(String toHash) throws NoSuchAlgorithmException;

}
