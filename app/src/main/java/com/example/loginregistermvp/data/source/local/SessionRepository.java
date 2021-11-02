package com.example.loginregistermvp.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.loginregistermvp.data.pojo.User;
import com.example.loginregistermvp.data.source.local.ISessionRepository;
import com.google.gson.Gson;

public class SessionRepository implements ISessionRepository {
    private static String SESSION_DATA_KEY = "SessionData";
    private static String SHARED_PREFERENCE_NAME = "SessionSharedPreference";
    private SharedPreferences sharedPreferences;

    public SessionRepository(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    @Override
    public User initializer(User sessionData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SESSION_DATA_KEY, new Gson().toJson(sessionData));
        editor.apply();
        String sessionDataJson = sharedPreferences.getString(SESSION_DATA_KEY, null);
        return new Gson().fromJson(sessionDataJson, User.class);
    }

    @Override
    public User getSessionData() {
        String sessionDataJson = sharedPreferences.getString(SESSION_DATA_KEY,null);
        if(sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, User.class);
        }
        return null;
    }

    @Override
    public void setSessionData(User sessionData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SESSION_DATA_KEY, new Gson().toJson(sessionData));
        editor.apply();
    }

    @Override
    public void destroy() {
        sharedPreferences.edit().clear().apply();
    }
}
