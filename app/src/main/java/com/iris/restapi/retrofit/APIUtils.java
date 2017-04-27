package com.iris.restapi.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.iris.restapi.retrofit.config.APIPreferencesKey;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * API Utils
 * Created by Ted
 */
public class APIUtils {

    public static final String TAG = APIUtils.class.getSimpleName();

    /**
     * Not Exist Token Error 떨어졌을 경우 한번 더 API 요청하기 위한 flag 값
     */
    public static boolean isRetryInvalidAccessToken = false;

    /**
     * Get api random code
     *
     * @return random code
     */
    public static String getAPIRandomCode() {
        return getRandomCode(20);
    }

    /**
     * Get random code (english and number)
     *
     * @param length random code length
     * @return random code
     */
    public static String getRandomCode(int length) {
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < length; i++) {
            if (rnd.nextBoolean()) {
                buf.append((char) (rnd.nextInt(26) + 97));
            } else {
                buf.append((rnd.nextInt(10)));
            }
        }

        return String.valueOf(buf);
        //return "fdsafa2f";
    }

    /**
     * Get device country
     *
     * @return country code
     */
    public static String getDeviceCountryCode() {
        return Locale.getDefault().getCountry();
    }

    /**
     * Get device language
     *
     * @return language code
     */
    public static String getDeviceLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * Get device timezone offset
     *
     * @return timezone offest
     */
    public static String getCurrentTimezoneOffset() {
        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = -mTimeZone.getRawOffset();

        return String.valueOf(TimeUnit.MINUTES.convert(mGMTOffset, TimeUnit.MILLISECONDS));
    }

    /**
     * Get app version code
     *
     * @param context     context
     * @param packageName app package name
     * @return version code
     */
    public static int getVersionCode(Context context, String packageName) {
        int v = 0;
        try {
            v = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    /**
     * Make a vo to json string
     *
     * @param json  json string
     * @param clazz convert class
     * @return vo object
     */
    public static Object toJsonString(String json, Class clazz) {
        Gson gson = new Gson();
        TypeAdapter adapter = gson.getAdapter(clazz);
        try {
            return adapter.fromJson(json);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Make a vo to json object
     *
     * @param object json object
     * @param clazz  convert class
     * @return vo object
     */
    public static Object toJsonObject(Object object, Class clazz) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        return gson.fromJson(jsonObject, clazz);
    }

    /**
     * Make to json
     *
     * @param obj vo object
     * @return Json String
     */
    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    /**
     * Base64 encoding
     *
     * @param content encoding string
     * @return Base64-encoded string
     */
    public static String getBase64encode(String content) {
        return Base64.encodeToString(content.getBytes(), 0);
    }

    /**
     * MD5 encoding
     *
     * @param content encoding string
     * @return MD5-encoded string
     */
    public static String getMD5encode(String content) {
        MessageDigest m = null;
        String hash = null;

        try {
            m = MessageDigest.getInstance("MD5");
            m.update(content.getBytes(), 0, content.length());
            hash = new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }

    /**
     * Get New-line character remove
     *
     * @param message changed message
     * @return Removed New-line character
     */
    public static String removeNewLineCharacter(String message) {
        return message.replace(System.getProperty("line.separator"), "");
    }


    /**
     * Check true / false
     *
     * @param value int vaule
     * @return true / false
     */
    public static boolean isValue(int value) {
        return value == 1;
    }


    /**
     * Get SID
     *
     * @param context context
     * @return sid
     */
    public static String getSID(Context context) {
        SharedPreferences pref = context.getSharedPreferences(APIPreferencesKey.PREFERENCES_NAME, Context.MODE_PRIVATE);
        String sid = pref.getString(APIPreferencesKey.SID, null);
        if (sid == null) {
            sid = APIUtils.getRandomCode(10);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(APIPreferencesKey.SID, sid);
            edit.apply();
        }

        return sid;
    }
}
