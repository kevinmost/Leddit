package com.kevinmost.leddit.prefs;

public interface LedditPreference<T> {
    public void setValue(T value);
    public T getValue();
    public String getKey();
}
