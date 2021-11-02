package com.example.loginregistermvp.modul.register;

import com.irfankhoirul.mvp_core.base.BasePresenter;
import com.irfankhoirul.mvp_core.base.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        void performRegister(String name, String email, String gender, String password);
    }
}
