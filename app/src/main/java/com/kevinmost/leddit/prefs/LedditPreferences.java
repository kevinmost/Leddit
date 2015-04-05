package com.kevinmost.leddit.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Set;

@Singleton
final class LedditPreferences {

    private static final String TAG = LedditPreferences.class.getSimpleName();

    @Inject
    Context context;


    // Disallow non-injected instantiation
    protected LedditPreferences() {}

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public <T> void put(LedditPreference<T> pref, T value) {
        final String key = pref.getKey();
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
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

    public <T> T get(LedditPreference<T> pref) {
        final String key = pref.getKey();
        final T defaultValue = pref.getDefaultValue();
        if (!getSharedPreferences().contains(key)) {
            return defaultValue;
        }
        final Map<String, ?> allPrefs = getSharedPreferences().getAll();
        @SuppressWarnings("unchecked")
        final T value = (T) allPrefs.get(key);

        if (value == null) {
            Log.w(TAG, "Value of key " + key + " was null, returning default value" + defaultValue);
            return defaultValue;
        }
        return value;
    }
}
