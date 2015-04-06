package com.kevinmost.leddit.preference

import android.content.SharedPreferences
import javax.inject.Inject

public enum class BooleanPreference private(key: String, defaultValue: Boolean = false):
        LedditPreference<Boolean> {

    FOO: BooleanPreference("bar");

    override protected var prefs: SharedPreferences? = null;
    override protected val key: String = key;
    override protected val defaultValue: Boolean = defaultValue;

    override fun SharedPreferences.Editor.put(value: Boolean): SharedPreferences.Editor {
        putBoolean(key, value);
        return this;
    }

    override fun SharedPreferences.get(): Boolean {
        return getBoolean(key, defaultValue);
    }
}