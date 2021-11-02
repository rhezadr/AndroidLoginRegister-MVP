package com.example.loginregistermvp.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.loginregistermvp.R;
import com.example.loginregistermvp.data.source.local.SessionRepository;
import com.example.loginregistermvp.data.source.remote.UserRepository;
import com.example.loginregistermvp.modul.profile.ProfileActivity;
import com.example.loginregistermvp.modul.register.RegisterActivity;
import com.irfankhoirul.mvp_core.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter>
        implements  LoginContract.View {
    @BindView((R.id.et_email))
    EditText etEmail;
    @BindView((R.id.et_pasword))
    EditText etPassword;

    public LoginFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        mPresenter = new LoginPresenter(this, new UserRepository(), new SessionRepository(getActivity()));
        mPresenter.start();

        return fragmentView;
    }

    @OnClick(R.id.bt_login)
    public void setBtnLoginClick() {
        if(validateLoginForm()) {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            mPresenter.performLogin(email, password);
        }
    }

    @OnClick(R.id.bt_register)
    public void setRegisterClick() {
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    private boolean validateLoginForm() {
        AwesomeValidation formValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);
        formValidation.addValidation(activity, R.id.ti_email,
                Patterns.EMAIL_ADDRESS, R.string.email_validation);
        formValidation.addValidation(activity, R.id.ti_email,
                RegexTemplate.NOT_EMPTY, R.string.isEmail_empty);
        formValidation.addValidation(activity, R.id.ti_password,
                RegexTemplate.NOT_EMPTY, R.string.isPassword_empty);

        return formValidation.validate();
    }

    @Override
    protected void setTitle() {
        title = getString(R.string.ftitle_login);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingDialog(boolean isLoading, String message) {
        super.setLoadingDialog(isLoading(), message);
    }

    @Override
    public void showStatus(int type, String message) {
        super.showStatus(type,message);
    }

    @Override
    public void redirectToProfile() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
