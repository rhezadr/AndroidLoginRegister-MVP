package com.example.loginregistermvp.modul.profile;

import com.example.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.base.BasePresenter;
import com.irfankhoirul.mvp_core.base.BaseView;

public class ProfileContract {
    interface View extends BaseView<Presenter> {
        void showProfile(User user);

        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        void performLogout();
    }
}
