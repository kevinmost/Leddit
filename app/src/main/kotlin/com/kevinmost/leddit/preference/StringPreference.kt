package com.kevinmost.leddit.preference

import android.content.SharedPreferences

public enum class StringPreference private(key: String, defaultValue: String = ""):
        LedditPreference<String> {

    override protected var prefs: SharedPreferences? = null;
    override protected val key: String = key;
    override protected val defaultValue: String = defaultValue;

    override protected fun SharedPreferences.Editor.put(value: String): SharedPreferences.Editor {
        putString(key, value);
        return this;
    }
    override protected fun SharedPreferences.get(): String {
        return getString(key, defaultValue);
    }
}