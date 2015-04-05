package com.kevinmost.leddit.prefs;

import javax.inject.Inject;

public enum IntPreference implements LedditPreference<Integer> {
    ;

    @Inject
    LedditPreferences prefs;

    private final String key;
    private final Integer defaultValue;

    private IntPreference(String key) {
        this(key, 0);
    }

    private IntPreference(String key, Integer defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public void setValue(Integer value) {
        prefs.put(this, value);
    }

    @Override
    public Integer getValue() {
        return prefs.get(this);
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Integer getDefaultValue() {
        return defaultValue;
    }
}
