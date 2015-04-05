package com.kevinmost.leddit.prefs;

import javax.inject.Inject;

public enum BooleanPreference implements LedditPreference<Boolean> {

    ;

    @Inject
    LedditPreferences prefs;

    private final String key;
    private final Boolean defaultValue;

    private BooleanPreference(String key) {
        this(key, false);
    }

    private BooleanPreference(String key, Boolean defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public Boolean getValue() {
        return prefs.get(this);
    }

    @Override
    public void setValue(Boolean value) {
        prefs.put(this, value);
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Boolean getDefaultValue() {
        return defaultValue;
    }
}
