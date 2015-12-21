package me.dozen.dpreference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by wangyida on 15/12/18.
 */
class PreferenceDao {

    private static Uri buildUri(String name, String key, int type) {
        return Uri.parse(getUriByType(type) + name + "/" + key);
    }

    public static String getString(Context context,String name, String key, String defaultValue) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_STRING);
        String value = defaultValue;
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getString(cursor.getColumnIndex(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static int getInt(Context context, String name, String key, int defaultValue) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_INT);
        int value = defaultValue;
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getInt(cursor.getColumnIndex(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static long getLong(Context context,String name, String key, long defaultValue) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_LONG);
        long value = defaultValue;
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getLong(cursor.getColumnIndex(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static boolean getBoolean(Context context,String name, String key, boolean defaultValue) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_BOOLEAN);
        int value = defaultValue ? 1 : 0;
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getInt(cursor.getColumnIndex(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value == 1;
    }

    public static void remove(Context context, String name, String key) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_STRING);
        context.getContentResolver().delete(URI, null, null);
    }

    public static void  setString(Context context, String name, String key, String value) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_STRING);
        ContentValues cv = new ContentValues();
        cv.put(PreferenceProvider.PREF_KEY, key);
        cv.put(PreferenceProvider.PREF_VALUE, value);
        context.getContentResolver().update(URI, cv, null, null);
    }

    public static void  setBoolean(Context context, String name, String key, boolean value) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_BOOLEAN);
        ContentValues cv = new ContentValues();
        cv.put(PreferenceProvider.PREF_KEY, key);
        cv.put(PreferenceProvider.PREF_VALUE, value);
        context.getContentResolver().update(URI, cv, null, null);
    }

    public static void  setInt(Context context, String name, String key, int value) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_INT);
        ContentValues cv = new ContentValues();
        cv.put(PreferenceProvider.PREF_KEY, key);
        cv.put(PreferenceProvider.PREF_VALUE, value);
        context.getContentResolver().update(URI, cv, null, null);
    }

    public static void  setLong(Context context, String name, String key, long value) {
        Uri URI = buildUri(name, key, PreferenceProvider.PREF_LONG);
        ContentValues cv = new ContentValues();
        cv.put(PreferenceProvider.PREF_KEY, key);
        cv.put(PreferenceProvider.PREF_VALUE, value);
        context.getContentResolver().update(URI, cv, null, null);
    }

    private static String getUriByType(int type) {
        switch (type) {
            case PreferenceProvider.PREF_BOOLEAN:
                return PreferenceProvider.CONTENT_PREF_BOOLEAN_URI;
            case PreferenceProvider.PREF_INT:
                return PreferenceProvider.CONTENT_PREF_INT_URI;
            case PreferenceProvider.PREF_LONG:
                return PreferenceProvider.CONTENT_PREF_LONG_URI;
            case PreferenceProvider.PREF_STRING:
                return PreferenceProvider.CONTENT_PREF_STRING_URI;
        }
        throw new IllegalStateException("unsupport preftype : " + type);
    }
}
