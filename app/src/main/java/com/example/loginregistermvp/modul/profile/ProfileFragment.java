package com.example.loginregistermvp.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.loginregistermvp.R;
import com.example.loginregistermvp.data.pojo.User;
import com.example.loginregistermvp.data.source.local.SessionRepository;
import com.example.loginregistermvp.data.source.remote.UserRepository;
import com.example.loginregistermvp.modul.login.LoginActivity;
import com.irfankhoirul.mvp_core.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.Presenter> implements ProfileContract.View {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    public ProfileFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        mPresenter = new ProfilePresenter(this, new SessionRepository(activity),
                new UserRepository());
        mPresenter.start();
        return  fragmentView;
    }

    @OnClick(R.id.bt_logout)
    public void setBtnLogoutClick() {
        mPresenter.performLogout();
    }

    @Override
    protected void setTitle() {
        title = getString(R.string.ftitle_profile);
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
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
    public void showProfile(User user) {
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }
}
