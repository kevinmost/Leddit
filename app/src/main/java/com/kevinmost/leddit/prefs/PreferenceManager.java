package com.kevinmost.leddit.prefs;

public class PreferenceManager {
    public static <T, P extends LedditPreference<T>> LedditPreference<T> get(P preference) {
        return preference;
    }
}
