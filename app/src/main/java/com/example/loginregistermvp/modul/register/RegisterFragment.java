package com.example.loginregistermvp.modul.register;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.loginregistermvp.R;
import com.example.loginregistermvp.data.pojo.User;
import com.example.loginregistermvp.data.source.remote.UserRepository;
import com.irfankhoirul.mvp_core.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter>
        implements RegisterContract.View{

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pasword)
    EditText etPassword;
    @BindView(R.id.rb_male)
    RadioButton rbMale;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;

    public RegisterFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_register,container,
                false);
        unbinder = ButterKnife.bind(this, fragmentView);
        mPresenter = new RegisterPresenter(this, new UserRepository());
        mPresenter.start();
        return  fragmentView;
    }

    @OnClick(R.id.bt_register)
    public void setBtnRegisterClick() {
        if(validateRegistrationForm()) {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String gender;
            if(rbMale.isChecked()) {
                gender = User.GENDER_MALE;
            } else {
                gender = User.GENDER_FEMALE;
            }
            mPresenter.performRegister(name,email,gender,password);
        }
    }

    private boolean validateRegistrationForm() {
        AwesomeValidation formValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);
        formValidation.addValidation(activity, R.id.ti_email,
                Patterns.EMAIL_ADDRESS, R.string.email_validation);
        formValidation.addValidation(activity, R.id.ti_email,
                RegexTemplate.NOT_EMPTY, R.string.isEmail_empty);
        formValidation.addValidation(activity, R.id.ti_name,
                RegexTemplate.NOT_EMPTY, R.string.isName_empty);
        formValidation.addValidation(activity, R.id.ti_password,
                RegexTemplate.NOT_EMPTY, R.string.isPassword_empty);

        return formValidation.validate();
    }

    @Override
    protected void setTitle() {
        title = getString(R.string.ftitle_register);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
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
    public void redirectToLogin() {
        activity.finish();
    }
}
