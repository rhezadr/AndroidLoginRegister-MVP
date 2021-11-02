package com.example.loginregistermvp.data.source.remote;

import android.util.Log;

import com.example.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.base.BaseRemoteRepository;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class UserRepository extends BaseRemoteRepository<UserEndPoint> implements IUserRepository {
    private static final String BASE_URL = "http://192.168.31.47:8000/api/";

    @Override
    protected String setBaseUrl() {
        return BASE_URL;
    }

    @Override
    protected void setEndPoint() {
        endPoint = retrofit.create(UserEndPoint.class);
    }

    @Override
    protected boolean enableLogging() {
        return true;
    }

    @Override
    public void register(User user, RequestResponseListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("gender", user.getGender());
        Call<DataResult> call = endPoint.register(params);
        execute(call, listener);
        Log.d("asa",call.toString());
    }

    @Override
    public void login(String email, String password, RequestResponseListener<User> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        Call<DataResult<User>> call = endPoint.login(params);
        execute(call, listener);
    }

    @Override
    public void logout(String token, RequestResponseListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        Call<DataResult> call = endPoint.logout(params);
        execute(call, listener);
    }
}
