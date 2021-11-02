package com.example.loginregistermvp.data.source.remote;

import com.example.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

public interface IUserRepository {
    void register(User user, RequestResponseListener listener);

    void login(String email, String password,
               RequestResponseListener<User> listener);

    void logout(String token, RequestResponseListener listener);
}
