package com.example.loginregistermvp.data.source.remote;

import com.example.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.data.DataResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserEndPoint {
    String REGISTER_ENDPOINT = "register";
    String LOGIN_ENDPOINT = "login";
    String LOGOUT_ENDPOINT = "logout";

    @FormUrlEncoded
    @POST(REGISTER_ENDPOINT)
    Call<DataResult> register(@FieldMap(encoded = true) Map<String, String> param);

    @FormUrlEncoded
    @POST(LOGIN_ENDPOINT)
    Call<DataResult<User>> login (@FieldMap(encoded = true) Map<String, String> param);

    @FormUrlEncoded
    @POST(LOGOUT_ENDPOINT)
    Call<DataResult> logout(@FieldMap Map<String, String> param);
}
