package com.kevinmost.leddit.prefs;

public class Preference {
    public static <T, P extends LedditPreference<T>> LedditPreference<T> get(P preference) {
        return preference;
    }
}
