package com.buddyservice.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Generická třída poskytující možnost ukládat objekty zvoleného typu T do mapy s klíčem typu String.
 * @param <T>
 */
public class CacheManager<T> {

    private Map<String, T> cachedData;

    public CacheManager() {
        this.cachedData = new HashMap<>();
    }

    public T getValue(String key) {
        return cachedData.get(key);
    }

    public void addValue(String key, T value){
        cachedData.put(key, value);
    }

    public void setCachedData(HashMap<String, T> cachedData) {
        this.cachedData = cachedData;
    }

    public void clearCache() {
        cachedData.clear();
    }
}
