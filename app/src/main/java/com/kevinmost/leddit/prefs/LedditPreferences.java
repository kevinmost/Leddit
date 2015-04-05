package com.kevinmost.leddit.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public final class LedditPreferences {

    private static final String TAG = LedditPreferences.class.getSimpleName();

    @Inject
    Context context;

    private SharedPreferences sharedPreferences;

    // Disallow non-injected instantiation
    protected LedditPreferences() {}

    public SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return sharedPreferences;
    }

    public <T> void put(LedditPreference<T> pref, T value) {
        final String key = pref.getKey();
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        // TODO: This is gross, any way we can improve this?
        if (value instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) value));
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Set) {
            //noinspection unchecked
            editor.putStringSet(key, (Set) value);
        } else {
            final String errorMsg =
                    "value was not of valid type. Type was: " + value.getClass().getCanonicalName();
            Log.e(TAG, errorMsg, new UnsupportedOperationException(errorMsg));
        }
        editor.apply();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(LedditPreference<T> pref) {
        final String key = pref.getKey();
        final T defaultValue = pref.getDefaultValue();
        // TODO: This is possibly even uglier than the above one
        if (defaultValue instanceof Boolean) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(key, (Boolean) defaultValue));
        } else if (defaultValue instanceof String) {
            return (T) sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return (T) Integer.valueOf(sharedPreferences.getInt(key, (Integer) defaultValue));
        } else if (defaultValue instanceof Float) {
            return (T) Float.valueOf(sharedPreferences.getFloat(key, (Float) defaultValue));
        } else if (defaultValue instanceof Long) {
            return (T) Long.valueOf(sharedPreferences.getLong(key, (Long) defaultValue));
        } else if (defaultValue instanceof Set) {
            return (T) sharedPreferences.getStringSet(key, (Set) defaultValue);
        } else {
            final String errorMsg = "defaultValue was not of valid type. Type was: " +
                    defaultValue.getClass().getCanonicalName();
            Log.e(TAG, errorMsg, new UnsupportedOperationException(errorMsg));
        }
        return defaultValue;
    }
}
