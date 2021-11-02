package com.example.loginregistermvp.modul.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loginregistermvp.modul.register.RegisterFragment;
import com.irfankhoirul.mvp_core.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, false);
    }
}