package com.kevinmost.leddit.prefs.preferences;

import com.kevinmost.leddit.prefs.LedditPreference;
import com.kevinmost.leddit.prefs.LedditPreferences;

import javax.inject.Inject;

public enum IntPreference implements LedditPreference<Integer> {
    ;

    @Inject
    LedditPreferences prefs;

    private final String key;

    private IntPreference(String key) {
        this.key = key;
    }

    @Override
    public void setValue(Integer value) {
        prefs.put(this, value);
    }

    @Override
    public Integer getValue() {
        return prefs.getSharedPreferences().getInt(key, 0);
    }

    @Override
    public String getKey() {
        return key;
    }
}
