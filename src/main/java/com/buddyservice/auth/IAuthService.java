package com.buddyservice.auth;

import java.security.NoSuchAlgorithmException;

/**
 * Serivsní třída poskytující základní kryptografické funkcionality.
 */
public interface IAuthService {

    /**
     * Metodě je předán string jako parametr. Metoda z této String hodnoty vytvoří 16B MD5 Hash, který
     * je zároveň návratovou hodnotou.
     * @param toHash
     * @return Zahashovaný string vložený jako parametr
     * @throws NoSuchAlgorithmException
     */
    String createMD5Hash(String toHash) throws NoSuchAlgorithmException;

}
