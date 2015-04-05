package com.kevinmost.leddit.prefs.preferences;

import com.kevinmost.leddit.prefs.LedditPreference;
import com.kevinmost.leddit.prefs.LedditPreferences;

import javax.inject.Inject;

public enum BooleanPreference implements LedditPreference<Boolean> {

    ;

    @Inject
    LedditPreferences prefs;

    private final String key;

    private BooleanPreference(String key) {
        this.key = key;
    }

    @Override
    public Boolean getValue() {
        return prefs.getSharedPreferences().getBoolean(key, false);
    }


    @Override
    public void setValue(Boolean value) {
        prefs.put(this, value);
    }

    @Override
    public String getKey() {
        return key;
    }

}
