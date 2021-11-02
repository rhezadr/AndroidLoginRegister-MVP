package com.example.loginregistermvp.modul.profile;

import com.example.loginregistermvp.data.pojo.User;
import com.example.loginregistermvp.data.source.remote.IUserRepository;
import com.example.loginregistermvp.data.source.local.SessionRepository;
import com.irfankhoirul.mvp_core.custom_views.ConstantStatus;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

public class ProfilePresenter implements ProfileContract.Presenter {
    private final ProfileContract.View view;
    private final SessionRepository sessionRepository;
    private final IUserRepository iUserRepository;
    private User user;

    public ProfilePresenter(ProfileContract.View view,
                            SessionRepository sessionRepository, IUserRepository iUserRepository) {
        this.view = view;
        this.iUserRepository = iUserRepository;
        this.sessionRepository = sessionRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        user = getUserData();
        view.showProfile(user);
    }

    @Override
    public void performLogout() {
        view.setLoadingDialog(true, "Log Out");
        iUserRepository.logout(user.getAuthToken(), new RequestResponseListener() {
            @Override
            public void onSuccess(DataResult dataResult) {
                view.setLoadingDialog(false, null);
                if(dataResult.getCode() == ConstantStatus.STATUS_SUCCESS) {
                    sessionRepository.destroy();
                    view.showStatus(ConstantStatus.STATUS_SUCCESS, dataResult.getMessage());
                    view.redirectToLogin();
                } else {
                    view.showStatus(ConstantStatus.STATUS_ERROR, dataResult.getMessage());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.setLoadingDialog(false, null);
                view.showStatus(ConstantStatus.STATUS_ERROR,"Logout Failed");
            }
        });
    }

    private User getUserData() {
        return sessionRepository.getSessionData();
    }
}
