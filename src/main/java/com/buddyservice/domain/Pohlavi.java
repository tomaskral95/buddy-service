package com.buddyservice.domain;

public enum Pohlavi {
    MUZ,
    ZENA;

    public static Pohlavi getPohlavi(String pohlavi) {
        if (pohlavi.toLowerCase().trim().equals("muz")) {
            return Pohlavi.MUZ;
        }
        if (pohlavi.toLowerCase().trim().equals("zena")) {
            return Pohlavi.ZENA;
        }
        return null;
    }
}
