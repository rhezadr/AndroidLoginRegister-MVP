package com.example.loginregistermvp.modul.register;

import com.example.loginregistermvp.data.pojo.User;
import com.example.loginregistermvp.data.source.remote.IUserRepository;
import com.irfankhoirul.mvp_core.custom_views.ConstantStatus;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

public class RegisterPresenter implements RegisterContract.Presenter {
    private final RegisterContract.View view;
    private final IUserRepository iUserRepository;

    public RegisterPresenter(RegisterContract.View view, IUserRepository iUserRepository) {
        this.view = view;
        this.iUserRepository = iUserRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void performRegister(String name, String email,
                                String gender, String password) {
        view.setLoadingDialog(true, "Registering");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        user.setPassword(password);

        iUserRepository.register(user, new RequestResponseListener() {
            @Override
            public void onSuccess(DataResult dataResult) {
                view.setLoadingDialog(false, null);
                if(dataResult.getCode() == ConstantStatus.STATUS_SUCCESS) {
                    view.showStatus(ConstantStatus.STATUS_SUCCESS, dataResult.getMessage());
                    view.redirectToLogin();
                } else {
                    view.showStatus(ConstantStatus.STATUS_ERROR, dataResult.getMessage());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.setLoadingDialog(false, null);
                view.showStatus(ConstantStatus.STATUS_ERROR, "Register Failed");
            }
        });
    }
}
