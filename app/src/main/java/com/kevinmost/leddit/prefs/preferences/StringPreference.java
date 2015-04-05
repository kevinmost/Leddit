package com.kevinmost.leddit.prefs.preferences;

import com.kevinmost.leddit.prefs.LedditPreference;
import com.kevinmost.leddit.prefs.LedditPreferences;

import javax.inject.Inject;

public enum StringPreference implements LedditPreference<String> {
    REDDIT_TOKEN("Auth token"),
    ;

    @Inject
    LedditPreferences prefs;

    private final String key;

    private StringPreference(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return prefs.getSharedPreferences().getString(key, "");
    }

    @Override
    public void setValue(String value) {
        prefs.put(this, value);
    }

    @Override
    public String getKey() {
        return key;
    }
}
