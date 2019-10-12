package com.chaow.openutils.adapter;

import android.content.Context;
import android.content.SharedPreferences;

import com.chaow.openutils.OpenUtils;

import java.util.Map;
import java.util.Set;

/**
 * @author : Char
 * @date : 2019/8/26
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 默认SharedPreferences适配器
 */
public final class DefaultSPAdapter implements SharePreferenceAdapter {

    private SharedPreferences mSharedPreferences;

    public DefaultSPAdapter() {
        this(OpenUtils.getApp().getPackageName());
    }

    public DefaultSPAdapter(String name) {
        mSharedPreferences = OpenUtils.getApp().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    @Override
    public boolean put(String key, Object value, boolean isCommit) {
        if (key != null && value != null) {
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
                editor.putStringSet(key, (Set) value);
            } else {
                return false;
            }
            if (isCommit) {
                return editor.commit();
            } else {
                editor.apply();
                return true;
            }
        } else {
            return false;
        }
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
    public void changeName(String name) {
        mSharedPreferences = OpenUtils.getApp().getSharedPreferences(name, Context.MODE_PRIVATE);
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

    @Override
    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }

    @Override
    public boolean contains(String key) {
        return mSharedPreferences.contains(key);
    }
}
