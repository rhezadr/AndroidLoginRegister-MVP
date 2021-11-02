package com.example.loginregistermvp.data.source.local;

import com.example.loginregistermvp.data.pojo.User;

public interface ISessionRepository {
    User initializer(User sessionData);

    User getSessionData();

    void setSessionData(User sessionData);

    void destroy();
}
