package com.chaow.openutils.adapter;

import android.content.Context;
import android.content.SharedPreferences;

import com.chaow.openutils.OpenUtils;

import java.util.Set;

/**
 * Creator Char 2019/8/26
 */
public final class DefaultSPAdapter implements SharePreferenceAdapter {

    private SharedPreferences mSharedPreferences;

    public DefaultSPAdapter() {

    }

    public DefaultSPAdapter(String name) {
    }


    @Override
    public void init(String zone) {
        mSharedPreferences = OpenUtils.getApp().getSharedPreferences(zone, Context.MODE_PRIVATE);
    }

    @Override
    public boolean put(String key, Object value, boolean isCommit) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, String.valueOf(value));
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        } else {
            return false;
        }
        if (isCommit) {
            return editor.commit();
        } else {
            editor.apply();
            return true;
        }
    }

    @Override
    public Object get(String key, Object defValue) {
        return null;
    }

    @Override
    public boolean clear(boolean isCommit) {
        if (isCommit) {
            return mSharedPreferences.edit().clear().commit();
        } else {
            mSharedPreferences.edit().clear().apply();
            return true;
        }
    }

    @Override
    public boolean remove(String key, boolean isCommit) {
        if (isCommit) {
            return mSharedPreferences.edit().remove(key).commit();
        } else {
            mSharedPreferences.edit().remove(key).apply();
            return true;
        }
    }

    @Override
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValue) {
        return mSharedPreferences.getStringSet(key, defValue);
    }
}
