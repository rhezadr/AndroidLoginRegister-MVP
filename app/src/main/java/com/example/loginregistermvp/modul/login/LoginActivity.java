package com.example.loginregistermvp.modul.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.irfankhoirul.mvp_core.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        btBack.setVisibility(View.GONE);
        LoginFragment loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }
}