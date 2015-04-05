package com.kevinmost.leddit.prefs;

import com.kevinmost.leddit.prefs.LedditPreference;
import com.kevinmost.leddit.prefs.LedditPreferences;

import javax.inject.Inject;

public enum StringPreference implements LedditPreference<String> {
    REDDIT_TOKEN("Auth token"),
    ;

    @Inject
    LedditPreferences prefs;

    private final String key;
    private final String defaultValue;

    private StringPreference(String key) {
        this(key, "");
    }

    private StringPreference(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getValue() {
        return prefs.get(this);
    }

    @Override
    public void setValue(String value) {
        prefs.put(this, value);
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }
}
