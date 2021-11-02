package com.example.loginregistermvp.modul.login;

import com.example.loginregistermvp.data.pojo.User;
import com.example.loginregistermvp.data.source.remote.IUserRepository;
import com.example.loginregistermvp.data.source.local.SessionRepository;
import com.irfankhoirul.mvp_core.custom_views.ConstantStatus;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View view;
    private final IUserRepository iUserRepository;
    private final SessionRepository sessionRepository;

    public LoginPresenter(LoginContract.View view, IUserRepository iUserRepository,
                          SessionRepository sessionRepository) {
        this.view = view;
        this.iUserRepository = iUserRepository;
        this.sessionRepository = sessionRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        if(isUserLoggedIn()) {
            view.redirectToProfile();
        }
    }

    @Override
    public void performLogin(String email, String password) {
        view.setLoadingDialog(true, "Logged In");
        iUserRepository.login(email, password, new RequestResponseListener<User>() {
            @Override
            public void onSuccess(DataResult<User> dataResult) {
                view.setLoadingDialog(false, null);
                if(dataResult.getCode() == ConstantStatus.STATUS_SUCCESS) {
                    sessionRepository.initializer(dataResult.getData());
                    view.showStatus(ConstantStatus.STATUS_SUCCESS, dataResult.getMessage());
                    view.redirectToProfile();
                } else {
                    view.showStatus(ConstantStatus.STATUS_ERROR, dataResult.getMessage());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.setLoadingDialog(false, null);
                view.showStatus(ConstantStatus.STATUS_ERROR, "Login Failed");
            }
        });
    }

    private boolean isUserLoggedIn() {
        if(sessionRepository.getSessionData() != null) {
            return true;
        }
        return false;
    }
}
