package com.kevinmost.leddit.preference

import android.content.SharedPreferences
import javax.inject.Inject

public trait LedditPreference<T> {
    protected var prefs: SharedPreferences?
        get
        [Inject] set;

    protected val key: String;
    protected val defaultValue: T;

    protected fun put(value: T) {
        prefs?.edit()!!.put(value).apply();
    }

    protected fun get(): T {
        return prefs.get();
    }

    protected fun SharedPreferences.Editor.put(value: T): SharedPreferences.Editor;
    protected fun SharedPreferences.get(): T;
}